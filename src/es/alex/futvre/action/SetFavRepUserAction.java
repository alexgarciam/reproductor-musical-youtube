package es.alex.futvre.action;

import java.util.ArrayList;
import java.util.Collection;
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

import es.alex.futvre.DTO.ListaReproduccionDTO;
import es.alex.futvre.DTO.TrackListaReproduccionDTO;
import es.alex.futvre.persistence.Cancion;
import es.alex.futvre.persistence.Favorito;
import es.alex.futvre.persistence.Lista;
import es.alex.futvre.service.persistence.listas.IListasPersistenceService;
import es.alex.futvre.utils.Utils;

public class SetFavRepUserAction extends DispatchAction {
	private static org.apache.log4j.Logger registro;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		registro = Utils.configurarLog(SetFavRepUserAction.class);
		String idCancion = request.getParameter("idCancion");
		
		if (idCancion != null) {
			ApplicationContext ctx;
			ctx = WebApplicationContextUtils
					.getRequiredWebApplicationContext(this.getServlet()
							.getServletContext());

			IListasPersistenceService listasService=null;
			listasService = (IListasPersistenceService) ctx.getBean("listasPersistenceService");			

			List<Favorito> favoritos=null;
			List<Cancion> tracks=null;
			Lista listaUsuario = null;
			try{			
				
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			    String username = auth.getName(); //get logged in username
				
			    favoritos=listasService.getFavoritos(username);
			    
			}catch(Exception e){
				registro.error(this.getClass()+"-Error: " + e.getMessage());
			}								
						
			boolean enc =false;
			Collection<TrackListaReproduccionDTO> lista=new ArrayList<TrackListaReproduccionDTO>();
			for (Favorito cancion : favoritos) {
				TrackListaReproduccionDTO tema=new TrackListaReproduccionDTO();
				tema.setAlbum(cancion.getAlbum());				
				tema.setArtista(cancion.getArtista());				
				tema.setIdYoutube(cancion.getId_youtube());
				Integer durat=new Integer(cancion.getDuracion());
				tema.setDuracion(durat.toString());
				tema.setNombre(cancion.getNombre());
				Integer cid=new Integer(cancion.getIdFavorito());
				tema.setId(cid.toString());
				tema.setReproduciendo("false");
				int idCancionInt=Integer.parseInt(idCancion);
				if(cancion.getIdFavorito()==idCancionInt )
				{
					tema.setReproduciendo("true");
					enc=true;
				}
				else{
					tema.setReproduciendo("dalse");
				}
				
				lista.add(tema);
			}								
			
			ListaReproduccionDTO milista=new ListaReproduccionDTO();
			milista.setNombre("Favoritos");
			milista.setTemas(lista);
			
			Gson gson = new Gson();			
			String jsonOutput = null;
			try{
				jsonOutput = gson.toJson(milista);
			}catch(Exception e){
				registro.error(this.getClass()+"-Error: " + e.getMessage());
			}			
			
			System.out.println("json: "+jsonOutput);
			
			request.setAttribute("listaRep", jsonOutput);
			request.getSession().setAttribute("listaRep", jsonOutput);
			request.getSession().setAttribute("listaRepDTO", milista);		
			
		}

		return mapping.findForward("success");
		
	}
}