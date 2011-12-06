package be.jsams.server.service.pdf;

/**
 * PDF service interface.
 * 
 * @param <M> the class model type
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface PdfService<M> {

    /**
     * Generates a PDF view from a class type and an object.
     * 
     * @param object the object to generate in Pdf
     */
    void generatePdf(M object);

}
