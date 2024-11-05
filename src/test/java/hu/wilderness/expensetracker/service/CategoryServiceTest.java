package hu.wilderness.expensetracker.service;

import hu.wilderness.expensetracker.dto.CategoryDto;
import hu.wilderness.expensetracker.dto.mapper.CategoryMapper;
import hu.wilderness.expensetracker.model.Category;
import hu.wilderness.expensetracker.repository.CategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryService categoryService;

    private Category category;
    private CategoryDto categoryDto;

    @BeforeEach
    void setUp() {
        category = Category.builder()
                .name("drive")
                .description("petrol, highway bills, etc.")
                .build();
        categoryDto = CategoryDto.builder()
                .name("drive")
                .description("petrol, highway bills, etc.")
                .build();
        when(categoryMapper.toEntity(categoryDto)).thenReturn(category);
        when(categoryMapper.toDto(category)).thenReturn(categoryDto);
        when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(category);
    }

    @Test
    public void createCategory_ReturnsCategoryDto() {
        CategoryDto savedCategory = categoryService.addNewCategory(categoryDto);
        Assertions.assertThat(savedCategory).isNotNull();
    }
}
