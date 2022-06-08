package ru.nsu.kot_i_kit.repository;

import com.sun.istack.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.kot_i_kit.entity.Film;

import java.util.List;

public interface FilmRepo extends JpaRepository<Film, Long> {
    List<Film> getAllByOrderId(@NotNull Long orderId);

    void removeByOrderId(@NotNull Long id);

}
