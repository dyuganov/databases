package ru.nsu.kot_i_kit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.kot_i_kit.model.Price;

public interface PriceRepo extends JpaRepository<Price, Long> {
}
