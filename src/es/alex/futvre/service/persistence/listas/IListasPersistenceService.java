package es.alex.futvre.service.persistence.listas;

import java.util.Date;
import java.util.List;

import es.alex.futvre.persistence.Cancion;
import es.alex.futvre.persistence.Favorito;
import es.alex.futvre.persistence.Lista;
import es.alex.futvre.persistence.User;
import es.alex.futvre.persistence.Usuario;
import es.alex.futvre.service.persistence.PersistenceException;

public interface IListasPersistenceService {

	public List<Lista> getListasUsuario(String usuario) throws PersistenceListasException;
	
	public Lista getListaUsuario(int idLista) throws PersistenceListasException;
	
	public boolean createLista(String nombreLista, String usuario) throws PersistenceListasException;
	
	public boolean borrarLista(int idLista) throws PersistenceListasException;
	
	public boolean modificarLista(int idLista, String nuevoNombreLista) throws PersistenceListasException;
	
	public Cancion getCancionById(int idCancion) throws PersistenceListasException;
	
	public boolean addCancionALista(int idLista, Cancion cancion) throws PersistenceListasException;
	
	public boolean deleteCancionFromLista(int idLista, Cancion cancion) throws PersistenceListasException;
	
	public List<Cancion> getCancionesFromLista(int idLista) throws PersistenceListasException;
	
	public boolean createUser(String username, String password, String email, Date created) throws PersistenceListasException;
	
	public boolean createUserAdmin() throws PersistenceListasException;
	
	public boolean validateUser(String username)throws PersistenceException;
	
	public Usuario getUserByName(String username) throws PersistenceException;
	
	public Usuario getUserByEmail(String email) throws PersistenceException;
	
	public boolean userHasListaID(String username,int idLista)throws PersistenceListasException;
	
	public List<Favorito> getFavoritos(String username)throws PersistenceException;
	
	public Favorito getFavorito(Integer idFavorito)throws PersistenceException;
	
	public int addFavorito(Favorito favorito) throws PersistenceException;
	
	public boolean checkFavorito(Favorito favorito)  throws PersistenceException;
	
	public boolean checkFavoritoByID(String username, int idFavorito)  throws PersistenceException;
	
	public boolean deleteFavorito(int idFavorito)throws PersistenceException;
	
	
	
}
