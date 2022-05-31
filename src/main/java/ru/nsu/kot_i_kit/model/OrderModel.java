package ru.nsu.kot_i_kit.model;

import lombok.Getter;
import lombok.Setter;
import ru.nsu.kot_i_kit.entity.Film;
import ru.nsu.kot_i_kit.entity.Order;
import ru.nsu.kot_i_kit.entity.User;

import java.time.OffsetDateTime;
import java.util.List;

public class OrderModel {
    @Getter
    @Setter
    private Long orderId;

    @Getter
    @Setter
    private OffsetDateTime creationTime;

    @Getter
    @Setter
    private User user;

    @Getter
    @Setter
    private List<Film> films;

    public OrderModel(){}

    public static OrderModel toModel(Order order, List<Film> orderFilms){
        OrderModel model = new OrderModel();
        model.setOrderId(order.getId());
        model.setCreationTime(order.getCreationTime());
        model.setUser(order.getUser());
        model.setFilms(orderFilms);
        return model;
    }
}
