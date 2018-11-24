package com.example.recipeproject.controllers;

import com.example.recipeproject.model.Recipe;
import com.example.recipeproject.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
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
        //given
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(new Recipe());

        /*w jednej recipe trzeba ustawic id zeby recipy sie roznily
        bo hash set nie dodaje takich samych biektow*/
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        recipes.add(recipe);

        when(recipeService.getRecipes()).thenReturn(recipes);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when
        String viewName = indexController.getIndexPage(model);


        //then
        assertEquals("index", viewName);
        verify(recipeService, times(1)).getRecipes();

        /* argumentCaptor.capture() lapie argument ktory zostal podany
         * w miejscu w ktorym sie znajduje to wywolanie, czyli
         * addAttribute(eq("recipes"), argumentCaptor.capture())
         * w tym wypadku lapie to co jest argumentem numer 2 we funkcji add atrbute mianowicie
         * model.addAttribute("recipes", recipeService.getRecipes()) w klasie IndexController;
         * czyli po prostu zlapal recipeService.getRecipes().
         * Caly zapis ponizszego wywolania znaczy odpal metode z modelu o nazwie addatribute
         * z danymi argumentami i zlap argument numer 2*/
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());

        /*tutaj zlapana wartosc przypisujemy do seta*/
        Set<Recipe> setInController = argumentCaptor.getValue();
        /*i sprawdzamy czy set ktory jest zlapany w index kontrloerze faktycznie ma 2 argumenty*/
        assertEquals(2, setInController.size());
    }
}