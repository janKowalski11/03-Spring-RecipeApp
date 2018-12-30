package com.example.recipeproject.repositories;
/*
Author: BeGieU
Date: 30.12.2018
*/

import com.example.recipeproject.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient,Long>
{
}
