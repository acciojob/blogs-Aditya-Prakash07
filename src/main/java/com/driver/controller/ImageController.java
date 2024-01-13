package com.driver.controller;

import com.driver.models.Image;
import com.driver.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/{blogId}/add-image")
    public ResponseEntity<String> addImage(@PathVariable Long blogId, @RequestParam String description, @RequestParam int dimensions) {
        String response = imageService.addImage(blogId, description, dimensions);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to add image to the blog", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/countImagesInScreen/{id}/{screenDimensions}")
    public ResponseEntity<Integer> countImagesInScreen(@PathVariable Long id, @PathVariable int screenDimensions) {
        int count = imageService.countImagesInScreen(id, screenDimensions);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        imageService.deleteImage(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


