function populaTabela() {

    var httpusuario = new XMLHttpRequest();

    httpusuario.open("GET", "http://localhost:8080/colheitas",false);
    
    httpusuario.send(null);

    var objLista = JSON.parse(httpusuario.responseText);

    console.log(objLista);

    var tabela = $('.estrutura_table').DataTable();

    for (var i = 0; i < objLista.length; i++) {

        const talhoes = objLista[i].plantacao.talhoes.map((talhao) => {
            return `<b>Id:</b> ${talhao.id}, <b>Nome:</b> ${talhao.nome}, <b>Disponível:</b> ${talhao.disponibilidade ? 'Sim' : 'Não'} \n`;
        });

        // const culturas = objLista[i].plantacao.cultura.map((culturas) => {
        //     return `<b>Id:</b> ${culturas.id}, <b>Nome:</b> ${culturas.nome}, <b>Disponível:</b> ${culturas.precoUnitario },<b>ciclo-maturação:</b> ${culturas.cicloDiasMaturacao} \n`;
        // });

        const insumo = objLista[i].plantacao.insumos.map((insumo) => {
            return `<b>Id:</b> ${insumo.id}, <b>Nome:</b> ${insumo.nome}, <b>Qtd. Recomendada:</b> ${insumo.quantRecomendada}, <b>Tempo de ação:</b> ${insumo.tempoAcao}, <b>Tipo:</b> ${insumo.tipo} \n`;
        });



        tabela.row.add([
            objLista[i].id,
            objLista[i].plantacao.cultura.nome,
            talhoes,
            insumo,
            objLista[i].dataColheita,
            objLista[i].caixas,
            objLista[i].precoColheita, 

            
        ]).draw();

    }
}