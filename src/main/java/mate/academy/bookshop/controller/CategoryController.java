package mate.academy.bookshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.bookshop.dto.book.BookDto;
import mate.academy.bookshop.dto.category.CategoryResponseDto;
import mate.academy.bookshop.dto.category.CreateCategoryRequestDto;
import mate.academy.bookshop.service.BookService;
import mate.academy.bookshop.service.CategoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Categories", description = "Operations related to category")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Create category",
            description = "Only the administrator can create a category")
    public CategoryResponseDto createCategory(@RequestBody @Valid CreateCategoryRequestDto requestDto) {
        return categoryService.createCategory(requestDto);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    @Operation(summary = "Get categories",
            description = "The administrator and user can get "
                    + "a list of categories by the following parameters")
    public List<CategoryResponseDto> getAllCategories(Pageable pageable) {
        return categoryService.getAll(pageable);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    @Operation(summary = "Get category by ID",
            description = "The administrator and user can find a category by id")
    public CategoryResponseDto getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary ="Update category",
            description = "Only the administrator can change the category")
    public CategoryResponseDto updateCategory(@PathVariable Long id, @RequestBody CreateCategoryRequestDto requestDto) {
        return categoryService.updateCategory(id, requestDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a category",
            description = "Only the administrator can delete a category by ID")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
    }

    @GetMapping("/{id}/books")
    public List<BookDto> getBooksByCategoryId(@PathVariable Long id) {
        return bookService.getBooksByCategoryId(id);
    }
}
