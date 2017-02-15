package es.alex.futvre.action;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;

import es.alex.futvre.DTO.LastFMArtistDTO;
import es.alex.futvre.service.lastFM.ILastFMService;
import es.alex.futvre.utils.Utils;

public class SearchArtistAction1 extends DispatchAction {
	private static org.apache.log4j.Logger registro;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		registro = Utils.configurarLog(SearchArtistAction1.class);

		String artista = request.getParameter("artist");
		
		//artista=(URLEncoder.encode(artista,"UTF-8"));
		//artista=artista.replaceAll("\\+", " ");
		
		if (artista.contains("%26")) {
			artista = artista.replaceAll("%26", "y");
		}

		LastFMArtistDTO artist = null;
		if (artista != null) {
			ApplicationContext ctx;
			ctx = WebApplicationContextUtils
					.getRequiredWebApplicationContext(this.getServlet()
							.getServletContext());

			ILastFMService lastFMService = null;
			lastFMService = (ILastFMService) ctx.getBean("lastFMService");
			Collection<LastFMArtistDTO> similares = new ArrayList<LastFMArtistDTO>();
			try {
				artist = lastFMService.getArtistHeader(artista);
				similares = lastFMService.getArtistSimilar(artista);
			} catch (Exception e) {
				registro.error(this.getClass() + "-Error: " + e.getMessage());
			}

			artist.setSummary(URLEncoder.encode(artist.getSummary(),"UTF-8"));
			artist.setSimilares(similares);
			request.setAttribute("artist", artist);
			request.getSession().setAttribute("artist", artist);

			String jsonOutput = null;
			Gson gson = new Gson();
			jsonOutput = gson.toJson(artist);
			
			request.setAttribute("jsonOutput", jsonOutput);
			request.getSession().setAttribute("jsonOutput", jsonOutput);	
			System.out.println("jsonOutput: "+ jsonOutput);
		}

		return mapping.findForward("success");
	}
}
