package com.example.recipeproject.repositories;
/*
Author: BeGieU
Date: 07.11.2018
*/

import com.example.recipeproject.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long>
{

}
