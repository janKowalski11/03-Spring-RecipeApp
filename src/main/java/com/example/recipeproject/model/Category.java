package com.example.recipeproject.model;
/*
Author: BeGieU
Date: 06.11.2018
*/

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category extends  BaseEntity
{


    private String description;

    //mappedBy mowi zekonfiguracja relacji po 2gej stronie
    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes = new HashSet<>();


    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Set<Recipe> getRecipes()
    {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes)
    {
        this.recipes = recipes;
    }
}
