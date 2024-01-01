package mate.academy.bookshop.repository;

import mate.academy.bookshop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Category, Long> {

}
