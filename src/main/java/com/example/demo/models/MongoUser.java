package com.example.demo.models;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.*;
//import org.springframework.security.core.userdetails.UserRole;

@Document
public class MongoUser {
    private @MongoId String id;
    private String username;
    private String password;
    private List<String> roles;


    private static Collection<GrantedAuthority> rolesToAuthorities(Collection<String> roles) {
        ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        }
        return authorities;
    }

    public MongoUser() {}
    public MongoUser(String id, String username, String password, List<String> roles) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
    public MongoUser(String id, String username, String password, String... roles) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = new ArrayList<String>();
        for(String role : roles) {
            this.roles.add(role);
        }
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    public Collection<GrantedAuthority> getAuthorities() {
        return rolesToAuthorities(roles);
    }
}
