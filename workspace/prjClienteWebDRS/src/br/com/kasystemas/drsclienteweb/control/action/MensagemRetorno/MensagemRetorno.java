package br.com.kasystemas.drsclienteweb.control.action.MensagemRetorno;

public class MensagemRetorno {
	
	private String tipo;
	private boolean erro;
	private String Mensagem;
	
	public MensagemRetorno(){
		
	}
	
	public MensagemRetorno(String tipo, boolean erro, String mensagem) {
		this.tipo = tipo;
		this.erro = erro;
		Mensagem = mensagem;
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
		return Mensagem;
	}
	
	public void setMensagem(String mensagem) {
		Mensagem = mensagem;
	}
}
