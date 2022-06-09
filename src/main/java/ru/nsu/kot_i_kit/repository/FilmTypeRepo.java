package ru.nsu.kot_i_kit.repository;

import com.sun.istack.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import ru.nsu.kot_i_kit.entity.FilmType;
import ru.nsu.kot_i_kit.model.FilmTypeUsage;

import java.util.List;

public interface FilmTypeRepo extends JpaRepository<FilmType, Long> {

    @Query(nativeQuery = true,
            value = "select film_types.name, film_types.iso as iso, film_usage.usage as usage " +
                    "from film_types cross join film_usage where film_types.iso >= film_usage.min_iso")
    List<FilmTypeUsage> getAllUsages();

    /*@Procedure("COUNT_WITH_ISO")*/
    @Query(nativeQuery = true, value = "call COUNT_WITH_ISO(:val);")
    Integer countWithIso(@NotNull Integer val);

    @Query(nativeQuery = true, value = "select name, is_monochrome from film_types group by name, is_monochrome having is_monochrome = true;")
    List<FilmType> getMonochrome();
}
