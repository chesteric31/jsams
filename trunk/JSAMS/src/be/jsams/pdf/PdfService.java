package be.jsams.pdf;

import com.lowagie.text.Document;
import com.lowagie.text.Header;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public interface PdfService {

	public Document createDocument();

	public Header createHeader();

	public PdfPTable createTable(final int columnNumber);

	public PdfPCell createCell(final Phrase value);

	public void populateTable();

}
