package com.project.carshar.services;

import com.project.carshar.model.Tax;
import com.project.carshar.repositories.TaxRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaxService {

    private final TaxRepository repository;

    public Iterable<Tax> findAll() {
        return repository.findAll();
    }

    public Tax findById(long id) {
        return repository.findById(id);
    }

    @Transactional
    public void delete(Tax tax) {
        repository.delete(tax);
    }

    @Transactional
    public void save(Tax tax) throws Exception{
        int sum = tax.getCar().getInsurance();
        tax.setSum(sum);
        tax.setDate(LocalDate.now());
        repository.save(tax);
    }
}