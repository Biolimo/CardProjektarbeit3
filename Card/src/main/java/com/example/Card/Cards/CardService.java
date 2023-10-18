package com.example.Card.Cards;

import com.example.Card.Cards.KinderKarten.*;
import com.example.Card.Cards.KinderKarten.NumberKinder.DoubleCard;
import com.example.Card.Cards.KinderKarten.NumberKinder.IntCard;
import com.example.Card.Cards.KinderKarten.NumberKinder.LongCard;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    private final DueDateManager dueDateManager;
    private final CardRepository cardRepository;

    @Autowired
    public CardService(DueDateManager dueDateManager, CardRepository cardRepository) {
        this.dueDateManager = dueDateManager;
        this.cardRepository = cardRepository;
    }


    public List<Card> getCard(){
        return cardRepository.findAll();
    }


    //adds new cards to the database. If a question already exists in the database, it raises an exception and provides the ID of the existing card.
    public Card addNewCard(Card card) {
        Optional<Card> cardByQuestion = cardRepository.findCardByQuestion(card.getQuestion());
        if(cardByQuestion.isPresent()){
            throw new IllegalStateException("Question already exists CardID = " + cardByQuestion.get().getId());
        }
        cardRepository.save(card);

        System.out.println("Card with ID " + card.getId() + " has been successfully created.");
        return card;
    }


    //This code deletes a card by its ID. If the ID is not found, it throws an exception.
    public String deleteCard(Long cardId) {
        boolean exists = cardRepository.existsById(cardId);
        if(!exists){
            throw new IllegalStateException("card with id = " + cardId + " is not found");
        }
        cardRepository.deleteById(cardId);
        return "Card with ID " + cardId + " has been successfully deleted.";

    }


    //This code changes a card by its ID. If the ID is not found, it throws an exception.
    @Transactional
    public String updateCard(Long cardId, UpdateInformation updateInformation){
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new IllegalStateException(
                        "card with id " + cardId + "is not found"));

        if(updateInformation.getQuestion() != null){
            card.setQuestion(updateInformation.question);
        }

        String response = "but you didnt change the answer";

        Answer answer = updateInformation.getAnswer();

        System.out.println(card.getDType());
        if(answer !=null){
            switch (card.getDType()) {
                case "MuSeCard":
                    try {
                        if(answer.getAnswerMuSeSiSe() == null){
                            throw new IllegalStateException("MuSeCard needs to be updated with \"answerMuSeSiSe\" variable");
                        }
                        MuSeCard muSeCard = (MuSeCard) card;
                        muSeCard.setAnswers(answer.getAnswerMuSeSiSe());
                    }catch(NullPointerException e){
                        throw new IllegalStateException("Invalid Input");
                    }
                    response = "answer was changed to " + Arrays.toString(answer.getAnswerMuSeSiSe());
                    break;
                case "IntCard":
                    try {
                        if(answer.getAnswerIntC() == 0){
                            throw new IllegalStateException("IntCard needs to be updated with \"answerIntC\" variable answer cant be 0");
                        }
                        IntCard intCard = (IntCard) card;
                        intCard.setAnswer(answer.getAnswerIntC());
                    }catch(NullPointerException e){
                        throw new IllegalStateException("Invalid Input");
                    }
                    response = "answer was changed to " + answer.getAnswerIntC();
                    break;
                case "DoubleCard":
                    try {
                        if(answer.getAnswerDC() == 0.0){
                            throw new IllegalStateException("MuSeCard needs to be updated with \"answerMuSeSiSe\" variable answer cant be 0.0");
                        }
                        DoubleCard doubleCard = (DoubleCard) card;
                        doubleCard.setAnswer(answer.getAnswerDC());
                    }catch(NullPointerException e){
                        throw new IllegalStateException("Invalid Input");
                    }
                    response = "answer was changed to " + answer.getAnswerDC();
                    break;
                case "LongCard":
                    try {
                        if(answer.getAnswerLC() == null){
                            throw new IllegalStateException("MuSeCard needs to be updated with \"answerLC\" variable");
                        }
                        LongCard longCard = (LongCard) card;
                        longCard.setAnswer(answer.getAnswerLC());
                    }catch(NullPointerException e){
                        throw new IllegalStateException("Invalid Input");
                    }
                    response = "answer was changed to " + answer.getAnswerLC();
                    break;
                case "SiSeCard":
                    try {
                        if(answer.getAnswerMuSeSiSe() == null){
                            throw new IllegalStateException("SiSeCard needs to be updated with \"answerMuSeSiSe\" variable");
                        }
                        SiSeCard siSeCard = (SiSeCard) card;
                        siSeCard.setAnswers(answer.getAnswerMuSeSiSe());
                    }catch(NullPointerException e){
                        throw new IllegalStateException("Invalid Input");
                    }
                    response = "answer was changed to " + Arrays.toString(answer.getAnswerMuSeSiSe());
                    break;
                case "TextCard":
                    try {
                        if(answer.getAnswerTC() == null){
                            throw new IllegalStateException("TextCard needs to be updated with \"answerTC\" variable");
                        }
                        TextCard textCard = (TextCard) card;
                        textCard.setAnswerTC(answer.getAnswerTC());
                    }catch(NullPointerException e){
                        throw new IllegalStateException("Invalid Input");
                    }
                    response = "answer was changed to " + answer.getAnswerTC();
                    break;
                default:
                    // Handle the case when the card type is not recognized
                    System.out.println("Unknown card type: " + card.getDType());
                    break;
            }
        }
        cardRepository.save(card);
        return "Card with ID " + cardId + " has been successfully updated." + response;
    }

    //This method checks what kind of card is given and checks the User Answer in the right class

    public boolean checkUserAnswer(Long cardId, String answer) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new IllegalStateException(
                        "card with id " + cardId + "is not found"));

        boolean userAnswerWas = false;

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
                userAnswerWas = muSeCard.checkUserAnswer(answerIntCast, muSeCard);
                if(userAnswerWas){
                    cardRepository.save(muSeCard);
                }
                break;
            case "IntCard":
                IntCard intCard = (IntCard) card;
                int answerNumberCard = 0;
                try {
                    answerNumberCard = Integer.parseInt(answer);
                } catch (NumberFormatException e) {
                    System.out.println("Ungültige Zahl");
                }
                userAnswerWas = intCard.checkUserAnswer(answerNumberCard, intCard);
                if(userAnswerWas){
                    cardRepository.save(intCard);
                }
                break;
            case "DoubleCard":
                DoubleCard doubleCard = (DoubleCard) card;
                double answerDoubleCard = 0;
                try {
                    answerDoubleCard = Double.parseDouble(answer);
                } catch (NumberFormatException e) {
                    System.out.println("Ungültige Zahl");
                }
                userAnswerWas = doubleCard.checkUserAnswer(answerDoubleCard, doubleCard);
                if(userAnswerWas){
                    cardRepository.save(doubleCard);
                }
                break;
            case "LongCard":
                LongCard longCard = (LongCard) card;
                long answerLongCard = 0L;
                try {
                    answerLongCard = Long.parseLong(answer);
                } catch (NumberFormatException e) {
                    System.out.println("Ungültige Zahl");
                }
                userAnswerWas = longCard.checkUserAnswer(answerLongCard, longCard);
                if(userAnswerWas){
                    cardRepository.save(longCard);
                }
                break;
            case "SiSeCard":
                SiSeCard siSeCard = (SiSeCard) card;
                int answerSiSeCard = 0;
                try {
                    answerSiSeCard = Integer.parseInt(answer);
                } catch (NumberFormatException e) {
                    System.out.println("Ungültige Zahl");
                }
                userAnswerWas = siSeCard.checkUserAnswer(answerSiSeCard, siSeCard);
                if(userAnswerWas){
                    cardRepository.save(siSeCard);
                }
                break;
            case "TextCard":
                TextCard textCard = (TextCard) card;
                userAnswerWas = textCard.checkUserAnswer(answer,textCard);
                if(userAnswerWas){
                    cardRepository.save(textCard);
                }
                break;
            default:
                // Handle the case when the card type is not recognized
                System.out.println("Unknown card type: " + card.getDType());
                // You can return an error message or throw an exception depending on your needs
                break;
        }
        if(userAnswerWas){
            dueDateManager.updateDueDate(card);
            cardRepository.save(card);
            return true;
        }
        return false;
    }
}
