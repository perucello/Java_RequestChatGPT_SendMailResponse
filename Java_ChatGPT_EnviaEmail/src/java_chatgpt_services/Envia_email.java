package java_chatgpt_services;

/**
 * *********************************************
 * EducaCiência FastCode 22/10/2023 Envia Email
 * *********************************************
 */
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;

public class Envia_email {

    public static void enviaEmail(String mensagem) {

        String emailDestino = "educaciencia-fastcode@outlook.com";
        String username = new credenciais.Credenciais().getUsername();
        String password = new credenciais.Credenciais().getPassword();

        // Configurações do servidor SMTP do Outlook
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp-mail.outlook.com");
        props.put("mail.smtp.port", "587");

        Session session;
        session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDestino));
            message.setSubject("Email automatico - response chatGPT");
            message.setText(mensagem);
           
            Transport.send(message);

            System.out.println("E-mail enviado com sucesso!");

        } catch (MessagingException e) {
            System.out.println("erro... -> " + e);
            throw new RuntimeException(e);
        }
    }
}
