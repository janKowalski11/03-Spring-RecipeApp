package com.example.recipeproject.converters;
/*
Author: BeGieU
Date: 20.12.2018
*/


import com.example.recipeproject.commands.RecipeCommand;
import com.example.recipeproject.model.Category;
import com.example.recipeproject.model.Recipe;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand>
{


    private final IngredientToIngredientCommand toIngredientCommand;
    private final NotesToNotesCommand toNotesCommand;
    private final CategoryToCategoryCommand toCategoryCommand;

    @Autowired
    public RecipeToRecipeCommand(IngredientToIngredientCommand toIngredientCommand,
                                 NotesToNotesCommand toNotesCommand,
                                 CategoryToCategoryCommand toCategoryCommand)
    {

        this.toIngredientCommand = toIngredientCommand;
        this.toNotesCommand = toNotesCommand;
        this.toCategoryCommand = toCategoryCommand;
    }


    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source)
    {
        if (source == null)
        {
            return null;
        }

        final RecipeCommand command = new RecipeCommand();
        command.setId(source.getId());
        command.setCookTime(source.getCookTime());
        command.setPrepTime(source.getPrepTime());
        command.setDescription(source.getDescription());
        command.setDifficulty(source.getDifficulty());
        command.setDirections(source.getDirections());
        command.setServings(source.getServings());
        command.setSource(source.getSource());
        command.setUrl(source.getUrl());
        command.setNotes(toNotesCommand.convert(source.getNotes()));
        command.setImage(source.getImage());

        if (source.getCategories() != null && source.getCategories().size() > 0)
        {
            source.getCategories()
                    .forEach((Category category) -> command.getCategories().add(toCategoryCommand.convert(category)));
        }

        if (source.getIngredients() != null && source.getIngredients().size() > 0)
        {
            source.getIngredients()
                    .forEach(ingredient -> command.getIngredients().add(toIngredientCommand.convert(ingredient)));
        }

        return command;
    }


}
