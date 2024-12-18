package com.projet.GestionOrder.Services;

import com.projet.GestionOrder.Models.Customer;
import com.projet.GestionOrder.Models.Order;
import com.projet.GestionOrder.Utils.GestionDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerServiceImp implements CustomerDAO {
    GestionDB Pilot = new GestionDB();
    @Override
    public boolean findCustomer(String idCustomer) throws Exception {
        boolean flag = false;
        String sql = "select Customerid from customer where Customerid = ?";
        try  {
            Pilot.connecte("ordercustomer", "root", "");
            PreparedStatement stmt = Pilot.connexion.prepareStatement(sql);
            stmt.setString(1, idCustomer);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                flag = true;
            }


            Pilot.close();
        } catch (SQLException e) {
            throw e;
        }
        return flag;
    }

    @Override
    public Customer getCustomer(String idCustomer) throws Exception {
        String sql = "select Customerid from customer where Customerid = ?";
        Customer customer = null;
        try {
            Pilot.connecte("ordercustomer", "root", "");
            PreparedStatement stmt = Pilot.connexion.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Customer C = new Customer(
                        rs.getString("Customerid"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("phone")


                );
                customer = C;
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            Pilot.close();
        }

        return customer;

    }
    }


