package com.example.Card.Cards;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Card {
    @Id
    @SequenceGenerator(
            name = "card_sequence",
            sequenceName = "card_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "card_sequence"
    )
    private long id;
    private LocalDate creationDate;
    private LocalDate dueDate;
    private int successCounter;
    private boolean isDrafted;
    private String question;

    private static long idCounter = 0;

    public Card() {
    }

    /*public Card(LocalDate dueDate, String question) {
        this.id = idCounter;
        idCounter++;
        this.creationDate = LocalDate.now();
        this.successCounter = 0;
        this.isDrafted = false;
        this.question = question;
        this.dueDate = dueDate;
    }*/
    public Card(LocalDate dueDate, String question) {
        this.creationDate = LocalDate.now();
        this.successCounter = 0;
        this.isDrafted = false;
        this.question = question;
        this.dueDate = dueDate;
    }
}
