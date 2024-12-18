package com.projet.GestionOrder;

import com.google.gson.Gson;
import com.projet.GestionOrder.Models.Order;
import com.projet.GestionOrder.Services.OrderDAOIm;
import com.projet.GestionOrder.Utils.LecteurConvertisseurJSON;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static javafx.application.Application.launch;

public class HelloApplication extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/projet/GestionOrder/views/tableOrder.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hotel Management");
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }
}

   






