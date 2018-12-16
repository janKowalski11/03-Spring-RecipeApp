package com.example.recipeproject.commands;
/*
Author: BeGieU
Date: 16.12.2018
*/


public class CategoryCommand
{
    private Long id;
    private String description;

    public CategoryCommand()
    {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
