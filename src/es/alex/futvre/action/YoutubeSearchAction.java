package es.alex.futvre.action;

import java.net.URLEncoder;
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
import es.alex.futvre.service.youtube.YoutubeServiceException;
import es.alex.futvre.utils.Utils;

public class YoutubeSearchAction  extends DispatchAction {

	private static org.apache.log4j.Logger registro;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		registro = Utils.configurarLog(YoutubeSearchAction.class);
		
		String tamanyoPantalla = request.getParameter("tamPantalla");
		request.setAttribute("tamanyoPantalla", tamanyoPantalla);
		request.getSession().setAttribute("tamanyoPantalla", tamanyoPantalla);		
		
		String q=request.getParameter("q");	
		
		q=URLEncoder.encode(q);
		
		if(q!=null)
		{
			ApplicationContext ctx;
			ctx = WebApplicationContextUtils
					.getRequiredWebApplicationContext(this.getServlet()
							.getServletContext());
			
			IYoutubeService youtubeService =null;
			youtubeService = (IYoutubeService) ctx.getBean("youtubeService");
			List<YoutubeVideoDTO> videosYoutube=null;
			try{
				videosYoutube = youtubeService.searchYoutubeVideo(q);	
			}catch(YoutubeServiceException e){
				registro.error(this.getClass()+"-Error: " + e.getMessage());
			}
			
								
			request.setAttribute("videosYoutube",videosYoutube);
			request.getSession().setAttribute("videosYoutube",videosYoutube);
			
		}
		
		return mapping.findForward("success");
	}
}
