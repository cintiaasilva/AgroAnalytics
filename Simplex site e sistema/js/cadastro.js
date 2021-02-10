$(document).ready(function () {
    $('#fixo').mask('(000)0000-0000');
    $('#celular').mask('(00)00000-0000');
    $('#data').mask('00/00/0000');
    $('#cpf').mask('00000000000');
    
})   


function cancelarForm() {
    window.history.back();
}

function validacao() {
    var nome = formuser.nomeUsuario.value;
    var email = formuser.emailUsuario.value;
    var senha = formuser.senhaUsuario.value;
    var senha1 = formuser.senha2.value;

    if (nome == "" || nome.length <8) {
        $.notify({

            message: 'Digite seu nome corretamente!',
            offset: 50
        },{
            type: 'danger'
        });			    
        formuser.nome.focus();
        return false;
    }

    else if (email == "" || email.indexOf('@') == -1 || email.indexOf('.')== -1) {
        $.notify({

            message: 'Digite um email válido!',
            offset: 50
        },{
            type: 'danger'
        });			
        formuser.email.focus();
        return false;
    }

   
    else if (senha == "" || senha.length < 6) {
        $.notify({
            message: 'Preencha a senha com no mínimo 6 caracteres!',
            offset: 50
        },{
            type: 'danger'
        });			  
        formuser.senha.focus();
        return false;
    }

    else if (senha != senha1) {
        $.notify({
            message: 'As senhas não correspondem, preencha corretamente!',
            offset: 50
        },{
            type: 'danger'
        });			
       
    }
    else{
      
        //cad_usuario();

	   document.getElementById("formuser").reset();
	   
	   $.notify({
            message: 'Cadastro feito com sucesso!',
            offset: 50
        },{
           type: 'success'
		});	
		formuser.senha2.focus();
        return false;
        	
    }


}

function verificarCPF(c) {
    var i;
    s = c;
    var c = s.substr(0, 9);
    var dv = s.substr(9, 2);
    var d1 = 0;
    var v = false;

    for (i = 0; i < 9; i++) {
        d1 += c.charAt(i) * (10 - i);
    }
    if (d1 == 0) {
        $.notify({

            message: 'CPF inválido!',
            offset: 50
        },{
            type: 'danger'
        }); 
        v = true;
        return false;
    }
    d1 = 11 - (d1 % 11);
    if (d1 > 9) d1 = 0;
    if (dv.charAt(0) != d1) {
        $.notify({

            message: 'CPF inválido!',
            offset: 50
        },{
            type: 'danger'
        });
        v = true;
        return false;
    }

    d1 *= 2;
    for (i = 0; i < 9; i++) {
        d1 += c.charAt(i) * (11 - i);
    }
    d1 = 11 - (d1 % 11);
    if (d1 > 9) d1 = 0;
    if (dv.charAt(1) != d1) {
        $.notify({

            message: 'CPF inválido!',
            offset: 50
        },{
            type: 'danger'
        });  
        v = true;
        return false;
    }
    if (!v) {
        $.notify({

            message: 'CPF válido!',
            offset: 50
        },{
            type: 'success'
        });
    }
}

function cad_usuario() {
    $.ajax({
        method: "POST",
        url: "/cadastro",
        data: $("#formuser").serialize()
    })
        .done(function (resposta) {
            var msg;
            var resposta;

            if (resposta == "erro") {
                msg = "Falha no cadastro, Por favor tente novamente!"
                alert(msg)            
            }
            else if(resposta == "Sucesso") {
                msg = "Cadastro feito com sucesso!"
                $.notify({
            message: msg ,
            offset: 50
        },{
            type: 'success'
        });      
                document.getElementById("formuser").reset();
            }
            else if(resposta == "cpf-duplicado") {
                msg = "Este CPF já existe na base, caso tenha esquecido sua senha entre em contato com administrador"
                alert(msg)
                           
            }
            else if(resposta == "usuario-duplicado") {
                msg = "Este usuario já existe na base, caso tenha esquecido sua senha entre em contato com administrador"
                alert(msg)

            }
            else if(resposta == "email-duplicado") {
                msg = "Este e-mail já existe na base, caso tenha esquecido sua senha entre em contato com administrador"
                alert(msg)           
            }
        });   
}