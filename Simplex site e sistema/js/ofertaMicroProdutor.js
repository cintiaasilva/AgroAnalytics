var enviarOferta = document.querySelector("#realizar_oferta");

enviarOferta.addEventListener("click", function (){

    var form = document.querySelector("#formuser");

    console.log(form);

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

        console.log(resposta);

    }


)
});