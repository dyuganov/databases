package ru.nsu.kot_i_kit.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.kot_i_kit.model.CreateOrderRequest;
import ru.nsu.kot_i_kit.model.OrderModel;
import ru.nsu.kot_i_kit.model.UpdateStatusRequest;
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
        return ResponseEntity.ok(orderService.getAllUsersOrders());
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<?> getAllOrdersByUserID(@PathVariable Long id){
        var orders = orderService.getAllOrdersByUserId(id);
        if(orders == null){
            ResponseEntity.badRequest().body("User with id=" + id + " don't exist");
        }
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/ordered/{id}")
    public ResponseEntity<List<OrderModel>> getAllOrdersByUserIdOrderedByTime(@PathVariable Long id){
        return ResponseEntity.ok(orderService.getAllOrderedByCreationTime(id));
    }

    @GetMapping("/active/{id}")
    public ResponseEntity<List<OrderModel>> getActiveOrdersByUserId(@PathVariable Long id){
        return ResponseEntity.ok(orderService.getActiveOrdersByUserId(id));
    }

    @GetMapping("/finished/{id}")
    public ResponseEntity<List<OrderModel>> getFinishedOrdersByUserId(@PathVariable Long id){
        return ResponseEntity.ok(orderService.getFinishedOrdersByUserId(id));
    }

    @PostMapping("/create")
    public ResponseEntity<String> createNewOrder(@RequestBody CreateOrderRequest createOrderRequest){
        return orderService.addNewOrder(createOrderRequest);
    }

    @PutMapping("/edit")
    public ResponseEntity<String> updateOrderStatus(@RequestBody UpdateStatusRequest updateStatusRequest){
        return orderService.updateOrderStatus(updateStatusRequest);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id){
        return orderService.deleteOrderById(id);
    }

}
