package com.example.recipeproject.services;
/*
Author: BeGieU
Date: 30.12.2018
*/

import com.example.recipeproject.commands.IngredientCommand;
import com.example.recipeproject.converters.IngredientToIngredientCommand;
import com.example.recipeproject.model.Recipe;
import com.example.recipeproject.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService
{
    private  final IngredientToIngredientCommand ingredientToIngredientCommand;
    private  final RecipeRepository recipeRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, RecipeRepository recipeRepository)
    {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        System.out.println(ingredientToIngredientCommand);

        this.recipeRepository = recipeRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientID)
    {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if (!recipeOptional.isPresent())
        {
            //todo impl error handling
            System.out.println("recipe id not found, it's id: " + recipeId);
        }

        Recipe recipe = recipeOptional.get();

        /* elementy ktore spelniaja warunki w filtrze sa zwracane jako stream i beda
         mapowane(zostanie wykonana na nich operacja) i zwracane jako stream !!! */
        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientID))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

        if (!ingredientCommandOptional.isPresent())
        {
            //todo impl error handling
            System.out.println("Ingredient id not found: " + ingredientID);
        }

        return ingredientCommandOptional.get();

    }
}
