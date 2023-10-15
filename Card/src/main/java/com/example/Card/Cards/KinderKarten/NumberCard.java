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
public class NumberCard extends Card{

        private int answer;

        //check if answer was right if so return true and add 1 to successCounter
        public boolean checkUserAnswer(int userAnswer, NumberCard numberCard){
            if(userAnswer == numberCard.getAnswer()) numberCard.setSuccessCounter(numberCard.getSuccessCounter()+1);
            return userAnswer == numberCard.getAnswer();
        }
}
