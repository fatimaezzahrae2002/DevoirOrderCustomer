package com.projet.GestionOrder.Services;

import com.projet.GestionOrder.Models.Customer;

public interface CustomerDAO {
    public boolean findCustomer(String idCustomer) throws Exception;
    public Customer getCustomer(String idCustomer) throws Exception;
}
