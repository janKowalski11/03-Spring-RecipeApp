package com.example.recipeproject.services;
/*
Author: BeGieU
Date: 08.01.2019
*/

import org.springframework.web.multipart.MultipartFile;

public interface ImageService
{
    void saveImageFile(Long recipeId, MultipartFile file);
}
