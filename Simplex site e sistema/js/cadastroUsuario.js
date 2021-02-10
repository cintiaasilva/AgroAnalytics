var botaoCriarUsuarios = document.querySelector("#cadastrar-usuario");
var botaoCancelarCriacao = document.querySelector("#cancelar");

botaoCriarUsuarios.addEventListener("click", function() {

    var form = document.querySelector("#formuser");

    var usuario = {
        nome: form.nomeUsuario.value,
        cpf: form.cpfUsuario.value,
        dataNascimento: form.dataNasc.value,
        telefone:{
           telefoneFixo:form.telUsuario.value,
           telefoneCelular:form.celUsuario.value
        },
        localizacao:{
            estado:form.estadoUsuario.value,
            cidade:form.cidadeUsuario.value
        },
        login:{
            email:form.emailUsuario.value,
            senha: form.senhaUsuario.value
        }

    }


    var xhr = new XMLHttpRequest();

    xhr.open("POST", "http://localhost:8080/usuarios");
    //xhr.open("POST", "http://localhost:8080/culturas?id=" + culturas.id);

    xhr.setRequestHeader("Content-type", "application/json");

    xhr.send(JSON.stringify(usuario));

    xhr.addEventListener("load", function() {

        var resposta = xhr.responseText;

        var usuario = JSON.parse(resposta);



        alert("usuario cadastrado com sucesso")
     

    });

});


