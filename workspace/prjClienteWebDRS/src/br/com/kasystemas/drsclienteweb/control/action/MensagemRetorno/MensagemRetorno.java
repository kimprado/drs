package br.com.kasystemas.drsclienteweb.control.action.MensagemRetorno;

public class MensagemRetorno {
	
	private String tipo;
	private boolean erro;
	private String mensagem;
	private String funcao;
	
	public MensagemRetorno(){
		
	}
	
	public MensagemRetorno(String tipo, boolean erro, String mensagem) {
		this.tipo = tipo;
		this.erro = erro;
		this.mensagem = mensagem;
	}
	
	public MensagemRetorno(String tipo, boolean erro, String mensagem, String funcao) {
		this.tipo = tipo;
		this.erro = erro;
		this.mensagem = mensagem;
		this.funcao = funcao;
	}

	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public boolean isErro() {
		return erro;
	}
	
	public void setErro(boolean erro) {
		this.erro = erro;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	
	public String getFuncao() {
		return funcao;
	}
}
