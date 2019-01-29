/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.utils.email;

import app.utils.ConfigUtil;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author PC
 */
public class EmailUtil {

    public static void sendEmail(String to,String subject, String messageBody) {
        
        String sendEmailProp = ConfigUtil.getProp("sendEmail");
        
        if(sendEmailProp.equals("off")){
            return;
        }
        
        final String username = ConfigUtil.getProp("emailUser");
        final String password = ConfigUtil.getProp("emailPass");

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", ConfigUtil.getProp("emailSMTP"));
        props.put("mail.smtp.port", ConfigUtil.getProp("emailPort"));

        Session session;
        session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            try {
                message.setFrom(new InternetAddress(username, "Application Email"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setContent(messageBody, "text/html; charset=utf-8");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    public static void sendMail(String to,String subject, String messageBody){
        SendMailThread t = new SendMailThread(to, subject, messageBody);
        Thread thread = new Thread(t, "emailSender");
        thread.start();
    }
}
