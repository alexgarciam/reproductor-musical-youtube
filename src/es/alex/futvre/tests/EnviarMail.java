package es.alex.futvre.tests;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * Ejemplo de envio de correo simple con JavaMail
 *
 * @author Chuidiang
 *
  */
public class EnviarMail
{
    /**
     * main de prueba
     * @param args Se ignoran.
     */
    public static void main(String[] args)
    {
        try
        {
            // Propiedades de la conexi�n
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "agmarchena@gmail.com");
            props.setProperty("mail.smtp.auth", "true");

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);

            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("agmarchena@gmail.com"));
            message.addRecipient(
                Message.RecipientType.TO,
                new InternetAddress("agarcia@isoin.es"));
            message.setSubject("Hola");
            message.setText(
                "Mensajito con Java Mail" + "de los buenos." + "poque si");

            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            t.connect("agmarchena@gmail.com", "4l3l01s4");
            t.sendMessage(message, message.getAllRecipients());

            // Cierre.
            t.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}