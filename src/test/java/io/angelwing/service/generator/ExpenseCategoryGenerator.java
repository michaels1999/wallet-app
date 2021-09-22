package io.angelwing.service.generator;

import io.angelwing.model.ExpenseCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExpenseCategoryGenerator {

    private ExpenseCategoryGenerator() {
        // NOOP
    }

    public static ExpenseCategory generateRandomExpenseCategoryWithId(final UUID id) {
        final ExpenseCategory expenseCategory = new ExpenseCategory();
        expenseCategory.setId(id);
        expenseCategory.setName("Travel");

        return expenseCategory;
    }

    public static List<ExpenseCategory> generateRandomExpenseCategories(int numberOfExpenseCategories) {
        final List<ExpenseCategory> expenseCategories = new ArrayList<>();

        for (int i = 0; i < numberOfExpenseCategories; i++) {
            expenseCategories.add(generateRandomExpenseCategoryWithId(UUID.randomUUID()));
        }
        return expenseCategories;
    }

}
