package ru.nsu.kot_i_kit.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.kot_i_kit.entity.User;
import ru.nsu.kot_i_kit.repository.UserRepo;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private UserRepo userRepo;

    @GetMapping("/list")
    public ResponseEntity<List<User>> getUserList() {
        return ResponseEntity.ok(userRepo.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody String username){


        return ResponseEntity.ok().body("kok");
    }
}
