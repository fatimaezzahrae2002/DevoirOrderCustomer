package com.projet.GestionOrder.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.projet.GestionOrder.Models.Order;
import com.projet.GestionOrder.Services.OrderDAOIm;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LecteurConvertisseurJSON {
    public static void main(String[] args) {
        OrderDAOIm dao = new OrderDAOIm();
        String inputFilePath = "C:\\Users\\Microsoft\\eclipse-workspace\\demo2\\src\\main\\java\\com\\projet\\GestionOrder\\Utils\\orders.json";
        String outputFilePath = "C:\\Users\\Microsoft\\eclipse-workspace\\demo2\\src\\main\\java\\com\\projet\\GestionOrder\\Utils\\output.json";
        String errorFilePath = "C:\\Users\\Microsoft\\eclipse-workspace\\demo2\\src\\main\\java\\com\\projet\\GestionOrder\\Utils\\error.json";

        List<Order> validOrders = new ArrayList<>();
        List<Order> invalidOrders = new ArrayList<>();

        Gson gson = new Gson();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            Order[] orders = gson.fromJson(reader, Order[].class);

            for (Order order : orders) {
                try {
                    dao.processOrder(gson.toJson(order), validOrders, invalidOrders, gson);
                } catch (Exception e) {
                    invalidOrders.add(order);
                    System.err.println("Erreur de traitement de la commande : " + e.getMessage());
                }
            }

            writeToFile(outputFilePath, gson.toJson(validOrders));
            writeToFile(errorFilePath, gson.toJson(invalidOrders));
        } catch (IOException | JsonSyntaxException e) {
            e.printStackTrace();
        }
    }

    private static void writeToFile(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }
}

