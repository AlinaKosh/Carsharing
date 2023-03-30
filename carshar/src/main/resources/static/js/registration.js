

function validateName() {
    var name=document.getElementById("name").value;
    var help=document.getElementById("name_help");

    if(name.length<2||name.length>15){
        help.innerHTML="Длина имени должна быть 2...15 символов";
        return false;
    }
    else {
        help.innerHTML="";

        return true;
    }

}

function validatePhone() {
    var regex = new RegExp("^(\\+7|8)\\d{10}$");
    var value=document.getElementById("phone").value;
    var help=document.getElementById("phone_help");

    if(regex.exec(value)==null){
        help.innerHTML="Формат +7XXXXXXXXXX или 8XXXXXXXXXX";
        return false;
    }
    else {
        help.innerHTML="";

        return true;
    }

}


function validatePassword() {
    var password=document.getElementById("password").value;
    var help=document.getElementById("password_help");

    if(password.length<6||password.length>20){
        help.innerHTML="Длина пароля должна быть 6...20 символов";
        return false;
    } else {
        help.innerHTML="";
        return true;
    }
}


function confirmPassword() {
    var password=document.getElementById("password").value;
    var password2=document.getElementById("password2").value;
    var help=document.getElementById("password_help2");

    if(password!=password2){
        help.innerHTML="Пароли должны совпадать";
        return false;
    } else {
        help.innerHTML="";
        return true;
    }
}



function validateRegForm(form) {

    if(validateName()&&validatePassword()&&validatePhone()&&confirmPassword()) {
        return true;
    }
    return false;
}

