package com.example.demo.controllers;

import com.example.demo.models.MongoUser;
import com.example.demo.repositories.MongoUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AdminController {
    private final MongoUserRepository userRepository;
    public AdminController(MongoUserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @GetMapping("/rolecheck")
    public ResponseEntity<Collection<String>> getRoles(Authentication principal) {
        Optional<MongoUser> user = userRepository.findByUsername(principal.getName());
        if(user.isPresent()) {
            return ResponseEntity.ok(principal.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList()));
        }
        return ResponseEntity.notFound().build();

    }
    @GetMapping("/admin")
    public ResponseEntity<String> getAdminPage() {
        System.out.println("ADMIN ACCESS\n");
        return ResponseEntity.ok("taina");
    }
}
