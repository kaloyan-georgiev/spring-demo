package com.example.demo.controllers;

import com.example.demo.models.BlogComment;
import com.example.demo.repositories.BlogCommentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/comments")
public class BlogCommentController
{
    private final BlogCommentRepository repository;

    private BlogCommentController(BlogCommentRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    private ResponseEntity<List<BlogComment>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }
    @GetMapping("/{requestedId}")
    private ResponseEntity<BlogComment> getBlogComment(@PathVariable String requestedId)
    {
        Optional<BlogComment> result = repository.findById(requestedId);
        if(result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    private ResponseEntity<Void> createBlogComment(BlogComment newBlogCommentReq, UriComponentsBuilder ucb, Principal principal) {
        System.out.println(newBlogCommentReq);
        BlogComment newBlogCommentOwned = new BlogComment(null, principal.getName(), newBlogCommentReq.postId(), newBlogCommentReq.content());
        BlogComment savedBlogComment = repository.save(newBlogCommentOwned);
        URI location =  ucb.path("/comments/{id}")
                .buildAndExpand(savedBlogComment.id())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{requestedId}")
    private ResponseEntity<Void> updateBlogComment(@PathVariable String requestedId, @RequestBody BlogComment BlogCommentUpdate, Principal principal) {
        BlogComment oldBlogComment = repository.findByIdAndOwnerId(requestedId, principal.getName());
        if(oldBlogComment != null) {
            BlogComment updatedBlogComment = new BlogComment(oldBlogComment.id(), principal.getName(), BlogCommentUpdate.postId(), BlogCommentUpdate.content());
            repository.save(updatedBlogComment);
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{requestedId}")
    private ResponseEntity<Void> deleteBlogComment(@PathVariable String requestedId, Principal principal) {
        if(repository.findByIdAndOwnerId(requestedId, principal.getName()) == null) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(requestedId);
        return ResponseEntity.noContent().build();
    }
}
