package main.java;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class SendEmailImpl implements SendEmail {

    private final Session session;
    private final Multipart multipart;

    public SendEmailImpl(Session session) {
        this.session = session;
        this.multipart = new MimeMultipart();
    }

    @Override
    public void send() throws MessagingException {
        String msg = "<html><body><h1>Hello Bhaa</h1></body></html>";
        String from = "sendemailapp19@gmail.com";
        List<String> to = List.of("sendemailapp19@gmail.com");
        String subject = "Mail from JENKINS every 30 minutes";
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to.get(0)));
            message.setSubject(subject);
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html");
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            attachmentBodyPart.attachFile(new File("src/main/resources/Bhaa.jpg"));
            multipart.addBodyPart(mimeBodyPart);
            multipart.addBodyPart(attachmentBodyPart);
            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException | IOException e) {
            throw new MessagingException("Error while sending mail to", e);
        }
    }
}
