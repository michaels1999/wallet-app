package io.angelwing.service;

import io.angelwing.dc.IncomeDc;
import io.angelwing.model.Income;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IncomeServiceImpl implements IncomeService{
    private IncomeDc incomeDc;

    public IncomeServiceImpl(IncomeDc incomeDc) {
        this.incomeDc = incomeDc;
    }

    @Override
    @Transactional
    public void addIncome(Income income) {
        this.incomeDc.addIncome(income);
    }

    @Override
    @Transactional
    public void updateIncome(Income income) {
        this.incomeDc.updateIncome(income);
    }

    @Override
    @Transactional
    public void removeIncome(int id) {
        this.incomeDc.removeIncome(id);
    }

    @Override
    @Transactional
    public Income getIncomeById(int id) {
        return incomeDc.getIncomeById(id);
    }

    @Override
    @Transactional
    public List<Income> listIncome() {
        return incomeDc.listExpense();
    }
}
