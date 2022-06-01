package ru.nsu.kot_i_kit.model;

import lombok.Getter;
import lombok.Setter;
import ru.nsu.kot_i_kit.entity.Film;
import ru.nsu.kot_i_kit.entity.User;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class CreateOrderRequest {
    private OffsetDateTime creationTime;
    private User user;
    private List<Film> films;

    public CreateOrderRequest(){}

}
