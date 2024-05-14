package com.example.spring7.service;


import com.example.spring7.entity.Address;

import java.util.List;

public interface AddressService {

  Address save(Address address);

  List<Address> findAll();

  Address delete(Long id);

  Address findById(Long id);
}
