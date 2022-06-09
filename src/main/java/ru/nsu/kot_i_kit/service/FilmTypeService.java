package ru.nsu.kot_i_kit.service;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.kot_i_kit.entity.FilmType;
import ru.nsu.kot_i_kit.model.FilmTypeUsage;
import ru.nsu.kot_i_kit.repository.FilmTypeRepo;

import java.util.List;

@AllArgsConstructor
@Service
public class FilmTypeService {

    private FilmTypeRepo filmTypeRepo;

    public FilmType getById(Long id) {
        return filmTypeRepo.getById(id);
    }

    public List<FilmType> getAll() {
        return filmTypeRepo.findAll();
    }

    public boolean existsById(@NotNull Long id) {
        return filmTypeRepo.existsById(id);
    }

    @Transactional
    public void create(FilmType filmType) {
        filmType.setId((long) 0);
        filmTypeRepo.save(filmType);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!filmTypeRepo.existsById(id)) {
            throw new IllegalArgumentException("FilmType with this id does not exist");
        }
        filmTypeRepo.deleteById(id);
    }

    @Transactional
    public void update(@NotNull FilmType filmType) {
        if (!filmTypeRepo.existsById(filmType.getId())) {
            throw new IllegalArgumentException("FilmType with this ID not found");
        }
        filmTypeRepo.save(filmType);
    }

    @Transactional
    public List<FilmTypeUsage> getAllFilmTypeUsages(){
        return filmTypeRepo.getAllUsages();
    }

    public Integer countWithIso(Integer iso){
        iso = filmTypeRepo.countWithIso(iso);
        return iso;
    }

    public List<FilmType> getMonochrome(){
        return filmTypeRepo.
    }
}
