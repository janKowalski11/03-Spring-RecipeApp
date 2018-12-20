package com.example.recipeproject.converters;

import com.example.recipeproject.commands.CategoryCommand;
import com.example.recipeproject.commands.IngredientCommand;
import com.example.recipeproject.commands.NotesCommand;
import com.example.recipeproject.commands.RecipeCommand;
import com.example.recipeproject.model.Difficulty;
import com.example.recipeproject.model.Recipe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest
{

    RecipeCommandToRecipe toRecipe;

    final Long ID_VAL = 1L;
    final String DESC = "desc";
    final Integer PREP_TIME = 1;
    final Integer COOK_TIME = 2;
    final String SOURCE = "src";
    final String URL = "url";
    final String DIRECTIONS = "dir";
    final Long NOTES_ID = 1L;


    @Before
    public void setUp() throws Exception
    {
        toRecipe = new RecipeCommandToRecipe(
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new NotesCommandToNotes(),
                new CategoryCommandToCategory());
    }

    @Test
    public void testNullObject()
    {
        assertNull(toRecipe.convert(null));
    }

    @Test
    public void testEmptyObject()
    {
        assertNotNull(toRecipe.convert(new RecipeCommand()));
    }


    @Test
    public void convert()
    {
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(ID_VAL);
        recipeCommand.setDescription(DESC);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);
        recipeCommand.setDirections(DIRECTIONS);

        IngredientCommand ingredientCommand1 = new IngredientCommand();
        ingredientCommand1.setId(1L);
        IngredientCommand ingredientCommand2 = new IngredientCommand();
        ingredientCommand2.setId(2L);
        recipeCommand.getIngredients().add(ingredientCommand1);
        recipeCommand.getIngredients().add(ingredientCommand2);

        recipeCommand.setDifficulty(Difficulty.EASY);

        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOTES_ID);
        recipeCommand.setNotes(notesCommand);

        CategoryCommand categoryCommand1 = new CategoryCommand();
        categoryCommand1.setId(1L);
        CategoryCommand categoryCommand2 = new CategoryCommand();
        categoryCommand2.setId(2L);
        recipeCommand.getCategories().add(categoryCommand1);
        recipeCommand.getCategories().add(categoryCommand2);

        //when
        Recipe recipe = toRecipe.convert(recipeCommand);

        //then
        assertNotNull(recipe);
        assertEquals(ID_VAL, recipe.getId());
        assertEquals(DESC, recipe.getDescription());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(DIRECTIONS, recipe.getDirections());

        assertNotNull(recipe.getIngredients());
        assertEquals(2, recipe.getIngredients().size());

        assertEquals(Difficulty.EASY, recipe.getDifficulty());

        assertNotNull(recipe.getNotes());
        assertEquals(NOTES_ID, recipe.getNotes().getId());

        assertNotNull(recipe.getCategories());
        assertEquals(2, recipe.getCategories().size());

    }
}