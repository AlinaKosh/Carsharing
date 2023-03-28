package ru.project.carsharing.model;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import ru.project.carsharing.enums.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Person")
@Data
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Transient
    private String transientDob;

    @Column(name = "username")
    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов диной")
    private String username;

    @Column(name = "password")
    @NotEmpty(message = "Пароль должен быть")
    private String password;

    @Column(name = "firstName")
    @NotEmpty
    @Size(min = 2, max = 100, message = "Имя должно быть в диапазоне от 2 до 100")
    private String firstName = "";

    @Column(name = "lastName")
    @Size(min = 2, max = 100, message = "")
    private String lastName = "Отчество должно быть в диапазоне от 2 до 100";

    @Column(name = "middleName")
    @Size(min = 2, max = 100, message = "Фамилия должна быть в диапазоне от 2 до 100")
    private String middleName = "";

    @Column(name = "dateOfBirth")
   // @NotEmpty(message = "Возраст должен быть введён")
    private LocalDateTime dateOfBirth = LocalDateTime.now();

    @Column(name = "email")
    @Email
    @NotEmpty(message = "Почта должна быть введена")
    private String email;

    @Column(name = "passport")
   // @NotEmpty(message = "Данные паспорта должны быть введены")
 //   @Size(min = 10, message = "Минимум должно быть введено 10")
    private BigInteger passport;

    @Column(name = "identityCard")
  //  @NotEmpty(message = "Данные номера прав должны быть введены")
 //   @Size(min = 10, message = "Права должны быть минимум 10 цифр")
    private BigInteger identityCard;

    @Column(name = "cardNumber")
 //   @NotEmpty(message = "Данные карты должны быть введены")
    private BigInteger cardNumber;
    @Column(name = "cvc")
   // @NotEmpty(message = "Данные должны быть введены")
    private int cvc;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    @Column(name = "dateOfCreate")
    private LocalDateTime dateOfCreate;

    @OneToMany(mappedBy = "person")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<Car> cars = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Image avatar;

    public Person(){}

    public int getId() {
        return id;
    }

    public Image getAvatar() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public LocalDateTime getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(LocalDateTime dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigInteger getPassport() {
        return passport;
    }

    public void setPassport(BigInteger passport) {
        this.passport = passport;
    }

    public BigInteger getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(BigInteger identityCard) {
        this.identityCard = identityCard;
    }

    public BigInteger getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(BigInteger cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
