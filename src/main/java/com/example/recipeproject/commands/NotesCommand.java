package com.example.recipeproject.commands;
/*
Author: BeGieU
Date: 16.12.2018
*/

public class NotesCommand
{
    private Long id;
    private String recipeNotes;

    public NotesCommand()
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

    public String getRecipeNotes()
    {
        return recipeNotes;
    }

    public void setRecipeNotes(String recipeNotes)
    {
        this.recipeNotes = recipeNotes;
    }
}
