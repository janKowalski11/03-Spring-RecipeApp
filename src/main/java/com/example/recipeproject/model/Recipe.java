package com.example.recipeproject.model;
/*
Author: BeGieU
Date: 03.11.2018
*/

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
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

    @Lob
    private String directions;

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();


    @Lob
    private Byte[] image;

    /*wartosci enuma konwerowane do stringa i wsazane do bazy
     * w ordinal warosci numerowane i numery do bazy.*/
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

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

    /*
     *JoinTable:
     * -utworz polaczona tabele o nazwie name
     * -polacz columne o nazwie recipe id i ona bedzie miec id
     * wlasciciela relacji
     * -polacz kolumne o nazwie category id i ona ma id
     * nie-wlasciciela
     * p.s usunac @jointable i mappedby po 2giej stronie
     * i zobaczyc jak dziala bez tego
     * zeb lepiej zrozumiec co robi ta anotacja
     *
     * */
    @ManyToMany
    @JoinTable(name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();




    public Recipe addIngredient(Ingredient ingredient)
    {
        ingredient.setRecipe(this);
        ingredients.add(ingredient);

        return this;
    }

    //ostroznie trzeba uzywac lomboka bo tutaj mamy zmodyfikowany setter
    // a lombok by go zastapil klasycznym(taki jak generuje intellij)
    public void setNotes(Notes notes)
    {
        this.notes = notes;
        notes.setRecipe(this);
    }


}
