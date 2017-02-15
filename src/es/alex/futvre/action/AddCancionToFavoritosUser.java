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

import es.alex.futvre.DTO.TrackListaReproduccionDTO;
import es.alex.futvre.persistence.Favorito;
import es.alex.futvre.persistence.User;
import es.alex.futvre.persistence.Usuario;
import es.alex.futvre.service.lastFM.ILastFMService;
import es.alex.futvre.service.persistence.listas.IListasPersistenceService;
import es.alex.futvre.utils.Utils;

public class AddCancionToFavoritosUser  extends DispatchAction {
	private static org.apache.log4j.Logger registro;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		registro = Utils.configurarLog(AddCancionToFavoritosUser.class);		
		ApplicationContext ctx;
		ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(this.getServlet()
						.getServletContext());
		
		String cancion = request.getParameter("cancion");
		String album = request.getParameter("album");
		String artista = request.getParameter("artista");
		String duracion = request.getParameter("duracion");
		String idYoutube = request.getParameter("idYoutube");
		
		IListasPersistenceService listasService= (IListasPersistenceService) ctx.getBean("listasPersistenceService");
		
		int done=0;
		boolean faltanDatos=false;
		boolean faltaAlbum=false;
		boolean faltaDuracion=false;
		boolean faltaidyoutube=false;
		
		try{
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    String username = auth.getName(); //get logged in username
		    
			Favorito song=new Favorito();
			
			song.setNombre(cancion);
			song.setArtista(artista);
			if(album!=null && !album.equals("")){
				song.setAlbum(album);
			}else{
				faltanDatos=true;
				faltaAlbum=true;			
			}
			if(duracion!=null && !duracion.equals(""))
				song.setDuracion(new Integer(duracion));			
			else{
				song.setDuracion(0);
				faltanDatos=true;
				faltaDuracion=true;
			}
			if(idYoutube!=null && !idYoutube.equals("")&& !idYoutube.equals("undefined")  )
				song.setId_youtube(idYoutube);
			else{
				song.setId_youtube("");
				faltanDatos=true;
				faltaidyoutube=true;
			}
			if(faltanDatos && !artista.equals("Youtube Artist")){
				ILastFMService lastFMService =null;
				lastFMService = (ILastFMService) ctx.getBean("lastFMService");
				TrackListaReproduccionDTO trackInfo=lastFMService.getTrackInfo(cancion, artista);
				if(faltaAlbum){
					song.setAlbum(trackInfo.getAlbum());
				}
				if(faltaDuracion || song.getDuracion()==0){
					song.setDuracion(new Integer(trackInfo.getDuracion()));
				}
				if(faltaidyoutube){
					//aquí no vamos a poner nada por ahora
				}				
			}
			if(artista.equals("youtube video")){
				song.setArtista("Youtube Artist");
				song.setAlbum("Youtube Album");
			}
			
			Usuario usuario=listasService.getUserByName(username);
			song.setUsername(usuario);			
			
			boolean alreadyFav=listasService.checkFavorito(song);
			if(alreadyFav)
				done=2;
			else
				done=listasService.addFavorito(song);	
			
			request.setAttribute("listaRep", done);
			 
		}catch(Exception e){
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			request.setAttribute("listaRep", done);			
			return mapping.findForward("success");
		}		
		
		return mapping.findForward("success");
	}
}
