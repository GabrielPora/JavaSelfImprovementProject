package com.assessment.todo.controllers;

import com.assessment.todo.entities.ToDo;
import com.assessment.todo.services.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping ("/api/todo")
public class ToDoController {
    private ToDoService toDoService;

    @Autowired // understand what constructors are and why we have this layout  look at constructor overloading
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping ("")
    public Iterable<ToDo> getAllToDo () {
        return toDoService.getAllToDos();
    }

    // read on Restful design pattern and resource mapping

    @GetMapping ("/{id}")
    public ResponseEntity getToDoById (@PathVariable Integer id) {
        Optional<ToDo> toDo = toDoService.getById(id);
        if (toDo == null) {
            return new ResponseEntity("No ID in database for "+ id, HttpStatus.NOT_FOUND );
        }
        return new ResponseEntity(toDo, HttpStatus.OK);
    }

    @PostMapping ("")
    public ResponseEntity createToDo(@RequestBody ToDo toDo) { // read up on @Requestbody. This is best practice
        toDoService.createToDo(toDo);
        return new ResponseEntity(toDo, HttpStatus.OK);
    }

//    New learning that I could have made delte return the ID and
//    I would not have to use the getById method to test if that id exsits
    @DeleteMapping ("/{id}")
    public ResponseEntity deleteToDo (@PathVariable Integer id) {
        if (null == toDoService.getById(id)) {
            return new ResponseEntity("No ToDo found for ID " + id, HttpStatus.NOT_FOUND);
        }
        toDoService.deleteToDo(id);
        return new ResponseEntity(id, HttpStatus.OK);
    }

    @PutMapping ("/{id}")
    public ResponseEntity updateToDo (@PathVariable Integer id, @RequestBody ToDo toDo) {
        if (toDo == null) {
            return new ResponseEntity("No customer found for ID " + id, HttpStatus.NOT_FOUND);
        }
        toDoService.updateToDo(id,toDo);
        return new ResponseEntity(toDo, HttpStatus.OK);
    }

    @PutMapping ("")
    public void markAllToDo() {
        toDoService.markAllToDos();
    }

    @DeleteMapping ("")
    public void deleteAllToDo () {
        toDoService.deleteAllToDos();
    }
}
