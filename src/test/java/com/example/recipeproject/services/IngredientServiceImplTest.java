package com.example.recipeproject.services;

import com.example.recipeproject.converters.IngredientToIngredientCommand;
import com.example.recipeproject.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.example.recipeproject.model.Ingredient;
import com.example.recipeproject.repositories.IngredientRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest
{

    IngredientServiceImpl ingredientService;

    @Mock
    IngredientRepository ingredientRepository;

    IngredientToIngredientCommand ingredientToIngredientCommand;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);

        ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand,ingredientRepository);
    }

    @Test
    public void findByRecipeIdAndIngredientId()
    {
    }

    @Test
    public void findByRecipeIdAndIngredientIdHappyPath()
    {
        //given


        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);


        when(ingredientRepository.findById(any())).thenReturn(Optional.of(ingredient3));
        //then
        Ingredient ingredient = ingredientService.findById( 3L);

        //when
        assertEquals(Long.valueOf(3L), ingredient.getId());
        verify(ingredientRepository, times(1)).findById(any());
    }


}