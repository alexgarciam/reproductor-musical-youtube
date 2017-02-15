package es.alex.futvre.persistence;

import java.util.List;

import javax.persistence.EntityManager;


//public class UserDao extends GenericDao<User, Integer> {
public class UserDao extends GenericDao<Usuario, Integer> {
 
 private EntityManager em;
 
 /*
   public UserDao() {
     super(User.class);
   }
   
   public User findUserByUsername (String username){
    
	   User usuario = null;
       String query = "Select u From User u WHERE u.username = '"+username+"'";
       List<User> usuarios = this.find(query); 
       if(usuarios.size()>0){
    	   usuario=usuarios.get(0);
       }
       return usuario;
   }
   */
 
 	public UserDao() {
     super(Usuario.class);
   }
   
   public Usuario findUserByUsername (String username){
    
	   Usuario usuario = null;
       String query = "Select u From Usuario u WHERE u.username = '"+username+"'";
       List<Usuario> usuarios = this.find(query); 
       if(usuarios.size()>0){
    	   usuario=usuarios.get(0);
       }
       return usuario;
   }
   
   public Usuario findUserByEmail (String email){
	    
	   Usuario usuario = null;
       String query = "Select u From Usuario u WHERE u.email = '"+email+"'";
       List<Usuario> usuarios = this.find(query); 
       if(usuarios.size()>0){
    	   usuario=usuarios.get(0);
       }
       return usuario;
   }
   

 }