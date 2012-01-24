package be.jsams.common.bean.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.stream.FileImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.swing.component.AbstractJsamsFrame;
import be.jsams.client.swing.component.JsamsButton;
import be.jsams.client.swing.component.JsamsFormattedTextField;
import be.jsams.client.swing.component.JsamsLabel;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.common.bean.model.SocietyBean;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Customized view for {@link SocietyBean}.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SocietyBeanView extends AbstractBeanView<SocietyBean> implements Editable<JPanel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -6050081974816301545L;

    /**
     * Constructor
     * 
     * @param bean the {@link SocietyBean}
     */
    public SocietyBeanView(SocietyBean bean) {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    public JPanel createEditView() {
        SocietyBean bean = getBean();
        ViewFactory<SocietyBean> viewFactory = getViewFactory();
        JsamsTextField textFieldName = viewFactory.createBindingTextComponent(bean, SocietyBean.NAME_PROPERTY, true,
                false);
        JsamsFormattedTextField textFieldCapital = viewFactory.createBindingDecimalComponent(bean,
                SocietyBean.CAPITAL_PROPERTY, true, false);
        JsamsTextField textFieldActivity = viewFactory.createBindingTextComponent(bean, SocietyBean.ACTIVITY_PROPERTY,
                true, false);
        JsamsTextField textFieldResponsible = viewFactory.createBindingTextComponent(bean,
                SocietyBean.RESPONSIBLE_PROPERTY, false, false);
        JsamsTextField textFieldVatNumber = viewFactory.createBindingTextComponent(bean,
                SocietyBean.VAT_NUMBER_PROPERTY, true, false);
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

        FormLayout layout = new FormLayout("right:p, 3dlu, 262dlu", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        builder.setDefaultDialogBorder();
        final int maxColumnSpan = 3;
        builder.appendI15d(JsamsI18nLabelResource.LABEL_NAME.getKey(), textFieldName);
        builder.nextLine();
        builder.append(bean.getAddress().buildView().createEditView(), maxColumnSpan);
        builder.nextLine();
        builder.append(bean.getContactInformation().buildView().createEditView(), maxColumnSpan);
        builder.nextLine();
        builder.appendSeparator(JsamsI18nLabelResource.LABEL_MISC.getTranslation());
        builder.appendI15d(JsamsI18nLabelResource.LABEL_LEGAL_FORM.getKey(), bean.getLegalForm().buildView()
                .createEditView(), 1);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_CAPITAL.getKey(), textFieldCapital);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_ACTIVITY.getKey(), textFieldActivity);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_RESPONSIBLE.getKey(), textFieldResponsible);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_VAT_NUMBER.getKey(), textFieldVatNumber);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_LOGO.getKey(), labelLogo, buttonBrowseLogo);
        return builder.getPanel();
    }

    /**
     * Builds a temporary image file from a byte array input.
     * 
     * @param bytes the byte array input image file
     * @return the generated temporary image file
     */
    private File buildImageFromByteArray(byte[] bytes) {
        FileOutputStream outputStream = null;
        byte[] buffer = null;
        int read = 0;
        ByteArrayInputStream input = null;
        File fileOut = null;
        try {
            fileOut = File.createTempFile("tmp", ".png");
            outputStream = new FileOutputStream(fileOut);
            buffer = new byte[512];

            input = new ByteArrayInputStream(bytes);
            while ((read = input.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return fileOut;
    }

}
