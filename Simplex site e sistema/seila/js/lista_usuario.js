function populaTabela() {
    var httpusuario = new XMLHttpRequest();
    httpusuario.open("GET", "/banco/usuariolista", false);
    httpusuario.send(null);

    var objLista = JSON.parse(httpusuario.responseText);

    var tabela = $('.estrutura_table').DataTable();

    for (var i = 0; i < objLista.length; i++) {

        tabela.row.add([
            objLista[i].idUsuario,
            objLista[i].nm_Usuario,
            objLista[i].CPF,
            objLista[i].telefone,
            objLista[i].email,
            objLista[i].nm_loc,
            objLista[i].nm_grupo,
            '<a href="#"><span class="glyphicon glyphicon-pencil"></span> Editar</a>'
        ]).draw();

    }
}