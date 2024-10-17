package hu.wilderness.expensetracker.dto.mapper;

import hu.wilderness.expensetracker.dto.ExpenseDto;
import hu.wilderness.expensetracker.model.Expense;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {
    ExpenseDto toDto(Expense entity);
    Expense toEntity(ExpenseDto dto);
}
