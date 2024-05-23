package com.capgemini.wsb.rest;

import com.capgemini.wsb.service.AddressService;
import com.capgemini.wsb.persistence.to.AddressTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/{id}")
    public ResponseEntity<AddressTO> getAddressById(@PathVariable Long id) {
        AddressTO address = addressService.findById(id);
        if (address != null) {
            return ResponseEntity.ok(address);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AddressTO> createAddress(@RequestBody AddressTO address) {
        AddressTO createdAddress = addressService.save(address);
        return ResponseEntity.ok(createdAddress);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }
}