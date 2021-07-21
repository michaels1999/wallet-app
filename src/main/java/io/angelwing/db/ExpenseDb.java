package io.angelwing.db;

import io.angelwing.model.Expense;

import java.util.List;

public interface ExpenseDb {

    public void addExpense(Expense expense);

    public void updateExpense(Expense expense);

    public void removeExpense(int id);

    public Expense getExpenseById(int id);

    public List<Expense> listExpense();
}
