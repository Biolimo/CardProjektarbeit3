package com.example.Card.CardSet;

import com.example.Card.Cards.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

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
    public CardSet addNewCardSet(CardSet cardSet){
        Optional<CardSet> cardSetByName = cardSetRepository.findCardSetByName(cardSet.getName());
        if(cardSetByName.isPresent()){
            throw new IllegalStateException("CardSet already exists ID = " + cardSetByName.get().getId());
        }
        cardSetRepository.save(cardSet);
        return cardSet;
    }

    //Delete a CardSet
    public String deleteCard(Long cardSetId){
        boolean exists = cardSetRepository.existsById(cardSetId);
        if(!exists){
            throw new IllegalStateException("cardSet with id " + cardSetId + "is not found");
        }
        cardSetRepository.deleteById(cardSetId);
        return "CardSet with ID " + cardSetId + " has been successfully deleted.";
    }

    //Update a CardSet name
    @Transactional
    public String updateCardSet(
            long cardSetId,
            String name){
        CardSet cardSet = cardSetRepository.findById(cardSetId).orElseThrow(() -> new IllegalStateException(
                "card with id " + cardSetId + "is not found"));

        if(name != null){
            cardSet.setName(name);
        }
        return "CardSet with ID " + cardSetId + " has been successfully updated.";
    }

    //Add a Card to a Set
    public Card addCardToSet(Long cardSetId,Card card) {
        CardSet cardSet = cardSetRepository.findById(cardSetId)
                .orElseThrow(() -> new IllegalStateException(
                "card with id " + cardSetId + "is not found"));
        cardSet.addCards(card);
        cardSetRepository.save(cardSet);
        return card;
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
            questionAndId = new QuestionAndId(dueCard.getQuestion(),dueCard.getId(), dueCard.getDType());
        } else {
            questionAndId = new QuestionAndId("No due card found for the specified card set ID.");
        }
        return questionAndId;
    }

    public QuestionAndId getQuestionFromCardSetByRandom(Long cardSetId) {

        CardSet cardSet = cardSetRepository.getReferenceById(cardSetId);
        QuestionAndId questionAndId = null;

        List<Card> cards = new ArrayList<>(cardSet.getCards());

        if (!cards.isEmpty()) {
            // Generate a random index within the size of the list
            Random random = new Random();
            int randomIndex = random.nextInt(cards.size());

            // Retrieve and return the random card
            Card dueCard = cards.get(randomIndex);
            if (dueCard != null) {
                questionAndId = new QuestionAndId(dueCard.getQuestion(),dueCard.getId(), dueCard.getDType());
            } else {
                questionAndId = new QuestionAndId("No due card found for the specified card set ID.");
            }
        }
        return questionAndId;
    }

    public QuestionAndId getQuestionFromCardSetBySuccessCount(Long cardSetId) {

        CardSet cardSet = cardSetRepository.getReferenceById(cardSetId);
        QuestionAndId questionAndId;

        List<Card> cards = new ArrayList<>(cardSet.getCards());
        cards.sort(Comparator.comparing(Card::getSuccessCounter));

        Card dueCard = cards.stream()
                .findFirst()
                .orElse(null);
        if (dueCard != null) {
            questionAndId = new QuestionAndId(dueCard.getQuestion(),dueCard.getId(), dueCard.getDType());
        } else {
            questionAndId = new QuestionAndId("No due card found for the specified card set ID.");
        }
        return questionAndId;
    }
}
