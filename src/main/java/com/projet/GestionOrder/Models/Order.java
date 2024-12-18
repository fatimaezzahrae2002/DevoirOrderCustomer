package com.projet.GestionOrder.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class Order {
    public String orderId;
    public Timestamp orderDate;
    public Double orderAmount;
    public String customerID;
    public String status;
}
