package com.example.Card.CardSet;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CardSetService {

    private final CardSetRepository cardSetRepository;

    @Autowired
    public CardSetService(CardSetRepository cardSetRepository){
        this.cardSetRepository = cardSetRepository;
    }

    public List<CardSet> getCardSet(){
        return cardSetRepository.findAll();
    }

    public void addNewCardSet(CardSet cardSet){
        Optional<CardSet> cardSetByName = cardSetRepository.findCardSetByName(cardSet.getName());
        if(cardSetByName.isPresent()){
            throw new IllegalStateException("CardSet already exists ID = " + cardSetByName.get().getId());
        }
        cardSetRepository.save(cardSet);
    }

    public void deleteCard(Long cardSetId){
        boolean exists = cardSetRepository.existsById(cardSetId);
        if(!exists){
            throw new IllegalStateException("cardSet with id " + cardSetId + "is not found");
        }
        cardSetRepository.deleteById(cardSetId);
    }

    @Transactional
    public void updateCardSet(
            long cardSetId,
            String name,
            LocalDate dueDate){
        CardSet cardSet = cardSetRepository.findById(cardSetId).orElseThrow(() -> new IllegalStateException(
                "card with id " + cardSetId + "is not found"));

        if(name != null){
            cardSet.setName(name);
        }
        if(dueDate != null){
            cardSet.setDueDate(dueDate);
        }
        System.out.println("Test update CardSet last print");
    }

}
