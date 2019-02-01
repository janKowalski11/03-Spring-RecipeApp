package com.example.recipeproject.controllers;
/*
Author: BeGieU
Date: 01.02.2019
*/

/*Handles exceptions that are thrown by every other
 controller i.e NumberFormatException, in other words
  this is Global Exception Handler used not to duplicate code*/

import com.example.recipeproject.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController
{

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleNumberFormatException(Exception e)
    {
        log.error(e.getMessage());
        e.printStackTrace();

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("exception",e);
        modelAndView.setViewName("exceptions/400error");

        return  modelAndView;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleNotFound(Exception e)
    {
        log.error(e.getMessage());
        e.printStackTrace();


        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("exception", e);
        modelAndView.setViewName("exceptions/404error");

        return modelAndView;
    }
}
