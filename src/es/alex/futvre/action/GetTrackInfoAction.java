package es.alex.futvre.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;

import es.alex.futvre.DTO.TrackListaReproduccionDTO;
import es.alex.futvre.service.lastFM.ILastFMService;
import es.alex.futvre.utils.Utils;

public class GetTrackInfoAction extends DispatchAction {
	private static org.apache.log4j.Logger registro;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		registro = Utils.configurarLog(GetTrackInfoAction.class);

		ApplicationContext ctx;
		ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this
				.getServlet().getServletContext());

		String cancion = request.getParameter("cancion");
		String artista = request.getParameter("artista");

		ILastFMService lastFMService = null;
		// LastFMTrackDTO track = new LastFMGTrackDTO();
		String jsonOutput = null;
		lastFMService = (ILastFMService) ctx.getBean("lastFMService");
		TrackListaReproduccionDTO track = new TrackListaReproduccionDTO();
		try {
			track = lastFMService.getTrackInfo(cancion, artista);
			// convertirlo en objeto JSON
			Gson gson = new Gson();
			jsonOutput = gson.toJson(track);

		} catch (Exception e) {
			registro.error(this.getClass()+"-Error: " + e.getMessage());
		}

		request.setAttribute("jsonOutput", jsonOutput);
		request.getSession().setAttribute("jsonOutput", jsonOutput);

		return mapping.findForward("success");
	}

}
