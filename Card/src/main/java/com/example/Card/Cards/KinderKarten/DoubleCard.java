package com.example.Card.Cards.KinderKarten;

import com.example.Card.Cards.Card;
import com.example.Card.Cards.KinderKarten.NumberCard;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
public class DoubleCard extends Card {

    private double answer;

}
