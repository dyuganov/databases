package ru.nsu.kot_i_kit.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.kot_i_kit.service.UserService;

@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @GetMapping("/list")
    public ResponseEntity<?> getUserList() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }


    /*@PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody String username){


        return ResponseEntity.ok().body("kok");
    }*/
}
