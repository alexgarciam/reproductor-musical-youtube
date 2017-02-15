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

public class addUserList  extends DispatchAction {
	private static org.apache.log4j.Logger registro;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		registro = Utils.configurarLog(addUserList.class);		
		
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String name = auth.getName(); //get logged in username
		
		ApplicationContext ctx;
		ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(this.getServlet()
						.getServletContext());
		
		String listName = request.getParameter("listName");
		
		IListasPersistenceService listasService=null;
		listasService = (IListasPersistenceService) ctx.getBean("listasPersistenceService");
		//List<Lista> listas=null;
		//List<ListasUsuarioDto> listasUsuario=new ArrayList<ListasUsuarioDto>();
		 boolean done=false;
		try{
			 done=listasService.createLista(listName, name);
			 if(done)			 
				 request.setAttribute("listasUsuario", 1);	
			 else
				 request.setAttribute("listasUsuario", 0);
			 
		}catch(Exception e){
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			request.setAttribute("listasUsuario", 0);	 		 
			return mapping.findForward("success");
		}		
		
		return mapping.findForward("success");
	}
}
