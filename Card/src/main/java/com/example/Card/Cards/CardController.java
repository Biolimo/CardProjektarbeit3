package com.example.Card.Cards;

import com.example.Card.Cards.KinderKarten.*;
import com.example.Card.Cards.KinderKarten.NumberKinder.DoubleCard;
import com.example.Card.Cards.KinderKarten.NumberKinder.IntCard;
import com.example.Card.Cards.KinderKarten.NumberKinder.LongCard;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Card> getCards(){
        return cardService.getCard();
    }

    //Post Methods for all Cards

    //Adds a TextCard
    @PostMapping("TextCard")
    public TextCard addNewTextCard(@RequestBody TextCard textCard){
        return (TextCard) cardService.addNewCard(textCard);
    }

    //Adds a NumberCard
    @PostMapping("IntCard")
    public IntCard addNewNumberCard(@RequestBody IntCard intCard){
        return (IntCard) cardService.addNewCard(intCard);
    }

    //Adds a DoubleCard
    @PostMapping("DoubleCard")
    public DoubleCard addNewDoubleCard(@RequestBody DoubleCard doubleCard){
        return (DoubleCard) cardService.addNewCard(doubleCard);
    }

    //Adds a LongCard
    @PostMapping("LongCard")
    public LongCard addNewLongCard(@RequestBody LongCard longCard){
        return (LongCard) cardService.addNewCard(longCard);
    }

    //Adds a SiSeCard
    @PostMapping("SiSeCard")
    public SiSeCard addNewSiSeCard(@RequestBody SiSeCard siSeCard){
        return (SiSeCard) cardService.addNewCard(siSeCard);
    }

    //Adds a MuSeCard
    @PostMapping("MuSeCard")
    public MuSeCard addNewMuSeCard(@RequestBody MuSeCard muSeCard){
        return (MuSeCard) cardService.addNewCard(muSeCard);
    }


    //Method to delete Cards
    @DeleteMapping(path = "{cardId}")
    public String deleteCard(@PathVariable("cardId") Long cardId){
        return cardService.deleteCard(cardId);
    }

    //Method to change Cards
    @PostMapping(path = "{cardId}/update")
    public String updateCard(
            @PathVariable("cardId")Long cardId,
            @RequestBody UpdateInformation updateInformation){

        return cardService.updateCard(cardId, updateInformation);
    }

    @PutMapping(path = "{cardId}/answer")
    public boolean checkUserAnswer(
            @PathVariable("cardId")Long cardId,
            @RequestBody Answer answer)
    {
        System.out.println("Test : answer = " + answer);
        return cardService.checkUserAnswer(cardId, answer);
    }

}
