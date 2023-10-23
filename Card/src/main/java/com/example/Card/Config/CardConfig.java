package com.example.Card.Config;

import com.example.Card.Cards.KinderKarten.MuSeCard;
import com.example.Card.Cards.KinderKarten.TextCard;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class CardConfig {


    /*@Bean
    CommandLineRunner commandLineRunner(CardRepository repository){
        return args -> {
            MuSeCard test1 = new MuSeCard(

            );
            TextCard test2 = new TextCard(

            );

            repository.saveAll(
                    List.of(test1,test2)
            );

        };
    }*/
}
