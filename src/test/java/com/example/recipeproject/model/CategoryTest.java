package com.example.recipeproject.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CategoryTest
{
    Category category;

    /*znaczy ze przed jakim kolwiek testem uruchomi sie metoda z @before*/
    @Before
    public void SetUp()
    {
        category = new Category();
    }

    @Test
    public void getId()
    {
        Long idValue = 4L;

        category.setId(idValue);
        Assert.assertEquals(idValue, category.getId());
    }

    @Test
    public void getDescription()
    {
    }

    @Test
    public void getRecipes()
    {
    }
}