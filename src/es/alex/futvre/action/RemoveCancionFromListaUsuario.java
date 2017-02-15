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

import es.alex.futvre.persistence.Cancion;
import es.alex.futvre.service.persistence.listas.IListasPersistenceService;
import es.alex.futvre.utils.Utils;

public class RemoveCancionFromListaUsuario  extends DispatchAction {
	private static org.apache.log4j.Logger registro;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		registro = Utils.configurarLog(RemoveCancionFromListaUsuario.class);		
		
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String name = auth.getName(); //get logged in username
		
		ApplicationContext ctx;
		ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(this.getServlet()
						.getServletContext());
		
		String listid = request.getParameter("idLista");
		String cancionid = request.getParameter("idCancion");
		int idLista=Integer.parseInt(listid);
		int idCancion=Integer.parseInt(cancionid);
		IListasPersistenceService listasService=null;
		listasService = (IListasPersistenceService) ctx.getBean("listasPersistenceService");
		try{
			 boolean done=listasService.userHasListaID(name,idLista);
			 
			 if(done){
				 Cancion c=listasService.getCancionById(idCancion);
				 done=listasService.deleteCancionFromLista(idLista, c);
			 }
			 request.setAttribute("listasUsuario", done);				 
		}catch(Exception e){
			registro.error(this.getClass()+"-Error: " + e.getMessage());
		}		
		
		return mapping.findForward("success");
	}
}
