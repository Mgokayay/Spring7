package com.example.spring7.service;


import com.example.spring7.entity.Account;
import com.example.spring7.entity.Customer;
import com.example.spring7.repository.AccountRepository;
import com.example.spring7.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        return customerOptional.orElseThrow(() -> new RuntimeException("customer not found!"));
    }

    @Override
    public Customer delete(Long id) {
        Customer customer=findById(id);
        customerRepository.delete(customer);
        return customer;
    }
}
