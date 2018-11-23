package com.example.recipeproject.controllers;

import com.example.recipeproject.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.ui.Model;

import static org.mockito.MockitoAnnotations.initMocks;

public class IndexControllerTest
{

    @Mock
    private  RecipeService recipeService;

    @Mock
    private Model model;

    private IndexController indexController;



    @Before
    public void setUp() throws Exception
    {
       initMocks(this);
       indexController=new IndexController(recipeService);
    }

    @Test
    public void getIndexPage()
    {
//      );
    }
}