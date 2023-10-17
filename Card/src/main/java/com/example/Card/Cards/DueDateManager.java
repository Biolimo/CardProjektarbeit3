package com.example.Card.Cards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class DueDateManager {

    private final CardRepository cardRepository;

    @Autowired
    public DueDateManager(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    private static final int DaysUntilNextDueDateAfterCorrectAnswer = 3;

    public void updateDueDate(Card card) {
        card.setDueDate(LocalDate.now().plus(Period.ofDays(DaysUntilNextDueDateAfterCorrectAnswer)));
        cardRepository.save(card);
    }
}
