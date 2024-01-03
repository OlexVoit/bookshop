package mate.academy.bookshop.repository;

import java.util.Optional;
import mate.academy.bookshop.model.ShoppingCart;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    Optional<ShoppingCart> findByUser_Id(Long userId);

    @EntityGraph(attributePaths = "cartItems.book")
    @Query("SELECT sc FROM ShoppingCart sc JOIN FETCH sc.cartItems ci "
            + "JOIN FETCH ci.book WHERE sc.user.id = :userId AND sc.isDeleted = false")
    Optional<ShoppingCart> findByUserId(@Param("userId") Long userId);

    void deleteById(Long id);
}
