package com.example.recipeproject.services;

import com.example.recipeproject.commands.RecipeCommand;
import com.example.recipeproject.converters.RecipeCommandToRecipe;
import com.example.recipeproject.converters.RecipeToRecipeCommand;
import com.example.recipeproject.model.Recipe;
import com.example.recipeproject.repositories.RecipeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest//brings whole spring context , potrzebne do Integration Test
public class RecipeServiceIT
{

    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeCommandToRecipe toRecipe;

    @Autowired
    RecipeToRecipeCommand toRecipeCommand;


    final String DESC = "desc";

    /*O transakcji http://www.javaexpress.pl/article/show/Transakcje_w_systemach_Java_Enterprise_Wprowadzenie*/
    @Transactional
    @Test
    public void testSaveOfDescription()
    {
        //given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        RecipeCommand testRecipeCommand = toRecipeCommand.convert(testRecipe);

        //when
        testRecipeCommand.setDescription(DESC);
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);

        //then
        assertEquals(DESC, savedRecipeCommand.getDescription());
        assertEquals(testRecipe.getId(), savedRecipeCommand.getId());
        assertEquals(testRecipe.getCategories().size(), savedRecipeCommand.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(), savedRecipeCommand.getIngredients().size());


    }
}