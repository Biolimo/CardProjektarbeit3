package com.example.Card.CardSet;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionAndId {

    String question;
    Long cardId;
    String cardType;

    public QuestionAndId(String question, long id, String cardType) {
        this.question = question;
        this.cardId = id;
        this.cardType = cardType;
    }

    public QuestionAndId(String question) {
        this.question = question;
    }
}
