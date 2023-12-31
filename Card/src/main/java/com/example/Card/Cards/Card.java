package com.example.Card.Cards;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
@NoArgsConstructor
public abstract class Card {
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

    private String question;
    private LocalDate dueDate = LocalDate.now();
    private LocalDate creationDate = LocalDate.now();
    private int successCounter = 0;

    @Column(name = "dtype", insertable = false, updatable = false)
    private String dType;

    public Card(String question) {
        this.question = question;
    }

}
