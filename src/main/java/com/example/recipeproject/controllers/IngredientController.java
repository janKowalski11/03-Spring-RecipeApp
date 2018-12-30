package com.example.recipeproject.controllers;
/*
Author: BeGieU
Date: 30.12.2018
*/

import com.example.recipeproject.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IngredientController
{

    private final RecipeService recipeService;

    @Autowired
    public IngredientController(RecipeService recipeService)
    {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/ingredients")
    public String listIngredients(@PathVariable String id, Model model)
    {
        model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(id)));


        return "recipe/ingredient/list";
    }
}
