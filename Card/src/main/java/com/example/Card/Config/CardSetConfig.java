package com.example.Card.Config;

import com.example.Card.CardSet.CardSet;
import com.example.Card.CardSet.DataBase.CardSetRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CardSetConfig {

    @Bean
    CommandLineRunner commandLineRunner2(CardSetRepository repository){
        return args -> {
            CardSet cardSet1 = new CardSet(
                    "Erstes Cardset"
            );
            CardSet cardSet2 = new CardSet(
                    "Zweites Cardset"
            );
            repository.saveAll(
                    List.of(cardSet1,cardSet2)
            );
        };
    }
}
