package uy.com.uma.comun.mail;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


/**
 * Utilidades para el manejo de correos electrónicos
 * 
 * @author Santiago Dalchiele
 */
public class UtilMail {

	public static void enviar (final MailParams parms) throws MessagingException {
		Properties p = new Properties();
		p.setProperty("mail.smtp.host", parms.getHostCorreo());
		p.setProperty("mail.smtp.port", String.valueOf(parms.getPuertoCorreo()));
		p.setProperty("mail.smtp.connectiontimeout", String.valueOf(parms.getTimeout()));
		p.setProperty("mail.smtp.timeout", String.valueOf(parms.getTimeout()));
		p.setProperty("mail.smtp.ssl.enable", "false");
		p.setProperty("mail.smtp.auth", "true");
		p.setProperty("mail.smtp.user", parms.getUsuarioCorreo());
		
		if (parms.getPuertoCorreo() == 587)
			p.setProperty("mail.smtp.starttls.enable", "true");		
		
		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(parms.getUsuarioCorreo(), parms.getClaveCorreo());
		}};
		
		Session session = Session.getInstance(p, auth);
		session.setDebug(true);
		session.setDebugOut(System.out);
		Transport t = null;
		
		try {
			t = session.getTransport("smtp");
			t.connect(parms.getUsuarioCorreo(), parms.getClaveCorreo());
			Message message = new MimeMessage(session);
			message.setFrom(parms.getFrom());
			message.setReplyTo(parms.getReplyTo());
			message.setRecipients(Message.RecipientType.TO, parms.getTo());
			message.setRecipients(Message.RecipientType.CC, parms.getCc());
			message.setRecipients(Message.RecipientType.BCC, parms.getBcc());
			
			if (parms.getAsunto() != null)
				message.setSubject(parms.getAsunto());
			
			if (parms.getAdjuntos().isEmpty()) {			
				if (parms.getTexto() != null)
					message.setText(parms.getTexto());
				
				if (parms.getContenidoHTML() != null)
					message.setContent(parms.getContenidoHTML(), "text/html");
			} else {
				Multipart multiparte = new MimeMultipart();
				BodyPart cuerpo = new MimeBodyPart();			
				
				if (parms.getTexto() != null)
					cuerpo.setText(parms.getTexto());
				
				if (parms.getContenidoHTML() != null)
					cuerpo.setContent(parms.getContenidoHTML(), "text/html");			
				
				multiparte.addBodyPart(cuerpo);
	
				for (String nombreArchivo : parms.getAdjuntos()) {
					File arch = new File(nombreArchivo);
					DataSource fuente = new FileDataSource(nombreArchivo);
				    BodyPart adjunto = new MimeBodyPart();        
				    adjunto.setDataHandler(new DataHandler(fuente));
				    adjunto.setFileName(arch.getName());
				    multiparte.addBodyPart(adjunto);
				}		
				
				message.setContent(multiparte);
			}		
						
			t.sendMessage(message, message.getAllRecipients());
		} finally {
			if (t != null)
				try {
					t.close();
				} catch (MessagingException e) {
					e.printStackTrace();
				}
		}
	}
}
