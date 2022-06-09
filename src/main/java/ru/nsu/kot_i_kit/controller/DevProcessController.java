package ru.nsu.kot_i_kit.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.kot_i_kit.entity.DevProcess;
import ru.nsu.kot_i_kit.service.DevProcessService;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/dev-process")
public class DevProcessController {
    private DevProcessService devProcessService;


    @GetMapping("/{id}")
    public ResponseEntity<?> getDevProcessById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(devProcessService.getById(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("DevProcess not found");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<DevProcess>> getAllDevProcesses() {
        return ResponseEntity.ok(devProcessService.getAll());
    }

    @PostMapping("/create")
    public ResponseEntity<String> addDevProcess(@RequestBody DevProcess devProcess) {
        try {
            devProcessService.create(devProcess);
            return ResponseEntity.ok().body("DevProcess added");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteById(@RequestBody DevProcess devProcess) {
        try {
            devProcessService.deleteById(devProcess.getId());
            return ResponseEntity.ok().body("DevProcess deleted");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("No DevProcess with this id");
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody DevProcess devProcess) {
        try {
            devProcessService.update(devProcess);
            return ResponseEntity.ok().body("DevProcess updated");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
