package org.drs.client;

//import java.util.Date;


import org.apache.axis.message.addressing.Address;
import org.apache.axis.message.addressing.EndpointReferenceType;
import org.globus.drs.stubs.Cube.AddCampo;
import org.globus.drs.stubs.Cube.GetCampo;
import org.globus.drs.stubs.Cube.GetCampoResponse;
import org.globus.drs.stubs.Cube.ResumoResponse;
import org.globus.drs.stubs.Cube.RemoveCampo;
import org.globus.drs.stubs.Cube.Atributo;
import org.globus.drs.stubs.Cube.CriarConsulta;
import org.globus.drs.stubs.Cube.Opcoes;
import org.globus.drs.stubs.Cube.DRSPortType;
import org.globus.drs.stubs.Cube.GetResumo;
import org.globus.drs.stubs.Cube.AgSel;
import org.globus.drs.stubs.Cube.AgHav;

//import org.globus.drs.stubs.Cube.
import org.globus.drs.stubs.Cube.bindings.*;
import org.globus.drs.stubs.Cube.service.*;
import org.globus.drs.stubs.Cube.ConsultaDescricao;
import org.globus.cube.stubs.Cube.CubePortType;
import org.globus.cube.stubs.Cube.ExecuteQuery;
import org.globus.cube.stubs.Cube.ExecuteQueryResponse;
import org.globus.cube.stubs.Cube.service.CubeServiceAddressingLocator;

public class ClientDRS {

	/**
	 * @param args
	 */
	
	public static void run(String serviceURI){
		DRSServiceAddressingLocator locator = new DRSServiceAddressingLocator();

		try {
			
			// Create endpoint reference to service
			EndpointReferenceType endpoint = new EndpointReferenceType();
			endpoint.setAddress(new Address(serviceURI));
			
			// Get PortType
			DRSPortType drs = locator.getDRSPortTypePort(endpoint);
			
			//*/
			
				//System.out.println("Criei a consulta: "+drs.criarConsulta(new CriarConsulta()));
			//new ConsultaDescricao(-1,1,1)
				//System.out.println("\nNão criei a consulta");
			
			//*/
			
			int cuboSelecionado = 1;
			int consulta = 2;
			//*
			//System.out.println("vou adicionar um atributo");
			
			/*/
			Opcoes[] op = new Opcoes[0];
			//op[0] = new Opcoes(4,1,new String("0"));
			//op[1] = new Opcoes(4,2,new String("60"));
			
			
			AgSel[] agSelect = new AgSel[1];
			agSelect[0] = new AgSel(1);
			
			AgHav[] agHaving = new AgHav[2];
			agHaving[0] = new AgHav(1,1,1,"2");
			agHaving[1] = new AgHav(3,6,2,"15");
			//						q r p s
			Atributo at = new Atributo(agHaving,agSelect,true,2,null,consulta,cuboSelecionado,null,true,op,-1,null);//*/

			/*
			Opcoes[] op = new Opcoes[0];
			//op[0] = new Opcoes(6,1,new String("'qq'"));
			//op[1] = new Opcoes(4,2,new String("60"));
			Atributo at = new Atributo(false,3,cuboSelecionado,true,op,-1);//*/
			
			/*
			int cons = drs.criarConsulta(new CriarConsulta());
			System.out.println("Criei a consulta "+cons);//*/
			
			
			/*
			if(drs.addCampo(new AddCampo(at)) == true){
				System.out.println("\nAdicionei o atributo");
			}else {
				System.out.println("Não pude adicionar o atributo");
			}//*/
			
			/*/
			Atributo atributoRM = new Atributo(false,2,1,false,null,-1);
			if ( drs.removeCampo( new RemoveCampo(atributoRM)) ) {
				System.out.println("Removido o atributo");
			}else System.out.println("Não foi removido o atributo");
			//*/
			
			/*
			System.out.println("vou receber o SQL:");      //SQL
			String sql = drs.getSQL(consulta);
			System.out.println(sql+"\n");
			//*/
			
			/*
			Atributo at = new Atributo(null,null,false,2,null,consulta,cuboSelecionado,null,false,null,-1,null);
			GetCampoResponse getcampo = drs.getCampo(new GetCampo(at));
			Atributo atributo = getcampo.getAtributo();
			
			System.out.println();
			
			System.out.println("Mostrar: " + atributo.isMostrar() );
			System.out.println("Agrupar: " + atributo.isAgrupar());
			
			if (atributo.getOpcoes() != null){
				for (int i=0; i < atributo.getOpcoes().length; i++){
					Opcoes op = atributo.getOpcoes(i);
					System.out.println(op.getLogico() +","+op.getComparacao() +","+ op.getValor());
				}
			}	
			
			//System.out.println(atributo.getAgSel());
			if (atributo.getAgSel() != null){
				for (int i=0; i < atributo.getAgSel().length; i++){
					AgSel agsel = atributo.getAgSel(i);
					System.out.println("Agregação Select: "+agsel.getAgregacao());
				}
			}
			
			System.out.println(atributo.getAgHav());
			if (atributo.getAgHav() != null){
				System.out.println("Agregação having: ");
				for (int i=0; i < atributo.getAgHav().length; i++){
					AgHav aghav = atributo.getAgHav(i);
					System.out.println(aghav.getLogico()+","+aghav.getAgregacao()+","+aghav.getComparacao()+","+aghav.getValor());
				}
			}
			
			
			//*/

			
			//*
			ResumoResponse getresumo = drs.getResumo(new GetResumo(consulta,cuboSelecionado,-1));
			//drs.getResumo(1).getAtributo(0).getCuboNome();
			if (getresumo != null){
			
			Atributo[] atributos = getresumo.getResumoAtributos();
			
			for (int j=0; j < atributos.length; j++){
				Atributo atributo = atributos[j];
				
				System.out.println("Cubo: " + atributo.getCuboNome() );
				System.out.println("Tabela: " + atributo.getTabelaNome() );
				System.out.println("Atributo: " + atributo.getCampoNome() );
				System.out.println("Mostrar: " + atributo.isMostrar() );
				System.out.println("Agrupar: " + atributo.isAgrupar());
				//System.out.println("opções "+atributo.getOpcoes());
				System.out.println("\nOpções:");
				if (atributo.getOpcoes() != null){
					for (int i=0; i < atributo.getOpcoes().length; i++){
						Opcoes op = atributo.getOpcoes(i);
						System.out.println(op.getLogico() +","+op.getComparacao() +","+ op.getValor());
					}
				}
				System.out.println();
				if (atributo.getAgSel() != null){
					for (int i=0; i < atributo.getAgSel().length; i++){
						AgSel agsel = atributo.getAgSel(i);
						System.out.println("Agregação Select: "+agsel.getAgregacao());
					}
				}
				
				System.out.println();
				if (atributo.getAgHav() != null){
					System.out.println("Agregação having: ");
					for (int i=0; i < atributo.getAgHav().length; i++){
						AgHav aghav = atributo.getAgHav(i);
						System.out.println(aghav.getLogico()+","+aghav.getAgregacao()+","+aghav.getComparacao()+","+aghav.getValor());
					}
				}
				
				System.out.println();
			}
			
			}//*/
			
			/*
			CubeServiceAddressingLocator locatorCube = new CubeServiceAddressingLocator();
			EndpointReferenceType endpointCube = new EndpointReferenceType();
			endpointCube.setAddress(new Address("http://eingrid002.unigranrio.br:8443/wsrf/services/cube/Cube"));
			CubePortType cube = locatorCube.getCubePortTypePort(endpointCube);
			
			ExecuteQueryResponse exquery = cube.executeQuery(new ExecuteQuery(cuboSelecionado, sql));
			
			
			String columns = new String(exquery.getResultColumnName(0));
			for (int k=1; k < exquery.getResultColumnName().length; k++){
				columns = columns+"    "+exquery.getResultColumnName(k);
			}
			System.out.println(columns);
			System.out.println("------------------------------------");
			
			if (exquery.getColumnResponse() == null){
				System.out.println("\n A CONSULTA NÃO OBTEVE RESULTADOS");
			}
			else {
				
				for(int v=0;v < exquery.getColumnResponse(0).getColumn().length; v++){
			
				if (! (exquery.getColumnResponse(0).getColumn(v) == null ))
					System.out.print(exquery.getColumnResponse(0).getColumn(v));
				else
					System.out.print("      ");
				
				for(int u = 1; u < exquery.getResultColumnName().length; u++){
					if (! (exquery.getColumnResponse(u).getColumn(v) == null ))
						System.out.print("  |  " + exquery.getColumnResponse(u).getColumn(v));
					else
						System.out.print("  |        ");
				}
				System.out.println();
			}
			}//*/
			
			String upcas = new String("Caixa alta");
			System.out.println(upcas.toUpperCase());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) { 
		
		String serviceURI = "http://eingrid005.unigranrio.br:8443/wsrf/services/cube/DRS"; //new String(args[0]);
		
		run(serviceURI);
		
	}

}
