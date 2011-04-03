package org.cube.service.impl.modelo;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Timer;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import org.cube.service.impl.control.tarefas.TaskRefresh;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
//@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Cubo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "cubo_sequence")
	@SequenceGenerator(allocationSize=1, initialValue=1, name="cubo_sequence", sequenceName="cubo_sequence")
	private Integer id;
	
	private String a_UriService; // uri do serviço Cube
	
	private String a_nome;
	private String a_server;
	
	@OneToOne(fetch=FetchType.LAZY)
	//@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Fato fato;
	
	private String a_driver;
	private String conn_url;
	private String conn_user;
	private String conn_password;

	private long refresh; // Tempo de vida no serviço de índice
	
	@Transient
	private int keyCubeIndex = -1; // key recebida ao se cadastrar no CubeIndex 
	
	@Transient
	private Timer a_timer;
	
	public Cubo(){
			
	}
	
	public Cubo(String nome){
		a_nome = nome;	
	}
	
	public Cubo(String nome,String server,Fato fato,String url,String user,String password, String driver, long refresh){
		a_nome = nome;
		a_server = server;
		this.fato = fato;
		conn_url = url;
		conn_user = user;
		conn_password = password;
		a_driver = driver;
		this.refresh = refresh;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setDriver(String driver){
		a_driver = driver;
	}
	
	public String getDriver(){
		return a_driver;
	}
	
	public void setURIService(String uri){
		a_UriService = uri;	
	}
	public String getURIService(){
		return a_UriService;		
	}	
	
	
	public void setKeyindex(int key){
		keyCubeIndex = key;
	}
	public int getKeyindex(){
		return keyCubeIndex; // mantém o index recebido do CubeIndex, é dinâmico e não armazenado
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
		this.fato = fato;
	}
	public Fato getFato(){
		return fato;
	}
	
	public void setTimer(String serviceURI, long millisecond, int idcube){
		a_timer = new Timer();
		a_timer.schedule(new TaskRefresh(serviceURI, idcube, this), 0, millisecond);
	}
	
	public void cancelTimer(){
		a_timer.cancel();
	}
	
	public void setRefresh(long refresh){
		this.refresh = refresh;
	}
	
	public long getRefresh(){
		return this.refresh;
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
		print = fato.imprimir(p,print);
		return print;
		
	}
	
	@Override
	public String toString(){
		return getNome();
	}
	
	
	
}

