package com.project.carshar.repositories;

import com.project.carshar.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findById(Long id);
    Optional<Card> findByCarId(long id);

    Optional<Card> findCardById(long id);

    List<Card> findAllByCarId(long id);
}
