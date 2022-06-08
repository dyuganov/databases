package ru.nsu.kot_i_kit.service;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.kot_i_kit.entity.User;
import ru.nsu.kot_i_kit.repository.UserRepo;

@AllArgsConstructor
@Service
public class UserService {
    private UserRepo userRepo;

    public java.util.List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getById(@NotNull Long id) {
        if (!userRepo.existsById(id)) {
            throw new IllegalArgumentException("User with this ID not found");
        }
        return userRepo.getById(id);
    }

    public void add(User user) {
        if (userRepo.existsById(user.getId())) {
            throw new IllegalArgumentException("User already exists");
        }
        userRepo.save(user);
    }

    public boolean existsById(@NotNull Long id) {
        return userRepo.existsById(id);
    }

    public void deleteById(@NotNull Long id) {
        userRepo.deleteById(id);
    }

    public void update(@NotNull User user) {
        if (!userRepo.existsById(user.getId())) {
            throw new IllegalArgumentException("User with this ID not found");
        }
        userRepo.save(user);
    }
}
