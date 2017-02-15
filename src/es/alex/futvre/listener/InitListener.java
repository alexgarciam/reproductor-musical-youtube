package es.alex.futvre.listener;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

import es.alex.futvre.persistence.Usuario;
import es.alex.futvre.service.persistence.PersistenceException;
import es.alex.futvre.service.persistence.listas.ListasPersistenceServiceImpl;
import es.alex.futvre.service.persistence.listas.PersistenceListasException;
import es.alex.futvre.utils.Utils;

/**
 * Servlet implementation class InitListener
 */
public class InitListener extends ContextLoaderListener {

	private static org.apache.log4j.Logger registro;

	/*
	 * (non-Javadoc)
	 * 
	 * @seejavax.servlet.ServletContextListener#contextDestroyed(javax.servlet.
	 * ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent event) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.ServletContextListener#contextInitialized(javax.servlet
	 * .ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event) {
		registro = Utils.configurarLog(InitListener.class);
		registro.info("Inicio de la aplicación");
		
		ListasPersistenceServiceImpl impl=new ListasPersistenceServiceImpl();
			
		boolean done;
		try {
			
			Usuario admin=impl.getUserByName("admin");
			if(admin==null)
			{
				done = impl.createUserAdmin();
				if(done){
					registro.info("Usuario admin creado");
				}
				else{
					registro.info("Usuario admin no pudo ser creado");
				}
			}
			else{
				registro.info("El usuario admin ya existía");
			}
		} catch (PersistenceListasException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
