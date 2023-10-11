package com.example.Card.Cards.KinderKarten;

import com.example.Card.Cards.Card;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;
@Data
@Entity
public class TextCard extends Card {
    private String answer;

    public TextCard(
                    LocalDate dueDate,
                    String question,
                    String answer) {
        super(dueDate, question);
        this.answer = answer;
    }

    public TextCard() {
    }

    //check if answer was right if so return true and add 1 to successCounter
    public boolean checkUserAnswer(String userAnswer, TextCard textCard){
        if(Objects.equals(userAnswer, textCard.getAnswer())) setSuccessCounter(getSuccessCounter()+1);
        return Objects.equals(userAnswer, textCard.getAnswer());
    }
}
