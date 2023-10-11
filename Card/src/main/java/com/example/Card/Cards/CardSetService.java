package com.example.Card.Cards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
