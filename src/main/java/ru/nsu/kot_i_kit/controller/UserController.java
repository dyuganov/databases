package ru.nsu.kot_i_kit.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.kot_i_kit.entity.User;
import ru.nsu.kot_i_kit.model.CreateUserRequest;
import ru.nsu.kot_i_kit.service.UserService;

@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<?> getUserList() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/all/{page}")
    public ResponseEntity<?> getPaginatedUserList(@PathVariable Integer page) {
        return ResponseEntity.ok(userService.getAllPaginated(page, 2));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.getById(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("User not found");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> addUser(@RequestBody CreateUserRequest createUserRequest) {
        try {
            userService.create(createUserRequest);
            return ResponseEntity.ok().body("User added");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody User user) {
        try {
            userService.deleteById(user.getId());
            return ResponseEntity.ok().body("User deleted");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("User not found");
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody User user) {
        try {
            userService.update(user);
            return ResponseEntity.ok().body("User updated");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
