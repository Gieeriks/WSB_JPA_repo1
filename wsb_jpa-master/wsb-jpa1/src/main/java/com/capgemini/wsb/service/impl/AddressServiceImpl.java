package com.capgemini.wsb.service.impl;

import com.capgemini.wsb.persistence.dao.AddressDao;
import com.capgemini.wsb.persistence.entity.AddressEntity;
import com.capgemini.wsb.service.AddressService;
import com.capgemini.wsb.persistence.to.AddressTO;
import com.capgemini.wsb.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public AddressTO findById(Long id) {
        AddressEntity entity = addressDao.findById(id).orElse(null);
        return addressMapper.toTO(entity);
    }

    @Override
    public AddressTO save(AddressTO address) {
        AddressEntity entity = addressMapper.toEntity(address);
        AddressEntity savedEntity = addressDao.save(entity);
        return addressMapper.toTO(savedEntity);
    }

    @Override
    public void delete(Long id) {
        addressDao.deleteById(id);
    }

    @Override
    public List<AddressTO> findAll() {
        return addressDao.findAll().stream()
                .map(addressMapper::toTO)
                .collect(Collectors.toList());
    }
}
