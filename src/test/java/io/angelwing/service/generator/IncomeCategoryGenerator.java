package io.angelwing.service.generator;

import io.angelwing.model.IncomeCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IncomeCategoryGenerator {

    private IncomeCategoryGenerator() {
        // NOOP
    }

    public static IncomeCategory generateRandomIncomeWithId(final UUID id) {
        final IncomeCategory incomeCategory = new IncomeCategory();
        incomeCategory.setId(id);
        incomeCategory.setName("Travel");
        return incomeCategory;
    }

    public static List<IncomeCategory> generateRandomIncomeCategory(int numberOfIncomeCategories) {
        final List<IncomeCategory> incomeCategories = new ArrayList<>();

        for (int i = 0; i < numberOfIncomeCategories; i++) {
            incomeCategories.add(generateRandomIncomeWithId(UUID.randomUUID()));
        }

        return incomeCategories;
    }
}
