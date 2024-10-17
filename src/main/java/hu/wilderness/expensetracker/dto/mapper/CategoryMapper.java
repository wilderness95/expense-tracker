package hu.wilderness.expensetracker.dto.mapper;

import hu.wilderness.expensetracker.dto.CategoryDto;
import hu.wilderness.expensetracker.model.Category;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto toDto(Category entity);
    Category toEntity(CategoryDto dto);

}
