package com.example.recipeproject.converters;
/*
Author: BeGieU
Date: 20.12.2018
*/

import com.example.recipeproject.commands.CategoryCommand;
import com.example.recipeproject.commands.IngredientCommand;
import com.example.recipeproject.commands.RecipeCommand;
import com.example.recipeproject.model.Category;
import com.example.recipeproject.model.Ingredient;
import com.example.recipeproject.model.Recipe;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe>
{
    private final IngredientCommandToIngredient toIngredient;
    private final NotesCommandToNotes toNotes;
    private final CategoryCommandToCategory toCategory;
    private final Recipe recipe;

    @Autowired
    public RecipeCommandToRecipe(IngredientCommandToIngredient toIngredient, NotesCommandToNotes toNotes, CategoryCommandToCategory toCategory)
    {
        recipe = new Recipe();

        this.toIngredient = toIngredient;
        this.toNotes = toNotes;
        this.toCategory = toCategory;
    }

    private void convertIngredients(RecipeCommand source)
    {
        Set<IngredientCommand> ingredientCommands = source.getIngredients();
        if (ingredientCommands == null || ingredientCommands.size() <= 0)
            return;


        Set<Ingredient> ingredients = new HashSet<>();
        ingredientCommands.forEach((ing) ->
        {
            ingredients.add(toIngredient.convert(ing));
        });

        recipe.setIngredients(ingredients);
    }

    private void convertCategories(RecipeCommand source)
    {
        Set<CategoryCommand> categoryCommands = source.getCategories();
        if (categoryCommands == null || categoryCommands.size() <= 0)
            return;

        Set<Category> categories = new HashSet<>();

        categoryCommands.forEach(categoryCommand ->
        {
            categories.add(toCategory.convert(categoryCommand));
        });

        recipe.setCategories(categories);
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source)
    {
        if (source == null)
        {
            return null;
        }

        recipe.setId(source.getId());
        recipe.setDescription(source.getDescription());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setCookTime(source.getCookTime());
        recipe.setSource(source.getSource());
        recipe.setUrl(source.getUrl());
        recipe.setDirections(source.getDirections());

        convertIngredients(source);

        recipe.setDifficulty(source.getDifficulty());
        recipe.setNotes(toNotes.convert(source.getNotes()));

        convertCategories(source);


        return recipe;
    }
}
