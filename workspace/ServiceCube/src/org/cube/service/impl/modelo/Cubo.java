package org.cube.service.impl.modelo;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Timer;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.cube.service.impl.control.tarefas.TaskRefresh;

@Entity
public class Cubo {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String a_UriService; // uri do servi�o Cube
	
	private String a_nome;
	private String a_server;
	
	@OneToOne(mappedBy="cubo", fetch=FetchType.LAZY)
	private Fato a_fato;
	
	private String a_driver;
	private String conn_url;
	private String conn_user;
	private String conn_password;
	
	@Transient
	private int keyCubeIndex = -1; // key recebida ao se cadastrar no CubeIndex 
	
	@Transient
	private Timer a_timer;
	@Transient
	private long a_refresh;
	

	public Cubo(){
			
	}
	
	public Cubo(String nome){
		a_nome = nome;	
	}
	
	public Cubo(String nome,String server,Fato fato,String url,String user,String password, String driver, long refresh){
		a_nome = nome;
		a_server = server;
		a_fato = fato;
		conn_url = url;
		conn_user = user;
		conn_password = password;
		setDriver(driver);
		a_refresh = refresh;
		
		System.out.println("\n" + nome +" "+ server +" "+ fato +" "+ url +" "+ user +" "+ password +" "+ driver +" "+ refresh);
		
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setDriver(String driver){
		a_driver = driver;
		try {
			//Class.forName(a_driver);   // Inicializa��o do driver jdbc
		}catch (Exception e){ e.printStackTrace();}
	}
	
	public String getDriver(){
		return a_driver;
	}
	
	public void setUri_service(String uri){
		a_UriService = uri;	
	}
	public String getUri_service(){
		return a_UriService;		
	}	
	
	
	public void setKeyindex(int key){
		keyCubeIndex = key;
	}
	public int getKeyindex(){
		return keyCubeIndex; // mant�m o index recebido do CubeIndex, � din�mico e n�o armazenado
	}
	
	public void setNome(String nome){
		a_nome = nome;	
	}
	public String getNome(){
		return a_nome;		
	}
	
	
	public void setServer(String servidor){
		a_server = servidor;
	}
	public String getServer(){
		return a_server;
	}
	
	
	public void setFato(Fato fato){
		a_fato = fato;
	}
	public Fato getFato(){
		return a_fato;
	}
	
	public void setTimer(String serviceURI, long millisecond, int idcube){
		a_timer = new Timer();
		a_timer.schedule(new TaskRefresh(serviceURI, idcube, this), 0, millisecond);
		
	}
	
	public void cancelTimer(){
		a_timer.cancel();
	}
	
	public void setRefresh(long refresh){
		a_refresh = refresh;
	}
	
	public long getRefresh(){
		return a_refresh;
	}
	
	public void setConnection(String url, String user, String password){
		try {
			
			this.conn_url = url;
			this.conn_user = user;
			this.conn_password = password;
			
		}catch (Exception e){ e.printStackTrace();}
	}
	
	
	public Connection getConnection(){
		try {
			Connection conn;
			conn = DriverManager.getConnection(conn_url, conn_user, conn_password);
			return  conn;
		}catch (Exception e){ e.printStackTrace();}
		return null;
	}
	
	
	public String imprimir(PrintStream p){
		String print;
		//p.println("Cubo: "+this.getNome().toUpperCase());
		print = ("Cubo: "+this.getNome().toUpperCase())+"\n\n";
		//p.println();
		print = a_fato.imprimir(p,print);
		return print;
		
	}
	
	@Override
	public String toString(){
		return getNome();
	}
	
	
	
}
