package com.example.Card.Cards.CardUtils;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionAndId {

    String question;
    Long cardId;
    String cardType;
    String[] answers;

    public QuestionAndId(String question, long id, String cardType) {
        this.question = question;
        this.cardId = id;
        this.cardType = cardType;
    }

    public QuestionAndId(String question, long id, String cardType, String[] answers) {
        this.question = question;
        this.cardId = id;
        this.cardType = cardType;
        this.answers = answers;
    }

    public QuestionAndId(String question) {
        this.question = question;
    }
}
