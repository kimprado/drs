package org.drs.service.impl;

import java.util.HashMap;

import org.apache.axis.message.addressing.Address;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.drs.service.impl.consulta.AgregacaoHaving;
import org.drs.service.impl.consulta.AgregacaoSelect;
import org.drs.service.impl.consulta.Campo;
import org.drs.service.impl.consulta.Clausulas;
import org.drs.service.impl.consulta.Condicao;
import org.drs.service.impl.consulta.Consulta;
import org.drs.service.impl.consulta.Ligacao;
import org.drs.service.impl.consulta.Tabela;
import org.globus.cube.stubs.Cube.CubeMetadataResponse;
import org.globus.cube.stubs.Cube.CubePortType;
import org.globus.cube.stubs.Cube.DimensaoMetaData;
import org.globus.cube.stubs.Cube.FatoMetaData;
import org.globus.cube.stubs.Cube.FieldMetaData;
import org.globus.cube.stubs.Cube.LigacaoMetaData;
import org.globus.cube.stubs.Cube.service.CubeServiceAddressingLocator;
import org.globus.drs.stubs.Cube.AddCampo;
import org.globus.drs.stubs.Cube.AgHav;
import org.globus.drs.stubs.Cube.AgSel;
import org.globus.drs.stubs.Cube.Atributo;
import org.globus.drs.stubs.Cube.ConsultaDescricao;
import org.globus.drs.stubs.Cube.CriarConsulta;
import org.globus.drs.stubs.Cube.GetCampo;
import org.globus.drs.stubs.Cube.GetCampoResponse;
import org.globus.drs.stubs.Cube.GetResumo;
import org.globus.drs.stubs.Cube.Opcoes;
import org.globus.drs.stubs.Cube.RemoveCampo;
import org.globus.drs.stubs.Cube.ResumoResponse;
import org.globus.drs.stubs.Cube.SalvarOrdemCampos;
import org.globus.drs.stubs.Cube.SalvarOrdemDados;
import org.globus.drs.stubs.Cube.VerificarConsulta;
import org.globus.wsrf.Resource;
import org.globus.wsrf.ResourceProperties;
import org.globus.wsrf.ResourceProperty;
import org.globus.wsrf.ResourcePropertySet;
import org.globus.wsrf.impl.ReflectionResourceProperty;
import org.globus.wsrf.impl.SimpleResourcePropertySet;

public class DRSService implements Resource, ResourceProperties{

	private HashMap<Integer,Consulta> a_consulta;
	private int idConsulta=0;
	
	private String DRSURI;
	
	/* Resource Property set */
	private ResourcePropertySet propSet;
	
	public DRSService(){
		a_consulta = new HashMap<Integer,Consulta>();
		
		/* Create RP set */
		this.propSet = new SimpleResourcePropertySet(DRSQNames.RESOURCE_PROPERTIES);
		
		/* Initialize the RP's */
		try {
			ResourceProperty cubeRP = new ReflectionResourceProperty(
				DRSQNames.RP_DRS, "DRSURI", this);
			this.propSet.add(cubeRP);
		} catch (Exception e) {	throw new RuntimeException(e.getMessage());
		}
	
	}
	
	
	public String getDRSURI() {
		return DRSURI;
	}

	public void setDRSURI(String cube) {
		DRSURI = cube;
	}
	
	public boolean removeCampo(RemoveCampo removeCampo){
		Atributo atributo = removeCampo.getAtributo();
		if (a_consulta.get(new Integer(atributo.getConsulta())).contemTabela(atributo.getTabela())){
			Tabela tabela = a_consulta.get(new Integer(atributo.getConsulta())).getTabela(atributo.getTabela());
			if (tabela.contemCampo(atributo.getCampo())){
				tabela.removeCampo(atributo.getCampo());
				return true;
			}
		}
		
		return false;
	}
	
	public boolean addCampo( AddCampo addcampo ){
		
		try {
			
			Atributo atributo = addcampo.getAtributo();
			
			/*
			 * Desculpe-nos. Esse é o método mais Esquisito que já codifiquei. Quebra várias regras de boas práticas, por exemplo, um método deve fazer penas uma coisa bem definida. Desculpa, mas, "esquisito", é pouco....
			 */
			verificarConsulta(new VerificarConsulta(new ConsultaDescricao(atributo.getConsulta(),atributo.getCubeEntry(),atributo.getCubo(),atributo.getTabela(),atributo.getUri())));
			/*
			 * Como o método ilário acima uma consulta que não existia passa a existir e continuamos nosso trabalho...
			 */
			
			if (a_consulta.get(new Integer(atributo.getConsulta())).getTabela(atributo.getTabela()).contemCampo(atributo.getCampo()) == true){  // transliterando: se na consulta houver a tabela que contém o atributo e retornar true
					//System.out.println("Vou no meio do caminho....");
					Campo campo = a_consulta.get(new Integer(atributo.getConsulta())).getTabela(atributo.getTabela()).getCampo(atributo.getCampo());
					campo.setAgrupar(atributo.isAgrupar());
					campo.setMostrar(atributo.isMostrar());
					campo.setOrdenar(atributo.isOrdenar());
					campo.setOrdenarDirecao(atributo.getOrdenarDirecao());
					
					
					campo.limparCondicao();
					if (atributo.getOpcoes() != null){
						for(int c=0; c < atributo.getOpcoes().length; c++){
							//System.out.println("Quero adicionar condicoes: "+c);
							Condicao cond = new Condicao(atributo.getOpcoes(c).getLogico(),atributo.getOpcoes(c).getComparacao(),atributo.getOpcoes(c).getValor());
							campo.AddCondicao(cond);
							//System.out.println("Adicionei a comparação "+(c+1));
						}
					}
					
					campo.limparAgregacaoSelect();
					if (atributo.getAgSel() != null){
						for(int c=0; c < atributo.getAgSel().length; c++){
							AgregacaoSelect agSelect = new AgregacaoSelect(atributo.getAgSel(c).getAgregacao());
							campo.AddAgregacaoSelect(agSelect);
							//System.out.println("\nIncluí AGREGAÇÂO");
							//System.out.println("Adicionei a comparação "+(c+1));
						}
					}
					
					campo.limparAgregacaoHaving();
					if (atributo.getAgHav() != null){
						for(int c=0; c < atributo.getAgHav().length; c++){
							AgHav atributoAgHav = atributo.getAgHav(c);
							AgregacaoHaving agHaving = new AgregacaoHaving(atributoAgHav.getLogico(),atributoAgHav.getAgregacao(), atributoAgHav.getComparacao(), atributoAgHav.getValor());
							campo.AddAgregacaoHaving(agHaving);
						}
					}
					
					
					//if( (campo.getMostrar() & atributo.getMostrarPosicao() > 0) || campo.sizeAgregacaoSelect() > 0 ){ //Se mostrar é true E mostrarPosicao > 0 OU tiver agregações na seleção
					if( campo.getMostrar() || campo.sizeAgregacaoSelect() > 0 ){ //Se mostrar é true E mostrarPosicao > 0 OU tiver agregações na seleção
						if( ! ( a_consulta.get(new Integer(atributo.getConsulta())).getOrdemCampos().containsKey(campo.getIdObjeto()) ) ){ // Se não tiver o campo na lista de ordenação de campo
							campo.setMostrarPosicao(atributo.getMostrarPosicao());
							a_consulta.get(new Integer(atributo.getConsulta())).getOrdemCampos().add(campo, campo.getIdObjeto()) ;
							System.out.println("\nCampo Vai ser adicionado em ordem campo. mostrar ou agSel são verdadeiros");
						}
						
					}
					else if( !campo.getMostrar() & campo.sizeAgregacaoSelect() == 0){
						a_consulta.get(new Integer(atributo.getConsulta())).getOrdemCampos().remove(campo.getIdObjeto());
						System.out.println("\nCampo Vai ser removido da ordem por que mostrar é false e não tem agSel");
					}
					
					if(campo.getOrdenar()){
						if( ! ( a_consulta.get(new Integer(atributo.getConsulta())).getOrdemDados().containsKey(campo.getIdObjeto()) ) ){ // Se não tiver o campo na lista de ordenação de dados
							campo.setOrdenarPosicao(atributo.getOrdenarPosicao());
							a_consulta.get(new Integer(atributo.getConsulta())).getOrdemDados().add(campo, campo.getIdObjeto()) ;
							System.out.println("\nCampo Vai ser adicionado em ordem dados. ordenar é verdadeiros");
						}
					}
					else{
						a_consulta.get(new Integer(atributo.getConsulta())).getOrdemDados().remove(campo.getIdObjeto());
						System.out.println("\nCampo Vai ser removido da ordem por que mostrar é false");
					}
					
					
					return true;
				}else{
					//System.out.println("O campo não existia");
					
			
			CubeServiceAddressingLocator locator = new CubeServiceAddressingLocator();
			EndpointReferenceType endpoint = new EndpointReferenceType();
			String uri = atributo.getUri(); // Serviço Cubo "http://eingrid002.unigranrio.br:8443/wsrf/services/cube/Cube";
			endpoint.setAddress(new Address(uri));
			CubePortType cube = locator.getCubePortTypePort(endpoint);
			//System.out.println("Meu portType do cubo é: "+cube);
			
			CubeMetadataResponse cubeMD = cube.getCubeMetaData(atributo.getCubo());
			FatoMetaData fatoMD = cubeMD.getFatoMetaData();
			DimensaoMetaData dimMD;
			if ( atributo.getTabela() > 0){
				for(int j=0; j < fatoMD.getDimensaoMetaData().length; j++){
					dimMD = fatoMD.getDimensaoMetaData(j);
					if ( atributo.getTabela() == dimMD.getKey() ){ // encontrar dimensão correspondente
						//System.out.println("Encontrei a Dimensão desejada: "+dimMD.getName());
						for(int i=0; i <dimMD.getFieldMetaData().length; i++){
							FieldMetaData fdMD = dimMD.getFieldMetaData(i);
							if( atributo.getCampo()  == fdMD.getKey() ){ // encontrar atributo correspondente
								//System.out.println("Encontrei o atributo desejado: "+ fdMD.getName());
								Campo campo = new Campo(a_consulta.get(new Integer(atributo.getConsulta())).getTabela(atributo.getTabela()));
								campo.setNome(fdMD.getName());
								campo.setTipo(fdMD.getType());
								
								campo.setAgrupar(atributo.isAgrupar());
								campo.setMostrar(atributo.isMostrar());
								campo.setMostrarPosicao(atributo.getMostrarPosicao());
								campo.setOrdenar(atributo.isOrdenar());
								campo.setOrdenarPosicao(atributo.getOrdenarPosicao());
								campo.setOrdenarDirecao(atributo.getOrdenarDirecao());
								
								if (atributo.getOpcoes() != null){
									for(int c=0; c < atributo.getOpcoes().length; c++){
										Condicao cond = new Condicao(atributo.getOpcoes(c).getLogico(),atributo.getOpcoes(c).getComparacao(),atributo.getOpcoes(c).getValor());
										campo.AddCondicao(cond);
										//System.out.println("Adicionei a comparação "+(c+1));
									}
								}
								
								if (atributo.getAgSel() != null){
									for(int c=0; c < atributo.getAgSel().length; c++){
										AgregacaoSelect agSelect = new AgregacaoSelect(atributo.getAgSel(c).getAgregacao());
										campo.AddAgregacaoSelect(agSelect);
										//System.out.println("\nIncluí AGREGAÇÂO");
									}
								}
								
								a_consulta.get(new Integer(atributo.getConsulta())).getTabela(atributo.getTabela()).addCampo(campo,fdMD.getKey());
								//System.out.println("O campo foi adicionado à tabela");
							}
						}
					}
				}
			} else if( atributo.getTabela() == (-1)){ // Casos seja a tabela FATO(-1)
				//System.out.println("Entri no fato");
				for(int j=0; j < fatoMD.getFieldMetaData().length ; j++){
					FieldMetaData fdMD = fatoMD.getFieldMetaData(j);
					if( atributo.getCampo()  == fdMD.getKey() ){ // encontrar atributo correspondente
						//System.out.println("Encontrei o atributo desejado: "+ fdMD.getName());
						Campo campo = new Campo(a_consulta.get(new Integer(atributo.getConsulta())).getTabela(atributo.getTabela()));
						campo.setNome(fdMD.getName());
						campo.setTipo(fdMD.getType());
						
						campo.setAgrupar(atributo.isAgrupar());
						campo.setMostrar(atributo.isMostrar());
						campo.setMostrarPosicao(atributo.getMostrarPosicao());
						campo.setOrdenar(atributo.isOrdenar());
						campo.setOrdenarPosicao(atributo.getOrdenarPosicao());
						campo.setOrdenarDirecao(atributo.getOrdenarDirecao());
						
						//System.out.println("As opções são: "+atributo.getOpcoes());
						if (atributo.getOpcoes() != null){
							for(int c=0; c < atributo.getOpcoes().length; c++){
								Condicao cond = new Condicao(atributo.getOpcoes(c).getLogico(),atributo.getOpcoes(c).getComparacao(),atributo.getOpcoes(c).getValor());
								campo.AddCondicao(cond);
								//System.out.println("Adicionei a comparação "+(c+1));
							}
						}
						
						if (atributo.getAgSel() != null){
							for(int c=0; c < atributo.getAgSel().length; c++){
								AgregacaoSelect agSelect = new AgregacaoSelect(atributo.getAgSel(c).getAgregacao());
								//System.out.println("QDT de AgSelect: "+campo.sizeAgregacaoSelect());
								campo.AddAgregacaoSelect(agSelect);
								//System.out.println("\nIncluí AGREGAÇÂO");
								//System.out.println("QDT de AgSelect: "+campo.sizeAgregacaoSelect());
							}
						}
						
						
						a_consulta.get(new Integer(atributo.getConsulta())).getTabela(atributo.getTabela()).addCampo(campo,fdMD.getKey());
						//System.out.println("O campo foi adicionado à tabela");
					}
				}
			}
			
			} //if
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public int criarConsulta(CriarConsulta criar){
		idConsulta++;
		a_consulta.put(new Integer(idConsulta), null);
		return idConsulta;
	}
	
	public int verificarConsulta(VerificarConsulta verificarConsulta){	
		ConsultaDescricao criar = verificarConsulta.getConsultaDescricao();
		
		try {
			//System.out.println("ESTOU EM criarConsulta");
			
			if (a_consulta.get(new Integer(criar.getConsulta())) != null){ // Se a consulta existir
				if( a_consulta.get(new Integer(criar.getConsulta())).contemTabela(criar.getTabela()) == true && criar.getCubeEntry() == a_consulta.get(new Integer(criar.getConsulta())).getCubeIndexEntry()){
					//System.out.println("Entrada do cubo no Index: "+criar.getCubeEntry()+" Cube id:"+criar.getCubo()+" . Consulta ja existe");
					return criar.getConsulta();
				}
				
				if (criar.getCubeEntry() != a_consulta.get(new Integer(criar.getConsulta())).getCubeIndexEntry()){ // Se a entrada do cubo for diferente da atual
					a_consulta.put(new Integer(criar.getConsulta()), null); //Apagar a consulta setando null
					//System.out.println("Entrada do cubo no Index: "+criar.getCubeEntry()+" Cube id:"+criar.getCubo()+" A entrada era diferente. Consulta Zerada");
				}
				
			}
			
			CubeServiceAddressingLocator locator = new CubeServiceAddressingLocator();
			EndpointReferenceType endpoint = new EndpointReferenceType();
			String uri = criar.getUri();//Service Cube "http://eingrid002.unigranrio.br:8443/wsrf/services/cube/Cube";
			endpoint.setAddress(new Address(uri));
			CubePortType cube = locator.getCubePortTypePort(endpoint);
			
			CubeMetadataResponse cubeMD = cube.getCubeMetaData(criar.getCubo());
			FatoMetaData fatoMD = cubeMD.getFatoMetaData();
			DimensaoMetaData dimMD;
			
			if ( criar.getTabela() > 0){
				for(int j=0; j < fatoMD.getDimensaoMetaData().length; j++){
					dimMD = fatoMD.getDimensaoMetaData(j);
					if ( criar.getTabela() == dimMD.getKey()){ // encontrar dimensão correspondente
						Tabela tb = new Tabela( );
						tb.setNome(dimMD.getName());
						//System.out.println("A Ligação da dimensão "+tb.getNome()+" é "+dimMD.getLigacaoMetaData());
						if (dimMD.getLigacaoMetaData() != null){
							LigacaoMetaData[] ligMD = dimMD.getLigacaoMetaData();
							for (int l=0; l < ligMD.length ;l++){
								Ligacao ligacao = new Ligacao(ligMD[l].getEstrangeiro().getName(),ligMD[l].getPrimario().getName());
								tb.addLigacao(ligacao);
								//System.out.println("\nDRS: adicionei ligacao a dimensão "+tb.getNome());
							}
						}
						//System.out.println("\nConsulta criada usando uma Dimensão");
						
						if ((a_consulta.get(new Integer(criar.getConsulta())) == null) && criar.getConsulta() > 0){
							Consulta consulta = new Consulta(criar.getCubeEntry(),criar.getCubo(), cubeMD.getName() ,fatoMD.getName());
							consulta.setIdObjeto(criar.getConsulta());
							a_consulta.put(new Integer(criar.getConsulta()), consulta);
							//System.out.println("Entrada do cubo no Index: "+criar.getCubeEntry()+" Cube id:"+criar.getCubo()+" Consulta refeita");
						}
						
						Consulta consulta = a_consulta.get(new Integer(criar.getConsulta()));
						consulta.addTabela(tb,criar.getTabela());
						tb.setConsulta(consulta);
						tb.setIdObjeto(criar.getTabela());
						
						return criar.getConsulta();
					}
				}
			} else if( criar.getTabela() == (-1)){
				for(int j=0; j < fatoMD.getFieldMetaData().length ; j++){
					Tabela tb = new Tabela();
					tb.setNome(fatoMD.getName());
					
					
					//System.out.println("\nConsulta criada usando O fato");
					
					if ((a_consulta.get(new Integer(criar.getConsulta())) == null) && criar.getConsulta() > 0){
						Consulta consulta = new Consulta(criar.getCubeEntry(),criar.getCubo(), cubeMD.getName() ,fatoMD.getName());
						consulta.setIdObjeto(criar.getConsulta());
						a_consulta.put(new Integer(criar.getConsulta()), consulta);
						//System.out.println("Entrada do cubo no Index: "+criar.getCubeEntry()+" Cube id:"+criar.getCubo()+" Consulta refeita");
					}
					
					Consulta consulta = a_consulta.get(new Integer(criar.getConsulta()));
					consulta.addTabela(tb,criar.getTabela());
					tb.setConsulta(consulta);
					tb.setIdObjeto(criar.getTabela());
					
					return criar.getConsulta();
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return criar.getConsulta();
	}
	
	public String getSQL(int consulta){
		return a_consulta.get(new Integer(consulta)).getSQL(new Clausulas());
	}
	
	public GetCampoResponse getCampo(GetCampo getcampo){
		Atributo atributo = getcampo.getAtributo();
		//System.out.println("ESTOU EM GET CAMPO");
		if (a_consulta.get(new Integer(atributo.getConsulta())) != null){
			if ( (atributo.getCubo() == a_consulta.get(new Integer(atributo.getConsulta())).getIdCubo()) && (a_consulta.get(new Integer(atributo.getConsulta())).contemTabela(atributo.getTabela())) ){
				Tabela tabela = a_consulta.get(new Integer(atributo.getConsulta())).getTabela(atributo.getTabela());
				if (tabela.contemCampo(atributo.getCampo())){
					Campo campo = tabela.getCampo(atributo.getCampo());
					Atributo at = new Atributo(null,null,campo.getAgrupar(),atributo.getCampo(),null,atributo.getConsulta(),a_consulta.get(new Integer(atributo.getConsulta())).getCubeIndexEntry(),atributo.getCubo(),null,null,campo.getMostrar(),campo.getMostrarPosicao(),null,campo.getOrdenar(),campo.getOrdenarDirecao(),campo.getOrdenarPosicao(),atributo.getTabela(),null,null);
					//Atributo at = new Atributo();
					
					Opcoes[] op = null;
					if (campo.sizeCondicao() > 0){
						op = new Opcoes[campo.sizeCondicao()];
						int opIndex = 0;
						for(int i=0; i < campo.sizeCondicao() + 1; i++){
							if (campo.contemCondicao(i) == true){
								Condicao cond = campo.getCondicao(i);
								op[opIndex] = new Opcoes(cond.getComparacao(),cond.getLogico(),cond.getValor());
								opIndex++;
							}
						}
					}
					at.setOpcoes(op);
					
					
					AgSel[] agsel = null;
					if (campo.sizeAgregacaoSelect() > 0){
						agsel = new AgSel[campo.sizeAgregacaoSelect()];
						int agselIndex = 0;
						for(int j=0; j < campo.sizeAgregacaoSelect() + 1; j++){
							if (campo.contemAgregacaoSelect(j) == true){
								AgregacaoSelect agregacaoSelect = campo.getAgregacaoSelect(j);
								agsel[agselIndex] = new AgSel(agregacaoSelect.getFuncao());
								agselIndex++;

							} // if
						} //for
					} //if
					at.setAgSel(agsel);
					
					
					AgHav[] aghav = null;
					if (campo.sizeAgregacaoHaving() > 0){
						aghav = new AgHav[campo.sizeAgregacaoHaving()];
						int aghavIndex = 0;
						for(int j=0; j < campo.sizeAgregacaoHaving() + 1; j++){
							if (campo.contemAgregacaoHaving(j) == true){
								AgregacaoHaving agregacaoHaving = campo.getAgregacaoHaving(j);
								aghav[aghavIndex] = new AgHav(agregacaoHaving.getFuncao(),agregacaoHaving.getComparacao(),agregacaoHaving.getLogico(),agregacaoHaving.getValor());
								aghavIndex++;

							} // if
						} //for
					} //if
					at.setAgHav(aghav);
					
					//System.out.println("\n\nVOU RETORNAR O CAMPO EM GETCAMPO\n");
					return new GetCampoResponse(at);
				}
			}
		}else {
		//System.out.println("\nVOU devolver NULL em getcampo: consulta não existe");	
			return null;
		}
		//System.out.println("\nVOU devolver NULL em getcampo");
		return null;
	}
	
	public ResumoResponse getResumo(GetResumo resumo){
		int cube = resumo.getCubo();
		if (a_consulta.get(new Integer(resumo.getConsulta())) != null){
			
			if ( (cube == a_consulta.get(new Integer(resumo.getConsulta())).getIdCubo()) && (a_consulta.get(new Integer(resumo.getConsulta())).isEmptyTabela() == false) ){
			//if ( (a_consulta.get(new Integer(resumo.getConsulta())).isEmptyTabela() == false) ){
				int qtdAtributos = 0;
				for (int i=-1; i < a_consulta.get(new Integer(resumo.getConsulta())).getIdTabelaMax() + 1; i++){
					if (a_consulta.get(new Integer(resumo.getConsulta())).contemTabela(i)){
						qtdAtributos = qtdAtributos + a_consulta.get(new Integer(resumo.getConsulta())).getTabela(i).getCampoSize();
					}
				}
				
				int atIndex = 0;
				Atributo[] resumoAtributos = new Atributo[qtdAtributos];
				
				//System.out.println("Qtd de atributos prevista " + qtdAtributos);
				//System.out.println("atributos: " + atributos);
				
				for (int i=-1; i < a_consulta.get(new Integer(resumo.getConsulta())).getIdTabelaMax() + 1; i++){
					if (a_consulta.get(new Integer(resumo.getConsulta())).contemTabela(i)){
						Tabela tabela = a_consulta.get(new Integer(resumo.getConsulta())).getTabela(i);
						for(int k=0; k < tabela.getIdCampoMax() + 1; k++){
							if (tabela.contemCampo(k)){
								Campo campo = tabela.getCampo(k);
								Atributo at = new Atributo(null,null,campo.getAgrupar(),k,campo.getNome(), resumo.getConsulta(),a_consulta.get(new Integer(resumo.getConsulta())).getCubeIndexEntry(),cube,a_consulta.get(new Integer(resumo.getConsulta())).getCubeNome(),null,campo.getMostrar(),campo.getMostrarPosicao(),null,campo.getOrdenar(),campo.getOrdenarDirecao(),campo.getOrdenarPosicao(),i,tabela.getNome(), null);
								//Atributo at = new Atributo();
								
								Opcoes[] op = null;
								if (campo.sizeCondicao() > 0){
									op = new Opcoes[campo.sizeCondicao()];
									int opIndex = 0;
									for(int j=0; j < campo.sizeCondicao() + 1; j++){
										if (campo.contemCondicao(j) == true){
											Condicao cond = campo.getCondicao(j);
											op[opIndex] = new Opcoes(cond.getComparacao(),cond.getLogico(),cond.getValor());
											opIndex++;
										} // if
									} //for
								} //if
								at.setOpcoes(op);
								
								AgSel[] agsel = null;
								if (campo.sizeAgregacaoSelect() > 0){
									agsel = new AgSel[campo.sizeAgregacaoSelect()];
									int agselIndex = 0;
									for(int j=0; j < campo.sizeAgregacaoSelect() + 1; j++){
										if (campo.contemAgregacaoSelect(j) == true){
											AgregacaoSelect agregacaoSelect = campo.getAgregacaoSelect(j);
											agsel[agselIndex] = new AgSel(agregacaoSelect.getFuncao());
											agselIndex++;

										} // if
									} //for
								} //if
								at.setAgSel(agsel);
								
								
								AgHav[] aghav = null;
								if (campo.sizeAgregacaoHaving() > 0){
									aghav = new AgHav[campo.sizeAgregacaoHaving()];
									int aghavIndex = 0;
									for(int j=0; j < campo.sizeAgregacaoHaving() + 1; j++){
										if (campo.contemAgregacaoHaving(j) == true){
											AgregacaoHaving agregacaoHaving = campo.getAgregacaoHaving(j);
											aghav[aghavIndex] = new AgHav(agregacaoHaving.getFuncao(),agregacaoHaving.getComparacao(),agregacaoHaving.getLogico(),agregacaoHaving.getValor());
											aghavIndex++;

										} // if
									} //for
								} //if
								at.setAgHav(aghav);
								
								
								resumoAtributos[atIndex] = at;
								atIndex++;
								
							} //if
						} //for campo
					} //if
				} // for tabela
				
				
				
				Object[] camposOrdem = a_consulta.get(new Integer(resumo.getConsulta())).getOrdemCampos().getCamposOrdenadosToArray();
				Atributo[] resumoOrdemAtributos = new Atributo[camposOrdem.length];
				for(int i = 0; i < camposOrdem.length ; i++ ){
					Campo campo = (Campo)camposOrdem[i];
					Atributo at = new Atributo();
					at.setIdObjeto(campo.getIdObjeto());
					at.setCampoNome(campo.getNome());
					resumoOrdemAtributos[i] = at;
					
					
				}//*/
				
				camposOrdem = a_consulta.get(new Integer(resumo.getConsulta())).getOrdemDados().getCamposOrdenadosToArray();
				Atributo[] resumoOrdemDados = new Atributo[camposOrdem.length];
				for(int i = 0; i < camposOrdem.length ; i++ ){
					Campo campo = (Campo)camposOrdem[i];
					Atributo at = new Atributo();
					at.setIdObjeto(campo.getIdObjeto());
					at.setCampoNome(campo.getNome());
					resumoOrdemDados[i] = at;
					
					
				}
				
				//System.out.println("QTD de Atributos: "+atributos.length);
				if (resumoAtributos.length == 0){
					return null;
				}
				return new ResumoResponse(resumoAtributos,resumoOrdemAtributos,resumoOrdemDados);
			} // if
		}else return null;
		
		return null;
		
		
	}
	
	public boolean salvarOrdemCampos(SalvarOrdemCampos atributos){
		System.out.println("\nNova Ordenação: " + atributos.getOrdemAtributos().length);
		for(int i = 0; i < atributos.getOrdemAtributos().length ; i++){
			Atributo atributo = atributos.getOrdemAtributos()[i];
			
			System.out.println(atributo.getIdObjeto() + " - " + atributo.getCuboNome() + " - " + atributo.getMostrarPosicao());
			
			if (a_consulta.get(new Integer(atributo.getConsulta())) != null){
				Campo campo = (Campo)(a_consulta.get(new Integer(atributo.getConsulta())).getOrdemCampos()).get( (Object)atributo.getIdObjeto() );
				
				if(campo != null){
					System.out.println("Campo com nova posição: " + campo.getIdObjeto() + " - " + campo.getNome() + " - " + atributo.getMostrarPosicao());
					campo.setMostrarPosicao(atributo.getMostrarPosicao());
					System.out.println("Campo com nova posição: " + campo.getIdObjeto() + " - " + campo.getNome() + " - " + atributo.getMostrarPosicao());
					
					System.out.println("Nova posição real: " + ((Campo)(a_consulta.get(new Integer(atributo.getConsulta())).getOrdemCampos()).get( (Object)atributo.getIdObjeto() )).getMostrarPosicao());
				}
			}
		}
		return false;
	}
	
	public boolean salvarOrdemDados(SalvarOrdemDados atributos){
		System.out.println("\nNova Ordenação: " + atributos.getOrdemDados().length);
		for(int i = 0; i < atributos.getOrdemDados().length ; i++){
			Atributo atributo = atributos.getOrdemDados()[i];
			
			System.out.println(atributo.getIdObjeto() + " - " + atributo.getCuboNome() + " - " + atributo.getOrdenarPosicao());
			
			if (a_consulta.get(new Integer(atributo.getConsulta())) != null){
				Campo campo = (Campo)(a_consulta.get(new Integer(atributo.getConsulta())).getOrdemDados()).get( (Object)atributo.getIdObjeto() );
				
				if(campo != null){
					System.out.println("Campo com nova posição: " + campo.getIdObjeto() + " - " + campo.getNome() + " - " + atributo.getOrdenarPosicao());
					campo.setOrdenarPosicao(atributo.getOrdenarPosicao());
					System.out.println("Campo com nova posição: " + campo.getIdObjeto() + " - " + campo.getNome() + " - " + atributo.getOrdenarPosicao());
					
					System.out.println("Nova posição real: " + ((Campo)(a_consulta.get(new Integer(atributo.getConsulta())).getOrdemDados()).get( (Object)atributo.getIdObjeto() )).getOrdenarPosicao());
				}
			}
		}
		return false;
	}
	
	/* Required by interface ResourceProperties */
	public ResourcePropertySet getResourcePropertySet() {
		return this.propSet;
	}
	
	
}
