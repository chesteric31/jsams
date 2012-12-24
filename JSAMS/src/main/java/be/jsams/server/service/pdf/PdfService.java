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
     * Generates a PDF view from an object.
     * 
     * @param object the object to generate in PDF
     * @param viewReport true if we will to see the report, false otherwise
     * @return the filename where the PDF was created
     */
    String generatePdf(M object, boolean viewReport);

}
