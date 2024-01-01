package mate.academy.bookshop.service;

import mate.academy.bookshop.dto.category.CategoryResponseDto;
import mate.academy.bookshop.dto.category.CreateCategoryRequestDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    CategoryResponseDto getCategoryById(Long id);

    CategoryResponseDto createCategory(CreateCategoryRequestDto categoryDto);

    CategoryResponseDto updateCategory(Long id, CreateCategoryRequestDto categoryDto);

    void deleteCategoryById(Long id);

    List<CategoryResponseDto> getAll(Pageable pageable);
}
