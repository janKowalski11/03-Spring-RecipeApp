package com.example.recipeproject.model;
/*
Author: BeGieU
Date: 06.11.2018
*/

import javax.persistence.Entity;

@Entity
public class UnitOfMeasure extends BaseEntity
{

    private String description;


    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
