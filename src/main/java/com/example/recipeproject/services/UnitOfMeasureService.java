package com.example.recipeproject.services;
/*
Author: BeGieU
Date: 31.12.2018
*/

import com.example.recipeproject.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService
{
    Set<UnitOfMeasureCommand> listAllUoms();
}
