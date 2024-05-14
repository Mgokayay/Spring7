package com.example.spring7.controller;


import com.example.spring7.dto.AccountResponse;
import com.example.spring7.dto.CustomerResponse;
import com.example.spring7.entity.Account;
import com.example.spring7.entity.Customer;
import com.example.spring7.service.AccountService;
import com.example.spring7.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    private final CustomerService customerService;

    @Autowired
    public AccountController(AccountService accountService, CustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
    }

    @GetMapping
    public List<Account> findAll(){
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public Account findById(@PathVariable Long id){
        return accountService.find(id);
    }

    @PostMapping("/{customerId}")
    public AccountResponse save(@RequestBody Account account, @PathVariable Long customerId){
        Customer customer = customerService.findById(customerId);
        customer.getAccounts().add(account);
        account.setCustomer(customer);
        Account savedAccount = accountService.save(account);
        return new AccountResponse(savedAccount.getId(),savedAccount.getAccountName(),savedAccount.getMoneyAmount(),
                new CustomerResponse(customer.getId(),customer.getEmail(),customer.getSalary()));
    }

    @DeleteMapping("/{id}")
    public AccountResponse delete(@PathVariable Long id){
        Account account=accountService.find(id);
        accountService.delete(id);
        return new AccountResponse(account.getId(),account.getAccountName(),account.getMoneyAmount(),
                new CustomerResponse(account.getCustomer().getId(),account.getCustomer().getEmail(),account.getCustomer().getSalary()));
    }

    @PutMapping("/{customerId}")
    public AccountResponse update(@RequestBody Account account,@PathVariable Long customerId){
        Customer customer = customerService.findById(customerId);
        Account foundAccount = null;
        for(Account a:customer.getAccounts()){
            if(account.getId() == a.getId()){
                foundAccount=a;
            }
        }
        if(foundAccount == null){
            throw new RuntimeException("account is not found");
        }

        int indexOfFound = customer.getAccounts().indexOf(foundAccount);
        customer.getAccounts().set(indexOfFound,account);
        account.setCustomer(customer);
        accountService.save(account);

        return new AccountResponse(account.getId(),account.getAccountName(),account.getMoneyAmount(),
                new CustomerResponse(customer.getId(),customer.getEmail(),customer.getSalary()));
    }
}
