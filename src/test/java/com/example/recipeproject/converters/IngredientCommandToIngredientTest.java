package com.example.recipeproject.converters;

import com.example.recipeproject.commands.IngredientCommand;
import com.example.recipeproject.commands.UnitOfMeasureCommand;
import com.example.recipeproject.model.Ingredient;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest
{

    IngredientCommandToIngredient ingredientConverter;

    public static final Long ID_VAL = 1L;
    public static final BigDecimal BIG_DECIMAL = new BigDecimal(1);
    public static final String STRING_VAL = "val";
    public static final Long UOM_ID = new Long(2L);


    @Before
    public void setUp()
    {
        ingredientConverter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void testNullObject()
    {
        assertNull(ingredientConverter.convert(null));
    }

    @Test
    public void testEmptyObject()
    {
        assertNotNull(ingredientConverter.convert(new IngredientCommand()));
    }

    @Test
    public void convert()
    {
        //when
        final IngredientCommand command = new IngredientCommand();
        command.setId(ID_VAL);
        command.setAmount(BIG_DECIMAL);
        command.setDescription(STRING_VAL);

        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(UOM_ID);
        command.setUnitOfMeasure(unitOfMeasureCommand);

        //when
        Ingredient ingredient = ingredientConverter.convert(command);

        assertNotNull(ingredient);
        assertNotNull(ingredient.getUnitOfMeasure());
        assertEquals(ID_VAL, ingredient.getId());
        assertEquals(BIG_DECIMAL, ingredient.getAmount());
        assertEquals(STRING_VAL, ingredient.getDescription());
        assertEquals(UOM_ID, ingredient.getUnitOfMeasure().getId());

    }

    @Test
    public void convertWithNullUom()
    {
        //given
        IngredientCommand command=new IngredientCommand();
        command.setId(ID_VAL);
        command.setAmount(BIG_DECIMAL);
        command.setDescription(STRING_VAL);
        command.setUnitOfMeasure(null);

        //when
        Ingredient ingredient=ingredientConverter.convert(command);

        assertNotNull(ingredient);
        assertNull(ingredient.getUnitOfMeasure());
        assertEquals(ID_VAL, ingredient.getId());
        assertEquals(BIG_DECIMAL, ingredient.getAmount());
        assertEquals(STRING_VAL, ingredient.getDescription());



    }
}