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

import es.alex.futvre.DTO.CancionesDeListasUsuarioDto;
import es.alex.futvre.persistence.Cancion;
import es.alex.futvre.persistence.Favorito;
import es.alex.futvre.service.persistence.listas.IListasPersistenceService;
import es.alex.futvre.utils.Utils;

public class GetCancionListaAction  extends DispatchAction {
	private static org.apache.log4j.Logger registro;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String idCancion = request.getParameter("idCancion");
		registro = Utils.configurarLog(GetCancionListaAction.class);		
		ApplicationContext ctx;
		ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(this.getServlet()
						.getServletContext());

		IListasPersistenceService listasService=null;
		listasService = (IListasPersistenceService) ctx.getBean("listasPersistenceService");
			
		try{
			CancionesDeListasUsuarioDto song=new CancionesDeListasUsuarioDto();			
			Cancion fav=listasService.getCancionById(Integer.parseInt(idCancion));	
			song.setIdCancion(fav.getIdCancion());
			song.setAlbum(fav.getAlbum());
			song.setArtista(fav.getArtista());
			song.setDuracion(fav.getDuracion());
			song.setLista(fav.getLista().getIdLista());
			song.setNombre(fav.getNombre());
			song.setId_youtube(fav.getId_youtube());
			
			Gson gson = new Gson();			
			String jsonOutput = gson.toJson(song);			
			
			request.setAttribute("listaRep", jsonOutput);			 		 
			 
		}catch(Exception e){
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			return mapping.findForward("success");
		}		
		
		return mapping.findForward("success");
	}
}
