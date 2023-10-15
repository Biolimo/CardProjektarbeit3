package com.example.Card.Cards.KinderKarten;

import com.example.Card.Cards.Card;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Entity
@NoArgsConstructor
public class SiSeCard extends Card {

    private String[] answers;
    private int correctAnswer;

    //check if answer was right if so return true and add 1 to successCounter
    public boolean checkUserAnswer(int userAnswer, SiSeCard siSeCard){
        if(userAnswer == siSeCard.getCorrectAnswer()) siSeCard.setSuccessCounter(siSeCard.getSuccessCounter()+1);
        return userAnswer == siSeCard.getCorrectAnswer();
    }

}
