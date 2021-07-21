package io.angelwing.repository;

import io.angelwing.model.Income;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class IncomeRepositoryImpl implements IncomeRepository {

    private static final Logger logger = LoggerFactory.getLogger(IncomeRepositoryImpl.class);

    private SessionFactory sessionFactory;

    public IncomeRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void addIncome(Income income) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(income);
        logger.info("Income successfully saved . Income details: " + income);

    }

    @Override
    public void updateIncome(Income income) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(income);
        logger.info("Income successfully updated . Income details: " + income);

    }

    @Override
    public void removeIncome(UUID id) {
        Session session = this.sessionFactory.getCurrentSession();
        Income income = session.load(Income.class , id);

        if(income!=null){
            session.delete(income);
        }
        logger.info("Income successfully removed . Income details: " + income);
    }

    @Override
    public Income getIncomeById(UUID id) {
        Session session =this.sessionFactory.getCurrentSession();
        Income income = session.load(Income.class , id);
        logger.info("Income successfully loaded . Income details: " + income);
        return income;

    }

    @Override
    @SuppressWarnings(value = "Unchecked")

    public List<Income> listExpense() {

        Session session = this.sessionFactory.getCurrentSession();
        List<Income> incomeList = session.createQuery("from Expense").list();

        for (Income income : incomeList){
            logger.info("Income list: " + income);
        }
        return incomeList;

    }
}
