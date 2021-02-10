var botaoLogin= document.querySelector("#btn-submit");
var botaoCancelarCriacao = document.querySelector("#cancelar");

botaoLogin.addEventListener("click", function(e) {

    e.preventDefault();

    // alert("vou comer seu...")

    var form = document.querySelector("#formlogin");

    var login = {
       
        email: form.username.value,
        senha: form.senha.value
    }

    var xhr = new XMLHttpRequest();

    xhr.open("POST", "http://localhost:8080/login");
    //xhr.open("POST", "http://localhost:8080/culturas?id=" + culturas.id);

    xhr.setRequestHeader("Content-type", "application/json");

    xhr.send(JSON.stringify(login));

    xhr.addEventListener("load", function() {

        var resposta = xhr.responseText; 

        if(resposta == "OK"){

             window.location = "../html/menuPrincipal.html"

        }else{

            alert(resposta);
        }

    });

});


