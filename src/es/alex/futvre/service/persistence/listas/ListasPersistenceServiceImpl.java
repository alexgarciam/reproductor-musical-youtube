package es.alex.futvre.service.persistence.listas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.alex.futvre.persistence.Authority;
import es.alex.futvre.persistence.AuthorityDao;
import es.alex.futvre.persistence.Cancion;
import es.alex.futvre.persistence.CancionDao;
import es.alex.futvre.persistence.DaoFactory;
import es.alex.futvre.persistence.Favorito;
import es.alex.futvre.persistence.FavoritosDao;
import es.alex.futvre.persistence.Lista;
import es.alex.futvre.persistence.ListaDao;
import es.alex.futvre.persistence.User;
import es.alex.futvre.persistence.UserDao;
import es.alex.futvre.persistence.Usuario;
import es.alex.futvre.service.persistence.PersistenceException;
import es.alex.futvre.utils.Utils;

public class ListasPersistenceServiceImpl implements IListasPersistenceService {

	private static org.apache.log4j.Logger registro;
	
	@Override
	public List<Lista> getListasUsuario(String usuario) throws PersistenceListasException {
		List<Lista> listas=null;
		registro = Utils.configurarLog(ListasPersistenceServiceImpl.class);		
		ListaDao listaDao=DaoFactory.getListasDao();		
		try{
			listas=listaDao.getUserListas(usuario);
		}catch(Exception e){
			registro.error("No se puedo recoger la lista de listas del usuario: "+e.getMessage());
			throw new PersistenceListasException("No se puedo recoger la lista de listas del usuario: "+e.getMessage());
		}
		return listas;
	}

	@Override
	public boolean createLista(String nombreLista, String usuario) throws PersistenceListasException {
		boolean done=false;
		ListaDao listaDao=DaoFactory.getListasDao();	
		Lista lista=new Lista();
		lista.setNombre(nombreLista);
		lista.setFecha(new Date());
		try{
			lista.setUser(getUserByName(usuario));
			listaDao.persist(lista);
			done=true;
		}catch(Exception e){
			registro.error("No se puedo crear la lista de listas del usuario: "+e.getMessage());
			throw new PersistenceListasException("No se puedo crear la lista de listas del usuario: "+e.getMessage());
		}
		return done;
	}

	@Override
	public boolean borrarLista(int idLista) throws PersistenceListasException {
		boolean done=false;
		ListaDao listaDao=DaoFactory.getListasDao();		
		try{
			Lista lista=listaDao.findById(idLista);
			listaDao.remove(lista, idLista);
			done=true;
		}catch(Exception e){
			registro.error("No se puedo crear la lista de listas del usuario: "+e.getMessage());
			throw new PersistenceListasException("No se puedo crear la lista de listas del usuario: "+e.getMessage());
		}
		return done;
	}

	@Override
	public boolean modificarLista(int idLista, String nuevoNombreLista) throws PersistenceListasException {
		boolean done=false;
		Lista lista=this.getListaUsuario(idLista);
		lista.setNombre(nuevoNombreLista);
		ListaDao listaDao=DaoFactory.getListasDao();	
		try{		
			listaDao.merge(lista);
			done=true;
		}catch(Exception e){
			registro.error("No se pudo actualizar la lista del usuario: "+e.getMessage());
			throw new PersistenceListasException("No se pudo actualizar la lista del usuario: "+e.getMessage());
		}
		return done;
	}

	@Override
	public boolean addCancionALista(int idLista, Cancion cancion)  throws PersistenceListasException{
		boolean done=false;
		CancionDao cancionDao=DaoFactory.getCancionDao();
		Lista lista=new Lista();
		lista.setIdLista(idLista);
		cancion.setLista(lista);
		try{
			cancionDao.persist(cancion);
			done=true;
		}catch(Exception e){
			registro.error("No se pudo agregar la canción: "+e.getMessage());
			throw new PersistenceListasException("No se pudo agregar la canción: "+e.getMessage());
		}
		return done;
	}

	@Override
	public boolean deleteCancionFromLista(int idLista, Cancion cancion) throws PersistenceListasException {
		boolean done=false;
		CancionDao cancionDao=DaoFactory.getCancionDao();		
		try{
			cancionDao.remove(cancion, cancion.getIdCancion());
			done=true;
		}catch(Exception e){
			registro.error("No se pudo eliminar la canción: "+e.getMessage());
			throw new PersistenceListasException("No se pudo eliminar la canción: "+e.getMessage());
		}
		return done;
	}

	@Override
	public List<Cancion> getCancionesFromLista(int idLista) throws PersistenceListasException {
		CancionDao cancionDao= DaoFactory.getCancionDao();
		Lista lista=this.getListaUsuario(idLista);		
		List<Cancion>canciones=null;
		try{		
			canciones=cancionDao.getCancionesFromLista(lista);			
		}catch(Exception e){
			registro.error("No se pudo actualizar la lista del usuario: "+e.getMessage());
			throw new PersistenceListasException("No se pudo actualizar la lista del usuario: "+e.getMessage());
		}
		return canciones;
	}

	@Override
	public Usuario getUserByName(String username) throws PersistenceException {
		UserDao userDao=DaoFactory.getUserDao();	
		try{
			return userDao.findUserByUsername(username);
		}catch(Exception e){
			registro.error("No se puedo recoger al usuario: "+e.getMessage());
			throw new PersistenceException("No se puedo recoger al usuario: "+e.getMessage());
		}
	}

	@Override
	public Lista getListaUsuario(int idLista) throws PersistenceListasException {
		ListaDao listaDao=DaoFactory.getListasDao();	
		Integer Integ=new Integer(idLista);
		Lista lista=listaDao.findById(Integ);
		return lista;
	}

	@Override
	public boolean userHasListaID(String username,int idLista)
			throws PersistenceListasException {
		List<Lista> lista=this.getListasUsuario(username);
		boolean hasLista=false;
		for (Lista lista2 : lista) {
			if(lista2.getIdLista()==idLista){
				return true;
			}
		}
		return hasLista;
	}

	@Override
	public Cancion getCancionById(int idCancion)throws PersistenceListasException {
		CancionDao cancionDaoDao=DaoFactory.getCancionDao();	
		Integer Integ=new Integer(idCancion);
		Cancion cancion=null;
		try{
			cancion=cancionDaoDao.findById(Integ);
		}catch(Exception e){
			registro.error("No se pudo recuperar la canción: "+e.getMessage());
			throw new PersistenceListasException("No se pudo recuperar la canción: "+e.getMessage());
		}
		return cancion;
	}

	@Override
	public List<Favorito> getFavoritos(String username) throws PersistenceException {
		List<Favorito> favoritos=null;
		registro = Utils.configurarLog(ListasPersistenceServiceImpl.class);		
		FavoritosDao dao=DaoFactory.getFavoritosDao();		
		try{
			favoritos=dao.getUserFavoritos(username);
		}catch(Exception e){
			registro.error("No se puedo recoger la lista de listas del usuario: "+e.getMessage());
			throw new PersistenceException("No se puedo recoger la lista de listas del usuario: "+e.getMessage());
		}
		return favoritos;
	}

	@Override
	public int addFavorito(Favorito favorito) throws PersistenceException {
		int done=0;
		FavoritosDao favoritoDao=DaoFactory.getFavoritosDao();
		try{
			favoritoDao.persist(favorito);
			done=1;
		}		
		catch(Exception e){
			registro.error("No se pudo agregar la canción: "+e.getMessage());			
			return 0;
		}
		return done;
	}

	@Override
	public boolean deleteFavorito(int idFavorito) throws PersistenceException {
		boolean done=false;
		FavoritosDao favoritoDao=DaoFactory.getFavoritosDao();
		try{
			Favorito myfavorito=favoritoDao.findById(new Integer(idFavorito));
			favoritoDao.remove(myfavorito, idFavorito);
			done=true;
		}catch(Exception e){
			registro.error("No se pudo eliminar la canción: "+e.getMessage());
			throw new PersistenceException("No se pudo eliminar la canción: "+e.getMessage());
		}
		return done;
	}

	@Override
	public boolean checkFavorito(Favorito favorito) throws PersistenceException {
		boolean done=true;
		FavoritosDao favoritoDao=DaoFactory.getFavoritosDao();
		try{
			List<Favorito>favoritos= favoritoDao.getUserFavoritosByCancionYArtista(favorito.getUsername().getNombre(),favorito.getNombre(), favorito.getArtista());
			if(favoritos.size()==0){
				done=false;
			}
		}		
		catch(Exception e){
			registro.error("No se pudo agregar la canción: "+e.getMessage());			
			return false;
		}
		return done;
	}

	@Override
	public boolean checkFavoritoByID(String username, int idFavorito)
			throws PersistenceException {
		boolean done=false;
		FavoritosDao favoritoDao=DaoFactory.getFavoritosDao();
		try{
			List<Favorito>favoritos=favoritoDao.getUserFavoritosByUsuarioYID(username, idFavorito);
			if(favoritos.size()>0){
				done=true;
			}
		}		
		catch(Exception e){
			registro.error("No se pudo agregar la canción: "+e.getMessage());			
			return false;
		}
		return done;
	}

	@Override
	public Favorito getFavorito(Integer idFavorito) throws PersistenceException {
		Favorito favorito=null;
		registro = Utils.configurarLog(ListasPersistenceServiceImpl.class);		
		FavoritosDao dao=DaoFactory.getFavoritosDao();		
		try{
			favorito=dao.findById(idFavorito);
		}catch(Exception e){
			registro.error("No se puedo recoger la lista de listas del usuario: "+e.getMessage());
			throw new PersistenceException("No se puedo recoger la lista de listas del usuario: "+e.getMessage());
		}
		return favorito;
	}

	@Override
	public Usuario getUserByEmail(String email) throws PersistenceException {
		UserDao userDao=DaoFactory.getUserDao();	
		try{
			return userDao.findUserByEmail(email);
		}catch(Exception e){
			registro.error("No se puedo recoger al usuario: "+e.getMessage());
			throw new PersistenceException("No se puedo recoger al usuario: "+e.getMessage());
		}
	}
	
	
	@Override
	public boolean createUser(String username, String password, String email, Date created) throws PersistenceListasException {
		boolean done=false;
		UserDao userDao=DaoFactory.getUserDao();	
		Usuario user=new Usuario();
		user.setLogin(username);
		user.setNombre(username);
		user.setEmail(email);
		user.setPassword(password);
		user.setActived(false);
		user.setAccountNonLocked(true);
		user.setCreated(created);
		Authority auth=createAuthority("ROLE_ADMIN");
		auth.setUsuario(user);
		
		List<Authority> roles=new ArrayList<Authority>() ;
		roles.add(auth);
		
		user.setAuthorities(roles);
		try{
			//guardamos al usuario
			userDao.persist(user);
			//recuperamos para obtener el id
			Usuario user2=userDao.findUserByUsername(user.getLogin());
			//ponemos el rol al usuario
			auth.setUsuario(user2);
			//guardamos el rol
			createAuthority(auth);
			done=true;
		}catch(Exception e){
			registro.error("No se puedo crear al usuario: "+e.getMessage());
			throw new PersistenceListasException("No se puedo crear la lista de listas del usuario: "+e.getMessage());
		}
		return done;
	}
	
	
	@Override
	public boolean createUserAdmin() throws PersistenceListasException {
		boolean done=false;
		UserDao userDao=DaoFactory.getUserDao();	
		Usuario user=new Usuario();
		user.setLogin("admin");
		user.setNombre("admin");
		user.setEmail("agarcia@isoin.es");
		user.setPassword("4l3j4ndr0");
		user.setActived(true);
		user.setAccountNonLocked(true);
		user.setCreated(new Date());
		Authority auth=createAuthority("ROLE_ADMIN");
		auth.setUsuario(user);
		
		List<Authority> roles=new ArrayList<Authority>() ;
		roles.add(auth);
		
		user.setAuthorities(roles);
		try{
			//guardamos al usuario
			userDao.persist(user);
			//recuperamos para obtener el id
			Usuario user2=userDao.findUserByUsername(user.getLogin());
			//ponemos el rol al usuario
			auth.setUsuario(user2);
			//guardamos el rol
			createAuthority(auth);
			done=true;
		}catch(Exception e){
			registro.error("No se puedo crear al usuario: "+e.getMessage());
			throw new PersistenceListasException("No se puedo crear la lista de listas del usuario: "+e.getMessage());
		}
		return done;
	}
	
	
	public boolean validateUser(String username)throws PersistenceException{
		boolean validated=false;
		UserDao userDao=DaoFactory.getUserDao();	
		Usuario user=userDao.findUserByUsername(username);
		if(!user.isActived()){
			user.setActived(true);
			userDao.merge(user);
			validated=true;
		}
		return validated;
	}
	
	/**
	 * Guarda el rol del usuario en la base de datos
	 * @param auth
	 * @return
	 */
	private boolean createAuthority(Authority auth){
		boolean done=false;
		try{
			AuthorityDao authorityDao=DaoFactory.getAuthoDao();		
			authorityDao.persist(auth);
			done=true;
		}catch(Exception e){
			registro.error("No se puedo crearel rol al usuario: "+e.getMessage());
			return done;
		}
		return done;
	}
	
	/**
	 * Crear un rol con el permiso especificado
	 * @param role
	 * @return
	 */
	private Authority createAuthority(String role ){
		Authority auth=new Authority();
		auth.setAuthority(role);
		return auth;
	}
	
	

}
