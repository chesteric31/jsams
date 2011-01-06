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

	Document createDocument();

	Header createHeader();

	PdfPTable createTable(final int columnNumber);

	PdfPCell createCell(final Phrase value);

	void populateTable();

}
