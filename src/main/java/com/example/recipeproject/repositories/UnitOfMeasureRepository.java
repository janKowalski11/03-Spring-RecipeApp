package com.example.recipeproject.repositories;
/*
Author: BeGieU
Date: 07.11.2018
*/

import com.example.recipeproject.model.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long>
{
    //the default should be to use Optional instead of null
    Optional<UnitOfMeasure> findByDescription(String description);
}
