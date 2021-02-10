var xhr = new XMLHttpRequest();

xhr.open("GET", "http://localhost:8080/graficos");

xhr.send();

var insumos;

var data = [];

var totalInsumosGastos = [];

var quantLitrosAplicados = [];

var quantInsumosTotal = [];

xhr.addEventListener("load", function () {

    if (xhr.status == 200) {

        var resposta = xhr.responseText;

        insumos = JSON.parse(resposta);

        for (var i = 0; i < insumos.length; i++) {

            data[i] = insumos[i].data;

            totalInsumosGastos[i] = insumos[i].totalInsumosGastos;

            quantLitrosAplicados[i] = insumos[i].quantLitrosAplicados
           
            quantInsumosTotal[i] = insumos[i].quantInsumosTotal;

        }}});

var referenciaInsumo = document.querySelector("#grafico-insumos").getContext("2d");

var tabelaInvestimento = new Chart(referenciaInsumo, {
    type: 'line',
    data: {
        labels: data,
        datasets: [{
            label: "Quantidade total de insumos gasta.",
            backgroundColor: "transparent",
            borderColor: "green",
            data: totalInsumosGastos,
            borderWidth: 3

        },
        {
            label: "Saldo de insumos.",
            backgroundColor: "transparent",
            borderColor: "red",
            data: quantInsumosTotal,
            borderWidth: 3

        }, {
            label: "Quantidade de insumos aplicada.",
            backgroundColor: "transparent",
            borderColor: "blue",
            data: quantLitrosAplicados,
            borderWidth: 3
        }
        ]
    },
    options: {
        title: {
            text: "Analise de gastos, aplicação e total de insumos.",
            display: true,
            fontSize: 40
        }
    }

});

