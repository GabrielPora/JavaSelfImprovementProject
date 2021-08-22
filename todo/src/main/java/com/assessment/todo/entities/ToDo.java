package com.assessment.todo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // jpa will handle this and do the auto increment on it
    private Integer id; // Primitive vs Object
    private String title;
    private Boolean state;
    private Date timeCreated;
    private Date lastUpdated;

    // do some more reading on getters and setters
    // way of controlling state
    // to prevent state mutation and controlling it state

    protected ToDo () {

    }

    public Integer getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Boolean getState() {
        return state;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }
}


