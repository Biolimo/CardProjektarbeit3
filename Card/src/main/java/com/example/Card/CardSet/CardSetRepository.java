package com.example.Card.CardSet;

import com.example.Card.Cards.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardSetRepository
        extends JpaRepository<CardSet, Long> {

    Optional<CardSet> findCardSetByName(String name);

}
