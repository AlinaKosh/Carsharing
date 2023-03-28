<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Вход</title>
    <link href="/static/styles/Enter.css" rel="stylesheet">
</head>
<body>
<div class="center">
    <div>
        <form action="/people/login" method="post" name="login" enctype="multipart/form-data">
            <p><label for = "username">Логин: </label> <input type="text" id = "username" name = "username" class="textbox"></p>
            <p><label for = "password">Пароль: </label><input type="password" id = "password" name = "password" class="textbox"></p>
            <input type = "hidden" name = "_csrf" value = "${_csrf.token}">
            <input type="submit" value = "Вход" class="blubtn1">
        </form>
        <button onclick = "reg()" class="blubtn1">регистрация</button>
    </div>
</div>
<script>
    function reg(){
        document.location.replace("/people/registration")
    }
</script>
</body>
</html>
