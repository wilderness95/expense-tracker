package hu.wilderness.expensetracker.service;

import hu.wilderness.expensetracker.dto.CategoryDto;
import hu.wilderness.expensetracker.dto.mapper.CategoryMapper;
import hu.wilderness.expensetracker.exceptions.CategoryExistsException;
import hu.wilderness.expensetracker.exceptions.CategoryNotFoundException;
import hu.wilderness.expensetracker.model.Category;
import hu.wilderness.expensetracker.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CategoryService {


    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryDto> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList
                .stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }
    public CategoryDto getCategoryById(long categoryId){
        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow(()-> {
                    log.error("category id {} does not exist...", categoryId);
                    return new CategoryNotFoundException(String.format("Category with id %d not found", categoryId));
                });
        return categoryMapper.toDto(category);
    }

    public CategoryDto getCategoryByName(String categoryName) {
        Category category = categoryRepository
                .findByName(categoryName)
                .orElseThrow(()-> {
                    log.error("category name {} does not exist...", categoryName);
                    return new CategoryNotFoundException(String.format("Category with name %s not found", categoryName));
                });
        return categoryMapper.toDto(category);
    }

    public CategoryDto addNewCategory(CategoryDto categoryDto) {
        try{
        Category category = categoryMapper.toEntity(categoryDto);
        Category newCategory = categoryRepository.save(category);
        return categoryMapper.toDto(newCategory);
        } catch (DataIntegrityViolationException e){
            log.error("Category already exists...");
            throw new CategoryExistsException("Category already exists");
        }
    }

    public void deleteCategoryById(Long categoryId) {
        categoryRepository
                .findById(categoryId)
                .orElseThrow(()-> {
                    log.error("category cannot delete,because category with id {} does not exist...", categoryId);
                    return new CategoryNotFoundException(String.format("Category with id %d not found", categoryId));
                });
        categoryRepository.deleteById(categoryId);
    }
    public void deleteCategoriesdByName(String name) {
            categoryRepository.findByName(name).ifPresent(categoryRepository::delete);
    }
    public void deleteCategoriesByName(String categoryName) {
        categoryRepository.findByName(categoryName).ifPresentOrElse(
                categoryRepository::delete,
                () -> {
                    log.error("Category with name {} not found", categoryName);
                    throw new CategoryNotFoundException(String.format("Category with name %s not found", categoryName));
                }
                );
    }

    public CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto) {
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> {
                    log.error("Failed to update category, because category with id {} does not exist...", categoryId);
                    return new CategoryNotFoundException("Category with ID " + categoryId + " not found.");
                });

        try {
            Category category = categoryMapper.toEntity(categoryDto);
            category.setId(categoryId);
            Category updatedCategory = categoryRepository.save(category);
            log.info("Category updated successfully: '{}'", category.toString());
            return categoryMapper.toDto(updatedCategory);

        } catch (DataIntegrityViolationException e) {
            log.error("Failed to update category. Category with name '{}' already exists.", categoryDto.getName());
            throw new CategoryExistsException("Category with name '" + categoryDto.getName() + "' already exists.");
        }
    }

}

