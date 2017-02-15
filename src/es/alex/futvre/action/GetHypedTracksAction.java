package es.alex.futvre.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import es.alex.futvre.DTO.LastFMGTrackDTO;
import es.alex.futvre.service.lastFM.ILastFMService;
import es.alex.futvre.utils.Utils;

public class GetHypedTracksAction  extends DispatchAction {
	private static org.apache.log4j.Logger registro;

	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			registro = Utils.configurarLog(GetHypedTracksAction.class);

			String tamanyoPantalla = request.getParameter("tamPantalla");
			request.setAttribute("tamanyoPantalla", tamanyoPantalla);
			request.getSession().setAttribute("tamanyoPantalla", tamanyoPantalla);
			
			ApplicationContext ctx;
			ctx = WebApplicationContextUtils
					.getRequiredWebApplicationContext(this.getServlet()
							.getServletContext());
			
			ILastFMService lastFMService =null;
			List<LastFMGTrackDTO> hypedTracks=new ArrayList<LastFMGTrackDTO>();
			lastFMService = (ILastFMService) ctx.getBean("lastFMService");
			try{
				hypedTracks=lastFMService.getHypedTracks();
			}
			catch(Exception e)
			{
				hypedTracks=new ArrayList<LastFMGTrackDTO>();
				registro.error(this.getClass()+"-Error: " + e.getMessage());
			}
			
			
			request.setAttribute("hypedTracks",hypedTracks);
			request.getSession().setAttribute("hypedTracks",hypedTracks);
			
		
		
		return mapping.findForward("success");
	}
		
}
