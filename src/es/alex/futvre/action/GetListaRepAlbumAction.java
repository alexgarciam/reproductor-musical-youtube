package es.alex.futvre.action;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;

import es.alex.futvre.DTO.LastFMAlbumDTO;
import es.alex.futvre.DTO.LastFMTrackDTO;
import es.alex.futvre.DTO.ListaReproduccionDTO;
import es.alex.futvre.DTO.TrackListaReproduccionDTO;
import es.alex.futvre.service.lastFM.ILastFMService;
import es.alex.futvre.utils.Utils;

public class GetListaRepAlbumAction   extends DispatchAction {
	private static org.apache.log4j.Logger registro;
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		registro = Utils.configurarLog(GetListaRepAlbumAction.class);
		
		String artist = request.getParameter("artista");
		String album = request.getParameter("album");
		String idTrack = request.getParameter("idTrack");
		
		if (artist != null && album != null) {
			ApplicationContext ctx;
			ctx = WebApplicationContextUtils
					.getRequiredWebApplicationContext(this.getServlet()
							.getServletContext());

			ILastFMService lastFMService = null;
			lastFMService = (ILastFMService) ctx.getBean("lastFMService");

			LastFMAlbumDTO myalbum = null;
			try{
				myalbum = lastFMService.getAlbum(artist, album);
			}catch(Exception e){
				registro.error(this.getClass()+"-Error: " + e.getMessage());
			}
			
			request.setAttribute("album", myalbum);
			request.getSession().setAttribute("album", myalbum);								
			
			Iterator<LastFMTrackDTO> tracks=myalbum.getTracks().iterator();
			boolean enc =false;
			Collection<TrackListaReproduccionDTO> lista=new ArrayList<TrackListaReproduccionDTO>();
			
			
			while(tracks.hasNext())
			{
				
				LastFMTrackDTO track=tracks.next();
				TrackListaReproduccionDTO tema=new TrackListaReproduccionDTO();
				tema.setAlbum(URLEncoder.encode(album, "UTF8"));
				tema.setArtista(URLEncoder.encode(artist, "UTF8"));
				Integer durat=new Integer(track.getDuration());
				tema.setDuracion(durat.toString());
				tema.setNombre(track.getName());
				tema.setId(track.getId());
				tema.setIdYoutube("");
				tema.setReproduciendo("false");
				if(!enc && (idTrack==null || idTrack.equals("")) )
				{
					tema.setReproduciendo("true");
					enc=true;
				}
				else{
					if(track.getId().equals(idTrack))
						tema.setReproduciendo("true");
				}
				
				lista.add(tema);
			}						
			
			ListaReproduccionDTO milista=new ListaReproduccionDTO();
			milista.setNombre(artist+" - "+album);
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
