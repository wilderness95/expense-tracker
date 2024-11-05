package hu.wilderness.expensetracker.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpenseDto {
    private Long expenseId;
    private String expenseName;
    private Long categoryId;
    private String expenseDescription;
    private int amount;
    private LocalDate createDate;
}
