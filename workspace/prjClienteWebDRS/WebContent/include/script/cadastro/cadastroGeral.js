function mensagemRetorno( mensagem ) {
	if ( mensagem.erro == true || mensagem.erro == 'true' ){
		alert('Ocorreu um erro:\n  ' + mensagem.mensagem);
		return;
	}
	
	if ( mensagem.tipo == 'alteracao' || mensagem.tipo == 'inclusao' ){
		alert('Cadastro realizado com sucesso.');
	} 
}