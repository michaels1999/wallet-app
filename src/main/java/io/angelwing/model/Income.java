package io.angelwing.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "income")
public class Income {

    @Id
    @Type(type = "uuid-char")
    private UUID id ;

    @Column(name = "category_id")
    @Enumerated(EnumType.STRING)
    private IncomeCategory category;

    private Double amount;

    private String currency;

    private LocalDateTime date;

    public Income() {
        // NOOP
    }

    public Income(UUID id, IncomeCategory category, Double amount, String currency, LocalDateTime date) {
        this.id = id;
        this.category = category;
        this.amount = amount;
        this.currency = currency;
        this.date = date;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setCategory(IncomeCategory category) {
        this.category = category;
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

    public IncomeCategory getCategory() {
        return category;
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
        return "Income{" +
                "id=" + id +
                ", category=" + category +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", date=" + date +
                '}';
    }
}
