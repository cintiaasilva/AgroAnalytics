//JQUERY INFORMANDO O QUE DEVE SER EXECUTADO AO CHAMAR O ARQUIVO HTML
$(document).ready(function () {
    incluirResources()
        .then(() => {
            return checagemSessao() // CHECAGEM DE SESSÃO SÓ É FEITA APÓS CARREGAR O HTML
        });

    //Função para logar apertando o ENTER
    $(document).on('keypress input', function (e) {
        if ($('#modalLogin').is(':visible')) {
            var key = e.which;
            if (key == 13) { //13 é o código da tecla ENTER
                login();
            }
            if (key == 27) { //27 é o código da tecla ESC
                $('#modalLogin').modal('hide');
            }
        }
    });
})

//Inclusão de cabeçalho via jquery
function incluirResources() {
    return new Promise(function (resolve, reject) {
        $.get("cabecalho.html", function (data, status) {
            $('#topo').html(data);
        }).done(function () {
            resolve(true);
        });
    });
}

// FUNÇÃO DO FRONT END QUE FAZ VALIDAÇÃO COM BACKEND DO LOGIN PARA GERAR SESSÃO
function login() {
    $.ajax({
        method: "POST",
        url: "/login",
        data: $("#login").serialize()
    })
        .done(function (sessao) {
            var msg;
            if (sessao == "Não autenticado") {
                msg = "Usuario ou senha incorretos!"
                alert(msg);
            }
            else {
                definirCab(sessao)
                $('#modalLogin').modal('hide')
            }
        });
    function foco() {
        document.login.username.focus();
    }
}

// FUNÇÃO PARA LOGOUT
function Logout() {
    $.ajax({
        method: "GET",
        url: "/logout",
    })
        .done(function () {
            window.location.replace("/html/home.html");
        });
}

// FUNÇÃO QUE DEFINE O CABEÇALHO COM BASE NO NIVEL DE ACESSO DO USUARIO APÓS LOGIN 
function definirCab(sessao) {
    if (sessao[0].idAcesso == 1) {
        document.getElementById("semLogin").hidden = true;
        document.getElementById("admin").hidden = false;
        document.getElementById("li_adm").innerHTML = `Bem vindo,  ${sessao[0].nm_usuario} <img src="../images/cientista_icon.png" class="hvr-buzz-out">`;
    }
    if (sessao[0].idAcesso == 2) {
        document.getElementById("semLogin").hidden = true;
        document.getElementById("monitor").hidden = false;
        document.getElementById("li_monitor").innerHTML = `Bem vindo,  ${sessao[0].nm_usuario} <img src="../images/cientista_icon.png" class="hvr-buzz-out">`;

    }
    if (sessao == "Não autenticado") {
        document.getElementById("monitor").hidden = true;
        document.getElementById("admin").hidden = true;
        document.getElementById("semLogin").hidden = false;
    }

}

// FUNÇÃO QUE FAZ SEMPRE A CHECAGEM DA SESSÃO NO LOAD DAS PAGINAS PARA MANTER O CABEÇALHO CORRETO

function checagemSessao() {
    $.ajax({
        method: "GET",
        url: "/users/session",
    })
        .done(function (sessao) {
            definirCab(sessao);
        });
}

function redirectnologin() {

    $.ajax({
        method: "GET",
        url: "/users/session",
    })
        .done(function (sessao) {
            if (sessao == "Não autenticado") {
                document.body.innerHTML = "";
                window.location.replace("/html/home.html");
                var msg = "Faça logon para ter acesso ao sistema!"

                $(document).ready(function () {
                    $('#modalAlerts').modal('show')
                    document.getElementById("msgAlertModal").innerHTML = msg;
                });

                // alert(msg); 

            }
        });
}

function alertaModal(msg) {
    $('#modalAlerts').modal(show)
    document.getElementById("msgAlertModal").innerHTML = msg;
}

//Usado nos forms para o botão cancelar
function cancelarForm() {
    window.history.back();
}


// CRIAÇÃO GOOGLE MAPS DA LOCALIDADE 

function initMap(idloc) {
    var map = new google.maps.Map(document.getElementById('modal-maps'), {
        zoom: 14,
        center: { lat: -34.397, lng: 150.644 }
    });
    var geocoder = new google.maps.Geocoder();

    geocodeAddress(geocoder, map, idloc);

}

function geocodeAddress(geocoder, resultsMap, idloc) {
    if (idloc == null) {
        selecionado = parseInt($('#localidades option:selected').val());
    } else {
        selecionado = idloc;
    }
    var httpLocEnd = new XMLHttpRequest();
    httpLocEnd.open("GET", '/banco/locEndereco/' + selecionado, false);
    httpLocEnd.send(null);

    var objLocEnd = JSON.parse(httpLocEnd.responseText);

    document.getElementById('logradouroLoc').innerHTML = objLocEnd[0].endereco + ", " + objLocEnd[0].numero
    document.getElementById('cidadeLoc').innerHTML = objLocEnd[0].cidade;
    document.getElementById('bairroLoc').innerHTML = objLocEnd[0].bairro;

    var address = `${objLocEnd[0].endereco}, ${objLocEnd[0].numero}, ${objLocEnd[0].bairro} - ${objLocEnd[0].estado}`;
    geocoder.geocode({ 'address': address }, function (results, status) {
        if (status === 'OK') {
            resultsMap.setCenter(results[0].geometry.location);
            var marker = new google.maps.Marker({
                map: resultsMap,
                position: results[0].geometry.location
            });
        } else {
            alert('Geocode was not successful for the following reason: ' + status);
        }
    });
}

function alerta(msg) {
    document.getElementById("alerta").innerHTML = msg;
    $("#alerta").fadeIn(500);

    setTimeout(function () {
        document.getElementById("alerta").innerHTML = null;
        $("#alerta").fadeOut(500);
    }, 7000)
}
function alertaSucesso(msg) {
    document.getElementById("sucesso").innerHTML = msg;
    $("#sucesso").fadeIn(500);

    setTimeout(function () {
        $("#sucesso").fadeOut(500);
        document.getElementById("sucesso").innerHTML = null;
    }, 5000)
}
function alertaErro(msg) {
    document.getElementById("erro").innerHTML = msg;
    $("#erro").fadeIn(500);

    setTimeout(function () {
        document.getElementById("erro").innerHTML = null;
        $("#erro").fadeOut(500);
    }, 5000)
}

