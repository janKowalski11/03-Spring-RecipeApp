package com.example.recipeproject.services;
/*
Author: BeGieU
Date: 08.01.2019
*/


import com.example.recipeproject.model.Recipe;
import com.example.recipeproject.repositories.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService
{

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository)
    {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(Long recipeId, MultipartFile file)
    {
        try
        {
            Recipe recipe = recipeRepository.findById(recipeId).get();
            Byte[] bytes = new Byte[file.getBytes().length];

            //copies the array
            int i = 0;
            for (byte b : file.getBytes())
            {
                bytes[i++] = b;
            }
            recipe.setImage(bytes);

            recipeRepository.save(recipe);
        }
        catch (Exception e)
        {
            //TODO implement this
            System.out.println("Error: "+ e);
            e.printStackTrace();
        }
    }

}
