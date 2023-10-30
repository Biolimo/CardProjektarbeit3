package com.example.Card.Cards;

import com.example.Card.Cards.CardUtils.Answer;
import com.example.Card.Cards.CardUtils.UpdateInformation;
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
@RequestMapping("/card")
public class CardController {
    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping()
    public ResponseEntity<List<Card>> getCards(){
        return new ResponseEntity<>(cardService.getCard(), HttpStatus.OK);
    }

    //Post Methods for all Cards

    //Adds a TextCard
    @PostMapping("TextCard")
    public ResponseEntity<TextCard> addNewTextCard(@RequestBody TextCard textCard){
        return new ResponseEntity<>((TextCard) cardService.addNewCard(textCard), HttpStatus.CREATED);
    }

    //Adds a NumberCard
    @PostMapping("IntCard")
    public ResponseEntity<IntCard> addNewNumberCard(@RequestBody IntCard intCard){
        return new ResponseEntity<>((IntCard) cardService.addNewCard(intCard), HttpStatus.CREATED);
    }

    //Adds a DoubleCard
    @PostMapping("DoubleCard")
    public ResponseEntity<DoubleCard> addNewDoubleCard(@RequestBody DoubleCard doubleCard){
        return new ResponseEntity<>((DoubleCard) cardService.addNewCard(doubleCard), HttpStatus.CREATED);
    }

    //Adds a LongCard
    @PostMapping("LongCard")
    public ResponseEntity<LongCard> addNewLongCard(@RequestBody LongCard longCard){
        return new ResponseEntity<>((LongCard) cardService.addNewCard(longCard), HttpStatus.CREATED);
    }

    //Adds a SiSeCard
    @PostMapping("SiSeCard")
    public ResponseEntity<SiSeCard> addNewSiSeCard(@RequestBody SiSeCard siSeCard){
        return new ResponseEntity<>((SiSeCard) cardService.addNewCard(siSeCard),HttpStatus.CREATED);
    }

    //Adds a MuSeCard
    @PostMapping("MuSeCard")
    public ResponseEntity<MuSeCard> addNewMuSeCard(@RequestBody MuSeCard muSeCard){
        return new ResponseEntity<>((MuSeCard) cardService.addNewCard(muSeCard), HttpStatus.CREATED);
    }


    //Method to delete Cards
    @DeleteMapping(path = "{cardId}")
    public ResponseEntity<String> deleteCard(@PathVariable("cardId") Long cardId){
        return new ResponseEntity<>(cardService.deleteCard(cardId), HttpStatus.NO_CONTENT);
    }

    //Method to change Cards
    @PutMapping(path = "{cardId}/update")
    public ResponseEntity<String> updateCard(
            @PathVariable("cardId")Long cardId,
            @RequestBody UpdateInformation updateInformation){

        return new ResponseEntity<>(cardService.updateCard(cardId, updateInformation), HttpStatus.OK);
    }

    @PutMapping(path = "{cardId}/answer")
    public boolean checkUserAnswer(
            @PathVariable("cardId")Long cardId,
            @RequestBody Answer answer)
    {
        return cardService.checkUserAnswer(cardId, answer);
    }

}
