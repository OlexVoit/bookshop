package mate.academy.bookshop.repository;

import java.util.List;
import java.util.Optional;
import mate.academy.bookshop.model.OrderItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Query("SELECT oi FROM OrderItem oi JOIN FETCH oi.order "
            + "JOIN FETCH oi.book WHERE oi.order.id = :id")
    List<OrderItem> findAllById(Pageable pageable, Long id);

    @Query("SELECT oi FROM OrderItem oi JOIN FETCH oi.order "
            + "JOIN FETCH oi.book WHERE oi.id = :itemId AND oi.order.id = :id")
    Optional<OrderItem> findItemByIdForUserById(Long id, Long itemId);
}
