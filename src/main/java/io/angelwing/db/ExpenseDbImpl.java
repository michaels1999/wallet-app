package io.angelwing.db;

import io.angelwing.model.Expense;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExpenseDbImpl implements ExpenseDb{
    private static final Logger logger = LoggerFactory.getLogger(ExpenseDbImpl.class);

    private SessionFactory sessionFactory;

    public ExpenseDbImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addExpense(Expense expense) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(expense);
        logger.info("Expense successfully saved . Expense details: " + expense);
    }

    @Override
    public void updateExpense(Expense expense) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(expense);
        logger.info("Expense successfully updated . Expense details: " + expense);
    }

    @Override
    public void removeExpense(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Expense expense = (Expense) session.load(Expense.class , new Integer(id));

        if(expense!=null){
            session.delete(expense);
        }
        logger.info("Expense successfully removed . Expense details: " + expense);
    }

    @Override
    public Expense getExpenseById(int id) {
        Session session =this.sessionFactory.getCurrentSession();
        Expense expense = (Expense) session.load(Expense.class , new Integer(id));
        logger.info("Expense successfully loaded . Expense details: " + expense);
        return expense;
    }

    @Override
    @SuppressWarnings(value = "Unchecked")
    public List<Expense> listExpense() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Expense> expenseList = session.createQuery("from Expense").list();

        for (Expense expense : expenseList){
            logger.info("Expense list: " + expense);
        }
        return expenseList;
    }
}
