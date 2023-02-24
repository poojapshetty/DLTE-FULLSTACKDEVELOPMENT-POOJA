function calculate(){
    let fsal = document.forms['tax'].salary.value;

    if(fsal<250000){
        document.getElementById("rate").setAttribute("value","Nil")
        document.getElementById("total").setAttribute("value","Nil")

    }
    else if(fsal>=250000 && fsal<500000){
        document.getElementById("rate").setAttribute("value","5%")
        document.getElementById("total").setAttribute("value",fsal*0.05)

    }
    else if(fsal>=500000 && fsal<700000){
        document.getElementById("rate").setAttribute("value","10%")
        document.getElementById("total").setAttribute("value",fsal*0.10)
    }
    else if(fsal>=700000 && fsal<900000){
        document.getElementById("rate").setAttribute("value","15%")
        document.getElementById("total").setAttribute("value",fsal*0.15)
    }
    else if(fsal>=900000 && fsal<1300000){
        document.getElementById("rate").setAttribute("value","20%")
        document.getElementById("total").setAttribute("value",fsal*0.20)
    }
    else if(fsal>=1300000){
        document.getElementById("rate").setAttribute("value","30%")
        document.getElementById("total").setAttribute("value",fsal*0.30)
    }
}
