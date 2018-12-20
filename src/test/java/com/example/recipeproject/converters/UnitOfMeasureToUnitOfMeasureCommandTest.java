package com.example.recipeproject.converters;

import com.example.recipeproject.commands.UnitOfMeasureCommand;
import com.example.recipeproject.model.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureToUnitOfMeasureCommandTest
{

    public static final Long ID = 1L;
    public static final String DESC = "DESC";

    UnitOfMeasureToUnitOfMeasureCommand toUnitOfMeasureCommand;

    @Before
    public void setUp() throws Exception
    {
        toUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void testNullObject()
    {
        assertNull(toUnitOfMeasureCommand.convert(null));
    }

    @Test
    public void testEmptyObject()
    {
        assertNotNull(toUnitOfMeasureCommand.convert(new UnitOfMeasure()));
    }

    @Test
    public void convert()
    {
        //given
        UnitOfMeasure unit = new UnitOfMeasure();
        unit.setId(ID);
        unit.setDescription(DESC);

        //then
        UnitOfMeasureCommand unitOfMeasureCommand = toUnitOfMeasureCommand.convert(unit);

        //when
        assertNotNull(unitOfMeasureCommand);
        assertEquals(ID, unitOfMeasureCommand.getId());
        assertEquals(DESC, unitOfMeasureCommand.getDescription());


    }
}