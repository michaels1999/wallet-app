package io.angelwing.model;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "income_category")
public class IncomeCategory {

    @Id
    @Type(type = "uuid-char")
    private UUID id;

    private String name;

    public IncomeCategory() {
        // NOOP
    }

    public IncomeCategory(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
