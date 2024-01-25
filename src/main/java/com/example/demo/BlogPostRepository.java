package com.example.demo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "blogposts", path = "blogposts")
public interface BlogPostRepository extends MongoRepository<BlogPost, String> {

//   List<Person> findByLastName(@Param("name") String name);

}