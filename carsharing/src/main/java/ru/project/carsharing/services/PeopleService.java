package ru.project.carsharing.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.project.carsharing.enums.Role;
import ru.project.carsharing.model.Car;
import ru.project.carsharing.model.Image;
import ru.project.carsharing.model.Person;
import ru.project.carsharing.repositories.PeopleRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;

    public List<Person> findAll(){
        return peopleRepository.findAll();
    }

    public Person findOne(int id){
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void addPerson(Person person){
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.getRoles().add(Role.ROLE_USER);
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatePerson){
        updatePerson.setId(id);
        peopleRepository.save(updatePerson);
    }

    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }


    public Optional<Person> getPersonBySurname(String username){
        return peopleRepository.findByUsername(username);
    }

    public boolean isPersonExist(String username){
        if (peopleRepository.findPersonByUsername(username)==null){
            return false;
        }return true;
    }

    public Person getPersonByPrincipal(Principal p){
        if (p==null) return new Person();
        return peopleRepository.findPersonByUsername(p.getName());
    }

    public void setAvatar(Image image, Person person){
        person.setAvatar(image);
        peopleRepository.save(person);
    }

    //TODO: ЭТО БУДЕТ ОЧЕНЬ ВАЖНЫЙ МЕТОД, надо подумать над реализацией, когда человек берёт машину
//    public List<Car> getProductsByPersonId(int id){
//
//    }
}
