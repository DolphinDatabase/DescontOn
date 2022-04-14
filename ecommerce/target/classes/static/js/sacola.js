document.addEventListener("DOMContentLoaded", function(event) {
    var btn = document.getElementById("btn-finalizar");
    if(localStorage.getItem("sacola")){
        sacola = JSON.parse(localStorage.getItem("sacola"));
        if(sacola.length == 0){
            btn.style.pointerEvents = "none";
            btn.style.cursor = "not-allowed";
        }else{
            btn.style.cursor = "default";
            btn.style.pointerEvents = null;
        }
    }else{
        console.log(btn);
        btn.style.pointerEvents = "none";
        btn.style.cursor = "not-allowed";
    }
});