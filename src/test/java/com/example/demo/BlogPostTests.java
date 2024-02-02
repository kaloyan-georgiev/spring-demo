package com.example.demo;

import com.example.demo.models.BlogPost;
import com.example.demo.models.Tag;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.annotation.DirtiesContext;

import java.net.URI;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BlogPostTests {
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnABlogPostList() {
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("user", "password")
                .getForEntity("/blogposts", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

//    @Test
//    void shouldCreateABlogPost() {
//        BlogPost newBlogPost = new BlogPost(null, null,  "test blogpost", "test blogpost", null);
//        ResponseEntity<Void> createResponse = restTemplate
//                .withBasicAuth("user", "password")
//                .postForEntity("/blogposts", newBlogPost, Void.class);
//        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//
//        URI locationOfNewBlogPost = createResponse.getHeaders().getLocation();
//        ResponseEntity<String> getResponse = restTemplate
//                .withBasicAuth("user", "password")
//                .getForEntity(locationOfNewBlogPost, String.class);
//        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
//    }

//    @Test
//    @DirtiesContext
//    void shouldUpdateAnExistingBlogPost() {
//        String newTitle = "updated";
//        String newContents = "updated contents";
//        ArrayList<Tag> newTags = new ArrayList<Tag>();
//
//        BlogPost BlogPostUpdate = new BlogPost(null, null, newTitle, newContents, newTags);
//        HttpEntity<BlogPost> request = new HttpEntity<>(BlogPostUpdate);
//        ResponseEntity<Void> response = restTemplate
//                .withBasicAuth("user", "password")
//                .exchange("/BlogPosts/99", HttpMethod.PUT, request, Void.class);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
//
//        ResponseEntity<String> getResponse = restTemplate
//                .withBasicAuth("user", "password")
//                .getForEntity("/blogposts/65b13d3c078d4005358f6cb6", String.class);
//        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
//        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
//        String title = documentContext.read("$.title");
//        String content = documentContext.read("$.content");
//        assertThat(title).isEqualTo(newTitle);
//        assertThat(content).isEqualTo(newContents);
//    }
}

