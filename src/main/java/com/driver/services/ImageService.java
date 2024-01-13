package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private ImageRepository imageRepository;

    public String addImage(Long blogId, String description, int dimensions) {
        Blog blog = blogRepository.findById(blogId).orElse(null);
        if (blog != null) {
            Image image = new Image(description, dimensions, blog);
            imageRepository.save(image);
            return "Added image successfully";
        }
        return null;
    }

    public void deleteImage(Long id) {
        imageRepository.delete(id);
    }

    public int countImagesInScreen(Long id, int screenDimensions) {
        Blog blog = blogRepository.findById(id).orElse(null);
        if (blog != null) {
            List<Image> images = blog.getImages();
            if (images != null) {
                int count = 0;
                for (Image image : images) {
                    if (image.getDimensions() <= screenDimensions) {
                        count++;
                    }
                }
                return count;
            }
        }
        return 0;
    }
}
