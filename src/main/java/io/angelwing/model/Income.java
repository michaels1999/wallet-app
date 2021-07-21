package io.angelwing.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Income {
    private UUID id ;
    private UUID category;
    private Double amount;
    private String currency;
    private LocalDateTime date;

    public Income() {
    }

    public Income(UUID id, UUID category, Double amount, String currency, LocalDateTime date) {
        this.id = id;
        this.category = category;
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
