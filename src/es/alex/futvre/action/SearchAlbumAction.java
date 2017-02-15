package es.alex.futvre.action;

import java.util.Iterator;

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
import es.alex.futvre.service.lastFM.ILastFMService;
import es.alex.futvre.utils.Utils;

public class SearchAlbumAction extends DispatchAction {
	private static org.apache.log4j.Logger registro;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		registro = Utils.configurarLog(SearchAlbumAction.class);
		String artist = request.getParameter("artist");
		String album = request.getParameter("album");
		LastFMAlbumDTO myalbum = null;
		if (artist != null && album != null) {
			ApplicationContext ctx;
			ctx = WebApplicationContextUtils
					.getRequiredWebApplicationContext(this.getServlet()
							.getServletContext());

			ILastFMService lastFMService = null;
			lastFMService = (ILastFMService) ctx.getBean("lastFMService");
			try {
				myalbum = lastFMService.getAlbum(artist, album);
			} catch (Exception e) {
				registro.error(this.getClass()+"-Error: " + e.getMessage());
			}

			// ponemos el nombre correcto del album por si las moscas (xq
			// fallaba cuando empieza por ¿)
			myalbum.setName(album);
			Iterator<LastFMTrackDTO> tracks = myalbum.getTracks().iterator();
			while (tracks.hasNext()) {
				LastFMTrackDTO track = tracks.next();
				track.setAlbum(album);
			}

			request.setAttribute("album", myalbum);
			request.getSession().setAttribute("album", myalbum);

			request.setAttribute("num_tracks", myalbum.getTracks().size());
			request.getSession().setAttribute("num_tracks",
					myalbum.getTracks().size());
		}

		return mapping.findForward("success");
	}
}
