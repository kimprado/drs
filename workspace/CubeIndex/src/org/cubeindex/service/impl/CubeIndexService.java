package org.cubeindex.service.impl;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import org.globus.index.stubs.Cube.CubeEntry;
import org.globus.index.stubs.Cube.CubeEntryResponse;
import org.globus.index.stubs.Cube.CubeListResponse;
import org.globus.index.stubs.Cube.GetCubeList;
import org.globus.wsrf.Resource;
import org.globus.wsrf.ResourceProperties;
import org.globus.wsrf.ResourceProperty;
import org.globus.wsrf.ResourcePropertySet;
import org.globus.wsrf.impl.ReflectionResourceProperty;
import org.globus.wsrf.impl.SimpleResourcePropertySet;


public class CubeIndexService implements Resource, ResourceProperties{

	private ConcurrentHashMap<Integer, CubeIndexEntry> cubeIndex = new ConcurrentHashMap<Integer, CubeIndexEntry>();
	private int idCount = 0;
	
	
	
	private String cubeURI;
	
	/* Resource Property set */
	private ResourcePropertySet propSet;
	
	
	public CubeIndexService(){
		
		
		/* Create RP set */
		this.propSet = new SimpleResourcePropertySet(CubeIndexQNames.RESOURCE_PROPERTIES);
		
		
		/* Initialize the RP's */
		try {
			ResourceProperty cubeRP = new ReflectionResourceProperty(
				CubeIndexQNames.RP_CubeURI, "cubeURI", this);
			this.propSet.add(cubeRP);
		} catch (Exception e) {	throw new RuntimeException(e.getMessage());
		}
	
	}
	
	
	
	public String getCubeURI() {
		return cubeURI;
	}

	public void setCubeURI(String cube) {
		cubeURI = cube;
	}
	
	
	
	
	public CubeListResponse getCubeList(GetCubeList cubeList) throws RemoteException{
		
		if (cubeIndex.size() > 0) {
			CubeEntry[] cubes_all = new CubeEntry[cubeIndex.size()];
			int entry = 0;
			for (int i=1; i < idCount + 1; i++){

				if (cubeIndex.containsKey(new Integer(i)) ){
					CubeIndexEntry cbIndexEntry = cubeIndex.get(new Integer(i));
					Date time = cbIndexEntry.getTime().getTime();
					if ( time.compareTo(new Date()) > 0 ){
						CubeEntry cb = new CubeEntry(i,cbIndexEntry.getIndex(),cbIndexEntry.getName(),cbIndexEntry.getTime(), cbIndexEntry.getURI());
						cubes_all[entry] = cb;
						entry++;
					}
				}
			}
			
			CubeEntry[] cubes = new CubeEntry[entry];
			for (int i=0; i < entry; i++){
				cubes[i] = cubes_all[i];
			}
			
			if ( entry > 0){
				return new CubeListResponse(cubes);
			}
			else {
				return new CubeListResponse(null);
			}
		}
		else {
			return new CubeListResponse(null);
		}
	}
	
	public CubeEntryResponse getCubeEntry(int entry) throws RemoteException{
		
		if (cubeIndex.containsKey(new Integer( entry )) ){
			Date time = (cubeIndex.get(new Integer( entry ))).getTime().getTime();
			if ( time.compareTo(new Date()) > 0 ){
				CubeIndexEntry cubeIndexEntry = cubeIndex.get( new Integer( entry ) );
				CubeEntry cubeEntry = new CubeEntry(entry,cubeIndexEntry.getIndex(), cubeIndexEntry.getName(), cubeIndexEntry.getTime(), cubeIndexEntry.getURI());
				return new CubeEntryResponse(cubeEntry);
			}
			else {
				return new CubeEntryResponse(null);
			}
		}
		else{
			return new CubeEntryResponse(null);
		}
	}
	
	public int addCubeEntry(CubeEntry entry){
		
		Calendar calend = Calendar.getInstance(); // similar ao processo de Refresh
		calend.setTime(new Date());
		calend.add(Calendar.MINUTE, 5); // cinco minutos de tempo de vida o refresh deve ocorrer antes de("Calendar.MINUTE, 5") 5 min.
		
		idCount++;
		CubeIndexEntry cubeIndexEntry = new CubeIndexEntry(entry.getName(),entry.getUri(),entry.getIndex(), calend);
		cubeIndex.put(new Integer(idCount), cubeIndexEntry);
		return idCount;
	}
	
	
	public boolean removeEntry(int entry){
		
		if (cubeIndex.containsKey(new Integer( entry )) ){
			cubeIndex.remove(new Integer(entry));
			return true;
		}
		else {
			return false;
		}
	}
	
	
	public boolean refreshCube(int entry){
		
		if (cubeIndex.containsKey(new Integer(entry))){
			Calendar calend = Calendar.getInstance(); 
			calend.setTime(new Date());
			calend.add(Calendar.MINUTE, 5); // adiciona o tempo de vida em 5 minutos da hora atual
			
			CubeIndexEntry cubeIndexEntry = cubeIndex.get( new Integer( entry ) );
			cubeIndexEntry.setTime(calend); //seta o novo tempo de vida
			
			return true;
		}
		else {
			return false;
		}
		
	}
	
	
	
	
	/* Required by interface ResourceProperties */
	public ResourcePropertySet getResourcePropertySet() {
		return this.propSet;
	}
	
	
}
