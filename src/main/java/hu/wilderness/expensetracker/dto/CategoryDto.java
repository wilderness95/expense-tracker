package hu.wilderness.expensetracker.dto;


import lombok.Data;

@Data
public class CategoryDto {
    private long id;
    private String name;
    private String description;
}
