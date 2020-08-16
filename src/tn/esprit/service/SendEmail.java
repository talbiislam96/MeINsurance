/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.service;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Tryvl
 */
public class SendEmail {
    public void SendEmail(String host, String port,
        final String userName, final String password, String toAddress,
        String subject, String message) throws AddressException,
        MessagingException {
 
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
 
        Session session = Session.getInstance(properties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        // set plain text message
        msg.setContent(message, "text/html");
 
        // sends the e-mail
        Transport.send(msg);
 
    }
    
    public static void main(String[] args) {
        // SMTP server information
        String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = "yassine.sta@esprit.tn";
        String password = "azertysta";
 
        // outgoing message information
        String mailTo = "stayassine3@gmail.com";
        String subject = "Hello my friend";
 
        // message contains HTML markups
        
        
        SendEmail mailer = new SendEmail();

        try {
            String message = "    <!-- START EMAIL CONTENT -->\n" +
            "    <table width=\"100%\" height=\"500px\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">        \n" +
            "        <tbody>\n" +
            "            \n" +
            "            <tr>\n" +
            "                \n" +
            "                <td align=\"center\" bgcolor=\"#12CB06\">\n" +
            "                    \n" +
            "                    <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\n" +
            "                        <tbody>\n" +
            "                            <tr>\n" +
            "                                <td width=\"100%\" align=\"center\">\n" +
            "                                    \n" +
            "                                                                    \n" +
            "                                    <!-- START LOGO -->\n" +
            "                                    <table width=\"200\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\n" +
            "                                        <tbody>\n" +
            "                                            <tr>\n" +
            "                                                <td width=\"100%\" align=\"center\">\n" +
            "                                                    <img width=\"200\" src=\"https://i.ibb.co/SsMCJsW/52833796-771009279952586-6241389141028765696-n.png\" alt=\"CodeWiz Logo\" border=\"0\" style=\"text-align: center;\"/>\n" +
            "                                                </td>\n" +
            "                                            </tr>\n" +
            "                                        </tbody>\n" +
            "                                    </table>\n" +
            "                                    <!-- END LOGO -->\n" +
            "                                    \n" +
            "                                    <!-- START SPACING -->\n" +
            "                                    <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\n" +
            "                                        <tbody>\n" +
            "                                            <tr>\n" +
            "                                                <td height=\"40\">&nbsp;</td>\n" +
            "                                            </tr>\n" +
            "                                        </tbody>\n" +
            "                                    </table>\n" +
            "                                    <!-- END SPACING -->\n" +
            "                                    \n" +
            "                                    <!-- START CONTENT -->\n" +
            "                                    <table width=\"500\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" style=\"padding-left:20px; padding-right:20px;\" class=\"responsive_at_550\">\n" +
            "                                        <tbody>\n" +
            "                                            <tr>\n" +
            "                                                <td align=\"center\" bgcolor=\"#ffffff\">\n" +
            "                                                    \n" +
            "                                                    <!-- START BORDER COLOR -->\n" +
            "                                                    <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\n" +
            "                                                        <tbody>\n" +
            "                                                            <tr>\n" +
            "                                                                <td width=\"100%\" height=\"7\" align=\"center\" border=\"0\"  style=\"background-color: #10ad06;\"></td>\n" +
            "                                                            </tr>\n" +
            "                                                        </tbody>\n" +
            "                                                    </table>\n" +
            "                                                    <!-- END BORDER COLOR -->\n" +
            "                                                    \n" +
            "                                                    <!-- START SPACING -->\n" +
            "                                                    <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\n" +
            "                                                        <tbody>\n" +
            "                                                            <tr>\n" +
            "                                                                <td height=\"30\">&nbsp;</td>\n" +
            "                                                            </tr>\n" +
            "                                                        </tbody>\n" +
            "                                                    </table>\n" +
            "                                                    <!-- END SPACING -->\n" +
            "                                                    \n" +
            "                                                    <!-- START HEADING -->\n" +
            "                                                    <table width=\"90%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\n" +
            "                                                        <tbody>\n" +
            "                                                            <tr>\n" +
            "                                                                <td width=\"100%\" align=\"center\">\n" +
            "                                                                    <h1 style=\"font-family:'Ubuntu Mono', monospace; font-size:24px; color:#202020; font-weight:bold; padding-left:20px; padding-right:20px;\">Merci de votre inscription!</h1>\n" +
            "                                                                </td>\n" +
            "                                                            </tr>\n" +
            "                                                        </tbody>\n" +
            "                                                    </table>\n" +
            "                                                    <!-- END HEADING -->\n" +
            "                                                    \n" +
            "                                                    <!-- START PARAGRAPH -->\n" +
            "                                                    <table style=\"margin-bottom: 50px\" width=\"90%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\n" +
            "                                                        <tbody>\n" +
            "                                                            <tr>\n" +
            "                                                                <td width=\"100%\" align=\"center\">\n" +
            "                                                                    <p style=\"font-family:'Ubuntu', sans-serif; font-size:18px; color:#202020; padding-left:20px; padding-right:20px; text-align:justify;\">Votre compte est maintenant activé, vous pouvez désormais utiliser notre application en toute sécurité!</p>\n" +
            "                                                                </td>\n" +
            "                                                            </tr>\n" +
            "                                                        </tbody>\n" +
            "                                                    </table>\n" +
            "                                                    <!-- END PARAGRAPH -->\n" +
            "                                                \n" +
            "                                                    \n" +
            "                                                </td>\n" +
            "                                            </tr>\n" +
            "                                        </tbody>\n" +
            "                                    </table>                                    \n" +
            "                                </td>\n" +
            "                            </tr>\n" +
            "                        </tbody>\n" +
            "                    </table>\n" +
            "                    \n" +
            "                </td>\n" +
            "                \n" +
            "            </tr>\n" +
            "            \n" +
            "        </tbody>        \n" +
            "    </table>";
            mailer.SendEmail(host, port, mailFrom, password, mailTo,subject, message);
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Failed to sent email.");
            ex.printStackTrace();
        }
    }
}
