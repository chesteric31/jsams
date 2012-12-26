package be.jsams.server.service.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Specific SMTP authenticator.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class SmtpAuthenticator extends Authenticator {

    private String username;
    private String password;
    private boolean needAuth;

    /**
     * Constructor.
     * 
     * @param username the username for the authentication
     * @param password the password for the authentication
     * @param needAuth true if need authentication, false otherwise
     */
    public SmtpAuthenticator(String username, String password, boolean needAuth) {
        this.username = username;
        this.password = password;
        this.needAuth = needAuth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        if (needAuth) {
            return new PasswordAuthentication(username, password);
        } else {
            return null;
        }
    }
}
