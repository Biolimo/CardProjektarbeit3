package com.example.Card.Cards.KinderKarten;

import com.example.Card.Cards.Card;
import com.example.Card.Cards.CardRepository;
import com.example.Card.Cards.CardService;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

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

        if(areAllNumbersInArray(userAnswers, muSeCard.getCorrectAnswers())) muSeCard.setSuccessCounter(getSuccessCounter()+1);
        return areAllNumbersInArray(userAnswers, muSeCard.getCorrectAnswers());
    }

    public static boolean areAllNumbersInArray(int[] array1, int[] array2) {
        // Convert array2 to a List for easy lookup
        Integer[] array2Integer = Arrays.stream(array2).boxed().toArray(Integer[]::new);

        // Check if all numbers from array1 are in array2
        for (int num : array1) {
            if (!Arrays.asList(array2Integer).contains(num)) {
                return false; // If a number from array1 is not in array2, return false
            }
        }
        return true; // All numbers from array1 are in array2
    }

}