package com.example.demo.controllers;

import com.example.demo.models.UserFavorite;
import com.example.demo.repositories.UserFavoriteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(("/favorites"))
public class FavoritesController {
    private final UserFavoriteRepository repository;
    public FavoritesController(UserFavoriteRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    private ResponseEntity<Void> createUserFavorite(@RequestBody UserFavorite favReq, UriComponentsBuilder ucb, Principal principal) {
        UserFavorite userFavorite = repository.save(favReq);
        URI location =  ucb.path("/blogposts/favorites/{id}")
                .buildAndExpand(userFavorite.id())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @GetMapping("/{userId}")
    private ResponseEntity<List<UserFavorite>> getUserFavorites(@PathVariable String userId) {
        return ResponseEntity.ok(repository.findByUserId(userId));
    }
}
