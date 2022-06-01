package ru.nsu.kot_i_kit.service;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.nsu.kot_i_kit.entity.Order;
import ru.nsu.kot_i_kit.model.CreateOrderRequest;
import ru.nsu.kot_i_kit.model.OrderModel;
import ru.nsu.kot_i_kit.model.UpdateStatusRequest;
import ru.nsu.kot_i_kit.repository.FilmRepo;
import ru.nsu.kot_i_kit.repository.OrderRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


// TODO: test
@AllArgsConstructor
@Service
public class OrderService {
    private OrderRepo orderRepo;
    private FilmRepo filmRepo;

    public List<OrderModel> getAllOrdersByUserId(@NotNull Long id){
        if(!orderRepo.existsByUserId(id)){
            return null;
        }
        List<OrderModel> models = new ArrayList<>();
        List<Order> orders = orderRepo.getAllByUserId(id);
        orders.forEach((order) -> {
            models.add(OrderModel.toModel(order, filmRepo.getAllByOrderId(order.getId())));
        });
        return models;
    }

    public ResponseEntity<String> addNewOrder(@NotNull CreateOrderRequest orderRequest){
        try{
            var savedOrder = orderRepo.save(Order.toOrder(orderRequest));
            var filmList = orderRequest.getFilms().stream().peek(film -> film.setOrder(savedOrder)).collect(Collectors.toList());
            filmRepo.saveAll(filmList);
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Repository saving order error");
        }
        return ResponseEntity.ok().body("New order added");
    }

    public List<OrderModel> getAllOrderedByCreationTime(@NotNull Long id){
        List<OrderModel> models = new ArrayList<>();
        List<Order> orders = orderRepo.getAllByUserIdOrderByCreationTime(id);
        orders.forEach((order) -> {
            models.add(OrderModel.toModel(order, filmRepo.getAllByOrderId(order.getId())));
        });
        return models;
    }

    public List<OrderModel> getActiveOrdersByUserId(@NotNull Long id){
        List<OrderModel> models = new ArrayList<>();
        List<Order> orders = orderRepo.getAllActiveByUserId(id);
        orders.forEach((order) -> {
            models.add(OrderModel.toModel(order, filmRepo.getAllByOrderId(order.getId())));
        });
        return models;
    }

    public List<OrderModel> getAllUsersOrders(){
        List<OrderModel> models = new ArrayList<>();
        List<Order> orders = orderRepo.findAll();
        orders.forEach((order) -> {
            models.add(OrderModel.toModel(order, filmRepo.getAllByOrderId(order.getId())));
        });
        System.out.println(models.size());
        return models;
    }

    public ResponseEntity<String> updateOrderStatus(UpdateStatusRequest updateStatusRequest){
        return ResponseEntity.ok().body("Updated");
    }
}
