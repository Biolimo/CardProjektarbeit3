package com.example.Card.Cards;

import com.example.Card.Cards.CardUtils.Answer;
import com.example.Card.Cards.CardUtils.DueDateManager;
import com.example.Card.Cards.CardUtils.UpdateInformation;
import com.example.Card.Cards.DataBase.CardRepository;
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
            card.setQuestion(updateInformation.getQuestion());
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

    public boolean checkUserAnswer(Long cardId, Answer answer) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new IllegalStateException(
                        "card with id " + cardId + "is not found"));

        boolean userAnswerWas = false;

        if(answer != null) {
            switch (card.getDType()) {
                case "MuSeCard":
                    MuSeCard muSeCard = (MuSeCard) card;
                    if (answer.getUserAnswerMuSe() == null)
                        throw new IllegalStateException("MuSeCard needs to be answered with userAnswerMuSe variable");
                    userAnswerWas = muSeCard.checkUserAnswer(answer.getUserAnswerMuSe(), muSeCard);
                    break;
                case "IntCard":
                    if(answer.getAnswerIntC() == 0)
                        throw new IllegalStateException("IntCard needs to be answered with answerIntC variable also answer cant be 0");
                    IntCard intCard = (IntCard) card;
                    userAnswerWas = intCard.checkUserAnswer(answer.getAnswerIntC(), intCard);
                    break;
                case "DoubleCard":
                    if(answer.getAnswerDC() == 0)
                        throw new IllegalStateException("DoubleCard needs to be answered with answerDC variable also answer cant be 0");
                    DoubleCard doubleCard = (DoubleCard) card;
                    userAnswerWas = doubleCard.checkUserAnswer(answer.getAnswerDC(), doubleCard);
                    break;
                case "LongCard":
                    if(answer.getAnswerLC() == null)
                        throw new IllegalStateException("LongCard needs to be answered with answerLC variable");
                    LongCard longCard = (LongCard) card;
                    userAnswerWas = longCard.checkUserAnswer(answer.getAnswerLC(), longCard);
                    break;
                case "SiSeCard":
                    if(answer.getAnswerIntC() == 0)
                        throw new IllegalStateException("SiSeCard needs to be answered with answerIntC variable");
                    SiSeCard siSeCard = (SiSeCard) card;
                    userAnswerWas = siSeCard.checkUserAnswer(answer.getAnswerIntC(), siSeCard);
                    break;
                case "TextCard":
                    if(answer.getAnswerTC() == null)
                        throw new IllegalStateException("TextCard needs to be answered with answerTC variable");
                    TextCard textCard = (TextCard) card;
                    userAnswerWas = textCard.checkUserAnswer(answer.getAnswerTC(), textCard);
                    break;
                default:
                    // Handle the case when the card type is not recognized
                    System.out.println("Unknown card type: " + card.getDType());
                    // You can return an error message or throw an exception depending on your needs
                    break;
            }
        }else throw new IllegalStateException("there was no answer given");
        if(userAnswerWas){
            dueDateManager.updateDueDate(card);
            cardRepository.save(card);
            return true;
        }
        return false;
    }
}
