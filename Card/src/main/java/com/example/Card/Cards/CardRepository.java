package com.example.Card.Cards;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository
        extends JpaRepository<Card,Long> {

    Optional<Card> findCardByQuestion(String question);

}
