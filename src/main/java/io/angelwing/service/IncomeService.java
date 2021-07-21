package io.angelwing.service;


import io.angelwing.model.Income;

import java.util.List;
import java.util.UUID;

public interface IncomeService {
    public void addIncome(Income income);

    public void updateIncome(Income income);

    public void removeIncome(UUID id);

    public Income getIncomeById(UUID id);

    public List<Income> listIncome();
}
