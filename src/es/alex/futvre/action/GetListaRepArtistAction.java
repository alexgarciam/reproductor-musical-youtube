package es.alex.futvre.action;

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
import es.alex.futvre.DTO.LastFMArtistDTO;
import es.alex.futvre.DTO.LastFMTrackDTO;
import es.alex.futvre.DTO.ListaReproduccionDTO;
import es.alex.futvre.DTO.TrackListaReproduccionDTO;
import es.alex.futvre.service.lastFM.ILastFMService;
import es.alex.futvre.utils.Utils;

public class GetListaRepArtistAction extends DispatchAction {
	private static org.apache.log4j.Logger registro;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		registro = Utils.configurarLog(GetListaRepArtistAction.class);
				
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

			Collection<TrackListaReproduccionDTO> lista = null;
			lista = new ArrayList<TrackListaReproduccionDTO>();

			LastFMArtistDTO artista = null;
			try {
				artista = lastFMService.getArtist(artist);
			} catch (Exception e) {
				registro.error(this.getClass()+"-Error: " + e.getMessage());
			}

			Iterator<LastFMAlbumDTO> albums = artista.getAlbums().iterator();
			while (albums.hasNext()) {
				LastFMAlbumDTO myalbum = albums.next();
				Iterator<LastFMTrackDTO> tracks = myalbum.getTracks()
						.iterator();

				boolean enc = false;

				while (tracks.hasNext() && !enc) {
					LastFMTrackDTO track = tracks.next();
					TrackListaReproduccionDTO tema = new TrackListaReproduccionDTO();
					tema.setAlbum(track.getAlbum());
					tema.setArtista(track.getArtist());
					Integer durat = new Integer(track.getDuration());
					tema.setDuracion(durat.toString());
					tema.setNombre(track.getName());
					tema.setId(track.getId());
					tema.setIdYoutube("");
					tema.setReproduciendo("false");
					if (track.getId().equals(idTrack))
						tema.setReproduciendo("true");
					lista.add(tema);
				}
			}

			ListaReproduccionDTO milista = new ListaReproduccionDTO();
			milista.setNombre("estopa - estopa");
			milista.setTemas(lista);
			String jsonOutput = "";
			Gson gson = new Gson();
			try {
				jsonOutput = gson.toJson(milista);
			} catch (Exception e) {
				registro.error(this.getClass()+"-Error: " + e.getMessage());
			}

			System.out.println("json: " + jsonOutput);

			request.setAttribute("num_track", jsonOutput);
			request.getSession().setAttribute("num_track", jsonOutput);

		}

		return mapping.findForward("success");

	}
}
