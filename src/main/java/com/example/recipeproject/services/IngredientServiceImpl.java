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

    /* save of update ingredient, polega na dodaniu nowego skladnika lub znalezieniu
     odpowiednika istniejacego i zaktualizowaniu go do tego trzeba jeszcze przeprowadzic
     kopnwersje z command na plain*/
    @Override
    @Transactional
    public IngredientCommand saveOrUpdateIngredientCommand(IngredientCommand command)
    {
        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

        //sprawdzamy czy nasz command nalezy do jakiegos przepisu jesli nie to wyjatek
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

            /* sprawdzamy czy dodajemy nasz skladnik czy aktualizujemy juz istniejacy
            tutaj mamy aktualizacje  */
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
                //tutaj nie obecny wiec dodajemy nowy
                Ingredient ingredient = toIngredient.convert(command);
                ingredient.setRecipe(recipe);
                recipe.addIngredient(ingredient);

            }

            /* zapisujemy zaktualizowany przepis ze zaktualizowanym/dodanym skladnikiem */
            Recipe savedRecipe = recipeRepository.save(recipe);

            /*odnajdujemy zapisany skladnik zeby go potem zwrocic na koncu metody*/
            Optional<Ingredient> savedIngredientOpt = savedRecipe.getIngredients().stream()
                    .filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
                    .findFirst();

            //jesli nie udalo sie znalezc po id to szukamy po  opisie
            if (!savedIngredientOpt.isPresent())
            {
                savedIngredientOpt=savedRecipe.getIngredients().stream()
                        .filter(recipeIngredients->recipeIngredients.getDescription().equals(command.getDescription()))
                        .filter(recipeIngredients->recipeIngredients.getAmount().equals(command.getAmount()))
                        .filter(recipeIngredients->recipeIngredients.getUnitOfMeasure().getId().equals(command.getUnitOfMeasure().getId()))
                        .findFirst();
            }

            //zwracamy
            return toIngredientCommand.convert(savedIngredientOpt.get());
        }

    }

    @Override
    public void deleteById(Long id)
    {
        ingredientRepository.deleteById(id);
    }
}
