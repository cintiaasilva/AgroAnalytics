<<<<<<< HEAD
var botaoAceitarLance = document.querySelector("#leilao");

console.log("Chegou aqui")

botaoAceitarLance.addEventListener("click", function(e) {

    e.preventDefault();

    console.log("OIII");

    var form = document.querySelector("#idOferta");

    var xhr = new XMLHttpRequest();

    xhr.open("POST", "http://localhost:8080/ofertas/aceitarOferta");
    //xhr.open("POST", "http://localhost:8080/culturas?id=" + culturas.id);

    xhr.setRequestHeader("Content-type", "application/json");

    xhr.send(JSON.stringify(form.value));

    xhr.addEventListener("load", function() {

        var resposta = xhr.responseText;

        console.log(resposta);
        
    });

});


=======
var enviarOferta = document.querySelector("#realizar_oferta");

enviarOferta.addEventListener("click", function (){

    var form = document.querySelector("#oferta");

    var oferta = {

        idColheita: form.colheita.value,
        valorOferta: form.oferta.value,
        quantCaixasCompradas: form.caixas.value
        
    }

    var xhr =  new XMLHttpRequest();

    xhr.open("POST","http://localhost:8080/ofertas");

    xhr.setRequestHeader("Content-type", "application/json");

    xhr.send(JSON.stringify(oferta));

    xhr.addEventListener("load", function(){

        var resposta = xhr.responseText;

        alert(resposta);

        var oferta = JSON.parse(resposta);

        alert(oferta);

    }


)
});
>>>>>>> 7fef3a1bbfee5da73ef6cfda042fa462060e9a40
