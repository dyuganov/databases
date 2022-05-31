package ru.nsu.kot_i_kit.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.nsu.kot_i_kit.repository.UserRepo;

@AllArgsConstructor
@Service
public class UserService {
    private UserRepo userRepo;

    public ResponseEntity<?> getUsers(){
        return ResponseEntity.ok(userRepo.findAll());
    }



}
