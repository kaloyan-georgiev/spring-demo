package com.example.demo;

import java.util.Optional;
import org.springframework.security.core.userdetails.User.
        UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.
        UserDetailsService;
import org.springframework.security.core.userdetails.
        UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.demo.models.MongoUser;
import com.example.demo.repositories.MongoUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MongoUserRepository repository;
    public UserDetailsServiceImpl(MongoUserRepository repository) {
        this.repository = repository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws
            UsernameNotFoundException {
        Optional<MongoUser> user = repository.findByUsername(username);
        UserBuilder builder = null;
        if (user.isPresent()) {
            MongoUser currentUser = user.get();
            builder = org.springframework.security.core.userdetails.
                    User.withUsername(username)
                    .password(currentUser.getPassword())
                    .authorities(currentUser.getAuthorities());
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
        return builder.build();
    }
}