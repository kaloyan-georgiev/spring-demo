package com.example.demo;

import java.util.UUID;
import org.springframework.data.annotation.Id;

record BlogPost(String id, String title, String content) {
}