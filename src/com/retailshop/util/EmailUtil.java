package com.retailshop.util;


import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailUtil {

	public static void main(String args[]){
		String to[] = {"vivektr2007@gmail.com"};
		try {
			sendEmail(to,"Hi, Your password is 12345","Password reset");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void sendEmail(String sendTo[],String msg,String subject) throws Exception{
        final String senderId = "sales@bestkart4u.com";
        final String senderPassword = "kart#$567";
        String fromEmail = "sales@bestkart4u.com";
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp"); 
        props.setProperty("mail.host", "mail.bestkart4u.com");
        props.put("mail.smtp.port", "26");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.from", "sales@bestkart4u.com");
        
      //  props.put("mail.debug", "false");
      //  props.put("mail.smtp.socketFactory.port", "26");

    //   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
    //  props.put("mail.smtp.socketFactory.fallback", "false");

        Session session = Session.getDefaultInstance(props,new Authenticator(){
            
                                    @Override
                                    protected PasswordAuthentication getPasswordAuthentication() 
                                    {
                                        return new PasswordAuthentication(senderId,senderPassword);
                                    }
            
                                    });
        
        //Session session = Session.getDefaultInstance(props,new Test(id,password) );
        session.setDebug(true); 
        Transport transport = session.getTransport();
        InternetAddress addressFrom = new InternetAddress(fromEmail); 
        MimeMessage message = new MimeMessage(session);
        message.setSender(addressFrom);
        message.setFrom(new InternetAddress("BestKart4U" + "<" + "sales@bestkart4u.com" + ">"));
       
        message.setSubject(subject);
        message.setContent(msg, "text/html");
        //String sendTo [] = {"vivektr2010@yahoo.com","vtripathi@gdiindia.com"};
        if (sendTo != null) {
            InternetAddress[] addressTo = new InternetAddress[sendTo.length];
            for (int i = 0; i < sendTo.length; i++) {
                addressTo[i] = new InternetAddress(sendTo[i]);
            }
            message.setRecipients(RecipientType.TO, addressTo);
            transport.connect();
            Transport.send(message);
            transport.close();
            //Transport.send(message);
           
        }

    }
	
	/*public static void sendEmail(String sendTo[],String msg,String subject) throws Exception{
        final String senderId = "bestkart4u@gmail.com";
        final String senderPassword = "bestkart@12345";
        String fromEmail = "customerservice@bestkart4u.com";
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp"); 
        props.setProperty("mail.host", "smtp.gmail.com");

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        props.put("mail.debug", "false");
        props.put("mail.smtp.socketFactory.port", "465");

        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        Session session = Session.getDefaultInstance(props,new Authenticator(){
            
                                    @Override
                                    protected PasswordAuthentication getPasswordAuthentication() 
                                    {
                                        return new PasswordAuthentication(senderId,senderPassword);
                                    }
            
                                    });
        
        //Session session = Session.getDefaultInstance(props,new Test(id,password) );
        session.setDebug(true); 
        Transport transport = session.getTransport();
        InternetAddress addressFrom = new InternetAddress(fromEmail); 
        MimeMessage message = new MimeMessage(session);
        message.setSender(addressFrom);

       
        message.setSubject(subject);
        message.setContent(msg, "text/html");
        //String sendTo [] = {"vivektr2010@yahoo.com","vtripathi@gdiindia.com"};
        if (sendTo != null) {
            InternetAddress[] addressTo = new InternetAddress[sendTo.length];
            for (int i = 0; i < sendTo.length; i++) {
                addressTo[i] = new InternetAddress(sendTo[i]);
            }
            message.setRecipients(RecipientType.TO, addressTo);
            transport.connect();
            Transport.send(message);
            transport.close();
            //Transport.send(message);
           
        }

    }
	*/
}
