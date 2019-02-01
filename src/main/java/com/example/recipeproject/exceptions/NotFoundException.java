package com.example.recipeproject.exceptions;
/*
Author: BeGieU
Date: 01.02.2019
*/

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*jesli jakas klasa to rzuci to wtedy
 przegladarka wyswietla 404 czyli notfound exception zamiast 500 bo mamy
 @ResponseStatus(HttpStatus.NOT_FOUND)*/

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException
{
    public NotFoundException()
    {
        super();
    }

    public NotFoundException(String msg)
    {
        super(msg);
    }

    public NotFoundException(String msg, Throwable cause)
    {
        super(msg,cause);
    }
}
