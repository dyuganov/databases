package ru.nsu.kot_i_kit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.kot_i_kit.entity.FilmType;

public interface FilmTypeRepo extends JpaRepository<FilmType, Long> {

}
