package ru.nsu.kot_i_kit.entity;

import ru.nsu.kot_i_kit.model.CreateOrderRequest;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "creation_time", nullable = false)
    private OffsetDateTime creationTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OffsetDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(OffsetDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public static Order toOrder(CreateOrderRequest orderModel){
        Order result = new Order();
        result.setCreationTime(orderModel.getCreationTime());
        result.setUser(orderModel.getUser());
        return result;
    }

}