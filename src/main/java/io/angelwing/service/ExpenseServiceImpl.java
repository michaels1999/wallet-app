package io.angelwing.service;

import io.angelwing.db.ExpenseDb;
import io.angelwing.model.Expense;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService{
    private ExpenseDb expenseDb;

    public ExpenseServiceImpl(ExpenseDb expenseDb) {
        this.expenseDb = expenseDb;
    }

    @Override
    @Transactional
    public void addExpense(Expense expense) {
        this.expenseDb.addExpense(expense);

    }

    @Override
    @Transactional
    public void updateExpense(Expense expense) {
        this.expenseDb.updateExpense(expense);
    }

    @Override
    @Transactional
    public void removeExpense(int id) {
        this.expenseDb.removeExpense(id);

    }

    @Override
    @Transactional
    public Expense getExpenseById(int id) {
        return this.expenseDb.getExpenseById(id);
    }

    @Override
    @Transactional
    public List<Expense> listExpense() {
        return this.expenseDb.listExpense();
    }
}
