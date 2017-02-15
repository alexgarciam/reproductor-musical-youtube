package es.alex.futvre.tests;

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
import es.alex.futvre.persistence.UserDao;
import es.alex.futvre.persistence.Usuario;
import es.alex.futvre.service.persistence.listas.ListasPersistenceServiceImpl;
import es.alex.futvre.service.persistence.listas.PersistenceListasException;

public class TestJPA {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub			 
		/*			
		UserDao user=DaoFactory.getUserDao();
		
		Usuario myuser=new Usuario();
		myuser.setLogin("admin");
		myuser.setNombre("admin");
		myuser.setPassword("4l3j4ndr0");
		myuser.setEmail("agarcia@isoin.es");
		myuser.setAccountNonLocked(true);
		myuser.setActived(true);
		myuser.setCreated(new Date());
		
		AuthorityDao authorityDao=DaoFactory.getAuthoDao();
		Authority auth=new Authority();
		auth.setAuthority("ROLE_ADMIN");
		auth.setUsuario(myuser);
		authorityDao.persist(auth);
		
		List<Authority> autorithies=new ArrayList<Authority>();
		autorithies.add(auth);
		myuser.setAuthorities(autorithies);
		
		user.persist(myuser);	
		Usuario admin=user.findUserByUsername("admin");
		System.out.println("usuario: "+admin.getLogin());
		*/
		
		ListasPersistenceServiceImpl impl=new ListasPersistenceServiceImpl();
		try {
			impl.createUser("admin", "password", "email",new Date());
		} catch (PersistenceListasException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
