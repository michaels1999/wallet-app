package io.angelwing.service.generator;

import io.angelwing.model.Currency;
import io.angelwing.model.Expense;
import io.angelwing.model.ExpenseCategory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExpenseGenerator {

    private ExpenseGenerator() {
        // NOOP
    }

    public static Expense generateRandomExpenseWithId(final UUID id) {
        final Expense expense = new Expense();

        expense.setId(id);
        expense.setExpenseCategory(new ExpenseCategory());
        expense.setName("Travel");
        expense.setAmount(350.0);
        expense.setCurrency(Currency.MDL);
        expense.setDate(LocalDateTime.now());

        return expense;
    }

    public static List<Expense> generateRandomExpenses(int numberOfExpenses) {
        final List<Expense> expenses = new ArrayList<>();

        for (int i = 0; i < numberOfExpenses; i++) {
            expenses.add(generateRandomExpenseWithId(UUID.randomUUID()));
        }

        return expenses;
    }

}
