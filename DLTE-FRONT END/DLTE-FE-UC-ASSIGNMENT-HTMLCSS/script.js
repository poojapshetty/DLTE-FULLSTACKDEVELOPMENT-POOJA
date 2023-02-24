function authen(){
    let json=[
        {
        "id":"pooja",
        "password:98765"
        },
        {
            "id":"shetty",
            "password":"12345"
        }
        ]
        let uid = document.forms['login'].id.value;
        let upass = document.forms['login'].pass.value;
        json.forEach(item)=>{
            if(item.id==uid&&item.password==upass){
                window.location.href="http:158.0.0.1:4500/homepage.html "
            }
        }
}