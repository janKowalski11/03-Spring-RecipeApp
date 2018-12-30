package com.example.recipeproject.services;

import com.example.recipeproject.commands.IngredientCommand;
import com.example.recipeproject.converters.IngredientToIngredientCommand;
import com.example.recipeproject.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.example.recipeproject.model.Ingredient;
import com.example.recipeproject.model.Recipe;
import com.example.recipeproject.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest
{
    IngredientServiceImpl ingredientService;

    @Mock
    RecipeRepository recipeRepository;

    IngredientToIngredientCommand ingredientToIngredientCommand;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);

        ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand, recipeRepository);
    }

    @Test
    public void findByRecipeIdAndIngredientId()
    {
    }

    @Test
    public void findByRecipeIdAndIngredientIdHappyPath()
    {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);

        when(recipeRepository.findById(any())).thenReturn(java.util.Optional.of(recipe));
        //then
        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 3L);

        //when
        assertEquals(Long.valueOf(1L), recipe.getId());
        assertEquals(Long.valueOf(3L), ingredientCommand.getId());
        verify(recipeRepository, times(1)).findById(any());
    }


}