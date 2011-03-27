package br.com.kasystemas.drsclienteweb.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.globus.cube.stubs.Cube.ExecuteQueryResponse;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.ProcessingInstruction;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class ResultadoParaXml {
	
	HttpServletRequest request;
	HttpServletResponse response;
	String sql;
	
	public ResultadoParaXml(HttpServletRequest request, HttpServletResponse response, String sql) {
		this.request = request;
		this.response = response;
		this.sql = sql;
	}
	
	public String parserToXML(ExecuteQueryResponse exquery) throws IOException{
		
		response.setHeader("Content-disposition", "attachment; filename=resultadoConsulta.xml");
		response.setContentType("text/xml");
		
		//PrintWriter out = response.getWriter();
		
		Element resuladoRoot = new Element("resultQuery");
		
		Element description = new Element("description"); // elemento com descrição do resultado: cubo, query submetida, hora de início e fim, quantidade de objetos no resultado etc...
		resuladoRoot.addContent(description);
		
		Element querySent = new Element("querySent"); // consulta submetida
		querySent.addContent(sql);
		description.addContent(querySent);
		
		
		Element result = new Element("result");
		resuladoRoot.addContent(result);
		
		int quantidadeObjetosResultado = 0;
		
		if (exquery.getColumnResponse() != null){ //Testa se o resultado possui alguma tupla
			
			
			
			
			
			for(int v=0;v < exquery.getColumnResponse(0).getColumn().length; v++){
				
				quantidadeObjetosResultado++;
				
				Element objetoResultado = new Element("resultObject");
				objetoResultado.setAttribute("counter", String.valueOf(quantidadeObjetosResultado) );
				
				for (int k=0; k < exquery.getResultColumnName().length; k++){
					exquery.getResultColumnName(k).toUpperCase();
				}
				
				
				if (! (exquery.getColumnResponse(0).getColumn(v) == null )){
					//out.print(exquery.getColumnResponse(0).getColumn(v));
					//System.out.print(exquery.getColumnResponse(0).getColumn(v));
					
					//Element atributo = new Element("field");
					//atributo.setAttribute("columnName", exquery.getResultColumnName(0));
					Element atributo = new Element(exquery.getResultColumnName(0));
					atributo.addContent(exquery.getColumnResponse(0).getColumn(v));
					objetoResultado.addContent(atributo);
				}
				else{
					//out.print("\nvazio");
				}
				//out.println("\n</td>");

				for(int u = 1; u < exquery.getResultColumnName().length; u++){
					//out.println("\n<td ALIGN=LEFT BGCOLOR=#FFFFFF>");
					if (! (exquery.getColumnResponse(u).getColumn(v) == null )){

						//Element atributo = new Element("field");
						//atributo.setAttribute("columnName", exquery.getResultColumnName(u));
						Element atributo = new Element(exquery.getResultColumnName(u));
						atributo.addContent(exquery.getColumnResponse(u).getColumn(v));
						objetoResultado.addContent(atributo);
					}
					else{
						//out.print("\nvazio");
					}
					//out.println("\n</td>");
				}//for
				
				//out.println("</tr>\n\n");
				
				
				//resuladoRoot.addContent(objetoResultado);
				result.addContent(objetoResultado);
				//quantidadeObjetosResultado++;
			}//for

		}
		
		Element resultSize = new Element("resultSize"); // consulta submetida
		resultSize.addContent(String.valueOf(quantidadeObjetosResultado));
		description.addContent(resultSize);
		
		//Criando o documento
	    Document doc = new Document();
	    doc.setRootElement(resuladoRoot);
	    
	    //Configurando saída do XML
	    XMLOutputter xout = new XMLOutputter();
	    
	    Format format = Format.getPrettyFormat();
	    format.setEncoding("ISO-8859-1");
	    xout.setFormat(format);
	    
	    
	    //Imptrimindo o XML
	    //System.out.println(xout.outputString(doc));
	    
	    PrintWriter out = response.getWriter();
		out.println(xout.outputString(doc));
	    
		return null;
	}
}
