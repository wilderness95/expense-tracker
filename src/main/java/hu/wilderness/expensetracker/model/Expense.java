package hu.wilderness.expensetracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "expense")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long expenseId;
    @NotEmpty
    private String expenseName;
    private long categoryId;
    private String expenseDescription;
    @Min(1)
    private int amount;
    @PastOrPresent
    private LocalDate createDate;
}
