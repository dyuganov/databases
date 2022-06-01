package ru.nsu.kot_i_kit.service;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.nsu.kot_i_kit.entity.Order;
import ru.nsu.kot_i_kit.model.CreateOrderRequest;
import ru.nsu.kot_i_kit.model.OrderModel;
import ru.nsu.kot_i_kit.repository.FilmRepo;
import ru.nsu.kot_i_kit.repository.OrderRepo;

import java.util.ArrayList;
import java.util.List;


// TODO: test
@AllArgsConstructor
@Service
public class OrderService {
    private OrderRepo orderRepo;
    private FilmRepo filmRepo;

    public ResponseEntity<?> getAllOrdersByUserId(@NotNull Long id){
        if(!orderRepo.existsByUserId(id)){
            return ResponseEntity.badRequest().body("User with id=" + id + " don't exist");
        }
        List<OrderModel> models = new ArrayList<>();
        List<Order> orders = orderRepo.getAllByUserId(id);
        orders.forEach((order) -> {
            models.add(OrderModel.toModel(order, filmRepo.getAllByOrderId(order.getId())));
        });
        return ResponseEntity.ok(models);
    }

    public ResponseEntity<?> addNewOrder(@NotNull CreateOrderRequest orderRequest){
        try{
            orderRepo.save(Order.toOrder(orderRequest)); // TODO: id generation is correct?
            filmRepo.saveAll(orderRequest.getFilms()); // TODO: id generation is correct?
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Repository saving order error");
        }
        return ResponseEntity.ok().body("New order added");
    }

    public ResponseEntity<?> getAllOrderedByCreationTime(@NotNull Long id){
        List<OrderModel> models = new ArrayList<>();
        List<Order> orders = orderRepo.getAllByUserIdOrderByCreationTime(id);
        orders.forEach((order) -> {
            models.add(OrderModel.toModel(order, filmRepo.getAllByOrderId(order.getId())));
        });
        return ResponseEntity.ok(models);
    }

    public ResponseEntity<?> getActiveOrdersByUserId(@NotNull Long id){
        List<OrderModel> models = new ArrayList<>();
        List<Order> orders = orderRepo.getAllActiveByUserId(id);
        orders.forEach((order) -> {
            models.add(OrderModel.toModel(order, filmRepo.getAllByOrderId(order.getId())));
        });
        return ResponseEntity.ok(models);
    }

    public ResponseEntity<List<OrderModel>> getAllUsersOrders(){
        List<OrderModel> models = new ArrayList<>();
        List<Order> orders = orderRepo.findAll();
        orders.forEach((order) -> {
            models.add(OrderModel.toModel(order, filmRepo.getAllByOrderId(order.getId())));
        });
        System.out.println(models.size());
        return ResponseEntity.ok(models);
    }
}
