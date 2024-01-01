package mate.academy.bookshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.bookshop.dto.category.CategoryResponseDto;
import mate.academy.bookshop.dto.category.CreateCategoryRequestDto;
import mate.academy.bookshop.service.CategoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Categories", description = "Operations related to category")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create category",
            description = "Only the administrator can create a category")
    public CategoryResponseDto createCategory(@RequestBody @Valid CreateCategoryRequestDto requestDto) {
        return categoryService.createCategory(requestDto);
    }

    @GetMapping
    @Operation(summary = "Get categories",
            description = "The administrator and user can get "
                    + "a list of categories by the following parameters")
    public List<CategoryResponseDto> getAllCategories(Pageable pageable) {
        return categoryService.getAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get category by ID")
    public CategoryResponseDto getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto updateCategory(@PathVariable Long id, @RequestBody CreateCategoryRequestDto requestDto) {
        return categoryService.updateCategory(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a category",
            description = "Only the administrator can delete a category by ID")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
    }


}
