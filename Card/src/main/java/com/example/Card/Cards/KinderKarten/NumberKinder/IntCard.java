package com.example.Card.Cards.KinderKarten.NumberKinder;

import com.example.Card.Cards.DueDateManager;
import com.example.Card.Cards.KinderKarten.NumberCard;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class IntCard extends NumberCard {

    private int answer;


    public boolean checkUserAnswer(int userAnswer, IntCard intCard){
        if(userAnswer == intCard.getAnswer()){
            intCard.setSuccessCounter(intCard.getSuccessCounter()+1);
        }
        return userAnswer == intCard.getAnswer();
    }

}
