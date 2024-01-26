package mate.academy.bookshop.repository;

import java.util.List;
import mate.academy.bookshop.model.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o JOIN FETCH o.orderItems is WHERE o.user.id = :id")
    List<Order> findAllById(Long id, Pageable pageable);
}
