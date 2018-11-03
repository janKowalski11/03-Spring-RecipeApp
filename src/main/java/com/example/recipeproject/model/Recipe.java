package com.example.recipeproject.model;
/*
Author: BeGieU
Date: 03.11.2018
*/

import javax.persistence.*;

@Entity
public class Recipe
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String Source;
    private String url;
    private String directins;
    //TODO add
    //private Difficulty difficulty ;
    // (difficulty is an enum type)

    /*
     * opis @Lob -> w clasie Notes
     * */
    @Lob
    private Byte[] image;

    /*
     * sprawia ze recipe jest wlascicielem notes (przygotowanie)
     * tworzy kaskadowe polaczenie recipe -> notes, to powoduje np:
     * jesli usuniemy obj recipe to usuniemy notes
     * jesli usuniemy notes nie usuniemy recipe bo to recipe jest owner w
     * tym poloczeniu kaskadowy
     */
    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Integer getPrepTime()
    {
        return prepTime;
    }

    public void setPrepTime(Integer prepTime)
    {
        this.prepTime = prepTime;
    }

    public Integer getCookTime()
    {
        return cookTime;
    }

    public void setCookTime(Integer cookTime)
    {
        this.cookTime = cookTime;
    }

    public Integer getServings()
    {
        return servings;
    }

    public void setServings(Integer servings)
    {
        this.servings = servings;
    }

    public String getSource()
    {
        return Source;
    }

    public void setSource(String source)
    {
        Source = source;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getDirectins()
    {
        return directins;
    }

    public void setDirectins(String directins)
    {
        this.directins = directins;
    }

    public Byte[] getImage()
    {
        return image;
    }

    public void setImage(Byte[] image)
    {
        this.image = image;
    }

    public Notes getNotes()
    {
        return notes;
    }

    public void setNotes(Notes notes)
    {
        this.notes = notes;
    }
}
