package com.example.Card.Cards;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping("/cardSet")
public class CardSetController {

    private final CardSetService cardSetService;

    @Autowired
    public CardSetController(CardSetService cardSetService) {
        this.cardSetService = cardSetService;
    }

    @GetMapping
    public List<CardSet> getCardSet(){
        return cardSetService.getCardSet();
    }

    @PostMapping
    public void addNewCardSet(@RequestBody CardSet cardSet){
        cardSetService.addNewCardSet(cardSet);
    }
    @DeleteMapping(path = "{cardSetId}")
    public void deleteCardSet(@PathVariable("cardSetId") Long cardSetId){
        cardSetService.deleteCard(cardSetId);
    }

    @PutMapping(path = "{cardSetId}")
    public void updateCardSet(
            @PathVariable("cardSetId")Long cardSetId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) LocalDate dueDate){
        System.out.println("Test : name = " + name + "dueDate = " + dueDate);
        cardSetService.updateCardSet(cardSetId, name, dueDate);
    }
}
