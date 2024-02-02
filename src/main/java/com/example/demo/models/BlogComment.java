package com.example.demo.models;

public record BlogComment(String id, String ownerId, String postId, String content) {
}
