package es.alex.futvre.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import es.alex.futvre.DTO.TrackListaReproduccionDTO;
import es.alex.futvre.persistence.Cancion;
import es.alex.futvre.service.lastFM.ILastFMService;
import es.alex.futvre.service.persistence.listas.IListasPersistenceService;
import es.alex.futvre.utils.Utils;

public class addCancionToUserList  extends DispatchAction {
	private static org.apache.log4j.Logger registro;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		registro = Utils.configurarLog(addCancionToUserList.class);		
		ApplicationContext ctx;
		ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(this.getServlet()
						.getServletContext());
		
		String idLista = request.getParameter("idLista");
		String cancion = request.getParameter("cancion");
		String album = request.getParameter("album");
		String artista = request.getParameter("artista");
		String duracion = request.getParameter("duracion");
		String idYoutube = request.getParameter("idYoutube");
		
		IListasPersistenceService listasService=null;
		listasService = (IListasPersistenceService) ctx.getBean("listasPersistenceService");
		
		boolean done=false;
		boolean faltanDatos=false;
		boolean faltaAlbum=false;
		boolean faltaDuracion=false;
		boolean faltaidyoutube=false;
		
		try{
			Cancion song=new Cancion();
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
			if(idYoutube!=null && !idYoutube.equals("")&& !idYoutube.equals("undefined"))
				song.setId_youtube(idYoutube);
			else{
				song.setId_youtube("");
				faltanDatos=true;
				faltaidyoutube=true;
			}
			if(faltanDatos && !artista.equals("youtube video")){
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
			done=listasService.addCancionALista(Integer.parseInt(idLista), song);			 							 
			request.setAttribute("listasUsuario", done);			 		 
			 
		}catch(Exception e){
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			done=false;
			return mapping.findForward("success");
		}		
		
		return mapping.findForward("success");
	}
}
