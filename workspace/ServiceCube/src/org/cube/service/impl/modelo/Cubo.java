package org.cube.service.impl.modelo;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Timer;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import org.cube.service.impl.control.tarefas.TaskRefresh;

@Entity
//@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Cubo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "cubo_sequence")
	@SequenceGenerator(allocationSize=1, initialValue=1, name="cubo_sequence", sequenceName="cubo_sequence")
	private Integer id;
	
	private String a_UriService; // uri do servi�o Cube
	
	private String a_nome;
	private String a_server;
	
	@OneToOne(fetch=FetchType.LAZY, orphanRemoval=true, cascade={CascadeType.REMOVE})
	private Fato fato;
	
	private String a_driver;
	private String connectionUrl;
	private String connectionUser;
	private String connectionPassword;

	private Long refresh; // Tempo de vida no servi�o de �ndice
	
	//TODO - Verificar se keyCubeIndex deve ser persistido 
	//@Transient
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
		connectionUrl = url;
		connectionUser = user;
		connectionPassword = password;
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
		this.fato = fato;
	}
	public Fato getFato(){
		return fato;
	}
	
	//TODO refatorar esta funcionalidade para uma classe gerenciadora de refresh baseada no id do cubo.
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
	
	public Long getRefresh(){
		return this.refresh;
	}
	
	public void setConnection(String url, String user, String password){
		try {
			
			this.connectionUrl = url;
			this.connectionUser = user;
			this.connectionPassword = password;
			
		}catch (Exception e){ e.printStackTrace();}
	}
	
	
	public Connection getConnection(){
		try {
			Connection conn;
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
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

	/**
	 * @return the conn_url
	 */
	public String getConnectionUrl() {
		return connectionUrl;
	}

	/**
	 * @param conn_url the conn_url to set
	 */
	public void setConnectionUrl(String conn_url) {
		this.connectionUrl = conn_url;
	}

	/**
	 * @return the conn_user
	 */
	public String getConnectionUser() {
		return connectionUser;
	}

	/**
	 * @param conn_user the conn_user to set
	 */
	public void setConnectionUser(String conn_user) {
		this.connectionUser = conn_user;
	}

	/**
	 * @return the conn_password
	 */
	public String getConnectionPassword() {
		return connectionPassword;
	}

	/**
	 * @param conn_password the conn_password to set
	 */
	public void setConnectionPassword(String conn_password) {
		this.connectionPassword = conn_password;
	}
	
	
	
}

