package com.example.Card.Cards;

import com.example.Card.Cards.KinderKarten.*;
import com.example.Card.Cards.KinderKarten.NumberKinder.DoubleCard;
import com.example.Card.Cards.KinderKarten.NumberKinder.LongCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/card")
public class CardController {
    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping()
    public List<Card> getCards(){
        return cardService.getCard();
    }

    //Post Methods for all Cards

    //Adds a normal Card
    @PostMapping("Card")
    public void addNewCard(@RequestBody Card card){
        cardService.addNewCard(card);
    }

    //Adds a TextCard
    @PostMapping("TextCard")
    public void addNewTextCard(@RequestBody TextCard textCard){
        cardService.addNewCard(textCard);
    }

    //Adds a NumberCard
    @PostMapping("NumberCard")
    public void addNewNumberCard(@RequestBody NumberCard numberCard){
        cardService.addNewCard(numberCard);
    }

    @PostMapping("DoubleCard")
    public void addNewDoubleCard(@RequestBody DoubleCard doubleCard){
        cardService.addNewCard(doubleCard);
    }

    @PostMapping("LongCard")
    public void addNewLongCard(@RequestBody LongCard longCard){
        cardService.addNewCard(longCard);
    }

    //Adds a SiSeCard
    @PostMapping("SiSeCard")
    public void addNewSiSeCard(@RequestBody SiSeCard siSeCard){
        cardService.addNewCard(siSeCard);
    }

    //Adds a MuSeCard
    @PostMapping("MuSeCard")
    public void addNewMuSeCard(@RequestBody MuSeCard muSeCard){
        cardService.addNewCard(muSeCard);
    }


    //Method to delete Cards
    @DeleteMapping(path = "{cardId}")
    public void deleteCard(@PathVariable("cardId") Long cardId){
        cardService.deleteCard(cardId);
    }

    //Method to change Cards
    @PostMapping(path = "{cardId}/update")
    public void updateCard(
            @PathVariable("cardId")Long cardId,
            @RequestBody UpdateInformation updateInformation){

        cardService.updateCard(cardId, updateInformation);
    }

    @PutMapping(path = "{cardId}/answer")
    public boolean checkUserAnswer(
            @PathVariable("cardId")Long cardId,
            @RequestParam String answer)
    {
        System.out.println("Test : question = " + answer);
        return cardService.checkUserAnswer(cardId, answer);
    }

}
