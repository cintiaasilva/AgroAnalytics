var deletaCultura = document.querySelector("#deletaCultura");

function deletaCultura(culturaId) {

    var xhr = new XMLHttpRequest();

    xhr.open("DELETE", "http://localhost:8080/culturas?id=" + culturaId);

    console.log("haiii","http://localhost:8080/culturas?id=" + culturaId)

    return;

    xhr.setRequestHeader("Content-type", "application/json");

    xhr.send();

    xhr.addEventListener("load", function() {

        var resposta = xhr.responseText;

        var cultura = JSON.parse(resposta);

        console.log(resposta);

        console.log(cultura.id);

        alert("deletado")

    });

};


