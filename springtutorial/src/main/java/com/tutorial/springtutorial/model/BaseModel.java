package com.tutorial.springtutorial.model;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
@MappedSuperclass
@Getter
@Setter
@ToString
public class BaseModel implements Serializable {

    private Date createdDate;
    private String createdBy;
    private Date updatedDate;
    private String updatedBy;

}
