package com.codegym.customermanagement.controller;

import com.codegym.customermanagement.model.Customer;
import com.codegym.customermanagement.service.ISimpleCustomerService;
import com.codegym.customermanagement.service.impl.SimpleCustomerServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CustomerController {
    private ISimpleCustomerService customerService = new SimpleCustomerServiceImpl();
    @GetMapping("/")
    public String showList(HttpServletRequest request) {
        List<Customer> customers = customerService.getCustomers();
        request.setAttribute("customers", customers);
        return "customers/list.jsp";
    }
    @GetMapping("/info")
    public String showDetail(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = customerService.getCustomerById(id);
        request.setAttribute("customer", customer);
         return "customers/info.jsp";
    }
}