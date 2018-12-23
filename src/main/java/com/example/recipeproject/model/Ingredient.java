package com.example.recipeproject.model;
/*
Author: BeGieU
Date: 03.11.2018
*/

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
public class Ingredient extends BaseEntity
{
    private String description;
    private BigDecimal amount;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure unitOfMeasure;

    //Enum
    @ManyToOne
    private Recipe recipe;

    public Ingredient()
    {
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure unitOfMeasure)
    {
        this.description = description;
        this.amount = amount;
        this.unitOfMeasure = unitOfMeasure;
    }


    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public Recipe getRecipe()
    {
        return recipe;
    }

    public void setRecipe(Recipe recipe)
    {
        this.recipe = recipe;
    }

    public UnitOfMeasure getUnitOfMeasure()
    {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure)
    {
        this.unitOfMeasure = unitOfMeasure;
    }
}
