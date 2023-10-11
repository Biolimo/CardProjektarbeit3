package com.example.Card.Cards.KinderKarten;

import com.example.Card.Cards.Card;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
import java.util.Arrays;

@Data
@Entity
public class MuSeCard extends Card {

    private String[] answers;
    private int[] correctAnswers;

    public MuSeCard(LocalDate dueDate, String question, String[] answers, int[] correctAnswer) {
        super(dueDate, question);
        this.answers = answers;
        this.correctAnswers = correctAnswer;
    }

    public MuSeCard() {

    }

    //check if answer was right if so return true and add 1 to successCounter
    public boolean checkUserAnswer(int[] userAnswers, MuSeCard muSeCard){
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