package com.assessment.todo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Date;

@Data
public class AddressJSonDTO {
    @JsonProperty("id")
    private Integer id; // Primitive vs Object

    @JsonProperty("state")
    private Boolean state;

    @JsonProperty("Name")
    private String Name;

    @JsonProperty("SurName")
    private String SurName;

    @JsonProperty("Street")
    private String Street;

    @JsonProperty("City")
    private String City;

    @JsonProperty("CityCode")
    private String CityCode;

    @JsonProperty("PostCode")
    private Integer PostCode;

    @JsonProperty("timeCreated")
    private Date timeCreated;

    @JsonProperty("lastUpdated")
    private Date lastUpdated;
}
