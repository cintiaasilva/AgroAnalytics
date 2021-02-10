var botaoBuscarCulturas = document.querySelector("#buscar-culturas");

botaoBuscarCulturas.addEventListener("click", function() {

    var xhr = new XMLHttpRequest();
    console.log("CHAMOU")
    xhr.open("GET", "http://localhost:8080/culturas");

    xhr.send();

    xhr.addEventListener("load", function() {

        if (xhr.status == 200) {

            var resposta = xhr.responseText;

            var culturas = JSON.parse(resposta);

            culturas.forEach(function(culturas) {

                buscarRegistros(culturas);

                console.log(culturas);

            });


        }

    });

});