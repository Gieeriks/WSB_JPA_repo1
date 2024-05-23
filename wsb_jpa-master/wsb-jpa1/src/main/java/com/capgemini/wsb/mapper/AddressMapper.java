package com.capgemini.wsb.mapper;

import com.capgemini.wsb.persistence.entity.AddressEntity;
import com.capgemini.wsb.persistence.to.AddressTO;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public AddressTO toTO(AddressEntity entity) {
        if (entity == null) {
            return null;
        }
        AddressTO to = new AddressTO();
        to.setId(entity.getId());
        to.setCity(entity.getCity());
        to.setAddressLine1(entity.getAddressLine1());
        to.setAddressLine2(entity.getAddressLine2());
        to.setPostalCode(entity.getPostalCode());
        return to;
    }

    public AddressEntity toEntity(AddressTO to) {
        if (to == null) {
            return null;
        }
        AddressEntity entity = new AddressEntity();
        entity.setId(to.getId());
        entity.setCity(to.getCity());
        entity.setAddressLine1(to.getAddressLine1());
        entity.setAddressLine2(to.getAddressLine2());
        entity.setPostalCode(to.getPostalCode());
        return entity;
    }
}
