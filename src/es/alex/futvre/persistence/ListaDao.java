package es.alex.futvre.persistence;

import java.util.List;


public class ListaDao extends GenericDao<Lista, Integer> {
   
   public ListaDao() {
     super(Lista.class);
   }
   
   public List<Lista>getUserListas (String username){	    
	   String query = "Select l From Lista l WHERE l.user.username = '"+username+"'";
       List<Lista> userListas  = this.find(query);       
       return userListas;
   }
   
   public List<Lista>getListasUsuario(String username){	    
	   String query = "Select l From Lista l WHERE l.user.username = '"+username+"'";
       List<Lista> userListas  = this.find(query);       
       return userListas;
   }

 }