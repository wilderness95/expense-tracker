package hu.wilderness.expensetracker.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpenseDto {
    private long expenseId;
    private String expenseName;
    private long categoryId;
    private String expenseDescription;
    private int amount;
    private LocalDate createDate;
}
