var botaoCriarCulturas = document.querySelector("#botao-criar-colheita");

botaoCriarCulturas.addEventListener("click", function() {

    event.preventDefault();

    var form = document.querySelector("#formuser");



    var dataCriacaoCol = form.dataColheita.value;

    var vetorData = dataCriacaoCol.split("-");

    var dataFormatada = "";

    dataFormatada = vetorData[2] + "/" + vetorData[1] + "/" + vetorData[0];



    var colheitas = {
        plantacao : {
        id : form.idPlantacao.value
    },
        dataColheita: dataFormatada,
        caixas: form.qtdCaixas.value,
        precoColheita: form.preco.value
    }


    console.log("Esta merda");

    var xhr = new XMLHttpRequest();

    xhr.open("POST", "http://localhost:8080/colheitas");

    xhr.setRequestHeader("Content-type", "application/json");

    console.log("Esta merda");

    xhr.send(JSON.stringify(colheitas));

    console.log("Esta merda");

    xhr.addEventListener("load", function() {

        console.log("Esta merda");

        var resposta = xhr.responseText;

        console.log("Esta merda");

        
        alert('colheita foi criado, talhões liberados')

   

    });

});