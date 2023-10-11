package com.example.Card.Cards.KinderKarten;

import com.example.Card.Cards.Card;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
@Data
@Entity
public class NumberCard extends Card{

        private int awnser;

        public NumberCard(
                LocalDate dueDate,
                String question,
                int awnser) {
            super(dueDate, question);
            this.awnser = awnser;
        }

        public NumberCard() {
        }

        //check if answer was right if so return true and add 1 to successCounter
        public boolean checkUserAnswer(int userAnswer, NumberCard numberCard){
            if(userAnswer == numberCard.getAwnser()) numberCard.setSuccessCounter(numberCard.getSuccessCounter()+1);
            return userAnswer == numberCard.getAwnser();
        }
}
