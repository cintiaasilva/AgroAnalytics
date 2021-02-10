var botaoCriarInsumo = document.querySelector("#botao-criar-insumo");

botaoCriarInsumo.addEventListener("click", function() {

    var form = document.querySelector("#formuser");

    var insumo = {
        nome: form.nome.value,
        tipo: form.tipo.value,
        volumeRecipiente: form.volumeRecipiente.value,
        tempoAcao: form.tempo.value,
        quantRecomendada: form.recomendacao.value
    }


    var xhr = new XMLHttpRequest();

    xhr.open("POST", "http://localhost:8080/insumos");

    xhr.setRequestHeader("Content-type", "application/json");

    xhr.send(JSON.stringify(insumo));

    xhr.addEventListener("load", function() {

        var resposta = xhr.responseText;

        var cultura = JSON.parse(resposta);

        console.log(resposta);

        console.log(cultura.id);

        alert("Cadastrado com sucesso!");

    });

});