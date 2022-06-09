package ru.nsu.kot_i_kit.service;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.kot_i_kit.entity.Film;
import ru.nsu.kot_i_kit.entity.Order;
import ru.nsu.kot_i_kit.model.CreateOrderRequest;
import ru.nsu.kot_i_kit.model.OrderModel;
import ru.nsu.kot_i_kit.model.UpdateStatusRequest;
import ru.nsu.kot_i_kit.repository.DevStatusRepo;
import ru.nsu.kot_i_kit.repository.FilmRepo;
import ru.nsu.kot_i_kit.repository.OrderRepo;

import javax.persistence.EntityNotFoundException;
import java.sql.Savepoint;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

@AllArgsConstructor
@Service
public class OrderService {
    private OrderRepo orderRepo;
    private FilmRepo filmRepo;
    private DevStatusRepo devStatusRepo;

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

    @Transactional
    public ResponseEntity<String> addNewOrder(@NotNull CreateOrderRequest orderRequest){
        try{
            var savedOrder = orderRepo.save(Order.toOrder(orderRequest));
            savedOrder.setCreationTime(OffsetDateTime.now());
            var filmList = orderRequest.getFilms().stream().peek(film -> film.setOrder(savedOrder)).collect(Collectors.toList());
            filmRepo.saveAll(filmList);
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
            throw new RuntimeException("Repository saving order error");
            //return ResponseEntity.badRequest().body("Repository saving order error");
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

    public List<OrderModel> getAllUsersOrders(){
        List<OrderModel> models = new ArrayList<>();
        List<Order> orders = orderRepo.findAll();
        orders.forEach((order) -> {
            models.add(OrderModel.toModel(order, filmRepo.getAllByOrderId(order.getId())));
        });
        System.out.println(models.size());
        return models;
    }

    @Transactional
    public ResponseEntity<String> updateOrderStatus(UpdateStatusRequest updateStatusRequest){
        Film film;
        try{
            film = filmRepo.getById(updateStatusRequest.getFilmId());
        }
        catch (EntityNotFoundException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Film with this id not found");
        }
        film.setDevStatus(devStatusRepo.getById(updateStatusRequest.getDevStatusId()));
        filmRepo.save(film);
        return ResponseEntity.ok().body("Film status updated");
    }

    public List<OrderModel> getActiveOrdersByUserId(@NotNull Long id){
        List<OrderModel> models = new ArrayList<>();
        List<Order> orders = orderRepo.getAllActiveByUserId(id);
        orders.forEach((order) -> {
            models.add(OrderModel.toModel(order, filmRepo.getAllByOrderId(order.getId())));
        });
        return models;
    }

    public List<OrderModel> getFinishedOrdersByUserId(@NotNull Long id){
        List<OrderModel> models = new ArrayList<>();
        List<Order> orders = orderRepo.getAllFinishedByUserId(id);
        orders.forEach((order) -> {
            models.add(OrderModel.toModel(order, filmRepo.getAllByOrderId(order.getId())));
        });
        return models;
    }

    public ResponseEntity<String> deleteOrderById(@NotNull Long id){
        if(orderRepo.existsById(id)){
            return ResponseEntity.badRequest().body("Order not found");
        }
        orderRepo.removeById(id);
        filmRepo.removeByOrderId(id);
        return ResponseEntity.ok().body("Order removed");
    }
}
