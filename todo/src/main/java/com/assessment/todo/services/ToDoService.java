package com.assessment.todo.services;

import com.assessment.todo.entities.ToDo;
import com.assessment.todo.repositories.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ToDoService {
    private ToDoRepository toDoRepository;

    @Autowired // depend injection, once and use it from here downward.  look into SOLID. makes depend explicit. Read the trade offs of depend injection
    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public Iterable<ToDo> getAllToDos() {
        return toDoRepository.findAll();
    }

    public Optional<ToDo> getById(Integer id) { // avoids null return
        return toDoRepository.findById(id);
    }

    public ToDo createToDo(ToDo newToDo) { // you will have the id at this point in time.
        return toDoRepository.save(newToDo);
    }

    public void deleteToDo(Integer id) {
        toDoRepository.deleteById(id);
    }

    public void deleteAllToDos() {
        toDoRepository.deleteAll();
    }

    public void markAllToDos(){
        for (ToDo todo : toDoRepository.findAll())  {
            todo.setState(true);
            updateToDo(todo.getId(), todo);
        }
    }
//    refactor from editToDo to updateToDo
//    done a refactor here as update is a better variable name than edit
    public void updateToDo(Integer id, ToDo editedToDo) {
        Optional<ToDo> toDoOptional = getById(id);
        if (toDoOptional.isPresent()) {
            ToDo toDo = toDoOptional.get(); // creating a temp
            toDo.setTitle(editedToDo.getTitle());
            toDo.setLastUpdated(new Date());
            toDo.setState(editedToDo.getState());
            toDoRepository.save(toDo);// this is to save the changes we just added.
        }
    }
}
