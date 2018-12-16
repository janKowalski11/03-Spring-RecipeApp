package com.example.recipeproject.converters;

import com.example.recipeproject.commands.CategoryCommand;
import com.example.recipeproject.model.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryCommandToIngredientTest
{

    CategoryCommandToIngredient converter;

    public final Long ID = 1L;
    public final String DESCRIPTION = "desc";

    @Before
    public void setUp() throws Exception
    {
        converter = new CategoryCommandToIngredient();
    }

    @Test
    public void testNullObject()
    {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject()
    {
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    public void convert()
    {
        //given
        CategoryCommand command = new CategoryCommand();
        command.setId(ID);
        command.setDescription(DESCRIPTION);

        //when
        Category category = converter.convert(command);

        //then
        assertNotNull(category);
        assertEquals(ID, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());


    }
}