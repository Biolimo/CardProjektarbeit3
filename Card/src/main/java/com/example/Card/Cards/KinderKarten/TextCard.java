package com.example.Card.Cards.KinderKarten;

import com.example.Card.Cards.Card;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;
@Data
public class TextCard extends Card {
    private String awnser;

    public TextCard(
                    LocalDate dueDate,
                    String question,
                    String awnser) {
        super(dueDate, question);
        this.awnser = awnser;
    }
    //check if awnser was right if so return true and add 1 to successCounter
    public boolean checkUserAwnser(String userAwnser, TextCard textCard){
        if(Objects.equals(userAwnser, textCard.getAwnser())) setSuccessCounter(getSuccessCounter()+1);
        return Objects.equals(userAwnser, textCard.getAwnser());
    }
}

