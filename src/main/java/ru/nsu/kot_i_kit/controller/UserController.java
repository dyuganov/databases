package ru.nsu.kot_i_kit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.kot_i_kit.model.User;
import ru.nsu.kot_i_kit.repository.UserRepo;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/list")
    public ResponseEntity<List<User>> getUserList() {
        return ResponseEntity.ok(userRepo.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody String username){
        //User newUser = new User(0, username, "ass word", n, new UserType());

        return ResponseEntity.ok().body("kok");
    }
}
