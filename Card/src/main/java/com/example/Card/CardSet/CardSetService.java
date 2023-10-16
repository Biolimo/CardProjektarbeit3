package com.example.Card.CardSet;

import com.example.Card.Cards.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CardSetService {

    private final CardSetRepository cardSetRepository;

    @Autowired
    public CardSetService(CardSetRepository cardSetRepository){
        this.cardSetRepository = cardSetRepository;
    }

    public List<CardSet> getCardSet(){
        return cardSetRepository.findAll();
    }

    //Adds a new CardSet
    public void addNewCardSet(CardSet cardSet){
        Optional<CardSet> cardSetByName = cardSetRepository.findCardSetByName(cardSet.getName());
        if(cardSetByName.isPresent()){
            throw new IllegalStateException("CardSet already exists ID = " + cardSetByName.get().getId());
        }
        cardSetRepository.save(cardSet);
    }

    //Delete a CardSet
    public void deleteCard(Long cardSetId){
        boolean exists = cardSetRepository.existsById(cardSetId);
        if(!exists){
            throw new IllegalStateException("cardSet with id " + cardSetId + "is not found");
        }
        cardSetRepository.deleteById(cardSetId);
    }

    //Update a CardSet name
    @Transactional
    public void updateCardSet(
            long cardSetId,
            String name){
        CardSet cardSet = cardSetRepository.findById(cardSetId).orElseThrow(() -> new IllegalStateException(
                "card with id " + cardSetId + "is not found"));

        if(name != null){
            cardSet.setName(name);
        }
        System.out.println("Test update CardSet last print");
    }

    //Add a Card to a Set
    public void addCardToSet(Long cardSetId,Card card) {
        CardSet cardSet = cardSetRepository.findById(cardSetId)
                .orElseThrow(() -> new IllegalStateException(
                "card with id " + cardSetId + "is not found"));
        cardSet.addCards(card);
        cardSetRepository.save(cardSet);
    }

    //Get a Question from a CardSet
    public QuestionAndId getQuestionFromCardSetByDueDate(Long cardSetId) {

        CardSet cardSet = cardSetRepository.getReferenceById(cardSetId);
        QuestionAndId questionAndId;

        // Get the list of cards from the card set
        List<Card> cards = new ArrayList<>(cardSet.getCards());

        // Sort the cards by due date (ascending order)
        cards.sort(Comparator.comparing(Card::getDueDate));

        // Find the first card with a due date on or before today
        LocalDate today = LocalDate.now();
        Card dueCard = cards.stream()
                .filter(card -> card.getDueDate().isBefore(today) || card.getDueDate().isEqual(today))
                .findFirst()
                .orElse(null);


        // Return the question of the due card, or a message if no due card is found
        if (dueCard != null) {
            questionAndId = new QuestionAndId(dueCard.getQuestion(),dueCard.getId());
        } else {
            questionAndId = new QuestionAndId("No due card found for the specified card set ID.");
        }
        return questionAndId;
    }
}
