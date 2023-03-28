<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Index</title>
</head>
<body>

<!--div th:each="car : ${cars}">
    <a th:href="@{/cars/{id}(id=${car.getId()})}"
       th:text="${car.getCarBrand() + ', ' + car.getNumberCar() + ', ' + car.getCarMileage() +
', ' + car.getParticipationInAnAccident() + car.getPts() + ', ' + car.getYearOfRealize()}">car</a>
</div-->

<#list cars as car>
    <div>${car.id}</div>
    <div>${car.carBrand}</div>
</#list>

</body>
</html>