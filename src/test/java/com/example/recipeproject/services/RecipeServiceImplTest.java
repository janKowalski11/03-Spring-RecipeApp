package com.example.recipeproject.services;

import com.example.recipeproject.model.Recipe;
import com.example.recipeproject.repositories.RecipeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class RecipeServiceImplTest
{
    private RecipeServiceImpl recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception
    {
        /*inicijalizuje @Mock czyli miejsca gdzie dziala Autowired, czyli innymi slowy
        nasladuje dependencje z testowanej klasy */
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository);

    }

    @Test
    public void getRecipes()
    {
        Recipe recipe1 = new Recipe();
        HashSet<Recipe> recipesData = new HashSet<>();
        recipesData.add(recipe1);

        /*wymusza ze jesli wywolana metoda getRecipes() to musi zwrocic recipesData*/
        Mockito.when(recipeService.getRecipes()).thenReturn(recipesData);

        Set<Recipe> recipes = recipeService.getRecipes();
        Assert.assertEquals(1, recipes.size());

        /*sprawdza czy findAll()z klasy recipe repositroy byl wywolany doklanie raz czy wiecej */
        verify(recipeRepository,times(1)).findAll();

    }
}