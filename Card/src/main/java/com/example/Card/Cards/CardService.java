package com.example.Card.Cards;

import com.example.Card.Cards.KinderKarten.*;
import com.example.Card.Cards.KinderKarten.NumberKinder.DoubleCard;
import com.example.Card.Cards.KinderKarten.NumberKinder.IntCard;
import com.example.Card.Cards.KinderKarten.NumberKinder.LongCard;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            throw new IllegalStateException("Question already exists CardID = " + cardByQuestion.get().getId());
        }
        cardRepository.save(card);
        System.out.println("Karte wurde Gespeichert mit ID = " + card.getId());
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
    public void updateCard(Long cardId, UpdateInformation updateInformation){
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new IllegalStateException(
                        "card with id " + cardId + "is not found"));

        if(updateInformation.getQuestion() != null){
            card.setQuestion(updateInformation.question);
        }
        System.out.println("Test update Card last print");
    }

    //This method checks what kind of card is given and checks the User Answer in the right class

    public boolean checkUserAnswer(Long cardId, String answer) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new IllegalStateException(
                        "card with id " + cardId + "is not found"));

        boolean userAwnserWas;

        switch (card.getDType()) {
            case "MuSeCard":
                MuSeCard muSeCard = (MuSeCard) card;
                String[] answerCast = answer.split(",");
                int[] answerIntCast = new int[answerCast.length]; // Erstellt ein int-Array gleicher Größe

                // Iteriere durch das String-Array und parsiere die Werte zu Integer
                for (int i = 0; i < answerCast.length; i++) {
                    try {
                        answerIntCast[i] = Integer.parseInt(answerCast[i]); // Parst den String zu Integer
                    } catch (NumberFormatException e) {
                        // Handle Ausnahmefall, falls das Parsen fehlschlägt
                        System.out.println("Ungültige Zahl: " + answerCast[i]);
                    }
                }
                userAwnserWas = muSeCard.checkUserAnswer(answerIntCast, muSeCard);
                if(userAwnserWas){
                    cardRepository.save(muSeCard);
                }
                return userAwnserWas;
            case "IntCard":
                IntCard intCard = (IntCard) card;
                int answerNumberCard = 0;
                try {
                    answerNumberCard = Integer.parseInt(answer);
                } catch (NumberFormatException e) {
                    System.out.println("Ungültige Zahl");
                }
                userAwnserWas = intCard.checkUserAnswer(answerNumberCard, intCard);
                if(userAwnserWas){
                    cardRepository.save(intCard);
                }
                return userAwnserWas;
            case "DoubleCard":
                DoubleCard doubleCard = (DoubleCard) card;
                double answerDoubleCard = 0;
                try {
                    answerDoubleCard = Double.parseDouble(answer);
                } catch (NumberFormatException e) {
                    System.out.println("Ungültige Zahl");
                }
                userAwnserWas = doubleCard.checkUserAnswer(answerDoubleCard, doubleCard);
                if(userAwnserWas){
                    cardRepository.save(doubleCard);
                }
                return userAwnserWas;
            case "LongCard":
                LongCard longCard = (LongCard) card;
                Long answerLongCard = 0L;
                try {
                    answerLongCard = Long.parseLong(answer);
                } catch (NumberFormatException e) {
                    System.out.println("Ungültige Zahl");
                }
                userAwnserWas = longCard.checkUserAnswer(answerLongCard, longCard);
                if(userAwnserWas){
                    cardRepository.save(longCard);
                }
                return userAwnserWas;
            case "SiSeCard":
                SiSeCard siSeCard = (SiSeCard) card;
                int answerSiSeCard = 0;
                try {
                    answerSiSeCard = Integer.parseInt(answer);
                } catch (NumberFormatException e) {
                    System.out.println("Ungültige Zahl");
                }
                userAwnserWas = siSeCard.checkUserAnswer(answerSiSeCard, siSeCard);
                if(userAwnserWas){
                    cardRepository.save(siSeCard);
                }
                return userAwnserWas;
            case "TextCard":
                TextCard textCard = (TextCard) card;
                userAwnserWas = textCard.checkUserAnswer(answer,textCard);
                if(userAwnserWas){
                    cardRepository.save(textCard);
                }
                return userAwnserWas;
            default:
                // Handle the case when the card type is not recognized
                System.out.println("Unknown card type: " + card.getDType());
                // You can return an error message or throw an exception depending on your needs
                break;
        }
        return false;
    }
}
