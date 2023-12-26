package com.codegym.customermanagement.service;

import com.codegym.customermanagement.model.Customer;

import java.util.List;

public interface ISimpleCustomerService {
    List<Customer> getCustomers();
    Customer getCustomerById(int id);
}
