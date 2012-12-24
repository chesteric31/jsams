package be.jsams.client.model.panel.sale;

import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.prefs.Preferences;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.ListSelectionModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.context.ApplicationContext;
import be.jsams.client.desktop.Desktop;
import be.jsams.client.i18n.I18nResource;
import be.jsams.client.model.dialog.sale.EditBillDialog;
import be.jsams.client.model.table.sale.BillTableModel;
import be.jsams.client.validator.search.sale.SearchBillValidator;
import be.jsams.common.bean.model.PaymentModeBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.common.bean.model.sale.BillMediator;
import be.jsams.common.util.JsamsConstants;
import be.jsams.server.service.pdf.impl.PdfBillServiceImpl;
import be.jsams.server.service.sale.BillService;

/**
 * {@link AbstractSaleSearchPanel} for {@link BillBean} objects.
 * 
 * @param <L> a customized {@link MouseListener}
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SearchBillPanel<L extends MouseListener> extends
        AbstractSaleSearchPanel<BillBean, L, BillService, SearchBillValidator, BillTableModel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 4113451180402839180L;

    private static final Log LOGGER = LogFactory.getLog(SearchBillPanel.class);

    private final boolean debug = LOGGER.isDebugEnabled();

    /**
     * Constructor.
     * 
     * @param bean the {@link BillBean}
     * @param listener the {@link MouseListener}
     * @param service the {@link BillService}
     * @param validator the {@link SearchBillValidator}
     * @param billTableModel the {@link BillTableModel}
     * @param showButtons a boolean that indicates if we have to display the
     *            buttons to manage the content: add, remove and modify
     * @param selectionMode the selection mode to use
     */
    public SearchBillPanel(BillBean bean, L listener, BillService service, SearchBillValidator validator,
            BillTableModel billTableModel, boolean showButtons, int selectionMode) {
        super(bean, listener, service, validator, billTableModel, showButtons, selectionMode);
    }

    /**
     * Constructor.
     * 
     * @param bean the {@link BillBean}
     * @param listener the {@link MouseListener}
     * @param service the {@link BillService}
     * @param validator the {@link SearchBillValidator}
     * @param billTableModel the {@link BillTableModel}
     */
    public SearchBillPanel(BillBean bean, L listener, BillService service, SearchBillValidator validator,
            BillTableModel billTableModel) {
        super(bean, listener, service, validator, billTableModel, true, ListSelectionModel.SINGLE_SELECTION);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performButtonAdd() {
        SocietyBean currentSociety = Desktop.getInstance().getCurrentSociety();
        CustomerBean customer = ApplicationContext.getCustomerBeanBuilder().build(null, currentSociety);
        PaymentModeBean mode = ApplicationContext.getPaymentModeBeanBuilder().build();
        BillBean bean = new BillBean(currentSociety, customer, mode);
        BillMediator mediator = new BillMediator();
        mediator.setBillBean(bean);
        bean.setMediator(mediator);
        new EditBillDialog(I18nResource.TITLE_EDIT_BILL, bean);
        updateUI();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performButtonModify() {
        int selectedRow = getResultTable().getSelectedRow();
        if (selectedRow > -1) {
            int selectedRowModel = getResultTable().convertRowIndexToModel(selectedRow);
            BillTableModel model = (BillTableModel) getResultTable().getModel();
            BillBean beanToModify = model.getRow(selectedRowModel);
            BillMediator mediator = new BillMediator();
            mediator.setBillBean(beanToModify);
            beanToModify.setMediator(mediator);
            if (debug) {
                LOGGER.debug("The bill to modify: " + beanToModify);
            }
            new EditBillDialog(I18nResource.TITLE_EDIT_BILL, beanToModify);
            updateUI();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performCancel() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performOk() {
        final BillBean criteria = getModel();
        if (super.prePerformOk(criteria)) {
            List<BillBean> bills = ((BillService) super.getService()).findByCriteria(criteria);
            fillTable(bills);
            if (bills != null && !bills.isEmpty()) {
                for (BillBean bean : bills) {
                    BillMediator mediator = new BillMediator();
                    mediator.setBillBean(bean);
                    bean.setMediator(mediator);
                }
            }
            super.postPerformOk();
        }
    }

    /**
     * Fills the data table.
     * 
     * @param bills the {@link BillBean} list
     */
    private void fillTable(final List<BillBean> bills) {
        BillTableModel model = new BillTableModel(bills);
        super.setTableModel(model);
        getResultTable().repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String performButtonPdf(boolean viewReport) {
        String pdf = "";
        int selectedRow = getResultTable().getSelectedRow();
        if (selectedRow > -1) {
            int selectedRowModel = getResultTable().convertRowIndexToModel(selectedRow);
            BillTableModel model = (BillTableModel) getResultTable().getModel();
            BillBean beanToPdf = model.getRow(selectedRowModel);
            PdfBillServiceImpl pdfService = ApplicationContext.getPdfBillService();
            pdf = pdfService.generatePdf(beanToPdf, viewReport);
        }
        return pdf;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performButtonEmail(String pdf) {
        Properties props = new Properties();
        props.setProperty("mail.from", "chesteric31@gmail.com");
        Preferences rootPreferences = Preferences.userRoot();
        Preferences preferences = rootPreferences.node(JsamsConstants.PACKAGE_ROOT_NAME);
        props.setProperty("mail.smtp.host", String.valueOf(preferences.get(JsamsConstants.EMAIL_SMTP, "")));
        props.setProperty("mail.smtp.port", String.valueOf(preferences.get(JsamsConstants.EMAIL_PORT, "")));
        String pass = String.valueOf(preferences.get(JsamsConstants.EMAIL_PASS, ""));
        props.setProperty("mail.smtp.user", String.valueOf(preferences.get(JsamsConstants.EMAIL_USER, "")));
        props.setProperty("mail.smtp.password", pass);
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        Session session = Session.getDefaultInstance(props, new SmtpAuthenticator("chesteric31@gmail.com", pass, true));

        Message message = new MimeMessage(session);
        InternetAddress recipient;
        Transport transport = null;
        try {
            recipient = new InternetAddress("eric.binard@gmail.com");
            message.setRecipient(Message.RecipientType.TO, recipient);
            message.setSubject("Hello JavaMail");

            MimeBodyPart mbp1 = new MimeBodyPart();
            mbp1.setText("JavaMail vous dit bonjour!");
            MimeBodyPart mbp2 = new MimeBodyPart();
            mbp2.attachFile(pdf);
            MimeMultipart mp = new MimeMultipart();
            mp.addBodyPart(mbp1);
            mp.addBodyPart(mbp2);
            message.setContent(mp);
            transport = session.getTransport("smtp");
            transport.connect();
            Transport.send(message);
        } catch (AddressException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } finally {
            try {
                transport.close();
            } catch (MessagingException ex) {
                // TODO Auto-generated catch block
                throw new RuntimeException(ex);
            }
        }
    }

    private class SmtpAuthenticator extends Authenticator {
        private String username;
        private String password;
        private boolean needAuth;

        public SmtpAuthenticator(String username, String password, boolean needAuth) {
            this.username = username;
            this.password = password;
            this.needAuth = needAuth;
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            if (needAuth)
                return new PasswordAuthentication(username, password);
            else
                return null;
        }
    }
}
