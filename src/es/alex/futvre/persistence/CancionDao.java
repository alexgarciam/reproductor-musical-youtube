package es.alex.futvre.persistence;

import java.util.ArrayList;
import java.util.List;


public class CancionDao extends GenericDao<Cancion, Integer> {
   
   public CancionDao() {
     super(Cancion.class);
   }
   
   public List<Cancion> getCancionesFromLista (Lista lista){	    
	   String query = "Select c From Cancion c WHERE c.lista.idLista = '"+lista.getIdLista()+"'";
       List<Cancion> canciones = this.find(query);
       if(!(canciones.size()>0)){
    	   canciones=new ArrayList<Cancion>();
       }
       return canciones;
   }

 }