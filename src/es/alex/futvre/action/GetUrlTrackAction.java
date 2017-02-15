package es.alex.futvre.action;

import java.net.URLEncoder;

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

public class GetUrlTrackAction extends DispatchAction {
	private static org.apache.log4j.Logger registro;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		registro = Utils.configurarLog(GetUrlTrackAction.class);

		ApplicationContext ctx;
		ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this
				.getServlet().getServletContext());

		
		String cancion=request.getParameter("cancion");
		String artista=request.getParameter("artista");
		String album=request.getParameter("album");
		if(cancion==null)
			cancion="";
		if(artista==null)
			artista="";
		if(album==null)
			album="";		
		
		String url="";
						
		ICriptoService criptoService = null;
		//LastFMTrackDTO track = new LastFMGTrackDTO();
		
		criptoService = (ICriptoService) ctx.getBean("criptoService");
		String code="";
		try {
			code = criptoService.enconde(cancion, album, artista);
			url="http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/openTrack.do?v="+code;			
			url=URLEncoder.encode(url);
		} catch (Exception e) {
			registro.error(this.getClass()+"-Error: " + e.getMessage());
		}

		request.setAttribute("jsonOutput", url);
		request.getSession().setAttribute("jsonOutput", url);

		return mapping.findForward("success");
	}

}
