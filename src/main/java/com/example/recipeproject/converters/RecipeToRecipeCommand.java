package com.example.recipeproject.converters;
/*
Author: BeGieU
Date: 20.12.2018
*/


import com.example.recipeproject.commands.RecipeCommand;
import com.example.recipeproject.model.Category;
import com.example.recipeproject.model.Ingredient;
import com.example.recipeproject.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand>
{
    private final RecipeCommand recipeCommand;

    private final IngredientToIngredientCommand toIngredientCommand;
    private final NotesToNotesCommand toNotesCommand;
    private final CategoryToCategoryCommand toCategoryCommand;

    @Autowired
    public RecipeToRecipeCommand(IngredientToIngredientCommand toIngredientCommand,
                                 NotesToNotesCommand toNotesCommand,
                                 CategoryToCategoryCommand toCategoryCommand)
    {
        this.recipeCommand = new RecipeCommand();

        this.toIngredientCommand = toIngredientCommand;
        this.toNotesCommand = toNotesCommand;
        this.toCategoryCommand = toCategoryCommand;
    }

    private void convertIngredent(Recipe source)
    {
        Set<Ingredient> ingredientSet = source.getIngredients();
        if (ingredientSet == null || ingredientSet.size() <= 0)
            return;

        ingredientSet.forEach(ingredient ->
        {
            recipeCommand.getIngredients().add(toIngredientCommand.convert(ingredient));
        });
    }

    private void convertCategories(Recipe source)
    {
        Set<Category> categorySet = source.getCategories();
        if (categorySet == null || categorySet.size() <= 0)
            return;

        categorySet.forEach(category ->
        {
            recipeCommand.getCategories().add(toCategoryCommand.convert(category));
        });
    }

    @Override
    public RecipeCommand convert(Recipe source)
    {
        if (source == null)
            return null;

        recipeCommand.setId(source.getId());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setServings(source.getServings());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setUrl(source.getUrl());
        recipeCommand.setDirections(source.getDirections());

        convertIngredent(source);

        recipeCommand.setDifficulty(source.getDifficulty());
        recipeCommand.setNotes(toNotesCommand.convert(source.getNotes()));

        convertCategories(source);

        return recipeCommand;


    }
}
