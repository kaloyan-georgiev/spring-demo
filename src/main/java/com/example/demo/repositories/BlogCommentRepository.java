package com.example.demo.repositories;

import com.example.demo.models.BlogComment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "comments", path = "comments")
public interface BlogCommentRepository extends MongoRepository<BlogComment, String> {

//   List<Person> findByLastName(@Param("name") String name);
    BlogComment findByIdAndOwnerId(String id, String owner);
    BlogComment findByIdAndPostId(String id, String postId);
    List<BlogComment> findByPostId(String postId);

}