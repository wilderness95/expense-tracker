package hu.wilderness.expensetracker.controller;

import hu.wilderness.expensetracker.dto.CategoryDto;
import hu.wilderness.expensetracker.dto.mapper.CategoryMapper;
import hu.wilderness.expensetracker.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        CategoryDto category = categoryService.getCategoryById(id);
        return ResponseEntity.ok().body(category);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getCategoryByName(@PathVariable String name) {
        CategoryDto category = categoryService.getCategoryByName(name);
        return ResponseEntity.ok().body(category);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto newCategoryDto = categoryService.addNewCategory(categoryDto);
        return new ResponseEntity<>(newCategoryDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        CategoryDto updatedCategoryDto = categoryService.updateCategory(id, categoryDto);
        return ResponseEntity.ok(updatedCategoryDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteCategoriesByName(@PathVariable String name) {
        categoryService.deleteCategoriesByName(name);
        return ResponseEntity.noContent().build();
    }
}
