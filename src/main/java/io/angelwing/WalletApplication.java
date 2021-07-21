package io.angelwing;

import io.angelwing.model.Expense;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootApplication
public class WalletApplication {

    public static void main(String[] args) {
        SpringApplication.run(WalletApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            System.out.println("Hello World");
            // TODO: crud operations with mysql
        };
    }

}


