package com.assessment.todo.services;

import com.assessment.todo.entities.Address;
import com.assessment.todo.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AddressService {
    private AddressRepository addressRepository;

    @Autowired // depend injection, once and use it from here downward.  look into SOLID. makes depend explicit. Read the trade offs of depend injection
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Iterable<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Optional<Address> getById(Integer id) { // avoids null return
        return addressRepository.findById(id);
    }

    public Address createAddress(Address newAddress) { // you will have the id at this point in time.
        return addressRepository.save(newAddress);
    }

    public void deleteAddress(Integer id) {
        addressRepository.deleteById(id);
    }

    public void deleteAllAddresses() {
        addressRepository.deleteAll();
    }

    public void markAllAddresses(){
        for (Address address : addressRepository.findAll())  {
            address.setState(true);
            updateAddress(address.getId(), address);
        }
    }
    
    public void updateAddress(Integer id, Address editedAddress) {
        Optional<Address> addressOptional = getById(id);
        if (addressOptional.isPresent()) {
            Address address = addressOptional.get(); // creating a temp
            address.setName(editedAddress.getName());
            address.setSurName(editedAddress.getSurName());
            address.setStreet(editedAddress.getStreet());
            address.setCity(editedAddress.getCity());
            address.setCityCode(editedAddress.getCityCode());
            address.setPostCode(editedAddress.getPostCode());
            address.setLastUpdated(new Date());
            address.setState(editedAddress.getState());
            addressRepository.save(address);// this is to save the changes we just added.
        }
    }
}
