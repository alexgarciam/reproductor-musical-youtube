package es.alex.futvre.action;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import es.alex.futvre.utils.Utils;

public class SetClipBoardAction extends DispatchAction {
	private static org.apache.log4j.Logger registro;
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		registro = Utils.configurarLog(SetClipBoardAction.class);
		String text = request.getParameter("text");
		String nombre = request.getParameter("nombre");
		String artista = request.getParameter("artista");
		String album = request.getParameter("album");
		String duracion = request.getParameter("duracion");
		try {
			if (text != null) {
				Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
				StringSelection ss = new StringSelection("http://"
						+ request.getServerName() + ":"
						+ request.getServerPort() + request.getContextPath()
						+ "/indice.jsp" + text + "&nombre=" + nombre
						+ "&artista=" + artista + "&album=" + album
						+ "&duracion=" + duracion);
				cb.setContents(ss, ss);
			}
		} catch (Exception e) {
			registro.error(this.getClass()+"-Error: " + e.getMessage());		}
		return mapping.findForward("success");
	}

}
