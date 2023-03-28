package ru.project.carsharing.model;



import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "Car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "carBrand")
    @NotEmpty(message = "Бренд у машины должен быть")
    private String carBrand;

    @Column(name = "numberCar")
    @NotEmpty(message = "Номер у машины должен быть")
    @Size(min = 3, message = "Минимум номер у машины должен быть 3 символа")
    private String numberCar;

    @Column(name = "yearOfRealize")
    @NotEmpty(message = "Введите год выпуска машины")
    private int yearOfRealize;

    @Column(name = "participationInAnAccident")
    @NotEmpty(message = "Вы должны ввести были ли машина в ДТП или нет")
    private String participationInAnAccident;

    @Column(name = "carMileage")
    @NotEmpty(message = "Введите пробег автомобиля")
    private String carMileage;

    @Column(name = "pts")
    @NotEmpty(message = "Введите есть ли у машины ПТС")
    private String pts;

    @ManyToOne
    @JoinColumn(name = "id_person", referencedColumnName = "id")
    private Person person;

    @Column(name = "takenAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date takenAt;

    public Car(){}

    public Car(String carBrand, String numberCar, int yearOfRealize) {
        this.carBrand = carBrand;
        this.numberCar = numberCar;
        this.yearOfRealize = yearOfRealize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getNumberCar() {
        return numberCar;
    }

    public void setNumberCar(String numberCar) {
        this.numberCar = numberCar;
    }

    public int getYearOfRealize() {
        return yearOfRealize;
    }

    public void setYearOfRealize(int yearOfRealize) {
        this.yearOfRealize = yearOfRealize;
    }

    public String getParticipationInAnAccident() {
        return participationInAnAccident;
    }

    public void setParticipationInAnAccident(String participationInAnAccident) {
        this.participationInAnAccident = participationInAnAccident;
    }

    public String getCarMileage() {
        return carMileage;
    }

    public void setCarMileage(String carMileage) {
        this.carMileage = carMileage;
    }

    public String getPts() {
        return pts;
    }

    public void setPts(String pts) {
        this.pts = pts;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Date getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(Date takenAt) {
        this.takenAt = takenAt;
    }
}
