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

import es.alex.futvre.DTO.LastFMAlbumDTO;
import es.alex.futvre.service.lastFM.ILastFMService;
import es.alex.futvre.utils.Utils;

public class GetAlbumArtistaAction  extends DispatchAction {
	private static org.apache.log4j.Logger registro;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		registro = Utils.configurarLog(GetAlbumArtistaAction.class);

		String artista=request.getParameter("artist");
		String album=request.getParameter("album");
		
		if(artista!=null && album!=null)
		{
			ApplicationContext ctx;
			ctx = WebApplicationContextUtils
					.getRequiredWebApplicationContext(this.getServlet()
							.getServletContext());
			
			ILastFMService lastFMService =null;
			lastFMService = (ILastFMService) ctx.getBean("lastFMService");
			
			LastFMAlbumDTO elalbum=null;
			try{
				elalbum=lastFMService.getAlbumSinInfo(artista, album);
			}catch(Exception e){
				registro.error(this.getClass()+"-Error: " + e.getMessage());
			}
			
			String jsonOutput = null;
			Gson gson = new Gson();
			jsonOutput = gson.toJson(elalbum);
			
			request.setAttribute("jsonOutput", jsonOutput);
			request.getSession().setAttribute("jsonOutput", jsonOutput);
			
		}
		
		return mapping.findForward("success");
	}
}
