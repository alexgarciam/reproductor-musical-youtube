package es.alex.futvre.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import es.alex.futvre.DTO.LastFMArtistDTO;
import es.alex.futvre.service.lastFM.ILastFMService;
import es.alex.futvre.utils.Utils;

public class SearchArtistAction  extends DispatchAction {
	private static org.apache.log4j.Logger registro;

	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		registro = Utils.configurarLog(SearchArtistAction.class);
		String artista=request.getParameter("artist");
					
		LastFMArtistDTO artist=null;
		
		if(artista!=null)
		{
			ApplicationContext ctx;
			ctx = WebApplicationContextUtils
					.getRequiredWebApplicationContext(this.getServlet()
							.getServletContext());
			
			ILastFMService lastFMService =null;
			lastFMService = (ILastFMService) ctx.getBean("lastFMService");
			
			try{
				artist=lastFMService.getArtist(artista);
			}catch(Exception e){
				registro.error(this.getClass()+"-Error: " + e.getMessage());
			}
			
			request.setAttribute("artist",artist);
			request.getSession().setAttribute("artist",artist);

			request.setAttribute("albums",artist.getAlbums());
			request.getSession().setAttribute("albums",artist.getAlbums());
			response.setContentType("text/html; charset=utf-8");
		}
		
		return mapping.findForward("success");
	}
}
