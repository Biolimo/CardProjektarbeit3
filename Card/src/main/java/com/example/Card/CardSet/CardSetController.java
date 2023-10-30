package com.example.Card.CardSet;


import com.example.Card.Cards.Card;
import com.example.Card.Cards.CardUtils.QuestionAndId;
import com.example.Card.Cards.KinderKarten.*;
import com.example.Card.Cards.KinderKarten.NumberKinder.DoubleCard;
import com.example.Card.Cards.KinderKarten.NumberKinder.IntCard;
import com.example.Card.Cards.KinderKarten.NumberKinder.LongCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<CardSet>> getCardSet(){
        return new ResponseEntity<>(cardSetService.getCardSet(), HttpStatus.OK);
    }

    //Adds a new CardSet
    @PostMapping
    public ResponseEntity<CardSet> addNewCardSet(@RequestBody CardSet cardSet){
        return new ResponseEntity<>(cardSetService.addNewCardSet(cardSet), HttpStatus.CREATED);
    }

    //Delete a CardSet
    @DeleteMapping(path = "{cardSetId}")
    public ResponseEntity<String> deleteCardSet(@PathVariable("cardSetId") Long cardSetId){
        return new ResponseEntity<>(cardSetService.deleteCard(cardSetId),HttpStatus.NO_CONTENT);
    }

    //Updates a CardSet
    @PutMapping(path = "{cardSetId}")
    public ResponseEntity<String> updateCardSet(
            @PathVariable("cardSetId")Long cardSetId,
            @RequestBody String name){
        return new ResponseEntity<>(cardSetService.updateCardSet(cardSetId, name), HttpStatus.OK);
    }


    //Add the different Cards to the set.
    @PostMapping(path = "{cardSetId}/TextCard")
    public ResponseEntity<Card> addTextCardToSet(
            @PathVariable("cardSetId") Long cardSetId,
            @RequestBody TextCard card
    ){
        return new ResponseEntity<>(cardSetService.addCardToSet(cardSetId, card), HttpStatus.CREATED);
    }
    @PostMapping(path = "{cardSetId}/IntCard")
    public ResponseEntity<Card> addIntCardToSet(
            @PathVariable("cardSetId") Long cardSetId,
            @RequestBody IntCard card
    ){
        return new ResponseEntity<>(cardSetService.addCardToSet(cardSetId, card), HttpStatus.CREATED);
    }
    @PostMapping(path = "{cardSetId}/DoubleCard")
    public ResponseEntity<Card> addDoubleCardToSet(
            @PathVariable("cardSetId") Long cardSetId,
            @RequestBody DoubleCard card
    ){
        return new ResponseEntity<>(cardSetService.addCardToSet(cardSetId, card), HttpStatus.CREATED);
    }
    @PostMapping(path = "{cardSetId}/LongCard")
    public ResponseEntity<Card> addLongCardToSet(
            @PathVariable("cardSetId") Long cardSetId,
            @RequestBody LongCard card
    ){
        return new ResponseEntity<>(cardSetService.addCardToSet(cardSetId, card), HttpStatus.CREATED);
    }
    @PostMapping(path = "{cardSetId}/MuSeCard")
    public ResponseEntity<Card> addMuSeCardToSet(
            @PathVariable("cardSetId") Long cardSetId,
            @RequestBody MuSeCard card
    ){
        return new ResponseEntity<>(cardSetService.addCardToSet(cardSetId, card), HttpStatus.CREATED);
    }
    @PostMapping(path = "{cardSetId}/SiSeCard")
    public ResponseEntity<Card> addSiSeCardToSet(
            @PathVariable("cardSetId") Long cardSetId,
            @RequestBody SiSeCard card
    ){
        return new ResponseEntity<>(cardSetService.addCardToSet(cardSetId, card), HttpStatus.CREATED);
    }


    //get A question from a CardSet
    @PutMapping("{cardSetId}/answerDueCard")
    public ResponseEntity<QuestionAndId> answerDueCard(
            @PathVariable("cardSetId") Long cardSetId){
        return new ResponseEntity<>(cardSetService.getQuestionFromCardSetByDueDate(cardSetId), HttpStatus.OK);
    }

    @PutMapping("{cardSetId}/answerRandomCard")
    public ResponseEntity<QuestionAndId> answerRandomCard(
            @PathVariable("cardSetId") Long cardSetId){
        return new ResponseEntity<>(cardSetService.getQuestionFromCardSetByRandom(cardSetId), HttpStatus.OK);
    }

    @PutMapping("{cardSetId}/answerLowSuccesscountCard")
    public ResponseEntity<QuestionAndId> answerLowSuccessCountCard(
            @PathVariable("cardSetId") Long cardSetId){
        return new ResponseEntity<>(cardSetService.getQuestionFromCardSetBySuccessCount(cardSetId), HttpStatus.OK);
    }

}

