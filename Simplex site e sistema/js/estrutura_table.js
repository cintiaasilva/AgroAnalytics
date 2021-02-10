$(document).ready(function () {
    $('.estrutura_table').DataTable({
        "language": {
            "search": "Pesquise qualquer campo:",
            "lengthMenu": "Mostrar _MENU_ entradas",
            "emptyTable": "Não há dados disponíveis na tabela",
            "info": "Mostrando _START_ a _END_ de _TOTAL_ entradas",
            "infoEmpty": "Mostrando 0 a 0 de 0 entradas",
            "infoFiltered": "(filtrando de _MAX_ total de entradas)",
            "loadingRecords": "Carregando...",
            "processing": "Processando...",
            "zeroRecords": "Nenhum dado correspondente encontrado",
            "paginate": {
                "first": "Primeira",
                "last": "Última",
                "next": "Próxima",
                "previous": "Anterior"
            }
        }
    }
    );
});