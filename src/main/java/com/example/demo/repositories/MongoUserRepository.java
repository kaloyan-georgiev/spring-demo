package com.example.demo.repositories;

import com.example.demo.models.MongoUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "blogposts", path = "blogposts")
public interface MongoUserRepository extends MongoRepository<MongoUser, String> {

//   List<Person> findByLastName(@Param("name") String name);

}