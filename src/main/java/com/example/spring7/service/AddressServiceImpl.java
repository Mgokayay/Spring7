package com.example.spring7.service;


import com.example.spring7.entity.Address;
import com.example.spring7.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AddressServiceImpl implements  AddressService{

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address delete(Long id) {
        Address address=findById(id);
        addressRepository.delete(address);
        return address;
    }

    @Override
    public Address findById(Long id) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        return addressOptional.orElseThrow(() -> new RuntimeException("address not found!"));
    }
}
