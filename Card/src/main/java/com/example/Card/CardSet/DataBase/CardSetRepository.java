package com.example.Card.CardSet.DataBase;

import com.example.Card.CardSet.CardSet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardSetRepository
        extends JpaRepository<CardSet, Long> {

    Optional<CardSet> findCardSetByName(String name);

}
