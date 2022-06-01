package ru.nsu.kot_i_kit.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.kot_i_kit.model.CreateOrderRequest;
import ru.nsu.kot_i_kit.model.OrderModel;
import ru.nsu.kot_i_kit.service.OrderService;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/order")
public class OrderController {
    private OrderService orderService;


    @GetMapping("/all")
    public ResponseEntity<List<OrderModel>> getAllOrders(){
        return orderService.getAllUsersOrders();
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<?> getAllOrdersByUserID(@PathVariable Long id){
        return orderService.getAllOrdersByUserId(id);
    }

    @GetMapping("/ordered/{id}")
    public ResponseEntity<?> getAllOrdersByUserIdOrderedByTime(@PathVariable Long id){
        return orderService.getAllOrderedByCreationTime(id);
    }

    @GetMapping("/active/{id}")
    public ResponseEntity<?> getActiveOrdersByUserId(@PathVariable Long id){
        return orderService.getActiveOrdersByUserId(id);
    }

    @PostMapping("/")
    public ResponseEntity<?> createNewOrder(@RequestBody CreateOrderRequest createOrderRequest){
        return  orderService.addNewOrder(createOrderRequest);
    }


}
