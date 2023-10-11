package com.example.Card.Cards;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class CardSet {

    @Id
    @SequenceGenerator(
            name = "cardset_sequence",
            sequenceName = "cardset_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cardset_sequence"
    )

    private long id;
    private LocalDate creationDate;
    private LocalDate dueDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "cardSerId") // This is the foreign key in the Card table
    private List<Card> cards;

    public CardSet() {
    }

    public CardSet(LocalDate creationDate, LocalDate dueDate, List<Card> cards) {
        this.creationDate = creationDate;
        this.dueDate = dueDate;
        this.cards = cards;
    }

    public void addCards(Card card, long cardid){
        cards.add(card);
    }
}
