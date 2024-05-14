package com.example.spring7.service;

import com.example.spring7.entity.Account;
import com.example.spring7.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account delete(Long id) {
        Account account=find(id);
        accountRepository.delete(account);
        return account;

    }

    @Override
    public Account find(Long id) {
        Optional<Account> accountOptional=accountRepository.findById(id);

        return accountOptional.orElseThrow(() -> new RuntimeException("account not found"));
    }
}
