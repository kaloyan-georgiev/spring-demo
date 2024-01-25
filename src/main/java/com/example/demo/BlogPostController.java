package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
// @RequestMapping("/blogposts")
class BlogPostController {

    // @GetMapping("/{requestedId}")
    // private ResponseEntity<BlogPost> findById(@PathVariable String requestedId) {
    //     BlogPost blogpost = new BlogPost(UUID.randomUUID(), "Post 1", "Post 1 contents");
    //     return ResponseEntity.ok(blogpost);
    // }    
}