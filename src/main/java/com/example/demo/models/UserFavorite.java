package com.example.demo.models;

import org.bson.types.ObjectId;

public record UserFavorite(String id, String userId, String postId) {
}
