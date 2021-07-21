package io.angelwing.model;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.*;

@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id ;

    @Column(name = "category")
    private UUID category;

    @Column(name = "name")
    private String name;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "date")
    private LocalDateTime date;


    public Expense() {
    }

    public Expense(UUID id, UUID category, String name, Double amount, String currency, LocalDateTime date) {
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

    public void setCategory(UUID category) {
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

    public UUID getCategory() {
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

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", date=" + date +
                '}';
    }
}
