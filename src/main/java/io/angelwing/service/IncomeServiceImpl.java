package io.angelwing.service;

import io.angelwing.repository.IncomeRepository;
import io.angelwing.model.Income;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class IncomeServiceImpl implements IncomeService {

    private IncomeRepository incomeRepository;

    public IncomeServiceImpl(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    @Override
    @Transactional
    public void addIncome(Income income) {
        this.incomeRepository.addIncome(income);
    }

    @Override
    @Transactional
    public void updateIncome(Income income) {
        this.incomeRepository.updateIncome(income);
    }

    @Override
    @Transactional
    public void removeIncome(UUID id) {
        this.incomeRepository.removeIncome(id);
    }

    @Override
    @Transactional
    public Income getIncomeById(UUID id) {
        return incomeRepository.getIncomeById(id);
    }

    @Override
    @Transactional
    public List<Income> listIncome() {
        return incomeRepository.listExpense();
    }
}
