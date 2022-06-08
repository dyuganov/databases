package ru.nsu.kot_i_kit.model;

import lombok.Getter;
import lombok.Setter;
import ru.nsu.kot_i_kit.entity.Contact;
import ru.nsu.kot_i_kit.entity.UserType;

@Getter
@Setter
public class CreateUserRequest {
    private String login;
    private String password;
    private Contact contact;
    private UserType userType;

    public CreateUserRequest(){}
}
