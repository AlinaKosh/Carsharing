function validateNumber() {
    var value=document.getElementById("number").value;
    var help=document.getElementById("number_help");

    var regex = new RegExp('^[АВЕКМНОРСТХABEKMHOPCTX]{1}[0-9]{3}[АВЕКМНОРСТХABEKMHOPCTX]{2}-[0-9]{2,3}$');
    var match = regex.exec(value);

    if(match==null){
        help.innerHTML="Допустимый формат 'x123xx-12(3)'. Допустимые символы: АВЕКМНОРСТХ, 0-9";
        return false;
    }
    else {
        help.innerHTML="";
        return true;
    }
}

function toUpperCase(inputObject) {
    inputObject.value=inputObject.value.toUpperCase();
}

function validateForm() {
    return validateNumber();
}




