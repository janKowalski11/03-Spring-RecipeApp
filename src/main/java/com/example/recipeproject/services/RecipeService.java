package com.example.recipeproject.services;
/*
Author: BeGieU
Date: 08.11.2018
*/

import com.example.recipeproject.commands.RecipeCommand;
import com.example.recipeproject.model.Recipe;

import java.util.Set;

public interface RecipeService
{
    Set<Recipe> getRecipes();

    Recipe findById(Long l);

    RecipeCommand findCommandById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    void deleteById(Long id);
}
