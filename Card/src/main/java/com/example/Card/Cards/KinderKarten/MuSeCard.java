package com.example.Card.Cards.KinderKarten;

import com.example.Card.Cards.Card;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class MuSeCard extends Card {

    private String[] answers;
    private int[] correctAnswers;


    //check if answer was right if so return true and add 1 to successCounter
    public boolean checkUserAnswer(int[] userAnswers, MuSeCard muSeCard){
        System.out.println("userAnswers = " + userAnswers[0]);
        System.out.println("userAnswers = " + userAnswers[1]);

        if(areAllNumbersInArray(muSeCard.getCorrectAnswers(), userAnswers)){
            muSeCard.setSuccessCounter(getSuccessCounter()+1);
        }
        return areAllNumbersInArray(userAnswers, muSeCard.getCorrectAnswers());
    }

    public static boolean areAllNumbersInArray(int[] array1, int[] array2) {

        return Arrays.equals(array1, array2);
    }

}