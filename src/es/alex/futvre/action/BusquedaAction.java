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

import es.alex.futvre.DTO.LastFMTrackDTO;
import es.alex.futvre.DTO.YoutubeVideoDTO;
import es.alex.futvre.service.lastFM.ILastFMService;
import es.alex.futvre.service.youtube.IYoutubeService;
import es.alex.futvre.utils.Utils;

public class BusquedaAction  extends DispatchAction {

	private static org.apache.log4j.Logger registro;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		registro = Utils.configurarLog(BusquedaAction.class);

		String q=request.getParameter("q");
			
		if(q!=null)
		{
			ApplicationContext ctx;
			ctx = WebApplicationContextUtils
					.getRequiredWebApplicationContext(this.getServlet()
							.getServletContext());
			List<YoutubeVideoDTO> videosYoutube=null;
			IYoutubeService youtubeService =null;
			youtubeService = (IYoutubeService) ctx.getBean("youtubeService");
			
			try{
				videosYoutube = youtubeService.searchYoutubeVideo(q);
			}catch(Exception e){
				registro.error(this.getClass()+"-Error: " + e.getMessage());
			}
			
			request.setAttribute("videos",videosYoutube);
			request.getSession().setAttribute("videos",videosYoutube);
			
			ILastFMService lastFMService =null;
			lastFMService = (ILastFMService) ctx.getBean("lastFMService");			
			List<LastFMTrackDTO> temas=lastFMService.searchTrack(q);
			
			request.setAttribute("temas",temas);
			request.getSession().setAttribute("temas",temas);			
		}
		
		return mapping.findForward("success");
	}	
	
}
