package ru.nsu.kot_i_kit.repository;

import com.sun.istack.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nsu.kot_i_kit.entity.Order;
import ru.nsu.kot_i_kit.model.OrderModel;

import java.util.List;
import java.util.Optional;

public interface OrderRepo extends JpaRepository<Order, Long> {
    Optional<Order> findByUserId(@NotNull Long id);

    List<Order> getAllByUserId(@NotNull Long id);

    List<Order> getAllByUserIdOrderByCreationTime(@NotNull Long id);

    @Query(nativeQuery = true, value = "select orders.id, user_id, creation_time from orders left join films f on orders.id = f.order_id where dev_status_id != 5")
    List<Order> getAllNotFinishedByUserId(@NotNull Long id);
}
