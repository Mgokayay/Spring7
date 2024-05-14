package com.example.spring7.service;


import com.example.spring7.entity.Customer;

import java.util.List;

public interface CustomerService {

    Customer save(Customer customer);

    List<Customer> findAll();

    Customer findById(Long id);

    Customer delete(Long id);
}
