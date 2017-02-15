package es.alex.futvre.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import es.alex.futvre.DTO.YoutubeVideoDTO;
import es.alex.futvre.service.youtube.IYoutubeService;
import es.alex.futvre.utils.Utils;

public class ReproducirVideoAction extends DispatchAction {

	private static org.apache.log4j.Logger registro;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		registro = Utils.configurarLog(ReproducirVideoAction.class);
		String cancion = request.getParameter("cancion");
		String error = request.getParameter("error");
		String video = "";
		if (cancion != null) {
			ApplicationContext ctx;
			ctx = WebApplicationContextUtils
					.getRequiredWebApplicationContext(this.getServlet()
							.getServletContext());
			List<YoutubeVideoDTO> videosYoutube = null;
			IYoutubeService youtubeService = null;
			youtubeService = (IYoutubeService) ctx.getBean("youtubeService");
			try {
				videosYoutube = youtubeService.searchYoutubeVideo(cancion);

				if (videosYoutube != null && videosYoutube.size() > 0) {
					YoutubeVideoDTO videoPrimero = null;
					if (error == null)
						videoPrimero = videosYoutube.get(0);
					else {						
						int num_error = Integer.parseInt(error);
						videoPrimero = videosYoutube.get(num_error);
					}
					video = videoPrimero.getIdVideo();
				}
			} catch (Exception e) {
				registro.error(this.getClass()+"-Error: " + e.getMessage());
			}
			request.setAttribute("video", video);
			request.getSession().setAttribute("video", video);

		}
		return mapping.findForward("success");
	}
}
