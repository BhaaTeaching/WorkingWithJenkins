package main.java;

import javax.mail.MessagingException;
import javax.mail.Session;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws MessagingException {
        System.out.println("Started");
        ConfigurationApp configurationApp = new ConfigurationApp();
        Properties properties = configurationApp.initEmailCredentials();
        Session session = configurationApp.getPasswordAuthenticationInit(properties);
        SendEmail sendEmail = new SendEmailImpl(session);
        sendEmail.send();
        System.out.println("Done");
    }
}
