package br.com.kasystemas.drsclienteweb.model.exportarResultado;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.globus.cube.stubs.Cube.ExecuteQueryResponse;

public class ResultadoParaExcel {
	
	HttpServletRequest request;
	HttpServletResponse response;
	String sql;
	OutputStream saida;
	
	int posicaoLinha = 1; // verificar se valor (limite) pode ser até (Short.MAX_VALUE * 2)
	
	public ResultadoParaExcel(HttpServletRequest request, HttpServletResponse response, String sql, OutputStream saida) {
		this.request = request;
		this.response = response;
		this.sql = sql;
		this.saida = saida;
	}
	
	public String parserToExcel(ExecuteQueryResponse exquery) throws IOException{
		
		Workbook wb = new HSSFWorkbook();
	    Sheet pastaResultado = wb.createSheet("Resultado");
	    
	    Row linha = pastaResultado.createRow(posicaoLinha);
	    
	    for (int k=0; k < exquery.getResultColumnName().length; k++){
	    	linha.createCell(k).setCellValue( exquery.getResultColumnName(k).toUpperCase() );
		}
	    
	    posicaoLinha++;
		int quantidadeObjetosResultado = 0;
		
		//Testa se existe resultado
		if (exquery.getColumnResponse() != null){ 
			for(int v=0;v < exquery.getColumnResponse(0).getColumn().length; v++){

				quantidadeObjetosResultado++;
				posicaoLinha++;
				linha = pastaResultado.createRow(posicaoLinha);
				
				for(int u = 0; u < exquery.getResultColumnName().length; u++){
					if ( exquery.getColumnResponse(u).getColumn(v) != null ){
						linha.createCell(u).setCellValue( exquery.getColumnResponse(u).getColumn(v) );
					}
				}
			}
		}
		
	    wb.write( saida );
	    
		return null;
	}

	public void definirTipoDeResultado() {
		response.setHeader("Content-disposition", "attachment; filename=resultadoConsulta.xls");
		response.setContentType("application/xls");
	}
}
