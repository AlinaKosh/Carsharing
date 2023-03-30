
function Result(){
    var costPerDay=parseInt(document.getElementById("costPerDay").innerHTML);
    var days=parseInt(document.getElementById("days").value);
    var result=costPerDay*days;
    if(isNaN(costPerDay)||isNaN(days))
        result=0;
    document.getElementById("result").innerHTML=result;
}


function validateDate() {
    var dateElement=document.getElementById("date");
    var help=document.getElementById("date_help");
    var date=new Date(dateElement.value);
    var today=new Date();

     if(date<today||isNaN(date.getTime())){
         help.innerHTML="Выберите дату в будущем";
         return false;
     } else {
         help.innerHTML="";
         return true;
     }
    return true;
}



function validateOrderForm() {
    return validateDate();
}

