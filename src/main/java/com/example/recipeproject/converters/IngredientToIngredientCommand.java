package com.example.recipeproject.converters;
/*
Author: BeGieU
Date: 20.12.2018
*/

import com.example.recipeproject.commands.IngredientCommand;
import com.example.recipeproject.model.Ingredient;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 6/21/17.
 */
@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand>
{

    private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    @Autowired
    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter)
    {
        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient ingredient)
    {
        if (ingredient == null)
        {
            return null;
        }

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ingredient.getId());
        if (ingredient.getRecipe() != null)
            ingredientCommand.setRecipeId(ingredient.getRecipe().getId());
        ingredientCommand.setAmount(ingredient.getAmount());
        ingredientCommand.setDescription(ingredient.getDescription());
        ingredientCommand.setUnitOfMeasure(uomConverter.convert(ingredient.getUnitOfMeasure()));
        return ingredientCommand;
    }
}