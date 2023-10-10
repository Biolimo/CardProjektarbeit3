package com.example.Card.Cards;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class CardConfig {


    @Bean
    CommandLineRunner commandLineRunner(CardRepository repository){
        return args -> {
            Card test1 = new Card(
                    LocalDate.of(2023, 3,25),
                    "Question"
            );
            Card test2 = new Card(
                    LocalDate.of(2003, 6,25),
                    "Question2"
            );

            repository.saveAll(
                    List.of(test1,test2)
            );

        };
    }
}
