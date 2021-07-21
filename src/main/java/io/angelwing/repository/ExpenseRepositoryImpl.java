package io.angelwing.repository;

import io.angelwing.model.Expense;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ExpenseRepositoryImpl implements ExpenseRepository {
    private static final Logger logger = LoggerFactory.getLogger(ExpenseRepositoryImpl.class);

    private SessionFactory sessionFactory;

    public ExpenseRepositoryImpl(SessionFactory sessionFactory) {
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
    public void removeExpense(UUID id) {
        Session session = this.sessionFactory.getCurrentSession();
        Expense expense = (Expense) session.load(Expense.class, id);

        if (expense != null) {
            session.delete(expense);
        }
        logger.info("Expense successfully removed . Expense details: " + expense);
    }

    @Override
    public Expense getExpenseById(UUID id) {
        Session session = this.sessionFactory.getCurrentSession();
        Expense expense = (Expense) session.load(Expense.class, id);
        logger.info("Expense successfully loaded . Expense details: " + expense);
        return expense;
    }

    @Override
    @SuppressWarnings(value = "Unchecked")
    public List<Expense> listExpense() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Expense> expenseList = session.createQuery("from Expense").list();

        for (Expense expense : expenseList) {
            logger.info("Expense list: " + expense);
        }
        return expenseList;
    }
}
