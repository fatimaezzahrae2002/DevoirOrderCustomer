package com.projet.GestionOrder.Services;

import com.google.gson.Gson;
import com.projet.GestionOrder.Models.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO {
    public  void processOrder(String orderJson, List<Order> validOrders, List<Order> invalidOrders, Gson gson) ;
    public  boolean isValidOrder(Order order);
    public void AddOrder(Order order) throws Exception;

   public  List<Order> getAllOrder() throws SQLException;
}

