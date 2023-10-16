package com.example.Card.CardSet;


import com.example.Card.Cards.KinderKarten.*;
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
    public void addNewCardSet(@RequestBody CardSet cardSet){
        cardSetService.addNewCardSet(cardSet);
    }

    //Delete a CardSet
    @DeleteMapping(path = "{cardSetId}")
    public void deleteCardSet(@PathVariable("cardSetId") Long cardSetId){
        cardSetService.deleteCard(cardSetId);
    }

    //Updates a CardSet
    @PostMapping(path = "{cardSetId}")
    public void updateCardSet(
            @PathVariable("cardSetId")Long cardSetId,
            @RequestBody String name){
        System.out.println("Test : name = " + name);
        cardSetService.updateCardSet(cardSetId, name);
    }


    //Add the different Cards to the set.
    @PostMapping(path = "{cardSetId}/TextCard")
    public void addTextCardToSet(
            @PathVariable("cardSetId") Long cardSetId,
            @RequestBody TextCard card
    ){
        System.out.println("the CardSetId is = " + cardSetId);
        cardSetService.addCardToSet(cardSetId, card);
    }
    @PostMapping(path = "{cardSetId}/NumberCard")
    public void addNumberCardToSet(
            @PathVariable("cardSetId") Long cardSetId,
            @RequestBody NumberCard card
    ){
        System.out.println("the CardSetId is = " + cardSetId);
        cardSetService.addCardToSet(cardSetId, card);
    }
    @PostMapping(path = "{cardSetId}/MuSeCard")
    public void addMuSeCardToSet(
            @PathVariable("cardSetId") Long cardSetId,
            @RequestBody MuSeCard card
    ){
        System.out.println("the CardSetId is = " + cardSetId);
        cardSetService.addCardToSet(cardSetId, card);
    }
    @PostMapping(path = "{cardSetId}/SiSeCard")
    public void addSiSeCardToSet(
            @PathVariable("cardSetId") Long cardSetId,
            @RequestBody SiSeCard card
    ){
        System.out.println("the CardSetId is = " + cardSetId);
        cardSetService.addCardToSet(cardSetId, card);
    }


    //get A question from a CardSet
    @PutMapping("{cardSetId}/answerDueCard")
    public String answerDueCard(
            @PathVariable("cardSetId") Long cardSetId){
        return cardSetService.getQuestionFromCardSetByDueDate(cardSetId);
    }

    @PutMapping("{cardSetId}/answerRandomCard")
    public String answerRandomCard(
            @PathVariable("cardSetId") Long cardSetId){
        return null;
    }

    @PutMapping("{cardSetId}/answerLowSuccesscountCard")
    public String answerLowSuccesscountCard(
            @PathVariable("cardSetId") Long cardSetId){
        return null;
    }
}
