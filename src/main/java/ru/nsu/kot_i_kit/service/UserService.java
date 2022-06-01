package ru.nsu.kot_i_kit.service;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.nsu.kot_i_kit.repository.UserRepo;

@AllArgsConstructor
@Service
public class UserService {
    private UserRepo userRepo;

    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userRepo.findAll());
    }

    public ResponseEntity<?> getById(@NotNull Long id){
        var user = userRepo.findById(id);
        if(user.isPresent()){
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.badRequest().body("User not found");
    }
}
