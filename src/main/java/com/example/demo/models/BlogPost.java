package com.example.demo.models;

import com.example.demo.models.Tag;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.annotation.Id;

import java.util.List;

public record BlogPost(@Id String id, String ownerId, String title, String content, List<String> tags) {}
//public class BlogPost {
//    @Id
//    private final String id;
//    private String ownerId;
//    private String title;
//    private String content;
//    private List<Tag> tags;
//    public BlogPost(String id, String ownerId, String title, String content, List<Tag> tags) {
//        this.id = id;
//        this.ownerId = ownerId;
//        this.title = title;
//        this.content = content;
//        this.tags = tags;
//    }
//
//    public String id() {
//        return id;
//    }
//
//    public String ownerId() {
//        return ownerId;
//    }
//
//    public void setOwnerId(String ownerId) {
//        this.ownerId = ownerId;
//    }
//
//    public String title() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String content() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public List<Tag> tags() {
//        return tags;
//    }
//
//    public void setTags(List<Tag> tags) {
//        this.tags = tags;
//    }
//}