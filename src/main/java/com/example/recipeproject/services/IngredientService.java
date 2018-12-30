package com.example.recipeproject.services;
/*
Author: BeGieU
Date: 30.12.2018
*/

import com.example.recipeproject.commands.IngredientCommand;

public interface IngredientService
{
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientID);
}
