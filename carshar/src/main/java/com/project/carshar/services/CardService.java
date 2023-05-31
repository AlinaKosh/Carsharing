package com.project.carshar.services;

import com.project.carshar.model.Card;
import com.project.carshar.repositories.CardRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Transactional(readOnly = true)
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    @Transactional
    public Card save(Card card){
        return cardRepository.save(card);
    }

    public Optional<Card> findById(long id){
        return cardRepository.findById(id);
    }

    public List<Card> findAll(){
        return cardRepository.findAll();
    }

    public List<Card> findAllByCarId(long id){
        return cardRepository.findAllByCarId(id);
    }
}
