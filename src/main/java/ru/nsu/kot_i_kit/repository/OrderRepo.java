package ru.nsu.kot_i_kit.repository;

import com.sun.istack.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.nsu.kot_i_kit.entity.Order;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> getAllByUserId(@NotNull Long id);

    List<Order> getAllByUserIdOrderByCreationTime(@NotNull Long id);

    @Query(nativeQuery = true, value = "select * from orders where orders.id in (select order_id from films where (films.dev_status_id != 5 and user_id = :user_id));")
    List<Order> getAllActiveByUserId(@NotNull @Param("user_id") Long id);


    @Query(nativeQuery = true, value = "select distinct orders.id, user_id, creation_time\n" +
            "from orders inner join films f on orders.id = f.order_id\n" +
            "where orders.id in (select order_id from films where films.dev_status_id = 5 and user_id = :user_id);")
    List<Order> getAllFinishedByUserId(@NotNull @Param("user_id") Long id);

    boolean existsByUserId(@NotNull Long id);

    void removeById(@NotNull Long id);
}
