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

public class GetAlbumImageAction   extends DispatchAction {
	private static org.apache.log4j.Logger registro;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		registro = Utils.configurarLog(GetAlbumImageAction.class);

		String artist = request.getParameter("artista");
		String album = request.getParameter("album");
		
		if (artist != null && album != null || !artist.equals("") && !album.equals("")) {
			ApplicationContext ctx;
			ctx = WebApplicationContextUtils
					.getRequiredWebApplicationContext(this.getServlet()
							.getServletContext());

			ILastFMService lastFMService = null;
			lastFMService = (ILastFMService) ctx.getBean("lastFMService");
			
			String imageAlbum=null;
			try{
				imageAlbum=lastFMService.getAlbumImage(artist, album);
			}catch(Exception e){
				registro.error(this.getClass()+"-Error: " + e.getMessage());
			}
			
			if(imageAlbum==null || imageAlbum.equals(""))
			{	
				if(!artist.equals("")){
					String artistaImg=lastFMService.getArtistImage(artist);
					if(artistaImg!=null && !artistaImg.equals("")){
						imageAlbum=artistaImg;
					}
					else{
						imageAlbum="404";					
					}	
				}else{
					imageAlbum="404";					
				}							
			}			
			request.setAttribute("imagenAlbum", imageAlbum);
			request.getSession().setAttribute("imagenAlbum", imageAlbum);						
		}

		return mapping.findForward("success");
		
	}
}
