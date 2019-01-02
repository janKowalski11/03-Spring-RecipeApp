package com.example.recipeproject.services;
/*
Author: BeGieU
Date: 30.12.2018
*/

import com.example.recipeproject.commands.IngredientCommand;
import com.example.recipeproject.converters.IngredientCommandToIngredient;
import com.example.recipeproject.converters.IngredientToIngredientCommand;
import com.example.recipeproject.model.Ingredient;
import com.example.recipeproject.model.Recipe;
import com.example.recipeproject.repositories.IngredientRepository;
import com.example.recipeproject.repositories.RecipeRepository;
import com.example.recipeproject.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService
{
    private final IngredientCommandToIngredient toIngredient;
    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final IngredientToIngredientCommand toIngredientCommand;

    public IngredientServiceImpl(IngredientCommandToIngredient toIngredient, IngredientRepository ingredientRepository,
                                 RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository,
                                 IngredientToIngredientCommand toIngredientCommand)
    {
        this.toIngredient = toIngredient;
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.toIngredientCommand = toIngredientCommand;
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

    @Override
    public IngredientCommand findCommandById(Long id)
    {
        return toIngredientCommand.convert(this.findById(id));
    }

    //save of update ingredient
    @Override
    @Transactional
    public IngredientCommand saveOrUpdateIngredientCommand(IngredientCommand command)
    {
        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

        if (!recipeOptional.isPresent())
        {

            //todo toss error if not found!
            System.out.println("Recipe not found for id: " + command.getRecipeId());
            return new IngredientCommand();
        }
        else
        {
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            if (ingredientOptional.isPresent())
            {
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(command.getDescription());
                ingredientFound.setAmount(command.getAmount());
                ingredientFound.setUnitOfMeasure(unitOfMeasureRepository
                        .findById(command.getUnitOfMeasure().getId())
                        .orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); //todo address this
            }
            else
            {
                //add new Ingredient
                recipe.addIngredient(toIngredient.convert(command));
            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            //to do check for fail
            return toIngredientCommand.convert(savedRecipe.getIngredients().stream()
                    .filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
                    .findFirst()
                    .get());
        }

    }
}
