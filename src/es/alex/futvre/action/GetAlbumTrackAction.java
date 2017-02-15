package es.alex.futvre.action;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import es.alex.futvre.DTO.LastFMAlbumDTO;
import es.alex.futvre.DTO.LastFMTrackDTO;
import es.alex.futvre.DTO.YoutubeVideoDTO;
import es.alex.futvre.service.lastFM.ILastFMService;
import es.alex.futvre.service.youtube.IYoutubeService;
import es.alex.futvre.utils.Utils;

public class GetAlbumTrackAction extends DispatchAction {
	private static org.apache.log4j.Logger registro;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		registro = Utils.configurarLog(GetAlbumTrackAction.class);

		String artist = request.getParameter("artista");
		String album = request.getParameter("album");
		String num_track = request.getParameter("track");
		String error = request.getParameter("error");

		int numero_cancion = Integer.parseInt(num_track);
		
		if (artist != null && album != null) {
			ApplicationContext ctx;
			ctx = WebApplicationContextUtils
					.getRequiredWebApplicationContext(this.getServlet()
							.getServletContext());

			ILastFMService lastFMService = null;
			lastFMService = (ILastFMService) ctx.getBean("lastFMService");

			LastFMAlbumDTO myalbum = null;
			try {
				myalbum = lastFMService.getAlbum(artist, album);
			} catch (Exception e) {
				registro.error(this.getClass()+"-Error: " + e.getMessage());
			}

			request.setAttribute("album", myalbum);
			request.getSession().setAttribute("album", myalbum);

			List<YoutubeVideoDTO> videosYoutube = null;
			IYoutubeService youtubeService = null;
			youtubeService = (IYoutubeService) ctx.getBean("youtubeService");

			Iterator<LastFMTrackDTO> tracks = myalbum.getTracks().iterator();
			int i = 1;
			boolean enc = false;
			while (tracks.hasNext() && !enc) {
				LastFMTrackDTO track = tracks.next();
				if (numero_cancion == i) {
					enc = true;
					try {
						videosYoutube = youtubeService.searchYoutubeVideo(track
								.getName()
								+ " " + track.getArtist());
					} catch (Exception e) {
						registro.error(this.getClass()+"-Error: " + e.getMessage());
					}
					String video = "";
					if (videosYoutube != null && videosYoutube.size() > 0) {
						YoutubeVideoDTO videoPrimero = null;
						if (error == null)
							videoPrimero = videosYoutube.get(0);
						else {
							int num_error = Integer.parseInt(error);
							try{
							videoPrimero = videosYoutube.get(num_error);
							}catch(IndexOutOfBoundsException e){
								System.out.println("Demasiados errores!!");
							}
						}
						video = videoPrimero.getIdVideo();
					}

					request.setAttribute("video", video);
					request.getSession().setAttribute("video", video);
				}
				i++;
			}

			request.setAttribute("num_track", myalbum.getTracks());
			request.getSession().setAttribute("num_track", myalbum.getTracks());
		}

		return mapping.findForward("success");

	}
}
