package com.projet.GestionOrder.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    public String customerID;
    public String nom;
    public String prenom;
    public String email;
    public String phone;
}
