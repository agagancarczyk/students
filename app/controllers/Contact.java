package controllers;

import play.Logger;
import play.mvc.Controller;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import play.libs.*;
import play.cache.*;

/**
 * sendMessage class configured only for localhost by jfitzgerald with exception to the addition
 * of captcha and validation for sendMessage and captcha form.  
 */
public class Contact extends Controller
{
  public static void index()
  {
    String randomID = Codec.UUID();
    render(randomID);
  }

  /**
   * 
   * @param firstName
   *          : the message sender's first and
   * @param lastName
   *          : last name.
   * @param emailSender
   *          : the email address of the sender of the message.
   * @param messageTxd
   *          : the message.
   * @param code
   *          : code for captcha.
   * @param randomID
   *          : random ID for captcha.
   */
  public static void sendMessage(String firstName, String lastName, String emailSender, String messageTxd, String code,
      String randomID)
  {

    // Validation for Email Us Form.
    validation.required("First name", firstName);
    validation.required("Last name", lastName);
    validation.required("Sender's email", emailSender);
    validation.required("Message", messageTxd);

    if (validation.hasErrors())
    {
      params.flash();
      validation.keep();
      index();
    }

    // Validation for captcha.
    validation.equals(code, Cache.get(randomID)).message("Invalid code. Please type it again");
    if (validation.hasErrors())
    {
      render("Contact/index.html", randomID);
    }
    Cache.delete(randomID);

    if (Accounts.getCurrentUser() == null)
    {
      Accounts.login();
    }
    final String username = "agagancarczyk@yahoo.com";
    final String password = "Garnek1986";

    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.mail.yahoo.com");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props, new javax.mail.Authenticator()
    {
      protected PasswordAuthentication getPasswordAuthentication()
      {
        return new PasswordAuthentication(username, password);
      }
    });

    try
    {
      String forwarderAddress = username;
      String destinationAddress1 = emailSender;
      String destinationAddress2 = username;
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(forwarderAddress));
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinationAddress1));
      message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(destinationAddress2));
      message.setSubject("Message for studEnts Webmaster");
      message.setText(messageTxd);

      Transport.send(message);

    }
    catch (MessagingException e)
    {
      Logger.info(e.getMessage());
      Acknowledgement.index();// a temporary fix: an exception caught when
                              // sendMessage invoked from Cloud
      throw new RuntimeException(e);
    }

  }
  
  public static void captcha(String id)
  {
    Images.Captcha captcha = Images.captcha();
    String code = captcha.getText("#E4EAFD");
    Cache.set(id, code, "10mn");
    renderBinary(captcha);
  }
}