package ru.project.carsharing.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.Date;

public class PersonDTO {
    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов диной")
    private String username;

    @NotEmpty(message = "Возраст должен быть введён")
    private Date dateOfBirth;

    @Email
    @NotEmpty(message = "Почта должна быть введена")
    private String email;

    @NotEmpty(message = "Данные паспорта должны быть введены")
    @Size(min = 6, message = "Минимум должно быть введено 6")
    private BigInteger passport;

    @NotEmpty(message = "Данные номера прав должны быть введены")
    private BigInteger identityCard;

    @NotEmpty(message = "Данные карты должны быть введены")
    private BigInteger cardNumber;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
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
}
