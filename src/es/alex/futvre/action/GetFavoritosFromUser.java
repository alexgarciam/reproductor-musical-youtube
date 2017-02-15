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

import es.alex.futvre.DTO.CancionesDeListasUsuarioDto;
import es.alex.futvre.DTO.FavoritosUsuarioDTO;
import es.alex.futvre.persistence.Favorito;
import es.alex.futvre.service.lastFM.ILastFMService;
import es.alex.futvre.service.persistence.listas.IListasPersistenceService;
import es.alex.futvre.utils.Utils;

public class GetFavoritosFromUser  extends DispatchAction {
	private static org.apache.log4j.Logger registro;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		registro = Utils.configurarLog(GetFavoritosFromUser.class);		
		ApplicationContext ctx;
		ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(this.getServlet()
						.getServletContext());
		 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName(); //get logged in username
			
	    FavoritosUsuarioDTO dto=new FavoritosUsuarioDTO();
			
		IListasPersistenceService listasService=null;
		listasService = (IListasPersistenceService) ctx.getBean("listasPersistenceService");
		
		boolean done=false;
		List<Favorito> favoritos=new ArrayList<Favorito>();
		List<String> artistas=new ArrayList<String>();
		List<String> imagenesArtistas=new ArrayList<String>();
		
		int duracionTotalLista=0;
		int numeroCanciones=0;
		try{		
						
			favoritos=listasService.getFavoritos(username);			 							 
			request.setAttribute("listasUsuario", done);	
			
			
			List<CancionesDeListasUsuarioDto> misCanciones=new ArrayList<CancionesDeListasUsuarioDto>();
			for (Favorito favorito: favoritos) {
				CancionesDeListasUsuarioDto cancionesDeListasUsuarioDto=new CancionesDeListasUsuarioDto();
				cancionesDeListasUsuarioDto.setNombre(favorito.getNombre());
				cancionesDeListasUsuarioDto.setAlbum(favorito.getAlbum());
				cancionesDeListasUsuarioDto.setArtista(favorito.getArtista());
				cancionesDeListasUsuarioDto.setId_youtube(favorito.getId_youtube());
				cancionesDeListasUsuarioDto.setDuracion(favorito.getDuracion());
				cancionesDeListasUsuarioDto.setIdCancion(favorito.getIdFavorito());
				misCanciones.add(cancionesDeListasUsuarioDto);
				duracionTotalLista+=favorito.getDuracion();
				if(!artistas.contains(favorito.getArtista())){
					artistas.add(favorito.getArtista());
				}
				numeroCanciones++;
			}		
			
			//Ahora cogemos las imagenes de los artistas
			ILastFMService lastFMService = null;
			lastFMService = (ILastFMService) ctx.getBean("lastFMService");
			if(artistas.size()<4){
				for (String artista : artistas) {
					imagenesArtistas.add(lastFMService.getArtistImageSmall(artista));
				}
			}
			else{
				for (String artista : artistas) {
					imagenesArtistas.add(lastFMService.getArtistImage(artista));
				}
			}
			
			//guardamos
			dto.setCanciones(misCanciones);
			dto.setImgArtistas(imagenesArtistas);
			dto.setDuracion(duracionTotalLista);
			dto.setNumeroCanciones(numeroCanciones);
			
			Gson gson = new Gson();			
			String jsonOutput = null;
			try{
				jsonOutput = gson.toJson(dto);
			}catch(Exception e){
				registro.error(this.getClass()+"-Error: " + e.getMessage());
			}
			
			System.out.println("json: "+jsonOutput);			
			request.setAttribute("listaRepUser", jsonOutput);
			 
		}catch(Exception e){
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			done=false;
			
			return mapping.findForward("success");
		}		
		
		return mapping.findForward("success");
	}
}
