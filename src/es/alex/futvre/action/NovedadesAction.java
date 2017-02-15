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

import es.alex.futvre.service.novedades.INovedadesService;
import es.alex.futvre.utils.Utils;

public class NovedadesAction extends DispatchAction {
	private static org.apache.log4j.Logger registro;
	private INovedadesService novedadesService;

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		registro = Utils.configurarLog(NovedadesAction.class);

		ApplicationContext ctx;
		ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this
				.getServlet().getServletContext());

		novedadesService = (INovedadesService) ctx.getBean("novedadesService");

		List noticias = null;
		List emisiones = null;
		List patrocinados = null;

		try {
			noticias = novedadesService.getNocitias();
			emisiones = novedadesService.getEmisiones();
			patrocinados = novedadesService.getPatrocinados();
		} catch (Exception e) {
			registro.error(this.getClass()+"-Error: " + e.getMessage());
		}

		// se añade la lista de categorias al request
		request.setAttribute("noticias", noticias);
		request.setAttribute("emisiones", emisiones);
		request.setAttribute("patrocinados", patrocinados);

		return mapping.findForward("success");
	}
}
