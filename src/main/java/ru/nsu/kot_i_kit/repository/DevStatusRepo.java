package ru.nsu.kot_i_kit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.kot_i_kit.entity.DevStatus;

public interface DevStatusRepo extends JpaRepository<DevStatus, Long> {

}
