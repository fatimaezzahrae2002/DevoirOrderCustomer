package com.projet.GestionOrder.Controllers;


import com.mysql.cj.x.protobuf.MysqlxCrud;
import com.projet.GestionOrder.Models.Customer;
import com.projet.GestionOrder.Models.Order;
import com.projet.GestionOrder.Services.CustomerServiceImp;
import com.projet.GestionOrder.Services.OrderDAOIm;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderController {


    @FXML
    private VBox ordreListContainer;// La VBox qui contient les utilisateurs dynamiques


    private final OrderDAOIm order = new OrderDAOIm();



    private void loadAndDisplayReservation() throws SQLException, IOException {
        // Récupérer la liste des utilisateurs depuis le service
        List<Order> orders= order.getAllOrder();


        for (Order order: orders) {

            HBox ordreRow = createOrdreRow(order);

            // Ajouter cette ligne à la VBox
            ordreListContainer.getChildren().add(ordreRow);
        }
    }
    @FXML
    public void initialize() throws SQLException, IOException {
        // Charger et afficher les utilisateurs
        loadAndDisplayReservation();
    }
    private HBox createOrdreRow(Order order) throws SQLException, IOException {
        HBox reservationRow = new HBox();

        reservationRow.setStyle("-fx-background-color: #f4f4f4; -fx-padding: 10; -fx-spacing: 20;");

        Label idOrdreLabel = new Label(String.valueOf(order.getOrderId()));
        Label dateOrdreLabel = new Label(String.valueOf(order.getOrderDate()));
        Label amountOrdreLabel = new Label(order.getOrderAmount().toString());
        Label idCustomerLabel = new Label(order.getCustomerID());
        Label statusOrdreLabel = new Label(order.getStatus());
        idOrdreLabel.setPrefWidth(130);
        dateOrdreLabel.setPrefWidth(130);
        amountOrdreLabel.setPrefWidth(130);
        idCustomerLabel.setPrefWidth(130);
        statusOrdreLabel.setPrefWidth(130);

        // Ajouter tous les labels à la HBox
        reservationRow.getChildren().addAll(idOrdreLabel,dateOrdreLabel,amountOrdreLabel,idCustomerLabel,statusOrdreLabel);

        return reservationRow;

    }}


