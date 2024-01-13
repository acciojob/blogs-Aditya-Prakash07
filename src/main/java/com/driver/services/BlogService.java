package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    public Blog createAndReturnBlog(Long userId, String title, String content) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            Blog blog = new Blog(title, content, user);
            blogRepository.save(blog);
            return blog;
        }
        return null;
    }

    public void deleteBlog(Long blogId) {
        Blog blog = blogRepository.findById(blogId).orElse(null);
        if (blog != null) {
            // Delete corresponding images
            List<Image> images = blog.getImages();
            if (images != null) {
                for (Image image : images) {
                    imageRepository.deleteById(image.getId());
                }
            }
            // Delete the blog
            blogRepository.deleteById(blogId);
        }
    }
}
