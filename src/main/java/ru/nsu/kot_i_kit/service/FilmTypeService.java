package ru.nsu.kot_i_kit.service;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.kot_i_kit.entity.FilmType;
import ru.nsu.kot_i_kit.repository.FilmTypeRepo;

import java.util.List;

@AllArgsConstructor
@Service
public class FilmTypeService {

    private FilmTypeRepo filmTypeRepo;

    public FilmType getById(Long id) {
        return filmTypeRepo.getById(id);
    }

    public List<FilmType> getAll(){
        return filmTypeRepo.findAll();
    }

    public boolean existsById(@NotNull Long id){
        return filmTypeRepo.existsById(id);
    }

    public void add(FilmType filmType){
        if(filmTypeRepo.existsById(filmType.getId())){
           throw new IllegalArgumentException("FilmType already exists") ;
        }
        filmTypeRepo.save(filmType);
    }

    public void deleteById(Long id) {
        filmTypeRepo.deleteById(id);
    }

    public void update(@NotNull FilmType filmType){
        if(!existsById(filmType.getId())){
            throw new IllegalArgumentException("FilmType with this ID not found");
        }
        filmTypeRepo.save(filmType);
    }
}
