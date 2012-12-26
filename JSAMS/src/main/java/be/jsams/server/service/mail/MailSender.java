package be.jsams.server.service.mail;

import java.io.IOException;
import java.util.Properties;
import java.util.prefs.Preferences;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import be.jsams.client.i18n.I18nString;
import be.jsams.common.bean.model.sale.AbstractDocumentBean;
import be.jsams.common.util.JsamsConstants;

/**
 * Mail sender service.
 *
 * @param <B> the {@link AbstractDocumentBean} to use
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class MailSender<B extends AbstractDocumentBean<?, ?>> {

    private String pdf;
    private B bean;

    /**
     * Constructor.
     * 
     * @param pdf the PDF to attach
     * @param bean the bean to use
     */
    public MailSender(String pdf, B bean) {
        this.pdf = pdf;
        this.bean = bean;
    }

    /**
     * Sends the email with the PDF in attachment.
     * 
     * @return true if the email was successfully sent, false otherwise
     */
    public boolean send() {
        if (bean != null) {
            Preferences rootPreferences = Preferences.userRoot();
            Preferences preferences = rootPreferences.node(JsamsConstants.PACKAGE_ROOT_NAME);
            Session session = buildSession(preferences, bean);

            Message message = new MimeMessage(session);
            InternetAddress recipient;
            Transport transport = null;
            String to = bean.getCustomer().getContactInformation().getEmail();
            if (to == null) {
                throw new IllegalArgumentException("The receiver email cannot be blank!");
            }
            try {
                recipient = new InternetAddress(to);
                message.setRecipient(Message.RecipientType.TO, recipient);
                String defaultSubject = String.valueOf(preferences
                        .get(JsamsConstants.EMAIL_DEFAULT_SUBJECT, ""));
                message.setSubject(defaultSubject);
                MimeMultipart multipart = buildContent(pdf, bean);
                message.setContent(multipart);
                transport = session.getTransport("smtp");
                transport.connect();
                Transport.send(message);
            } catch (AddressException ae) {
                throw new RuntimeException(ae);
            } catch (MessagingException me) {
                throw new RuntimeException(me);
            } catch (IOException ioe) {
                throw new RuntimeException(ioe);
            } finally {
                try {
                    if (transport != null) {
                        transport.close();
                    }
                } catch (MessagingException me) {
                    throw new RuntimeException(me);
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Builds content message.
     * 
     * @param pdf the PDF to attach
     * @param bean the bean to use
     * @return the built MimeMultipart message
     * @throws MessagingException a MessagingException if an error occurred
     * @throws IOException an IOException if an error occurred
     */
    private MimeMultipart buildContent(String pdf, B bean) throws MessagingException, IOException {
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        I18nString emailMessage = new I18nString("label.email.message");
        emailMessage.setArguments(new Object[] {bean.getId()});
        messageBodyPart.setText(emailMessage.getTranslation());
        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        attachmentBodyPart.attachFile(pdf);
        MimeMultipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        multipart.addBodyPart(attachmentBodyPart);
        return multipart;
    }
    
    /**
     * Builds a session from the bean and the preferences.

     * @param preferences the preferences to use
     * @param bean the bean to use
     * @return the built session
     */
    private Session buildSession(Preferences preferences, B bean) {
        Properties properties = new Properties();
        String from = bean.getSociety().getContactInformation().getEmail();
        if (from == null) {
            throw new IllegalArgumentException("The sender email cannot be blank!");
        }
        properties.setProperty("mail.from", from);
        properties.setProperty("mail.smtp.host",
                String.valueOf(preferences.get(JsamsConstants.EMAIL_SMTP, "")));
        String port = String.valueOf(preferences.get(JsamsConstants.EMAIL_PORT, ""));
        properties.setProperty("mail.smtp.port", port);
        String user = String.valueOf(preferences.get(JsamsConstants.EMAIL_USER, ""));
        properties.setProperty("mail.smtp.user", user);
        String pass = String.valueOf(preferences.get(JsamsConstants.EMAIL_PASS, ""));
        properties.setProperty("mail.smtp.password", pass);
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.socketFactory.port", port);
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        return Session.getDefaultInstance(properties, new SmtpAuthenticator(user, pass, true));
    }

}
