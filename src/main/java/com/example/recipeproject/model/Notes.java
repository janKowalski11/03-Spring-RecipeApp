package com.example.recipeproject.model;
/*
Author: BeGieU
Date: 03.11.2018
*/

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Recipe recipe;

    /*
     * Lob - large object
     * w hibernate string ma defultowo 255 char ow
     * a w javie w pizdu wiecej, wiec oznaczamy recipeNotes
     * jako Lob zeby hibernate przyjmowal stringi wieksze niz 255
     * */
    @Lob
    private String recipeNotes;

}
