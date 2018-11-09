package com.example.recipeproject.repositories;
/*
Author: BeGieU
Date: 07.11.2018
*/

import com.example.recipeproject.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long>
{
    /*Nie ma implementacji tego interfejsu jednak w index controller
     * spring i tak umie z autowirowac ten interfejs
     * jest to spowodowane tym ze hibernate generuje metode
     * na podstawie nazwy metody ale musie ten interfejs dziedziczc z
     * crud reposiory wiecej na ten temat :
     * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods*/
    Optional<Category> findByDescription(String description);
}
