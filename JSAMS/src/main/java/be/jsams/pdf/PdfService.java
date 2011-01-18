package be.jsams.pdf;

import com.lowagie.text.Document;
import com.lowagie.text.Header;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

/**
 * PDF service interface.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface PdfService {

    /**
     * Creates the {@link Document}.
     * 
     * @return the created {@link Document}
     */
    Document createDocument();

    /**
     * Creates the {@link Header}.
     * 
     * @return the created {@link Header}
     */
    Header createHeader();

    /**
     * Creates the {@link PdfPTable}.
     * 
     * @param columnNumber
     *            the column number
     * @return the created {@link PdfPTable}
     */
    PdfPTable createTable(final int columnNumber);

    /**
     * Creates the {@link PdfPCell}.
     * 
     * @param value
     *            the {@link Phrase}
     * @return the created {@link PdfPCell}
     */
    PdfPCell createCell(final Phrase value);

    /**
     * Populates the table.
     */
    void populateTable();

}
