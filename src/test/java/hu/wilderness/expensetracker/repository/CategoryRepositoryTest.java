package hu.wilderness.expensetracker.repository;

import hu.wilderness.expensetracker.model.Category;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection =  EmbeddedDatabaseConnection.H2)
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    private Category category;

    @BeforeEach
    void setUp() {
        category = Category.builder()
                .name("drive")
                .description("petrol, highway bills, etc.")
                .build();
        categoryRepository.save(category);
    }

    @Test
    public void saveCategory_returnSavedCategory() {
        Category newCategory = Category.builder()
                .name("entertainment")
                .description("movies, concerts, etc.")
                .build();
        Category savedCategory = categoryRepository.save(newCategory);
        Assertions.assertThat(savedCategory).isNotNull();
        Assertions.assertThat(savedCategory.getId()).isGreaterThan(0);
    }

    @Test
    public void getAll_ReturnsAllCategories() {
        Category category2 = Category.builder()
                .name("food")
                .description("groceries, dining out, etc.")
                .build();
        categoryRepository.save(category2);

        List<Category> categories = categoryRepository.findAll();

        Assertions.assertThat(categories).isNotNull();
        Assertions.assertThat(categories.size()).isEqualTo(2);
    }

    @Test
    public void findById_ReturnsCategory() {

        Category foundCategory = categoryRepository.findById(category.getId()).get();

        Assertions.assertThat(foundCategory).isNotNull();
        Assertions.assertThat(foundCategory.getName()).isEqualTo(category.getName());
    }

    @Test
    public void findByName_ReturnsCategory() {

        Category foundCategory = categoryRepository.findByName(category.getName()).get();

        Assertions.assertThat(foundCategory).isNotNull();
        Assertions.assertThat(foundCategory.getName()).isEqualTo(category.getName());
    }

    @Test
    public void deleteCategoryById_returnCategoryIsEmpty() {

        categoryRepository.deleteById(category.getId());

        Optional<Category> deletedCategory = categoryRepository.findById(category.getId());

        Assertions.assertThat(deletedCategory).isEmpty();
    }

    @Test
    public void deleteCategoryByName_returnCategoryIsEmpty() {

        categoryRepository.delete(category);

        Optional<Category> deletedCategory = categoryRepository.findByName(category.getName());

        Assertions.assertThat(deletedCategory).isEmpty();
    }

    @Test
    public void updateCategory_returnCategoryNotNull() {
        Category foundCategory = categoryRepository.findById(category.getId()).get();

        foundCategory.setName("newCategory");
        foundCategory.setDescription("newDescription");

        Category updatedCategory = categoryRepository.save(foundCategory);

        Assertions.assertThat(updatedCategory.getDescription()).isNotNull();
        Assertions.assertThat(updatedCategory.getName()).isNotNull();
    }



}
