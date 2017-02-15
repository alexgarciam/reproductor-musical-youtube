package es.alex.futvre.service.mail;


public interface IMailService {
	
	/**
	 * Obtiene las novedades
	 * @return
	 * @throws MailServiceException
	 */
	@SuppressWarnings("unchecked")
	public boolean sendMail(String emailTo,String msg, String subject) throws MailServiceException;
	
	
	

}
