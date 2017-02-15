package es.alex.futvre.persistence;

import java.util.List;


public class FavoritosDao extends GenericDao<Favorito, Integer> {
   
   public FavoritosDao() {
     super(Favorito.class);
   }
   
   public List<Favorito>getUserFavoritos (String username){	    
	   String query = "Select f From Favorito f WHERE f.username.username = '"+username+"'";
       List<Favorito> userListas  = this.find(query);       
       return userListas;
   }

   public List<Favorito>getUserFavoritosByCancionYArtista (String username, String cancion, String artista){	    
	   String query = "Select f From Favorito f WHERE f.username.username = '"+username+"' and f.nombre='"+cancion+"' and f.artista='"+artista+"'";
       List<Favorito> userListas  = this.find(query);       
       return userListas;
   }
   
   public List<Favorito>getUserFavoritosByUsuarioYID (String username, int idFavorito){	    
	   String query = "Select f From Favorito f WHERE f.username.username = '"+username+"' and f.idFavorito="+idFavorito;
       List<Favorito> userListas  = this.find(query);       
       return userListas;
   }

   
 }