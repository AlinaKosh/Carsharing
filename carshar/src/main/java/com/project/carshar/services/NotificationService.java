package com.project.carshar.services;

import com.project.carshar.model.Notification;
import com.project.carshar.repositories.NotificationRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotificationService implements ToUpperCase{
    private final NotificationRepository repository;

    public Notification findById(long id) {
        return repository.findById(id);
    }

    public List<Notification> findByUser(long id){
        return repository.findByUserAndReadFalseOrderByDateDesc(id);
    }

    @Transactional
    public void delete(Notification notification){
        repository.delete(notification);
    }

    @Transactional
    public void save(Notification notification){
            repository.save(notification);
    }
}
