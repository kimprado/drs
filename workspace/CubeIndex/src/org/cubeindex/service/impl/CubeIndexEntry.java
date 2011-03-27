package org.cubeindex.service.impl;
import java.util.Calendar;

public class CubeIndexEntry {
	
	private String a_name;
	private String a_uri;
	private int a_index;
	private Calendar a_time; // define o tempo de vida da entrada
	
	public CubeIndexEntry(){
		
	}
	
	
	public CubeIndexEntry(String nome, String URL, int index, Calendar time){
		a_name = nome;
		a_uri = URL;
		a_index = index;
		a_time = time;
	}
	
	
	public String getName(){
		return a_name;
	}
	public void setName(String nome){
		a_name = nome;
	}
	
	public String getURI(){
		return a_uri;
	}
	public void setURI(String url){
		a_uri = url;
	}
	
	public int getIndex(){
		return a_index;
	}
	public void setIndex(int index){
		a_index = index;
	}
	
	public Calendar getTime(){
		return a_time;
	}
	public void setTime(Calendar time){
		a_time = time;
	}
}
