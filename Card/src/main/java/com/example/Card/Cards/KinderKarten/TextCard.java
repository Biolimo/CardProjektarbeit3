package com.example.Card.Cards.KinderKarten;

import com.example.Card.Cards.Card;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
@Data
@Entity
@NoArgsConstructor
public class TextCard extends Card {

    private String answerTC;

    //check if answer was right if so return true and add 1 to successCounter
    public boolean checkUserAnswer(String userAnswer, TextCard textCard){
        if(Objects.equals(userAnswer, textCard.getAnswerTC())) setSuccessCounter(getSuccessCounter()+1);
        return Objects.equals(userAnswer, textCard.getAnswerTC());
    }
}

