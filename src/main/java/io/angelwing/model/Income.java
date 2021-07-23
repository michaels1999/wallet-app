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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "category_id")
    private Income incomeCategory;

    private Double amount;

    private String currency;

    private LocalDateTime date;

    public Income() {
    super();
    }

    public Income(UUID id, Income incomeCategory, Double amount, String currency, LocalDateTime date) {
        this.id = id;
        this.incomeCategory = incomeCategory;
        this.amount = amount;
        this.currency = currency;
        this.date = date;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Income getIncomeCategory() {
        return incomeCategory;
    }

    public void setIncomeCategory(Income incomeCategory) {
        this.incomeCategory = incomeCategory;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Income{" +
                "id=" + id +
                ", incomeCategory=" + incomeCategory +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", date=" + date +
                '}';
    }
}
