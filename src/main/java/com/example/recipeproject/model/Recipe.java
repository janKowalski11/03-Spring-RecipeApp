package com.example.recipeproject.model;
/*
Author: BeGieU
Date: 03.11.2018
*/

import javax.persistence.*;
import java.util.Set;

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
    private String directions;
    //TODO add
    //private Difficulty difficulty ;
    // (difficulty is an enum type)

    /*
     *      wytlumaczenie mappedby :
     * https://stackoverflow.com/a/9108618/9611203
     * Value of mappedBy is name of the field that is owning side of bidirectional relationship.
     * Required unless the relationship is unidirectional.
     * znaczy ze recipe jest tym obiektem one a ingredients many
     * znaczy ze ingredients beda przechowywac id recipe bo sa mapowane dla recipe
     *      Cascade:
     * znaczy ze jesli usuniemy recipe to usuniemy wszystkie skladniki
     * a jesl usuiniemy skadnik to nie usuniemy recipe
     * */
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "recipe")
    private Set<Ingredient> ingredients;

    /*
     * opis @Lob -> w clasie Notes
     * */
    @Lob
    private Byte[] image;

    /*cascade:
     *  tworzy kaskadowe polaczenie recipe -> notes, to powoduje np:
     * jesli usuniemy obj recipe to usuniemy notes
     * jesli usuniemy notes nie usuniemy recipe.
     *  Cascade nie specyfikuje
     * wlasciciela relacji, tylko mappedby, jednak w tym przypadku
     *
    /*
     Jak utworzyc bidirectional relationship:
    This is bidirectional relationship between Recipe and Notes
     since both of the classes happen to have
      each other's reference marked with @OneToOne annotaion
      */
    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    public Long getId()
    {
        return id;
    }

    //Setter do id jest potrzebny zeby hibernate mogl ustawic id
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

    public String getDirections()
    {
        return directions;
    }

    public void setDirections(String directions)
    {
        this.directions = directions;
    }

    public Set<Ingredient> getIngredients()
    {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients)
    {
        this.ingredients = ingredients;
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
