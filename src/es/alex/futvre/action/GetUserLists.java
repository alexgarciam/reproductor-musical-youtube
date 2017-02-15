package es.alex.futvre.action;

import java.util.ArrayList;
import java.util.List;

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

import com.google.gson.Gson;

import es.alex.futvre.DTO.ListasUsuarioDto;
import es.alex.futvre.persistence.Lista;
import es.alex.futvre.service.persistence.listas.IListasPersistenceService;
import es.alex.futvre.utils.Utils;

public class GetUserLists  extends DispatchAction {
	private static org.apache.log4j.Logger registro;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		registro = Utils.configurarLog(GetUserLists.class);		
		
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String name = auth.getName(); //get logged in username
		
		ApplicationContext ctx;
		ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(this.getServlet()
						.getServletContext());
		
		IListasPersistenceService listasService=null;
		listasService = (IListasPersistenceService) ctx.getBean("listasPersistenceService");
		List<Lista> listas=null;
		 List<ListasUsuarioDto> listasUsuario=new ArrayList<ListasUsuarioDto>();
		try{
			 listas=listasService.getListasUsuario(name);
			
			 for (Lista lista : listas) {
				 ListasUsuarioDto listasUsuarioDto=new ListasUsuarioDto();
				 listasUsuarioDto.setIdLista(lista.getIdLista());
				 listasUsuarioDto.setNombreLista(lista.getNombre());
				 listasUsuario.add(listasUsuarioDto);
			}			
			 
		}catch(Exception e){
			registro.error(this.getClass()+"-Error: " + e.getMessage());
		}
		
		String jsonOutput = null;
		Gson gson = new Gson();
		jsonOutput = gson.toJson(listasUsuario);
		
		request.setAttribute("listasUsuario", jsonOutput);
		
		return mapping.findForward("success");
	}
}
