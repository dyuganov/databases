package ru.nsu.kot_i_kit.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.kot_i_kit.entity.Order;
import ru.nsu.kot_i_kit.repository.OrderRepo;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderRepo orderRepo;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable("id") Long id){
        Optional<Order> order = orderRepo.findById(id);
        if(!order.isPresent()){
            return ResponseEntity.badRequest().body("Not found");
        }
        return ResponseEntity.ok(order.get());
    }


}
