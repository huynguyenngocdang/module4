
package com.codegym.customermanagement.service.impl;

import com.codegym.customermanagement.model.Customer;
import com.codegym.customermanagement.service.ISimpleCustomerService;

import java.util.ArrayList;
import java.util.List;

public class SimpleCustomerServiceImpl implements ISimpleCustomerService {
    private List<Customer> customers;

    public SimpleCustomerServiceImpl() {
        this.customers = new ArrayList<>();
    }
    @Override
    public List<Customer> getCustomers(){
        this.customers = new ArrayList<>();
        customers.add(new Customer(1, "Nguyen Khac Nhat", "nhat@codegym.vn", "Hanoi"));
        customers.add(new Customer(2, "Dang Huy Hoa", "hoa.dang@codegym.vn", "Danang"));
        customers.add(new Customer(3, "Le Thi Chau", "chau.le@codegym.vn", "Hanoi"));
        customers.add(new Customer(4, "Nguyen Thuy Duong", "duong.nguyen@codegym.vn", "Saigon"));
        customers.add(new Customer(5, "Codegym", "codegym@codegym.vn", "Vietnam"));
        return customers;
    }
    @Override
    public Customer getCustomerById(int id) {

        for (Customer cust: customers
             ) {
            if(cust.getId() == id) {
                return cust;
            }
        }
        return null;
    }
}

