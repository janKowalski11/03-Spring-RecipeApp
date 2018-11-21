package com.example.recipeproject.model;
/*
Author: BeGieU
Date: 06.11.2018
*/

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"recipes"}) //naprawia wyjatek
@Entity
public class Category
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    //mappedBy mowi zekonfiguracja relacji po 2gej stronie
    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes = new HashSet<>();

    public Category()
    {
    }

    protected boolean canEqual(final Object other)
    {
        return other instanceof Category;
    }

}
