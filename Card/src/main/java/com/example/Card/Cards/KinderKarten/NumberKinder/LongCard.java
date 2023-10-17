package com.example.Card.Cards.KinderKarten.NumberKinder;

import com.example.Card.Cards.DueDateManager;
import com.example.Card.Cards.KinderKarten.NumberCard;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Objects;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class LongCard extends NumberCard {

    private Long answer;


    public boolean checkUserAnswer(Long userAnswer, LongCard longCard){
        if(Objects.equals(userAnswer, longCard.getAnswer())){
            longCard.setSuccessCounter(longCard.getSuccessCounter()+1);
        }
        return Objects.equals(userAnswer, longCard.getAnswer());
    }

}
