package com.example.recipeproject.controllers;
/*
Author: BeGieU
Date: 14.12.2018
*/

import com.example.recipeproject.commands.RecipeCommand;
import com.example.recipeproject.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController
{
    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService)
    {
        this.recipeService = recipeService;
    }

    /* {id} oznacza ,że id pobrane z adresu url
     @ParhVariable zmienna pobrana z URL' a
     czyli w tym wypadku id */
    @RequestMapping("/recipe/show/{id}")
    public String showById(@PathVariable String id, Model model)
    {
        model.addAttribute("recipe", recipeService.findById(new Long(id)));
        return "recipe/show";
    }

    @RequestMapping("recipe/new")
    public String newRecipe(Model model)
    {
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";
    }

    @PostMapping
    @RequestMapping(name = "recipe") //maping dla atrybutu o nazwie recipe
    public String saveOrUpdate(@ModelAttribute RecipeCommand command)
    {
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        //przekierowuje do innej metody controllera
        return "redirect:/recipe/show/" + savedCommand.getId();
    }

}
