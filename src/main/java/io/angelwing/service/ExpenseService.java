package io.angelwing.service;

import io.angelwing.model.Expense;

import java.util.List;

public interface ExpenseService {

    public void addExpense(Expense expense);

    public void updateExpense(Expense expense);

    public void removeExpense(int id);

    public Expense getExpenseById(int id);

    public List<Expense> listExpense();

}
