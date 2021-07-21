package io.angelwing.dc;


import io.angelwing.model.Income;

import java.util.List;

public interface IncomeDc {
    public void addIncome(Income income);

    public void updateIncome(Income income);

    public void removeIncome(int id);

    public Income getIncomeById(int id);

    public List<Income> listExpense();
}
