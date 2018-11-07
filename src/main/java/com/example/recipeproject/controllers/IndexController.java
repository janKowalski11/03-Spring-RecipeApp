package com.example.recipeproject.controllers;
/*
Author: BeGieU
Date: 27.10.2018
*/

import com.example.recipeproject.model.Category;
import com.example.recipeproject.model.UnitOfMeasure;
import com.example.recipeproject.repositories.CategoryRepository;
import com.example.recipeproject.repositories.UnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController
{
    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository)
    {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage()
    {
        //uzywamy optional bo moze zwrocic nulla
        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        System.out.println("category id :" + categoryOptional.get().getId());
        System.out.println("unit id: " + unitOfMeasureOptional.get().getId());

        return "index";
    }

}
