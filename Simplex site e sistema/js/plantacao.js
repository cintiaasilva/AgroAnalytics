var botaoPlantacao = document.querySelector("#cadastrar-plantacao");

botaoPlantacao.addEventListener("click", function() {

    function Talhao(id) {
        this.id = id;
    }

    function Insumo(id) {
        this.id = id;
    }

    event.preventDefault();

    var form = document.querySelector("#formuser");

    var valorTalhoes = form.codTalhao.value;

    var valorInsumos = form.codInsumo.value;

    var vetorTalhoes = valorTalhoes.split(";");

    var vetorInsumos = valorInsumos.split(";");

    var dataCriacaoPlant = form.dataCriacao.value;

    var vetorData = dataCriacaoPlant.split("-");

    var dataFormatada = "";

    dataFormatada = vetorData[2] + "/" + vetorData[1] + "/" + vetorData[0];

    var plantacao = {

        dataCriacao:dataFormatada,
        quantLitrosAplicados: form.qtdLitros.value,

        cultura: {
            id: form.codCultura.value
        },
        talhoes: [

        ],
        insumos: [

        ]
    }

    vetorTalhoes.forEach(function(vetorTalhoes) {

        var cont = 0;

        var id = vetorTalhoes[cont];

        plantacao.talhoes.push(new Talhao(id));

        cont++;

    });

    vetorInsumos.forEach(function(vetorInsumos) {

        var cont = 0;

        var id = vetorInsumos[cont];

        plantacao.insumos.push(new Insumo(id));

        cont++;

    })

    
    

    console.log(plantacao);

    var xhr = new XMLHttpRequest();

    xhr.open("POST", "http://localhost:8080/plantacoes");

    xhr.setRequestHeader("Content-type", "application/json");

    xhr.send(JSON.stringify(plantacao));

    xhr.addEventListener("load", function() {

        var resposta = xhr.responseText;

        alert(resposta);

        var plantacao = JSON.parse(resposta);

       

    });


});