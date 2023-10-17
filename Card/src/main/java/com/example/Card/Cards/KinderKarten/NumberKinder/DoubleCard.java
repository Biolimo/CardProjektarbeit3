package com.example.Card.Cards.KinderKarten.NumberKinder;

import com.example.Card.Cards.KinderKarten.NumberCard;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
public class DoubleCard extends NumberCard {

    private double answer;

    public boolean checkUserAnswer(double userAnswer, DoubleCard doubleCard){
        if(userAnswer == doubleCard.getAnswer()) doubleCard.setSuccessCounter(doubleCard.getSuccessCounter()+1);
        return userAnswer == doubleCard.getAnswer();
    }

}
