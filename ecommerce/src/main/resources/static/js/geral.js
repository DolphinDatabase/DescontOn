/*===== CADASTRO PRODUTO =====*/
(function() {
    'use strict';
     window.addEventListener('load', function() {
    // Pega todos os formulários para aplicar estilos de validação Bootstrap personalizados.
     var forms = document.getElementsByClassName('needs-validation');
    // Faz um loop e evita o envio
     var validation = Array.prototype.filter.call(forms, function(form) {
     form.addEventListener('submit', function(event) {
     if (form.checkValidity() === false) {
     event.preventDefault();
     event.stopPropagation();
    }
     if (form.checkValidity() === true) {
     alert("Produto Cadastrado com Sucesso");
     }
     form.classList.add('was-validated') ;
      }, false);
      });
      }, false);
    })();
	
document.addEventListener("DOMContentLoaded", function(event) {

const showNavbar = (toggleId, navId, bodyId, headerId) =>{
const toggle = document.getElementById(toggleId),
nav = document.getElementById(navId),
bodypd = document.getElementById(bodyId),
headerpd = document.getElementById(headerId)

// Validate that all variables exist
if(toggle && nav && bodypd && headerpd){
toggle.addEventListener('click', ()=>{
// show navbar
nav.classList.toggle('show2')
// change icon
toggle.classList.toggle('bx-x')
// add padding to body
bodypd.classList.toggle('body-pd')
// add padding to header
headerpd.classList.toggle('body-pd')
})
}
}

showNavbar('header-toggle','nav-bar','body-pd','header')

/*===== LINK ACTIVE =====*/
const linkColor = document.querySelectorAll('.nav_link')

function colorLink(){
if(linkColor){
linkColor.forEach(l=> l.classList.remove('active'))
this.classList.add('active')
}
}
linkColor.forEach(l=> l.addEventListener('click', colorLink))

//  to run since DOM is loaded and ready
});

//function to change table
function changeTable(){
    var op = document.getElementById("op").value;
    console.log(op);
    if(op == "0"){
        document.getElementById("disp").style.display="contents";
        document.getElementById("arqui").style.display="none";
    }else{
        document.getElementById("arqui").style.display="contents";
        document.getElementById("disp").style.display="none";
    }
}

//function to add sacola in localStorage
function adicionarSacola(value){
    var sacola;
    if(localStorage.getItem("sacola")){
        sacola = JSON.parse(localStorage.getItem("sacola"));
        if(!sacola.includes(value)){
            sacola.push(value);
        }
    }else{
        sacola = [value];
    }
    localStorage.setItem("sacola",JSON.stringify(sacola));
    window.location.href = "/adicionarSacola/"+value
}

//function to remove sacola in localStorage
function removerSacola(value){
    var sacola = JSON.parse(localStorage.getItem("sacola"));
    sacola.splice(sacola.indexOf(value),1);
    localStorage.setItem("sacola",JSON.stringify(sacola));
    window.location.href = "/removerProduto/"+value;
}

function finalizar(){
    localStorage.setItem("sacola",JSON.stringify([]))
    window.location.href = "/finalizar";
}