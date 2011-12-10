package be.jsams.common.bean.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.stream.FileImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.swing.component.AbstractJsamsFrame;
import be.jsams.client.swing.component.JsamsButton;
import be.jsams.client.swing.component.JsamsLabel;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.common.bean.model.ContactInformationBean;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Customized view for {@link ContactInformationBean}.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::   $Author$
 */
public class ContactInformationBeanView extends AbstractBeanView<ContactInformationBean> implements Editable<JPanel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 5063759475826319119L;

    /**
     * Constructor
     * 
     * @param bean the {@link ContactInformationBean}
     */
    public ContactInformationBeanView(ContactInformationBean bean) {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    public JPanel createEditView() {
        ContactInformationBean bean = getBean();
        ViewFactory<ContactInformationBean> viewFactory = getViewFactory();
        JsamsTextField textFieldPhone = viewFactory.createBindingTextComponent(bean,
                ContactInformationBean.PHONE_PROPERTY, true, false);
        JsamsTextField textFieldFax = viewFactory.createBindingTextComponent(bean, ContactInformationBean.FAX_PROPERTY,
                false, false);
        JsamsTextField textFieldMobile = viewFactory.createBindingTextComponent(bean,
                ContactInformationBean.MOBILE_PROPERTY, false, false);
        JsamsTextField textFieldEmail = viewFactory.createBindingTextComponent(bean,
                ContactInformationBean.EMAIL_PROPERTY, false, false);
        JsamsTextField textFieldWebsite = viewFactory.createBindingTextComponent(bean,
                ContactInformationBean.WEBSITE_PROPERTY, false, false);
        final JsamsLabel labelLogo = new JsamsLabel();
        byte[] logo = bean.getLogo();
        if (logo != null && logo.length != 0) {
            File file = buildImageFromByteArray(logo);
            ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());
            labelLogo.setIcon(imageIcon);
        }
        JsamsButton buttonBrowseLogo = new JsamsButton(JsamsI18nResource.BUTTON_BROWSE_LOGO);
        buttonBrowseLogo.addActionListener(new ActionListener() {
            
            /**
             * {@inheritDoc}
             */
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser chooser = new JFileChooser();
                int returnValue = chooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());
                    labelLogo.setIcon(imageIcon);
                    byte[] bytes = readImage2ByteArray(file);
                    getBean().setLogo(bytes);
                }
            }

            /**
             * Retrieves file bytes from file.
             * 
             * @param file the file to use
             * @return the array of bytes
             */
            private byte[] readImage2ByteArray(File file) {
                FileImageInputStream input = null;
                ByteArrayOutputStream output = null;
                byte[] buf = null;
                byte[] bytes = null;
                try {
                    input = new FileImageInputStream(file);
                    output = new ByteArrayOutputStream();
                    buf = new byte[512];

                    int numBytesRead = 0;
                    while ((numBytesRead = input.read(buf)) != -1) {
                        output.write(buf, 0, numBytesRead);
                    }

                    bytes = output.toByteArray();
                    output.close();
                    input.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return bytes;
            }
        });

        FormLayout layout = new FormLayout(
                "right:p, 3dlu, 70dlu, 3dlu, right:p, 3dlu, 70dlu, 3dlu, right:p, 3dlu, 70dlu", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        final int maxColumnSpan = 5;
        // builder.setDefaultDialogBorder();
        builder.appendSeparator(JsamsI18nLabelResource.LABEL_CONTACT_INFORMATIONS.getTranslation());
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PHONE.getKey(), textFieldPhone);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_FAX.getKey(), textFieldFax);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_MOBILE.getKey(), textFieldMobile);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_EMAIL.getKey(), textFieldEmail);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_WEBSITE.getKey(), textFieldWebsite, maxColumnSpan);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_LOGO.getKey(), labelLogo, buttonBrowseLogo);
        return builder.getPanel();
    }
    

    private File buildImageFromByteArray(byte[] bytes) {
        FileOutputStream fos = null;
        byte[] buf = null;
        int read = 0;
        ByteArrayInputStream input = null;
        File fileOut = null;
        try {
            fileOut = File.createTempFile("tmp", ".png");
            fos = new FileOutputStream(fileOut);
            buf = new byte[512];

            input = new ByteArrayInputStream(bytes);
            while ((read = input.read(buf)) != -1) {
                fos.write(buf, 0, read);
            }

            fos.flush();
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileOut;
    }
}
