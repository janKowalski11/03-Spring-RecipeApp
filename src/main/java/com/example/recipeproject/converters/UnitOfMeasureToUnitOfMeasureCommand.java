package com.example.recipeproject.converters;
/*
Author: BeGieU
Date: 20.12.2018
*/

import com.example.recipeproject.commands.UnitOfMeasureCommand;
import com.example.recipeproject.model.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand>
{

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure source)
    {
        if (source == null)
            return null;

        UnitOfMeasureCommand unit = new UnitOfMeasureCommand();
        unit.setId(source.getId());
        unit.setDescription(source.getDescription());

        return unit;
    }
}
