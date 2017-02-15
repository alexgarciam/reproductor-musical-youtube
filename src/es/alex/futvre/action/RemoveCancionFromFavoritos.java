package es.alex.futvre.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.context.ApplicationContext;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.web.context.support.WebApplicationContextUtils;

import es.alex.futvre.service.persistence.listas.IListasPersistenceService;
import es.alex.futvre.utils.Utils;

public class RemoveCancionFromFavoritos  extends DispatchAction {
	private static org.apache.log4j.Logger registro;
		
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		registro = Utils.configurarLog(RemoveCancionFromFavoritos.class);		
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName(); //get logged in username
		
		ApplicationContext ctx;
		ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(this.getServlet()
						.getServletContext());
		
	
		String cancionid = request.getParameter("idCancion");
		int idCancion=Integer.parseInt(cancionid);
		IListasPersistenceService listasService=null;
		listasService = (IListasPersistenceService) ctx.getBean("listasPersistenceService");
		try{
			 boolean done=listasService.checkFavoritoByID(name, idCancion);
			 
			 if(done){
				 done=listasService.deleteFavorito(idCancion);
				 if(done)
					 request.setAttribute("listaRep", 1);	
				 else
					 request.setAttribute("listaRep", 0);		
			 }
		}catch(Exception e){
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			request.setAttribute("listaRep", 0);		
		}		
		
		return mapping.findForward("success");
	}
}
