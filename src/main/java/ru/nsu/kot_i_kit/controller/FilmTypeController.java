package ru.nsu.kot_i_kit.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.kot_i_kit.entity.FilmType;
import ru.nsu.kot_i_kit.model.FilmTypeUsage;
import ru.nsu.kot_i_kit.service.FilmTypeService;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/film-type")
public class FilmTypeController {
    private FilmTypeService filmTypeService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getFilmTypeByID(@PathVariable Long id){
        try{
            return ResponseEntity.ok(filmTypeService.getById(id));
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("Film Type not found");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<FilmType>> getAll(){
        return ResponseEntity.ok(filmTypeService.getAll());
    }

    @PostMapping("/create")
    public ResponseEntity<String> addFilmType(@RequestBody FilmType filmType){
        try{
            filmTypeService.create(filmType);
            return ResponseEntity.ok().body("Done");
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteById(@RequestParam Long id){
        try{
            filmTypeService.deleteById(id);
            return ResponseEntity.ok().body("FilmType deleted");
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody FilmType filmType){
        try{
            filmTypeService.update(filmType);
            return ResponseEntity.ok().body("FilmType updated");
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/usage/all")
    public ResponseEntity<List<FilmTypeUsage>> getAllFilmTypeUsages() {
        return ResponseEntity.ok(filmTypeService.getAllFilmTypeUsages());
    }

    @GetMapping("/by-iso/{iso}")
    public ResponseEntity<Integer> countWithIso(@PathVariable Integer iso){
        return ResponseEntity.ok(filmTypeService.countWithIso(iso));
    }
}
