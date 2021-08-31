package com.assessment.todo.repositories;

import com.assessment.todo.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository <Address, Integer> {
}
