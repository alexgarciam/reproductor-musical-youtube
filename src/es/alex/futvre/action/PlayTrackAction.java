package es.alex.futvre.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import es.alex.futvre.service.crypto.ICriptoService;
import es.alex.futvre.utils.Utils;

public class PlayTrackAction extends DispatchAction {

	private static org.apache.log4j.Logger registro;
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		registro = Utils.configurarLog(PlayTrackAction.class);
		String code=request.getParameter("v");
			
		if(code!=null)
		{
			ApplicationContext ctx;
			ctx = WebApplicationContextUtils
					.getRequiredWebApplicationContext(this.getServlet()
							.getServletContext());
			
			code=code.replaceAll(" ", "+");
			ICriptoService criptoService = null;
			//LastFMTrackDTO track = new LastFMGTrackDTO();
			
			criptoService = (ICriptoService) ctx.getBean("criptoService");
			String s="";
			String [] params;
			
			String cancion="";
			String album="";
			String artista="";
			String image="";		
			
			cancion =cancion + " - " +artista;
			try{
			s = criptoService.decode(code);
			}catch(Exception e){
				registro.error(this.getClass()+"-Error: " + e.getMessage());
			}
			
			params=s.split("&");
			cancion=params[0];
			album=params[1];
			artista=params[2];		
			
			/*
			List<YoutubeVideoDTO> videosYoutube=null;
			IYoutubeService youtubeService =null;
			youtubeService = (IYoutubeService) ctx.getBean("youtubeService");
			videosYoutube = youtubeService.searchYoutubeVideo(cancion);	
			String video="";
			if(videosYoutube!=null && videosYoutube.size()>0)
			{
				YoutubeVideoDTO videoPrimero=null;
				
				videoPrimero=videosYoutube.get(0);	
				video=videoPrimero.getIdVideo();
				
				System.out.println("id: "+video);
				System.out.println("ID del video a reproducir: "+video);
				
			}			
			
			request.setAttribute("video",video);
			request.getSession().setAttribute("video",video);
			*/
		}
		
		return mapping.findForward("success");
	}
}
