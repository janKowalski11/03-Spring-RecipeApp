package com.example.recipeproject.controllers;

import com.example.recipeproject.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class IndexControllerTest
{
    @Mock
    private RecipeService recipeService;

    @Mock
    private Model model;

    private IndexController indexController;

    @Before
    public void setUp() throws Exception
    {
        initMocks(this);
        indexController = new IndexController(recipeService);


    }

    @Test
    public void getIndexPage()
    {
        assertEquals(indexController.getIndexPage(model),
                "index");

        verify(recipeService, times(1)).getRecipes();

        verify(model, times(1))
                .addAttribute(eq("recipes"), anySet());
    }
}