package com.example.demo;
import com.example.demo.models.MongoUser;
import com.example.demo.repositories.MongoUserRepository;
import org.bson.types.ObjectId;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.CommandLineRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
    private MongoUserRepository userRepository;

    public DemoApplication(MongoUserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        userRepository.save(new MongoUser(null,
//                "user",
//                "$2a$10$uM.xGPAvW7AgQs5Z.SdafOJTxIOO/qmmNiWnQAZp/sBD1ksV7Guhq",
//                "user"));
        Optional<MongoUser> user = userRepository.findByUsername("kalo");
        System.out.println("USER KALO AUTHORITIES:\n");
        if(user.isPresent()) System.out.println(user.get().getAuthorities());
    }
}