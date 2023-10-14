package com.example.Card.Cards;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@Table
@NoArgsConstructor
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

    private LocalDate creationDate = LocalDate.now();
    private LocalDate dueDate;
    private int successCounter = 0;
    private boolean isDrafted = false;
    private String question;

    public Card(LocalDate dueDate, String question) {
        this.question = question;
        this.dueDate = dueDate;
    }
}
