package com.example.spring7.service;

import com.example.spring7.entity.Account;

import java.util.List;

public interface AccountService {

    Account save(Account account);

    List<Account> findAll();

    Account delete(Long id);

    Account find(Long id);
}
