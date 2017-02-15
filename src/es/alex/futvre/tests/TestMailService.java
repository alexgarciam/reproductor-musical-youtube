package es.alex.futvre.tests;

import es.alex.futvre.service.mail.IMailService;
import es.alex.futvre.service.mail.MailServiceException;
import es.alex.futvre.service.mail.MailServiceImpl;

public class TestMailService {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IMailService iMailService=new MailServiceImpl();
		try {
			iMailService.sendMail("agmarchena@gmail.com", "test de menasaje", "sputifly test message");
		} catch (MailServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
