package es.alex.futvre.service.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import es.alex.futvre.utils.Utils;

public class MailServiceImpl implements IMailService {

	private static org.apache.log4j.Logger registro;

	private static final String SMTP="smtp";
	private static final String MAIL_SMTP_HOST="mail.smtp.host";
	private static final String MAIL_SMTP_STARTTLS_ENABLE="mail.smtp.starttls.enable";
	private static final String MAIL_SMTP_PORT="mail.smtp.port";
	private static final String MAIL_SMTP_USER="mail.smtp.user";
	private static final String MAIL_SMTP_AUTH="mail.smtp.auth";
	private static final String MAIL_SMTP_PASSWORD="mail.smtp.password";
	
	
	public boolean sendMail(String emailTo,String msg, String subject) throws MailServiceException {

		registro = Utils.configurarLog(MailServiceImpl.class);
		boolean sended=false;
		
		 try
	        {			  
	            // Propiedades de la conexión
	            Properties props = new Properties();
	            props.setProperty(MAIL_SMTP_HOST, Utils.getStringResource(MAIL_SMTP_HOST));
	            props.setProperty(MAIL_SMTP_STARTTLS_ENABLE, Utils.getStringResource(MAIL_SMTP_STARTTLS_ENABLE));
	            props.setProperty(MAIL_SMTP_PORT, Utils.getStringResource(MAIL_SMTP_PORT));
	            props.setProperty(MAIL_SMTP_USER, Utils.getStringResource(MAIL_SMTP_USER));
	            props.setProperty(MAIL_SMTP_AUTH, Utils.getStringResource(MAIL_SMTP_AUTH));

	            // Preparamos la sesion
	            Session session = Session.getDefaultInstance(props);

	            // Construimos el mensaje
	            MimeMessage message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(Utils.getStringResource(MAIL_SMTP_USER)));
	            message.addRecipient(
	                Message.RecipientType.TO,
	                new InternetAddress(emailTo));
	            message.setSubject(subject);
	            message.setText(msg);

	            // Lo enviamos.
	            Transport t = session.getTransport(SMTP);
	            t.connect(Utils.getStringResource(MAIL_SMTP_USER), Utils.getStringResource(MAIL_SMTP_PASSWORD));
	            t.sendMessage(message, message.getAllRecipients());

	            // Cierre.
	            t.close();
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
		
		
		return sended;
	}

	

}
