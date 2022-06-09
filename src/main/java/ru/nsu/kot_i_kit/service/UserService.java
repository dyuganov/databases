package ru.nsu.kot_i_kit.service;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.nsu.kot_i_kit.entity.User;
import ru.nsu.kot_i_kit.model.CreateUserRequest;
import ru.nsu.kot_i_kit.repository.UserRepo;

import java.awt.print.Pageable;
import java.util.List;

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

    public void create(@NotNull CreateUserRequest createUserRequest) {
        var user = CreateUserRequest.toUser(createUserRequest);
        user.setId((long) 0);
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

    public List<User> getAllPaginated(int page, int pageSize){
        PageRequest paging = PageRequest.of(page, pageSize);
        Page<User> pageResult = userRepo.findAll(paging);
        return pageResult.toList();
    }

}
