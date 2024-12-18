package com.projet.GestionOrder.Services;

import com.google.gson.Gson;

import com.projet.GestionOrder.Models.Order;
import com.projet.GestionOrder.Utils.GestionDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOIm implements OrderDAO {
    GestionDB Pilot = new GestionDB();

    @Override
    public  void processOrder(String orderJson, List<Order> validOrders, List<Order> invalidOrders, Gson gson) {
        try {
            Order order = gson.fromJson(orderJson, Order.class);
            if (isValidOrder(order)) {
                validOrders.add(order);
                //System.out.println("Order valid: " + order);
                AddOrder(order);

            } else {
                invalidOrders.add(order);
                //System.out.println("Order invalid: " + orderJson);

            }
        } catch (Exception e) {
            //System.out.println("Error parsing order: " + orderJson);
            invalidOrders.add(null); // Ou une autre logique pour les erreurs de syntaxe
        }
    }
    @Override
    public  boolean isValidOrder(Order order) {
        //System.out.println(order);
        return order!=null;
    }

    @Override
    public void AddOrder(Order order) throws Exception {
        CustomerServiceImp cs = new CustomerServiceImp();
        //System.out.println(order.getCustomerID());
        boolean customerExists = cs.findCustomer(order.getCustomerID());
        System.out.println("Customer exists: " + customerExists);


        if (customerExists) {
            String sql = "INSERT INTO commande (idOrder, orderDate, orderAmount, idCustomer, status) VALUES (?, ?, ?, ?, ?)";

            try {
                Pilot.connecte("ordercustomer", "root", "");
                PreparedStatement stmt = Pilot.connexion.prepareStatement(sql);

                stmt.setString(1, order.getOrderId());
                stmt.setTimestamp(2, order.getOrderDate()); // Assurez-vous que getOrderDate() retourne une date
                stmt.setDouble(3, order.getOrderAmount());
                stmt.setString(4, order.getCustomerID()); // Si c'est un objet, appelez getCustomerId()
                stmt.setString(5, order.getStatus());

                stmt.executeUpdate();
                System.out.println("Commande ajoutée avec succès.");
            } catch (SQLException e) {
                System.err.println("Erreur lors de l'ajout de la commande : " + e.getMessage());
            } finally {
                Pilot.close();
            }
        } else {
            System.out.println("Client non trouvé : " + order.getCustomerID());
        }
    }

    @Override
    public List<Order> getAllOrder() throws SQLException {
        String sql = "SELECT * FROM commande";
        List<Order> orders = new ArrayList<>();

        try {
            Pilot.connecte("ordercustomer", "root", "");
            PreparedStatement stmt = Pilot.connexion.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Order order = new Order(
                        rs.getString("orderID"),
                        rs.getTimestamp("orderDate"),
                        rs.getDouble("orderAmount"),
                        rs.getString("customerID"),
                        rs.getString("status")

                );
                orders.add(order);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            Pilot.close();
        }

        return orders;

    }
}

