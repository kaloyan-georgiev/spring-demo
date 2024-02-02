package com.example.demo.controllers;

import com.example.demo.models.Tag;
import com.example.demo.repositories.TagRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tags")
public class TagController {
    private final TagRepository repository;

    private TagController(TagRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    private ResponseEntity<List<Tag>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }
    @GetMapping("/{requestedId}")
    private ResponseEntity<Tag> getTag(@PathVariable String requestedId)
    {
        Optional<Tag> result = repository.findById(requestedId);
        if(result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    private ResponseEntity<Void> createTag(Tag newTagReq, UriComponentsBuilder ucb, Principal principal) {
        System.out.println(newTagReq);
        Tag newTagOwned = new Tag(null, newTagReq.title());
        Tag savedTag = repository.save(newTagOwned);
        URI location =  ucb.path("/tags/{id}")
                .buildAndExpand(savedTag.id())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{requestedId}")
    private ResponseEntity<Void> updateTag(@PathVariable String requestedId, @RequestBody Tag tagUpdate, Principal principal) {
        Optional<Tag> oldTag = repository.findById(requestedId);
        if(oldTag.isPresent()) {
            Tag updatedTag = new Tag(oldTag.get().id(), tagUpdate.title());
            repository.save(updatedTag);
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{requestedId}")
    private ResponseEntity<Void> deleteTag(@PathVariable String requestedId, Principal principal) {
        if(!repository.findById(requestedId).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(requestedId);
        return ResponseEntity.noContent().build();
    }
}
