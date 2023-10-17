package com.example.Card.Cards.KinderKarten.NumberKinder;

import com.example.Card.Cards.KinderKarten.NumberCard;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class DoubleCard extends NumberCard {

    private double answer;

    public boolean checkUserAnswer(double userAnswer, DoubleCard doubleCard){
        if(userAnswer == doubleCard.getAnswer()){
            doubleCard.setSuccessCounter(doubleCard.getSuccessCounter()+1);
        }
        return userAnswer == doubleCard.getAnswer();
    }

}
