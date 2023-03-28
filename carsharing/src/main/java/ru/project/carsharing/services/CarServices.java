package ru.project.carsharing.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.project.carsharing.model.Car;
import ru.project.carsharing.model.Person;
import ru.project.carsharing.repositories.CarRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CarServices {
    private final CarRepository carRepository;

    @Autowired
    public CarServices(CarRepository productRepository) {
        this.carRepository = productRepository;
    }

    public List<Car> findAll(){
        return carRepository.findAll();
    }

    public Car findOne(int id){
        Optional<Car> car = carRepository.findById(id);
        return car.orElse(null);
    }

    @Transactional
    public void save(Car car){
        carRepository.save(car);
    }

    public void update(int id, Car updateCar){
        Car carToBeUpdate = carRepository.findById(id).get();

        updateCar.setId(id);
        updateCar.setPerson(carToBeUpdate.getPerson());

        carRepository.save(updateCar);
    }

    @Transactional
    public void delete(int id){
        carRepository.deleteById(id);
    }

    public Person getPerson(int id){
        return carRepository.findById(id).map((car->car.getPerson())).orElse(null);
    }

    //Метод необходим, если человек освобождает машину
    @Transactional
    public void release(int id){
        carRepository.findById(id).ifPresent(
                car -> {
                    car.setPerson(null);
                    car.setTakenAt(null);
                }
        );
    }

    //Метод необходим, если человек берёт машину на прокат
    @Transactional
    public void assign(int id, Person selectPerson){
        carRepository.findById(id).ifPresent(car -> {
            car.setPerson(selectPerson);
            car.setTakenAt(new Date());
        });
    }

    public List<Car> searchByCarBrand(String carBrand){
        return carRepository.findByCarBrandStartingWith(carBrand);
    }
}
