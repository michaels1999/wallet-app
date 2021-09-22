package io.angelwing.service.generator;

import io.angelwing.model.Currency;
import io.angelwing.model.Income;
import io.angelwing.model.IncomeCategory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IncomeGenerator {

    private IncomeGenerator() {
        // NOOP
    }

    public static Income generateRandomIncomeWithId(final UUID id) {
        final Income income = new Income();

        income.setId(id);
        income.setIncomeCategory(new IncomeCategory());
        income.setName("Travel");
        income.setAmount(350.0);
        income.setCurrency(Currency.MDL);
        income.setDate(LocalDateTime.now());

        return income;
    }

    public static List<Income> generateRandomIncome(int numberOfIncomes) {

        final List<Income> incomes = new ArrayList<>();

        for (int i = 0; i < numberOfIncomes; i++) {
            incomes.add(generateRandomIncomeWithId(UUID.randomUUID()));

        }
        return incomes;
    }

}
