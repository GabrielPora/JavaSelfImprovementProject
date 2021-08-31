package com.assessment.todo.controllers;


import com.assessment.todo.entities.Address;
import com.assessment.todo.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/address")
public class AddressController {
    private AddressService addressService;


    @Autowired // understand what constructors are and why we have this layout  look at constructor overloading
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("")
    public Iterable<Address> getAll  () {
        return addressService.getAllAddresses();
    }

    // read on Restful design pattern and resource mapping

    @GetMapping ("/{id}")
    public ResponseEntity getAddressById (@PathVariable Integer id) {
        Optional<Address> address = addressService.getById(id);
        if (address == null) {
            return new ResponseEntity("No ID in database for "+ id, HttpStatus.NOT_FOUND );
        }
        return new ResponseEntity(address, HttpStatus.OK);
    }

    @PostMapping ("")
    public ResponseEntity createAddress(@RequestBody Address address) throws Exception { // read up on @Requestbody. This is best practice
        addressService.createAddress(address);
        return new ResponseEntity(address, HttpStatus.OK);
    }

    //    New learning that I could have made delete return the ID and
    //    I would not have to use the getById method to test if that id exsits
    @DeleteMapping ("/{id}")
    public ResponseEntity deleteAddress (@PathVariable Integer id) {
        if (null == addressService.getById(id)) {
            return new ResponseEntity("No Address found for ID " + id, HttpStatus.NOT_FOUND);
        }
        addressService.deleteAddress(id);
        return new ResponseEntity(id, HttpStatus.OK);
    }

    @PutMapping ("/{id}")
    public ResponseEntity updateAddress (@PathVariable Integer id, @RequestBody Address address) {
        if (address == null) {
            return new ResponseEntity("No customer found for ID " + id, HttpStatus.NOT_FOUND);
        }
        addressService.updateAddress(id,address);
        return new ResponseEntity(address, HttpStatus.OK);
    }

    @PutMapping ("")
    public void markAllAddress() {
        addressService.markAllAddresses();
    }

    @DeleteMapping ("")
    public void deleteAllAddress () {
        addressService.deleteAllAddresses();
    }
}
