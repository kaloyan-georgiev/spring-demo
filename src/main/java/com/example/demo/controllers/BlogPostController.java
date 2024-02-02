package com.example.demo.controllers;

import com.example.demo.repositories.BlogPostRepository;
import com.example.demo.models.BlogPost;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/blogposts")
class BlogPostController {
    private final BlogPostRepository repository;

    private BlogPostController(BlogPostRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    private ResponseEntity<List<BlogPost>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }
    @GetMapping("/{requestedId}")
    private ResponseEntity<BlogPost> getBlogPost(@PathVariable String requestedId)
    {
        Optional<BlogPost> result = repository.findById(requestedId);
        if(result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    private ResponseEntity<Void> createBlogPost(BlogPost newBlogPostReq, UriComponentsBuilder ucb, Principal principal) {
        System.out.println(newBlogPostReq);
        BlogPost newBlogPostOwned = new BlogPost(null, principal.getName(), newBlogPostReq.title(), newBlogPostReq.content(), newBlogPostReq.tags());
        BlogPost savedBlogPost = repository.save(newBlogPostOwned);
        URI location =  ucb.path("/blogposts/{id}")
                .buildAndExpand(savedBlogPost.id())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{requestedId}")
    private ResponseEntity<Void> updateBlogPost(@PathVariable String requestedId, @RequestBody BlogPost BlogPostUpdate, Principal principal) {
        BlogPost oldBlogPost = repository.findByIdAndOwnerId(requestedId, principal.getName());
        if(oldBlogPost != null) {
            BlogPost updatedBlogPost = new BlogPost(oldBlogPost.id(), principal.getName(), BlogPostUpdate.title(), BlogPostUpdate.content(), BlogPostUpdate.tags());
            repository.save(updatedBlogPost);
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{requestedId}")
    private ResponseEntity<Void> deleteBlogPost(@PathVariable String requestedId, Principal principal) {
        if(repository.findByIdAndOwnerId(requestedId, principal.getName()) == null) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(requestedId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/view")
    private String listView(Model model) {
        model.addAttribute("posts", repository.findAll());
        return "blogposts_view";
    }
}