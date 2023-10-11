package com.example.Card.Cards;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> getCard(){
        return cardRepository.findAll();
    }


    //adds new cards to the database. If a question already exists in the database, it raises an exception and provides the ID of the existing card.
    public void addNewCard(Card card) {
        Optional<Card> cardByQuestion = cardRepository.findCardByQuestion(card.getQuestion());
        if(cardByQuestion.isPresent()){
            throw new IllegalStateException("Card already exists ID = " + cardByQuestion.get().getId());
        }
        cardRepository.save(card);
    }


    //This code deletes a card by its ID. If the ID is not found, it throws an exception.
    public void deleteCard(Long cardId) {
        boolean exists = cardRepository.existsById(cardId);
        if(!exists){
            throw new IllegalStateException("card with id " + cardId + "is not found");
        }
        cardRepository.deleteById(cardId);

    }


    //This code changes a card by its ID. If the ID is not found, it throws an exception.
    @Transactional
    public void updateCard(Long cardId,
                           String question,
                           LocalDate dueDate){
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new IllegalStateException(
                        "card with id " + cardId + "is not found"));

        if(question != null){
            card.setQuestion(question);
        }
        if(dueDate != null){
            card.setDueDate(dueDate);
        }
        System.out.println("Test update Card last print");
    }
}
