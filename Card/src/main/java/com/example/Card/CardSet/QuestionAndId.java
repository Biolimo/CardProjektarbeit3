package com.example.Card.CardSet;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionAndId {

    String question;
    Long cardId;

    public QuestionAndId(String question, long id) {
        this.question = question;
        this.cardId = id;
    }

    public QuestionAndId(String question) {
        this.question = question;
    }
}
