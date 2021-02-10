var botaoCriarCulturas = document.querySelector("#cadastrar-talhao");

botaoCriarCulturas.addEventListener("click", function() {

    var form = document.querySelector("#formuser");

    var talhoes = {
        nome:form.nomeTalhao.value,
        disponibilidade:form.disponibilidade.value
    }

    var xhr = new XMLHttpRequest();

    xhr.open("POST", "http://localhost:8080/talhoes");

    xhr.setRequestHeader("Content-type", "application/json");

    xhr.send(JSON.stringify(talhoes));

    xhr.addEventListener("load", function() {

        var resposta = xhr.responseText;

        var talhao = JSON.parse(resposta);


        alert('talhão ' + talhoes.nome+   ' criado com sucesso!');

     

    });

});