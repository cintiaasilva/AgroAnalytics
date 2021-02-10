var botaoCriarCulturas = document.querySelector("#cadastrar-cultura");

botaoCriarCulturas.addEventListener("click", function() {

    var form = document.querySelector("#formuser");

    var culturas = {
        nome: form.nomeCultura.value,
        tipo: form.tipoCultura.value,
        precoUnitario: form.precoUnitario.value,
        cicloDiasMaturacao: form.cicloDeMaturacao.value
    }


    var xhr = new XMLHttpRequest();

    xhr.open("POST", "http://localhost:8080/culturas");
    //xhr.open("POST", "http://localhost:8080/culturas?id=" + culturas.id);

    xhr.setRequestHeader("Content-type", "application/json");

    xhr.send(JSON.stringify(culturas));

    xhr.addEventListener("load", function() {

        var resposta = xhr.responseText;

        var cultura = JSON.parse(resposta);

        console.log(resposta);

        console.log(cultura.id);

        alert("A cultura foi criada: \n" +"Id_Cultura: "+cultura.id+"\n"+"Nome: "+cultura.nome+"\n"+"Tipo: "+cultura.tipo+"\n"+
        "Preço-Unitario: "+cultura.precoUnitario+"\n"+"Ciclo-Maturação: "+cultura.cicloDiasMaturacao);

     

    });

});


