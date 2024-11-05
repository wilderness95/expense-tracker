package hu.wilderness.expensetracker.dto.mapper;

import hu.wilderness.expensetracker.dto.CategoryDto;
import hu.wilderness.expensetracker.model.Category;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto toDto(Category entity);
    Category toEntity(CategoryDto dto);

}
