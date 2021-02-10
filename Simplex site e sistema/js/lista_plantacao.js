function populaTabela() {

    var httpusuario = new XMLHttpRequest();

    httpusuario.open("GET", "http://localhost:8080/plantacoes",false);
    
    httpusuario.send(null);

    var objLista = JSON.parse(httpusuario.responseText);

    console.log(objLista);

    var tabela = $('.estrutura_table').DataTable();

    for (var i = 0; i < objLista.length; i++) {

        const talhoes = objLista[i].talhoes.map((talhao) => {
            return `<b>Id:</b> ${talhao.id}, <b>Nome:</b> ${talhao.nome}, <b>Disponível:</b> ${talhao.disponibilidade ? 'Sim' : 'Não'} \n`;
        });

        const maturacoes = objLista[i].insumos.map((insumo) => {
            return `<b>Id:</b> ${insumo.id}, <b>Nome:</b> ${insumo.nome}, <b>Qtd. Recomendada:</b> ${insumo.quantRecomendada}, <b>Tempo de ação:</b> ${insumo.tempoAcao}, <b>Tipo:</b> ${insumo.tipo} \n`;
        });

        tabela.row.add([
            objLista[i].id,
            objLista[i].dataCriacao,
            objLista[i].dataColheita,
            objLista[i].dataConsumo,
            objLista[i].cultura.nome,
            talhoes,
            maturacoes,
            
            '<a href="#"><span class="glyphicon glyphicon-pencil"></span> Editar</a>'
        ]).draw();

    }
}