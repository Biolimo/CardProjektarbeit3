package com.example.Card.Cards;

import com.example.Card.Cards.KinderKarten.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public void addNewNumberCard(@RequestBody NumberCard NumberCard){
        cardService.addNewCard(NumberCard);
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
    @PutMapping(path = "{cardId}")
    public void updateCard(
            @PathVariable("cardId")Long cardId,
            @RequestParam(required = false) String question,
            @RequestParam(required = false) LocalDate dueDate){
        System.out.println("Test : question = " + question + " dueDate = " + dueDate);
        cardService.updateCard(cardId, question, dueDate);

    }

}
