function buscarRegistros(culturas) {

    var culturasTr = montaTr(culturas);

    var tabelaCulturas = document.querySelector("#tabela-culturas");

    tabelaCulturas.appendChild(culturasTr);

    

}

function criarRegistro() {

    event.preventDefault();

    var form = document.querySelector("#form-cadastro-plantacao");

    var cultura = obtemPacienteDoFormulario(form);

    var culturasTr = montaTr(cultura);

    console.log(cultura);

    var corpoTabelaCulturas = document.querySelector("#corpo-tabela-culturas");

    corpoTabelaCulturas.appendChild(culturasTr);

    form.reset();
}

function obtemPacienteDoFormulario(form) {

    var paciente = {

        nome: form.nome.value,

        tipo: form.tipo.value,

        preco: form.preco.value,

        maturacao: form.maturacao.value
    }

    return paciente;

}

function montaTr(cultura) {

    var culturasTr = document.createElement("tr");

    culturasTr.classList.add("culturas");

    var idCulturaTd = document.createElement("td");

    var nomeCulturaTd = document.createElement("td");

    var tipoCulturaTd = document.createElement("td");

    var precoUnitarioTd = document.createElement("td");

    var diasMaturacaoTd = document.createElement("td");

    idCulturaTd.textContent = cultura.id;

    nomeCulturaTd.textContent = cultura.nome;

    console.log(cultura.nome);

    tipoCulturaTd.textContent = cultura.tipo;

    precoUnitarioTd.textContent = cultura.precoUnitario;

    console.log(cultura.preco);

    diasMaturacaoTd.textContent = cultura.cicloDiasMaturacao;

    console.log(cultura.maturacao);
    culturasTr.appendChild(idCulturaTd);

    culturasTr.appendChild(nomeCulturaTd);

    culturasTr.appendChild(tipoCulturaTd);

    culturasTr.appendChild(precoUnitarioTd);

    culturasTr.appendChild(diasMaturacaoTd);

    return culturasTr;

}