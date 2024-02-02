package com.example.demo.repositories;

import com.example.demo.models.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "tags", path = "tags")
public interface TagRepository extends MongoRepository<Tag, String> {

//   List<Person> findByLastName(@Param("name") String name);
//    Tag findById(String id);
}