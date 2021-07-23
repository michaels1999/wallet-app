package io.angelwing.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;

import java.util.UUID;

@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @Type(type = "uuid-char")
    private UUID id ;

    @Column(name = "category_id")
   // @Enumerated(EnumType.STRING)
    private ExpenseCategory category;


    private HashMap<String,Integer> category_id;

 // @ManyToOne
  // private Expense ExpenseCategory;

    private String name;

    private Double amount;

    private String currency;

    private LocalDateTime date;


    public Expense() {
        // NOOP
    }

    public Expense(UUID id, ExpenseCategory category, String name, Double amount, String currency, LocalDateTime date) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.amount = amount;
        this.currency = currency;
        this.date = date;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setCategory(ExpenseCategory category) {
        this.category = category;
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

    public ExpenseCategory getCategory() {
        return category;
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


    public HashMap<String, Integer> getCategory_id() {
        return category_id;
    }

    public void setCategory_id(HashMap<String, Integer> category_id) {
        this.category_id = category_id;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", category=" + category +
                ", category_id=" + category_id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", date=" + date +
                '}';
    }
}
