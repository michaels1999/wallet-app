package io.angelwing.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @Type(type = "uuid-char")
    private UUID id ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "category_id")
    private Expense ExpenseCategory;

    private String name;

    private Double amount;

    private String currency;

    private LocalDateTime date;


    public Expense() {
        super();
    }

    public Expense(UUID id, Expense ExpenseCategory, String name, Double amount, String currency, LocalDateTime date) {
        this.id = id;
        this.ExpenseCategory = ExpenseCategory;
        this.name = name;
        this.amount = amount;
        this.currency = currency;
        this.date = date;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public UUID getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public Double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Expense getExpenseCategory() {
        return ExpenseCategory;
    }

    public void setExpenseCategory(Expense ExpenseCategory) {
        this.ExpenseCategory = ExpenseCategory;
    }


    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", expenseCategory=" + ExpenseCategory +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", date=" + date +
                '}';
    }
}
