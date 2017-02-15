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
import es.alex.futvre.service.crypto.ICriptoService;
import es.alex.futvre.service.lastFM.ILastFMService;
import es.alex.futvre.utils.Utils;

public class OpenTrackAction extends DispatchAction {
	private static org.apache.log4j.Logger registro;
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		registro = Utils.configurarLog(OpenTrackAction.class);
		ApplicationContext ctx;
		ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this
				.getServlet().getServletContext());

		String code = request.getParameter("v");
		code = code.replaceAll(" ", "+");
		ICriptoService criptoService = null;
		// LastFMTrackDTO track = new LastFMGTrackDTO();

		criptoService = (ICriptoService) ctx.getBean("criptoService");
		String s = "";
		String[] params;

		String cancion = "";
		String album = "";
		String artista = "";
		String image = "";

		TrackListaReproduccionDTO track = null;

		try {

			s = criptoService.decode(code);

			params = s.split("&");
			cancion = params[0];
			album = params[1];
			artista = params[2];

			ILastFMService lastFMService = null;
			lastFMService = (ILastFMService) ctx.getBean("lastFMService");
			track = lastFMService.getTrackInfo(cancion, artista);

			String imageAlbum = lastFMService.getAlbumImage(artista, album);
			if (imageAlbum == null || imageAlbum.equals("")) {
				String artistImage = lastFMService.getArtistImage(artista);
				if (artistImage != null && !artistImage.equals("")) {
					imageAlbum = artistImage;
				} else {
					imageAlbum = "images/albumNotFound.jpg";
				}
			}
			image = imageAlbum;

		} catch (Exception e) {
			registro.error(this.getClass()+"-Error: " + e.getMessage());
		}

		request.getSession().setAttribute("id", track.getId());
		request.getSession().setAttribute("duracion", track.getDuracion());
		request.getSession().setAttribute("cancion", cancion);
		request.getSession().setAttribute("album", album);
		request.getSession().setAttribute("artista", artista);
		request.getSession().setAttribute("image", image);
		request.getSession().setAttribute("code", code);
		request.getSession().setAttribute("openTrack", true);

		return mapping.findForward("success");
	}

}
