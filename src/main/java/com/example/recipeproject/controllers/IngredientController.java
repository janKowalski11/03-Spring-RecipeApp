package com.example.recipeproject.controllers;
/*
Author: BeGieU
Date: 30.12.2018
*/

import com.example.recipeproject.commands.IngredientCommand;
import com.example.recipeproject.services.IngredientService;
import com.example.recipeproject.services.RecipeService;
import com.example.recipeproject.services.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IngredientController
{

    private final RecipeService recipeService;

    private final IngredientService ingredientService;

    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService)
    {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping("/recipe/{id}/ingredients")
    public String listIngredients(@PathVariable String id, Model model)
    {
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));


        return "recipe/ingredient/list";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showRecipesIngredient(@PathVariable String recipeId,
                                        @PathVariable String ingredientId,
                                        Model model)
    {
        model.addAttribute("ingredient", ingredientService.findById(Long.valueOf(ingredientId)));

        return "recipe/ingredient/show";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId,
                                         @PathVariable String ingredientId,
                                         Model model)
    {
        //todo finish this,
        model.addAttribute("ingredient", ingredientService.findCommandById(Long.valueOf(ingredientId)));
        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());

        return "recipe/ingredient/ingredient_form";

    }

    @PostMapping("/recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand ingredient)
    {
        IngredientCommand savedCommand = ingredientService.saveOrUpdateIngredientCommand(ingredient);

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }
}
