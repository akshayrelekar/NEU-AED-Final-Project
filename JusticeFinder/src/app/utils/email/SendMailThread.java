/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.utils.email;

/**
 *
 * @author PC
 */
public class SendMailThread implements Runnable{

    String to,subject,messageBody;

    public SendMailThread(String to, String subject, String messageBody) {
        this.to = to;
        this.subject = subject;
        this.messageBody = messageBody;
    }
    
    @Override
    public void run() {
        EmailUtil.sendEmail(to, subject, messageBody);
    }
    
}
