package com.example.Card.CardSet;

import java.util.*;

import com.example.Card.Cards.Card;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
@NoArgsConstructor
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
    private String name;


    //relationship is one-to-many
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY) //when card in cardset is changed it should also change in card, fetch type lazy for performance.
    @JoinColumn(name = "cardSetId") // This is the foreign key in the Card table
    private Set<Card> cards;


    public CardSet(String name) {
        this.name = name;
    }

    public void addCards(Card card) {
        this.cards.add(card);
    }
}
