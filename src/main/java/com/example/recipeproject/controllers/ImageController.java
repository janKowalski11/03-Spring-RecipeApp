package com.example.recipeproject.controllers;
/*
Author: BeGieU
Date: 08.01.2019
*/

import com.example.recipeproject.commands.RecipeCommand;
import com.example.recipeproject.services.ImageService;
import com.example.recipeproject.services.RecipeService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

@Controller
public class ImageController
{
    private final RecipeService recipeService;
    private final ImageService imageService;

    public ImageController(RecipeService recipeService, ImageService imageService)
    {
        this.recipeService = recipeService;
        this.imageService = imageService;
    }

    @GetMapping("recipe/{id}/image")
    public String showUploadForm(@PathVariable String id, Model model)
    {
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/image_upload_form";
    }

    @PostMapping("recipe/{id}/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("imageFile") MultipartFile file)
    {

        imageService.saveImageFile(Long.valueOf(id), file);

        return "redirect:/recipe/" + id + "/show";
    }

    //spring gives us  HttpServletResponse
    @GetMapping("recipe/{id}/recipe_image")
    public void renderImageFromDb(@PathVariable String id, HttpServletResponse response) throws IOException
    {
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(id));



        byte[] byteArr = null;
        if (recipeCommand.getImage() != null)
        {
            byteArr = new byte[recipeCommand.getImage().length];

            int i = 0;
            for (Byte wrappedByte : recipeCommand.getImage())
            {
                byteArr[i++] = wrappedByte;//auto unboxing wrapped byte
            }
        }
        else
        {
            File defaultPhoto = new ClassPathResource("static/images/guacamole400x400WithX.jpg").getFile();
            byte[] defaultPhotoBytes = Files.readAllBytes(defaultPhoto.toPath());

            byteArr=defaultPhotoBytes;
        }
        response.setContentType("image/jpeg");
        InputStream is = new ByteArrayInputStream(byteArr);
        IOUtils.copy(is, response.getOutputStream());

    }

}