package com.example.recipeproject.services;

import com.example.recipeproject.commands.IngredientCommand;
import com.example.recipeproject.converters.IngredientCommandToIngredient;
import com.example.recipeproject.converters.IngredientToIngredientCommand;
import com.example.recipeproject.converters.UnitOfMeasureCommandToUnitOfMeasure;
import com.example.recipeproject.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.example.recipeproject.model.Ingredient;
import com.example.recipeproject.model.Recipe;
import com.example.recipeproject.repositories.IngredientRepository;
import com.example.recipeproject.repositories.RecipeRepository;
import com.example.recipeproject.repositories.UnitOfMeasureRepository;
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

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    IngredientCommandToIngredient ingredientCommandToIngredient;

    IngredientToIngredientCommand ingredientToIngredientCommand;


    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);

        ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
        ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());


        ingredientService = new IngredientServiceImpl(
                ingredientCommandToIngredient,
                ingredientRepository,
                recipeRepository, unitOfMeasureRepository,
                ingredientToIngredientCommand);
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
        Ingredient ingredient = ingredientService.findById(3L);

        //when
        assertEquals(Long.valueOf(3L), ingredient.getId());
        verify(ingredientRepository, times(1)).findById(any());
    }

    @Test
    public void testSaveOrUpdateIngredientCommand() //Testing with recipe that contains no recipes
    {
        //given
        IngredientCommand command = new IngredientCommand();
        command.setId(3L);
        command.setRecipeId(2L);

        Optional<Recipe> recipeOptional = Optional.of(new Recipe());

        Recipe savedRecipe = new Recipe();
        savedRecipe.addIngredient(new Ingredient());
        savedRecipe.getIngredients().iterator().next().setId(3L);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        when(recipeRepository.save(any())).thenReturn(savedRecipe);

        //when
        IngredientCommand savedCommand = ingredientService.saveOrUpdateIngredientCommand(command);

        //then
        assertEquals(Long.valueOf(3L), savedCommand.getId());
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));

    }

    @Test
    public void testDeleteById()
    {
        //Given
        Long id = 1L;

        ingredientService.deleteById(1L);

        verify(ingredientRepository, times(1)).deleteById(anyLong());


    }
}