package com.example.demo.controllers;

import com.example.demo.models.UserFavorite;
import com.example.demo.repositories.MongoUserRepository;
import com.example.demo.models.MongoUser;
import com.example.demo.repositories.UserFavoriteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
class MongoUserController {
    private final MongoUserRepository repository;
    private final UserFavoriteRepository favoriteRepository;

    private MongoUserController(MongoUserRepository repository, UserFavoriteRepository favoriteRepository) {
        this.repository = repository;
        this.favoriteRepository = favoriteRepository;
    }
    @GetMapping
    private ResponseEntity<List<MongoUser>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }
    @GetMapping("/{requestedId}")
    private ResponseEntity<MongoUser> getMongoUser(@PathVariable String requestedId)
    {
        Optional<MongoUser> result = repository.findById(requestedId);
        if(result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    private ResponseEntity<Void> createMongoUser(@RequestBody MongoUser newMongoUserReq, UriComponentsBuilder ucb, Principal principal) {
        System.out.println(newMongoUserReq);
        MongoUser savedMongoUser = repository.save(newMongoUserReq);
        URI location =  ucb.path("/blogposts/{id}")
                .buildAndExpand(savedMongoUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{requestedId}")
    private ResponseEntity<Void> updateMongoUser(@PathVariable String requestedId, @RequestBody MongoUser MongoUserUpdate, Principal principal) {
        Optional<MongoUser> oldMongoUser = repository.findByIdAndUsername(requestedId, principal.getName());
        if(oldMongoUser.isPresent()) {
            MongoUser updatedMongoUser = new MongoUser(oldMongoUser.get().getId(), MongoUserUpdate.getUsername(), MongoUserUpdate.getPassword(), MongoUserUpdate.getRoles());
            repository.save(updatedMongoUser);
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{requestedId}")
    private ResponseEntity<Void> deleteMongoUser(@PathVariable String requestedId, Principal principal) {
        if(!repository.findByIdAndUsername(requestedId, principal.getName()).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(requestedId);
        return ResponseEntity.noContent().build();
    }

}