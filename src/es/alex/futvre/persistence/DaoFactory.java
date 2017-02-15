package es.alex.futvre.persistence;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DaoFactory {

	private static ApplicationContext applicationContext;

	private static UserDao userDao;
	private static AuthorityDao authoDao;
	private static ListaDao listaDao;
	private static CancionDao cancionDao;
	private static FavoritosDao favoritoDao;

	static {
		applicationContext = new ClassPathXmlApplicationContext("beans-jpa.xml");
		userDao = (UserDao) applicationContext.getBean("userDao");
		authoDao = (AuthorityDao) applicationContext.getBean("authorityDao");
		listaDao = (ListaDao) applicationContext.getBean("listaDao");
		cancionDao = (CancionDao) applicationContext.getBean("cancionDao");
		favoritoDao= (FavoritosDao) applicationContext.getBean("favoritoDao");
	}

	private DaoFactory() {

	}

	public static UserDao getUserDao() {
		return userDao;
	}

	public static AuthorityDao getAuthoDao() {
		return authoDao;
	}

	public static ListaDao getListasDao() {
		return listaDao;
	}

	public static CancionDao getCancionDao() {
		return cancionDao;
	}

	public static FavoritosDao getFavoritosDao() {
		return favoritoDao;
	}

}
