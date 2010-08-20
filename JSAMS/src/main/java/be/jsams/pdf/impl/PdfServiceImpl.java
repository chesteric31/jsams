package be.jsams.pdf.impl;

import be.jsams.pdf.PdfService;

import com.lowagie.text.Document;
import com.lowagie.text.Header;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class PdfServiceImpl implements PdfService {

	public PdfPCell createCell(final Phrase value) {
		PdfPCell cell = new PdfPCell(value);
		return cell;
	}

	public Document createDocument() {
		Document document = new Document(PageSize.A4);
		return document;
	}

	public Header createHeader() {
		// TODO Auto-generated method stub
		return null;
	}

	public PdfPTable createTable(final int columnNumber) {
		PdfPTable table = new PdfPTable(columnNumber);
		return table;
	}

	public void populateTable() {
		// TODO Auto-generated method stub
		
	}

}
