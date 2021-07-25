package io.angelwing;

import io.angelwing.repository.ExpenseCategoryRepository;
import io.angelwing.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WalletApplication {

    public static void main(String[] args) {
        SpringApplication.run(WalletApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(@Autowired final ExpenseService expenseService ,
                                 @Autowired final ExpenseCategoryRepository expenseCategoryRepository) {
        return args -> {

        };
    }

}


