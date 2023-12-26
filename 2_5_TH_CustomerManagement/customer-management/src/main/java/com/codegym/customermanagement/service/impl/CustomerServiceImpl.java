package com.codegym.customermanagement.service.impl;

import com.codegym.customermanagement.model.Customer;
import com.codegym.customermanagement.service.ICustomerService;

import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements ICustomerService {
    private final List<Customer> customers;

    public CustomerServiceImpl() {
        customers = new ArrayList<>();
        customers.add(new Customer(1L, "Nguyen Khac Nhat", "nhat@codegym.vn", "Hà Nội"));
        customers.add(new Customer(2L, "Dang Huy Hoa", "hoa.dang@codegym.vn", "Đà Nẵng"));
        customers.add(new Customer(3L, "Nguyen Thuy Duong", "duong.nguyen@codegym.vn", "Sài Gòn"));
    }
    @Override
    public List<Customer> findAll() {
        return customers;
    }

    @Override
    public Customer findById(long id) {
        for (Customer customer: customers
             ) {
            if(customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public void save(Customer customer) {
        Customer c = findById(customer.getId());
        c.setName(customer.getName());
        c.setAddress(customer.getAddress());
        c.setEmail(customer.getEmail());
    }
}
