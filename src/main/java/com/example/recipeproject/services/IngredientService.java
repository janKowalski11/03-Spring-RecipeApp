package com.example.recipeproject.services;
/*
Author: BeGieU
Date: 30.12.2018
*/

import com.example.recipeproject.commands.IngredientCommand;
import com.example.recipeproject.model.Ingredient;

public interface IngredientService
{
    Ingredient findById(Long ingredientID);

    IngredientCommand findCommandById(Long Id);

    IngredientCommand saveOrUpdateIngredientCommand(IngredientCommand command);

}
