package com.example.Card.CardSet;


import com.example.Card.Cards.Card;
import com.example.Card.Cards.CardUtils.QuestionAndId;
import com.example.Card.Cards.KinderKarten.*;
import com.example.Card.Cards.KinderKarten.NumberKinder.DoubleCard;
import com.example.Card.Cards.KinderKarten.NumberKinder.IntCard;
import com.example.Card.Cards.KinderKarten.NumberKinder.LongCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/cardSet")
public class CardSetController {

    private final CardSetService cardSetService;
    @Autowired
    public CardSetController(CardSetService cardSetService) {
        this.cardSetService = cardSetService;
    }

    //Returns all CardSets
    @GetMapping
    public List<CardSet> getCardSet(){
        return cardSetService.getCardSet();
    }

    //Adds a new CardSet
    @PostMapping
    public CardSet addNewCardSet(@RequestBody CardSet cardSet){
        return cardSetService.addNewCardSet(cardSet);
    }

    //Delete a CardSet
    @DeleteMapping(path = "{cardSetId}")
    public String deleteCardSet(@PathVariable("cardSetId") Long cardSetId){
        return cardSetService.deleteCard(cardSetId);
    }

    //Updates a CardSet
    @PostMapping(path = "{cardSetId}")
    public String updateCardSet(
            @PathVariable("cardSetId")Long cardSetId,
            @RequestBody String name){
        return cardSetService.updateCardSet(cardSetId, name);
    }


    //Add the different Cards to the set.
    @PostMapping(path = "{cardSetId}/TextCard")
    public Card addTextCardToSet(
            @PathVariable("cardSetId") Long cardSetId,
            @RequestBody TextCard card
    ){
        System.out.println("the CardSetId is = " + cardSetId);
        return cardSetService.addCardToSet(cardSetId, card);
    }
    @PostMapping(path = "{cardSetId}/IntCard")
    public Card addNumberCardToSet(
            @PathVariable("cardSetId") Long cardSetId,
            @RequestBody IntCard card
    ){
        System.out.println("the CardSetId is = " + cardSetId);
        return cardSetService.addCardToSet(cardSetId, card);
    }
    @PostMapping(path = "{cardSetId}/DoubleCard")
    public Card addDoubleCardToSet(
            @PathVariable("cardSetId") Long cardSetId,
            @RequestBody DoubleCard card
    ){
        System.out.println("the CardSetId is = " + cardSetId);
        return cardSetService.addCardToSet(cardSetId, card);
    }
    @PostMapping(path = "{cardSetId}/LongCard")
    public Card addLongCardToSet(
            @PathVariable("cardSetId") Long cardSetId,
            @RequestBody LongCard card
    ){
        System.out.println("the CardSetId is = " + cardSetId);
        return cardSetService.addCardToSet(cardSetId, card);
    }
    @PostMapping(path = "{cardSetId}/MuSeCard")
    public Card addMuSeCardToSet(
            @PathVariable("cardSetId") Long cardSetId,
            @RequestBody MuSeCard card
    ){
        System.out.println("the CardSetId is = " + cardSetId);
        return cardSetService.addCardToSet(cardSetId, card);
    }
    @PostMapping(path = "{cardSetId}/SiSeCard")
    public Card addSiSeCardToSet(
            @PathVariable("cardSetId") Long cardSetId,
            @RequestBody SiSeCard card
    ){
        System.out.println("the CardSetId is = " + cardSetId);
        return cardSetService.addCardToSet(cardSetId, card);
    }


    //get A question from a CardSet
    @PutMapping("{cardSetId}/answerDueCard")
    public QuestionAndId answerDueCard(
            @PathVariable("cardSetId") Long cardSetId){
        return cardSetService.getQuestionFromCardSetByDueDate(cardSetId);
    }

    @PutMapping("{cardSetId}/answerRandomCard")
    public QuestionAndId answerRandomCard(
            @PathVariable("cardSetId") Long cardSetId){
        return cardSetService.getQuestionFromCardSetByRandom(cardSetId);
    }

    @PutMapping("{cardSetId}/answerLowSuccesscountCard")
    public QuestionAndId answerLowSuccessCountCard(
            @PathVariable("cardSetId") Long cardSetId){
        return cardSetService.getQuestionFromCardSetBySuccessCount(cardSetId);
    }

}

