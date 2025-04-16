package com.dcr.api.utils;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author SB034153
 *
 */
public class EmailUtil {

	/**
	 * 
	 */
	public EmailUtil() {

	}
	
	private static final String username = ""; 
	private static final String password = "";
	private static String from = "agent_automatico@honda.com.br";	
	private static String ipServerNotesSMTP = "10.146.2.49";
	private static String portServerNotesSMTP = "25";
	
	private static final Logger logger = LoggerFactory.getLogger(EmailUtil.class);

	public static void sendMail(String to, String codigo) throws Exception {
		

		StringBuilder sb = new StringBuilder();
		sb.append("<html><head>\r\n"
				+ "<title>DCR - Reset de senha</title>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ " <div class=\"container\" style=\"\r\n"
				+ "    width: 800px;\">\r\n"
				+ "<h1 style=\"color: rgba(204,0,0,1);font-family: helvetica,arial,sans-serif;\"> SGDCR – Sistema de Gestão da Obrigação Fiscal DCR-e/DCI - Redefina sua senha!</h1>\r\n"
				+ "<p style=\"\r\n"
				+ "    font-weight: 400;\r\n"
				+ "    font-size: 1.3em;\r\n"
				+ "    margin: 0;\r\n"
				+ "    padding: 0;\r\n"
				+ "    font-family: helvetica,arial,sans-serif;\r\n"
				+ "    color: #5c5c5c;\r\n"
				+ "\">Foi solicitado uma redefinição de senha para seu usuário do DCR, caso não tenha sido você, ignore!</p>\r\n"
				+ "<br/>\r\n"
				+ "<p style=\"\r\n"
				+ "    /* text-transform: uppercase; */\r\n"
				+ "    font-weight: 400;\r\n"
				+ "    font-size: 1.3em;\r\n"
				+ "    margin: 0;\r\n"
				+ "    padding: 0;\r\n"
				+ "    font-family: helvetica,arial,sans-serif;\r\n"
				+ "    color: #5c5c5c;\r\n"
				+ "\">Para redefinir a sua senha, informe o código de verificação abaixo:</p>\r\n"
				+ "<br/>\r\n"
				+ "<div>\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "<div style=\"\r\n"
				+ "    text-align: center;\r\n"
				+ "\">\r\n"
				+ "<div id=\"codigo\" style=\"\r\n"
				+ "    width: 180px;\r\n"
				+ "    height: 40px;\r\n"
				+ "    border: 0;\r\n"
				+ "    border-radius: 0;\r\n"
				+ "    box-shadow: none;\r\n"
				+ "    display: inline-block;\r\n"
				+ "    font-size: 1rem;\r\n"
				+ "    font-weight: 400;\r\n"
				+ "    line-height: 20px;\r\n"
				+ "    margin-right: -4px;\r\n"
				+ "    padding: 10px 20px;\r\n"
				+ "    text-align: center;\r\n"
				+ "    text-decoration: none;\r\n"
				+ "    text-transform: none;\r\n"
				+ "    vertical-align: bottom;\r\n"
				+ "    letter-spacing: 0 !important;\r\n"
				+ "    background: #cc0000;\r\n"
				+ "    color: #ffffff;\r\n"
				+ "    transition: background .3s ease-in-out;\r\n"
				+ "    -o-transition: background .3s ease-in-out;\r\n"
				+ "    -moz-transition: background .3s ease-in-out;\r\n"
				+ "\">\r\n"
				+ "        <h1 style=\"\r\n"
				+ "    font-family: helvetica,arial,sans-serif;\r\n"
				+ "    margin-bottom: 10%;\r\n"
				+ "    text-align: center;\r\n"
				+ "	font-size: 1.4rem;\r\n"
				+ "    margin-top: 5%;\r\n"
				+ "\">"+codigo+"</h1>\r\n"
				+ "</div>\r\n"
				+ "</div>\r\n"
				+ "\r\n"
				+ "</div>\r\n"
				+ "<br/>\r\n"
				+ "<br/>\r\n"
				+ "<p style=\"\r\n"
				+ "    font-weight: 400;\r\n"
				+ "    font-size: 1.3em;\r\n"
				+ "    margin: 0;\r\n"
				+ "    font-style: inherit;\r\n"
				+ "    padding: 0;\r\n"
				+ "    font-family: helvetica,arial,sans-serif;\r\n"
				+ "    color: rgba(204,0,0,1);\r\n"
				+ "    font-weight: bold;\r\n"
				+ "\">Importante: Esse código expira em 2 minutos!</p>\r\n"
				+ "<br/>\r\n"
				+ "<p style=\"\r\n"
				+ "    font-weight: 400;\r\n"
				+ "    font-size: 1em;\r\n"
				+ "    margin: 0;\r\n"
				+ "    font-style: inherit;\r\n"
				+ "    padding: 0;\r\n"
				+ "    font-family: helvetica,arial,sans-serif;\r\n"
				+ "    color: #5c5c5c;\r\n"
				+ "    font-weight: bold;\r\n"
				+ "\">Esta mensasem é automaticamente enviada por um serviço. Por favor, não a responda. </p>\r\n"
				+ "</div>\r\n"
				+ "</body></html>\r\n"
				+ "");
		
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "false");
		props.put("mail.from", from);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", ipServerNotesSMTP);
		props.put("mail.smtp.port", portServerNotesSMTP);

		// Get the Session object.
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message email = new MimeMessage(session);
			email.isMimeType("text/html");
			// Seta a variavel From do email: Endereco de Origem
			email.setFrom(new InternetAddress(from));
			// Seta a variavel To do email: : Endereco de Destino
			email.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			// Seta o assunto do email
			email.setSubject("Código de Verificação - DCR");
			// Seta a mensagem do email
			
			Multipart mp = new MimeMultipart();

			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(sb.toString(), "text/html; charset=UTF-8");
			mp.addBodyPart(htmlPart);

			
			email.setContent(mp);
			// Envia Email
			Transport.send(email);

		} catch (MessagingException e) {
			String msg = "Método sendMail() da classe EmailUtil lançou uma Exception: "
					+  "=> " + e.getMessage();
			logger.error(msg);
		}
	}

}
