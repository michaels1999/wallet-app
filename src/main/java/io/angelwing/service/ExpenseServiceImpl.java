package io.angelwing.service;

import io.angelwing.repository.ExpenseRepository;
import io.angelwing.model.Expense;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.UUID;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    @Transactional
    public void addExpense(Expense expense) {
        this.expenseRepository.addExpense(expense);

    }

    @Override
    @Transactional
    public void updateExpense(Expense expense) {
        this.expenseRepository.updateExpense(expense);
    }

    @Override
    @Transactional
    public void removeExpense(UUID id) {
        this.expenseRepository.removeExpense(id);

    }

    @Override
    @Transactional
    public Expense getExpenseById(UUID id) {
        return this.expenseRepository.getExpenseById(id);
    }

    @Override
    @Transactional
    public List<Expense> listExpense() {
        return this.expenseRepository.listExpense();
    }
}
