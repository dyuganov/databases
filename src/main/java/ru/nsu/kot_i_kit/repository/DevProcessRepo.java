package ru.nsu.kot_i_kit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.nsu.kot_i_kit.model.DevProcess;

public interface DevProcessRepo extends JpaRepository<DevProcess, Long> {
}
