package io.angelwing.dc;

import io.angelwing.model.Expense;
import io.angelwing.model.Income;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IncomeDcImpl implements IncomeDc{
    private static final Logger logger = LoggerFactory.getLogger(IncomeDcImpl.class);

    private SessionFactory sessionFactory;

    public IncomeDcImpl(SessionFactory sessionFactory) {
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
    public void removeIncome(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Income income = (Income) session.load(Income.class , new Integer(id));

        if(income!=null){
            session.delete(income);
        }
        logger.info("Income successfully removed . Income details: " + income);
    }

    @Override
    public Income getIncomeById(int id) {
        Session session =this.sessionFactory.getCurrentSession();
        Income income = (Income) session.load(Income.class , new Integer(id));
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
