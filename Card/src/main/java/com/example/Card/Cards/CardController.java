package com.example.Card.Cards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController()
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

    @PostMapping
    public void addNewCard(@RequestBody Card card){
        cardService.addNewCard(card);
    }


    @DeleteMapping(path = "{cardId}")
    public void deleteCard(@PathVariable("cardId") Long cardId){
        cardService.deleteCard(cardId);
    }

    @PutMapping(path = "{cardId}")
    public void updateCard(
            @PathVariable("cardId")Long cardId,
            @RequestParam(required = false) String question,
            @RequestParam(required = false) LocalDate dueDate){
        System.out.println(question);
        cardService.updateCard(cardId, question, dueDate);

    }

}
