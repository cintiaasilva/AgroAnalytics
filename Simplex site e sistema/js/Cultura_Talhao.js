$(document).ready(function () {
  $(".menu").click(function () {
    $(".keep").toggleClass("width");
  });
});

//Função que duplica e exclui os campos da cultura

//Função que duplica e exclui os campos do talão
function duplicarCamposTa(){
  var clone = document.getElementById('talhao').cloneNode(true);
  var destino = document.getElementById('destinoTa');
  destino.appendChild (clone);
  var camposClonados = clone.getElementsByTagName('input');
  
    for(i=0; i<camposClonados.length;i++){
      camposClonados[i].value = '';
    }
  
  }

  function removerCamposTa(id){
  var node1 = document.getElementById('destinoTa');
  node1.removeChild(node1.childNodes[0]);
  }

//---------------------------------------------------------------------------------------------------------
//Função que esconde os dados da coluna de menu (Estoque)
function esconde(id) {
  var node1 = document.getElementById('destinoTa');
  node1.removeChild(node1.childNodes[0]);
}

mostra = 0
function btnMenu() {
  var mostraE = document.getElementById("esconderE");
  var mostraF = document.getElementById("esconderF");
  var mostraI = document.getElementById("esconderI");
  var mostraS = document.getElementById("esconderS");
  if (mostra == 0) {
    mostraE.style.display = 'block';
    mostraF.style.display = 'block';
    mostraI.style.display = 'block';
    mostraS.style.display = 'block';
    mostra = 1;
  }
  else {
    mostraE.style.display = 'none';
    mostraF.style.display = 'none';
    mostraI.style.display = 'none';
    mostraS.style.display = 'none';
    mostra = 0;
  }

}
