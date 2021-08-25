package com.assessment.dbapi.repositories;

import com.assessment.dbapi.entities.ToDo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends CrudRepository <ToDo, Integer>{
}

// create an interface so no implementation needed.
// crud
// create
// read
// update
// delete
