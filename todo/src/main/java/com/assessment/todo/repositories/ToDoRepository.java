package com.assessment.todo.repositories;

import com.assessment.todo.entities.ToDo;
import org.springframework.data.repository.CrudRepository;

public interface ToDoRepository extends CrudRepository <ToDo, Integer>{
}

// create an interface so no implementation needed.
// crud
// create
// read
// update
// delete
