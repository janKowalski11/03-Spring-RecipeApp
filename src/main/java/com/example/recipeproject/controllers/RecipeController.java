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
import org.springframework.web.bind.annotation.*;

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
    @GetMapping
    @RequestMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model)
    {
        model.addAttribute("recipe", recipeService.findById(new Long(id)));
        return "recipe/show";
    }

    @GetMapping
    @RequestMapping("recipe/new")
    public String newRecipe(Model model)
    {
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";
    }

    /*mapuje    <form  th:object="${recipe}" th:action="@{/recipe/}" method="post">
     * czyli w tym wypadku postmaping nie mauje zadnego konkretnego url'a tylko
     * th:action="@{/recipe/}" method="post".Czyli mapuje akcje ktora dzieje sie
     * po kliknieciu guzika zubmit w recipeform . Jest to spowodowane tym ze
     *  ten mapping dziala dla dodwania i aktualizowania i ponaddto nie potrzeba wyswietlac
     *  zadnej konkretnej strony tylko wykonywac konkretne akcje na formiei potem ta
     *  akcje obsłóżyć i do tego dedykowana jest ta metoda*/

    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute("recipe") RecipeCommand command)
    {
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        //przekierowuje do innej metody controllera
        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @GetMapping
    @RequestMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model)
    {
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/recipeform";
    }

    @GetMapping
    @RequestMapping("recipe/{id}/delete")
    public String deleteById(@PathVariable String id)
    {
        System.out.println("Deleting id: " + id);

        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }

}
