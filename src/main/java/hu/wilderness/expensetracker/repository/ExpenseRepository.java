package hu.wilderness.expensetracker.repository;

import hu.wilderness.expensetracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

    Expense findByExpenseId(long expenseId);

    Expense findByExpenseName(String name);

    List<Expense> findAll();

    List<Expense> findAllExpenseByCategoryId(long categoryId);

    List<Expense> findAllExpenseByCreateDate(LocalDate createDate);

}