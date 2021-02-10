function populaTabela() {

    var httpusuario = new XMLHttpRequest();

    httpusuario.open("GET", "http://localhost:8080/ofertas",false);
    
    httpusuario.send(null);

    var objLista = JSON.parse(httpusuario.responseText);

    var tabela = $('.estrutura_table').DataTable();

    for (var i = 0; i < objLista.length; i++) {

        tabela.row.add([
            objLista[i].id,
            objLista[i].idColheita,
            objLista[i].valorOferta,
            objLista[i].quantCaixasCompradas,
        ]).draw();

    }
}

