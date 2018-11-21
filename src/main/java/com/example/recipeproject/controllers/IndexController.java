package com.example.recipeproject.controllers;
/*
Author: BeGieU
Date: 27.10.2018
*/

import com.example.recipeproject.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
//TODO add java configuration

@Slf4j
@Controller
public class IndexController
{

    private final RecipeService recipeService;

    @Autowired
    public IndexController(RecipeService recipeService)
    {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model)
    {
        log.debug("Getting index Page");
        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }

}
