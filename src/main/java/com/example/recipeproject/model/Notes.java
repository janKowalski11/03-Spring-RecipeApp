package com.example.recipeproject.model;
/*
Author: BeGieU
Date: 03.11.2018
*/

import javax.persistence.*;

@Entity
public class Notes
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "Recipe")
    private Recipe recipe;

    /*
     * Lob - large object
     * w hibernate string ma defultowo 255 char ow
     * a w javie w pizdu wiecej, wiec oznaczamy recipeNotes
     * jako Lob zeby hibernate przyjmowal stringi wieksze niz 255
     * */
    @Lob
    private String recipeNotes;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Recipe getRecipe()
    {
        return recipe;
    }

    public void setRecipe(Recipe recipe)
    {
        this.recipe = recipe;
    }

    public String getRecipeNotes()
    {
        return recipeNotes;
    }

    public void setRecipeNotes(String recipeNotes)
    {
        this.recipeNotes = recipeNotes;
    }
}
