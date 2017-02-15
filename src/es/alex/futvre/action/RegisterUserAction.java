package es.alex.futvre.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.context.ApplicationContext;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.web.context.support.WebApplicationContextUtils;

import es.alex.futvre.DTO.TrackListaReproduccionDTO;
import es.alex.futvre.persistence.Favorito;
import es.alex.futvre.persistence.User;
import es.alex.futvre.persistence.Usuario;
import es.alex.futvre.service.crypto.ICriptoService;
import es.alex.futvre.service.lastFM.ILastFMService;
import es.alex.futvre.service.mail.IMailService;
import es.alex.futvre.service.persistence.listas.IListasPersistenceService;
import es.alex.futvre.utils.Utils;

public class RegisterUserAction  extends DispatchAction {
	private static org.apache.log4j.Logger registro;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		registro = Utils.configurarLog(RegisterUserAction.class);		
		ApplicationContext ctx;
		ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(this.getServlet()
						.getServletContext());
		
		String username = request.getParameter("userNameForm");
		String email = request.getParameter("userEmailForm");
		String password1 = request.getParameter("userPassword1Form");
		String password2 = request.getParameter("userPassword2Form");
		
		
		try{
			if(!password1.equals(password2)){
				throw new Exception("Password mismatch error");
			}		
			
			
			IListasPersistenceService listasService=null;
			listasService = (IListasPersistenceService) ctx.getBean("listasPersistenceService");
			Date created=new Date();
			boolean done= listasService.createUser(username, password1, email,created);
			
			if(done){
				ICriptoService criptoService = (ICriptoService) ctx.getBean("criptoService");
				String encodedRequest=criptoService.encodeUserAccountRequest(username, created);
				
				String url="http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/validateAccount.do?v="+encodedRequest;
				
				IMailService mailService = (IMailService) ctx.getBean("mailService");
				mailService.sendMail(email, url, "Validar Cuenta");
				
			}
			request.setAttribute("listaRep", done);
			
		}catch(Exception e){
			
			return mapping.findForward("success");
		}		
		
		return mapping.findForward("success");
	}
}
