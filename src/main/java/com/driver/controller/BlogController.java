package com.driver.controller;

import com.driver.models.Blog;
import com.driver.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping("/create")
    public ResponseEntity<Void> createBlog(@RequestParam Long userId,
                                          @RequestParam String title,
                                          @RequestParam String content) {
        Blog blog = blogService.createAndReturnBlog(userId, title, content);
        if (blog != null) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{blogId}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long blogId) {
        blogService.deleteBlog(blogId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

