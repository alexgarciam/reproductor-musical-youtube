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
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;

import es.alex.futvre.DTO.LastFMAlbumDTO;
import es.alex.futvre.DTO.LastFMArtistDTO;
import es.alex.futvre.DTO.LastFMTrackDTO;
import es.alex.futvre.DTO.YoutubeVideoDTO;
import es.alex.futvre.service.lastFM.ILastFMService;
import es.alex.futvre.service.youtube.IYoutubeService;
import es.alex.futvre.utils.Utils;

public class SearchTrackAction extends DispatchAction {
	private static org.apache.log4j.Logger registro;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		registro = Utils.configurarLog(SearchTrackAction.class);
		
		String q = request.getParameter("q");
		
		String tamanyoPantalla = request.getParameter("tamPantalla");
		request.setAttribute("tamanyoPantalla", tamanyoPantalla);
		request.getSession().setAttribute("tamanyoPantalla", tamanyoPantalla);
		
		List<LastFMTrackDTO> temas = null;
		List<LastFMArtistDTO> artistas = null;
		List<LastFMAlbumDTO> albums = null;	
		List<YoutubeVideoDTO> videosYoutube=null;
		String artistasJSON ="";
		String albumsJSON ="";
		String youtubeJSON ="";
		
		if (q != null) {
			ApplicationContext ctx;
			ctx = WebApplicationContextUtils
					.getRequiredWebApplicationContext(this.getServlet()
							.getServletContext());

			ILastFMService lastFMService = null;
			lastFMService = (ILastFMService) ctx.getBean("lastFMService");

			IYoutubeService youtubeService =null;
			youtubeService = (IYoutubeService) ctx.getBean("youtubeService");
			
			Gson gson = new Gson();	
			
			try {
				//busqueda de canciones
				temas = lastFMService.searchTrack(q);
				
				//busqueda de artistas
				artistas=lastFMService.searchArtist(q);						
				artistasJSON = gson.toJson(artistas);
				
				//busqueda de albums
				albums=lastFMService.searchAlbum(q);
				albumsJSON = gson.toJson(albums);
				
				//busqueda videos de youtube
				videosYoutube = youtubeService.searchYoutubeVideo(q);	
				youtubeJSON = gson.toJson(videosYoutube);
				
			} catch (Exception e) {
				registro.error(this.getClass()+"-Error: " + e.getMessage());
				temas=new ArrayList<LastFMTrackDTO>();
		}
			
			request.setAttribute("artistasJSON", artistasJSON);
			request.setAttribute("albumsJSON", albumsJSON);
			request.setAttribute("youtubeJSON", youtubeJSON);

			request.setAttribute("temas", temas);
			request.getSession().setAttribute("temas", temas);
			
		}
		
		return mapping.findForward("success");
	}

}
