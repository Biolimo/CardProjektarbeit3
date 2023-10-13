package com.example.Card.CardSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class CardSetConfig {

    @Bean
    CommandLineRunner commandLineRunner2(CardSetRepository repository){
        return args -> {
            CardSet cardSet1 = new CardSet(
                    "Erstes Cardset",
                    LocalDate.of(2024,3,30)
            );
            CardSet cardSet2 = new CardSet(
                    "Zweites Cardset",
                    LocalDate.of(2025,3,30)
            );
            repository.saveAll(
                    List.of(cardSet1,cardSet2)
            );
        };
    }
}
