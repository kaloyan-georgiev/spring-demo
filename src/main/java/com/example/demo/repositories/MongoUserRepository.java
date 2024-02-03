package com.example.demo.repositories;

import com.example.demo.models.MongoUser;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "blogposts", path = "blogposts")
public interface MongoUserRepository extends MongoRepository<MongoUser, String> {

//   List<Person> findByLastName(@Param("name") String name);
    Optional<MongoUser> findByUsername(String username);
    Optional<MongoUser> findByIdAndUsername(String id, String username);
}