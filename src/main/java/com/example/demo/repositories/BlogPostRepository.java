package com.example.demo.repositories;

import com.example.demo.models.BlogPost;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.security.Principal;

@RepositoryRestResource(collectionResourceRel = "blogposts", path = "blogposts")
public interface BlogPostRepository extends MongoRepository<BlogPost, String> {

//   List<Person> findByLastName(@Param("name") String name);
    BlogPost findByIdAndOwnerId(String id, String owner);
}