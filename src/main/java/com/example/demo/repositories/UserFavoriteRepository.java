package com.example.demo.repositories;

import com.example.demo.models.UserFavorite;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface UserFavoriteRepository extends MongoRepository<UserFavorite, String> {
    List<UserFavorite> findByUserId(String userId);
}
