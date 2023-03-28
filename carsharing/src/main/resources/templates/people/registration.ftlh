<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Регистрация пользователя</title>
    <link rel="stylesheet" href="/static/styles/Registration.css">
</head>
<body>
<form action="/people/registration" method="post" enctype="multipart/form-data">
    <div class="t1">
        <p>Форма для регистрации</p>
    </div>
    <div class="t1">
        <p>Личная информация</p>
        <div class="t2">
            <p><b>*</b>Фамилия:<br><input type="text" pattern="[А-Яа-яЁё ]+" placeholder="Фамилия" class="textbox" name="middleName"></p>
            <p><b>*</b>Имя:<br><input type="text" pattern="[А-Яа-яЁё ]+" placeholder="Имя" class="textbox" name="firstName"></p>
            <p>Отчество(при наличии):<br><input type="text" pattern="[А-Яа-яЁё ]+" placeholder="Отчество" class="textbox" name="lastName"></p>
            <hr width="150px">
            <p><b>*</b>Дата рождения</p><input type="date"  min="1900-01-01" max="101213-10-08" name="transientDob">
            <hr  width="150px">
            <p><b>*</b>Данные номера прав:</p><input type="number" name="identityCard" placeholder="Права">
            <hr  width="150px">
            <p><b>*</b>Данные паспорта:</p><input type="number" name="passport" placeholder="Паспорт">
            <hr  width="150px">
            <p><b>*</b>Данные платёжной карты:</p><input type="number" name="cardNumber" placeholder="Карта">
            <hr  width="150px">
            <p><b>*</b>Данные cvc:</p><input type="number" name="cvc" placeholder="cvc">
            <hr  width="150px">
            <p><b>*</b><label>Email</label><br>
                <input style="width: 200px;" class="textbox" type="email" id="mail" name="email" placeholder="Adress@mail.ru" pattern="[a-zA-Z]{1,}[a-zA-Z0-9.-_][a-zA-Z0-9]{1,}@(mail|gmail|yahoo|yandex).(ru|com)" required>
                <br>
            <p><b>*</b><lable>Логин:</lable><br>
                <input style="width: 200px;" class="textbox" type="text" name="username" placeholder="Логин" required>
                <br>
            <p><b>*</b><lable>Пароль:</lable><br>
                <input style="width: 200px;" class="textbox" type="password" name="password" placeholder="Пароль" required>
                <br>
        </div>

        <input type = "submit" class="blubtn1" value="Зарегистрироваться">

        <button class="blubtn1" type="reset">Очистить</button><br>
    </div><br>
    <input type= "hidden" name = "_csrf" value = "${_csrf.token}">
    </div>
</form>
</body>
</html>