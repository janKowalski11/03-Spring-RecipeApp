package com.example.recipeproject.services;
/*
Author: BeGieU
Date: 30.12.2018
*/

import com.example.recipeproject.converters.IngredientToIngredientCommand;
import com.example.recipeproject.model.Ingredient;
import com.example.recipeproject.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService
{
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, IngredientRepository ingredientRepository)
    {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient findById(Long ingredientID)
    {
        Optional<Ingredient> ingredientOptional = ingredientRepository.findById(ingredientID);
        if (!ingredientOptional.isPresent())
        {
            throw new RuntimeException("Ingredient not found, it's id: " + ingredientID);
        }

        return ingredientOptional.get();

    }
}
