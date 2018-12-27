package com.example.recipeproject.services;
/*
Author: BeGieU
Date: 08.11.2018
*/

import com.example.recipeproject.commands.RecipeCommand;
import com.example.recipeproject.converters.RecipeCommandToRecipe;
import com.example.recipeproject.converters.RecipeToRecipeCommand;
import com.example.recipeproject.model.Recipe;
import com.example.recipeproject.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService
{
    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe toRecipe;
    private final RecipeToRecipeCommand toRecipeCommand;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe toRecipe, RecipeToRecipeCommand toRecipeCommand)
    {
        this.recipeRepository = recipeRepository;
        this.toRecipe = toRecipe;
        this.toRecipeCommand = toRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipes()
    {
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Override
    public Recipe findById(Long l)
    {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(l);

        if (!optionalRecipe.isPresent())
        {
            throw new RuntimeException("Recipe not found");
        }

        return optionalRecipe.get();
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand)
    {
        Recipe detachedRecipe = toRecipe.convert(recipeCommand);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        System.out.println("Saved RecipeId:" + savedRecipe.getId());

        return toRecipeCommand.convert(savedRecipe);

    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long l) {
        return toRecipeCommand.convert(this.findById(l));
    }

    @Override
    public void deleteById(Long id)
    {
        recipeRepository.deleteById(id);
    }
}
