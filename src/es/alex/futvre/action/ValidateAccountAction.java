package es.alex.futvre.action;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import es.alex.futvre.DTO.TrackListaReproduccionDTO;
import es.alex.futvre.service.crypto.ICriptoService;
import es.alex.futvre.service.lastFM.ILastFMService;
import es.alex.futvre.service.persistence.listas.IListasPersistenceService;
import es.alex.futvre.utils.Utils;

public class ValidateAccountAction extends DispatchAction {
	private static org.apache.log4j.Logger registro;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		registro = Utils.configurarLog(ValidateAccountAction.class);
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(this.getServlet()
						.getServletContext());

		ICriptoService criptoService = (ICriptoService) ctx
				.getBean("criptoService");
		IListasPersistenceService listasService = (IListasPersistenceService) ctx
				.getBean("listasPersistenceService");

		String code = request.getParameter("v");
		code = code.replaceAll(" ", "+");

		String s = "";
		String[] params;

		String username = "";
		String date = "";

		try {

			s = criptoService.decodeUserAccountRequest(code);

			params = s.split("&");
			username = params[0];
			date = params[1];

			if (checkValidateTime(date)) {

				listasService.validateUser(username);
				registro.debug("validación del usuario: " + username
						+ " correcta!");
				request.setAttribute("listaRep",
						"validación correcta, ya puede iniciar sesion");
			} else {
				registro.debug("validación del usuario: " + username
						+ " fuera de plazo!");
				request.setAttribute("listaRep", "validación fuera de plazo");
			}

		} catch (Exception e) {
			registro.error(this.getClass() + "-Error: " + e.getMessage());
		}

		return mapping.findForward("success");
	}

	/**
	 * comprueba si el usuario valida el enlace dentro de las 24 horas siguiente
	 * a la generación del enlace
	 */
	public boolean checkValidateTime(String time) {
		boolean validated = false;

		// tomamos la fecha y hora de registro del usuario que se codifico en el
		// enlace
		Date thedate = Utils.parseDate(time, "dd/MM/yyyy-hh:mm:ss");
		registro.debug("fecha de la validación: " + thedate);
		thedate = Utils.increaseDate(thedate, Calendar.DAY_OF_MONTH, 1);
		registro.debug("fecha de la validación incrementada: " + thedate);

		// tomamos la fecha y hora actual
		Date actualdate = new Date();

		// comprobamos que no han pasado 24h desde que se envió el enlace
		if (actualdate.before(thedate)) {
			validated = true;
		}
		return validated;
	}

}
