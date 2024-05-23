package com.capgemini.wsb.service;

import com.capgemini.wsb.persistence.to.AddressTO;

import java.util.List;

public interface AddressService {
    AddressTO findById(Long id);
    AddressTO save(AddressTO address);
    void delete(Long id);
    List<AddressTO> findAll();
}
