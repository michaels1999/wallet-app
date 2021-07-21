package io.angelwing.repository;

import io.angelwing.model.Expense;

import java.util.List;
import java.util.UUID;

public interface ExpenseRepository {

    void addExpense(Expense expense);

    void updateExpense(Expense expense);

    void removeExpense(UUID id);

    Expense getExpenseById(UUID id);

    List<Expense> listExpense();
}
