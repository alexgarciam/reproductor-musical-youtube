package es.alex.futvre.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;

import es.alex.futvre.persistence.Usuario;
import es.alex.futvre.service.persistence.listas.IListasPersistenceService;
import es.alex.futvre.utils.Utils;

public class CheckUserEmailAction  extends DispatchAction {
	private static org.apache.log4j.Logger registro;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		registro = Utils.configurarLog(CheckUserEmailAction.class);		
			
		ApplicationContext ctx;
		ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(this.getServlet()
						.getServletContext());
		
		String email = request.getParameter("userEmailForm");		
		
		IListasPersistenceService listasService=null;
		listasService = (IListasPersistenceService) ctx.getBean("listasPersistenceService");
		//List<Lista> listas=null;
		//List<ListasUsuarioDto> listasUsuario=new ArrayList<ListasUsuarioDto>();
		try{
			 Usuario u=listasService.getUserByEmail(email);
			 if(u!=null){		 
				 Gson gson = new Gson();
				 String jsonOutput = gson.toJson(false);			 
				 request.setAttribute("listaRep", jsonOutput);
			 }
			 else{
				 Gson gson = new Gson();
				 String jsonOutput = gson.toJson(true);			 
				 request.setAttribute("listaRep", jsonOutput);
			 }			 
		}catch(Exception e){
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			request.setAttribute("listaRep", 0);	 		 
			return mapping.findForward("success");
		}				
		return mapping.findForward("success");
	}
}
