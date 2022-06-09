package ru.nsu.kot_i_kit.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.kot_i_kit.entity.ServiceType;
import ru.nsu.kot_i_kit.service.ServiceTypeService;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/service-type")
public class ServiceTypeController {
    private ServiceTypeService serviceTypeService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getServiceTypeById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(serviceTypeService.getById(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Service not found");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ServiceType>> getAllServiceTypes() {
        return ResponseEntity.ok(serviceTypeService.getAll());
    }

    @PostMapping("/create")
    public ResponseEntity<String> addServiceType(@RequestBody ServiceType serviceType) {
        try {
            serviceTypeService.create(serviceType);
            return ResponseEntity.ok().body("ServiceType added");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody ServiceType serviceType) {
        try {
            serviceTypeService.deleteById(serviceType.getId());
            return ResponseEntity.ok().body("ServiceType deleted");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("No service with this id");
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody ServiceType serviceType) {
        try {
            serviceTypeService.update(serviceType);
            return ResponseEntity.ok().body("ServiceType updated");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
