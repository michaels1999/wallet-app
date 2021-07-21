package io.angelwing.repository;


import io.angelwing.model.Income;

import java.util.List;
import java.util.UUID;

public interface IncomeRepository {
    void addIncome(Income income);

    void updateIncome(Income income);

    void removeIncome(UUID id);

    Income getIncomeById(UUID id);

    List<Income> listExpense();
}
