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

/*===== SACOLA DE COMPRAS =====*/ 				
function finalizar(){
alert("Compra Efetuada com Sucesso");
}
	
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
nav.classList.toggle('show')
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