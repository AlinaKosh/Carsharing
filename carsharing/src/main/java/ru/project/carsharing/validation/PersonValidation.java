package ru.project.carsharing.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.project.carsharing.model.Person;
import ru.project.carsharing.services.PeopleService;

@Component
@RequiredArgsConstructor
public class PersonValidation implements Validator {
    private final PeopleService peopleService;
    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (peopleService.isPersonExist(person.getUsername())){
            errors.rejectValue("email","","this email is already in use");
        }
    }
}
