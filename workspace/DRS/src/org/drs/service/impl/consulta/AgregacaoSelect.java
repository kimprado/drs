package org.drs.service.impl.consulta;

public class AgregacaoSelect {
	
	private int a_funcao;
	
	public AgregacaoSelect(){
		
	}
	
	public AgregacaoSelect(int funcao){
		a_funcao = funcao;
	}
	
	public int getFuncao(){
		return a_funcao;
	}
	
	public void setFuncao(int funcao){
		a_funcao = funcao;
	}
	
	public Clausulas getClausulas(Clausulas cls, String tabela, String campo, String tipo){
		if (cls.getSelect() == null){
				cls.setSelect(" " + toStrFuncao() +"("+ tabela +"."+ campo + ") AS " + toFormatacao(campo));
			
		}else {
			cls.setSelect(cls.getSelect() + toStrFuncao() +"("+ tabela +"."+ campo + ") AS "+ toFormatacao(campo));
		}
		
		return cls;
	}

	
	private String toStrFuncao(){
		switch (a_funcao) {
		
		case 1:	return new String("COUNT");
		case 2:	return new String("SUM");
		case 3:	return new String("MAX");
		case 4:	return new String("MIN");
		case 5:	return new String("AVG");
		
		}
		
		return null;
	}
	
	private String toFormatacao(String campo){
		switch (a_funcao) {
		
		case 1:	return new String("Contar_"+campo);
		case 2:	return new String("Somar_"+campo);
		case 3:	return new String("Maior_"+campo);
		case 4:	return new String("Menor_"+campo);
		case 5:	return new String("Media_"+campo);
		
		}
		return null;
	}
	
}
