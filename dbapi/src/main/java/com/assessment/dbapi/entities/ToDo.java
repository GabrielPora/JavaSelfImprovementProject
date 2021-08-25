package com.assessment.dbapi.entities;

import javax.persistence.*;
import java.util.Date;


@Entity
//@Table(name = "to_do_list")
@Table(name = "ToDoList")
//@Data
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // jpa will handle this and do the auto increment on it
    private Integer id; // Primitive vs Object

    @Column(name = "title")
    private String title;

    @Column(name = "state")
    private Boolean state;

    @Column(name = "timeCreated")
    private Date timeCreated;

    @Column(name = "lastUpdated")
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




