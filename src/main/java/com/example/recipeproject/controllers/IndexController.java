package com.example.recipeproject.controllers;
/*
Author: BeGieU
Date: 27.10.2018
*/

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController
{
    @RequestMapping({"", "/", "/index"})
    public String getIndexPage()
    {
        System.out.println("--------> geting Index Page");
        return "index";
    }

}
