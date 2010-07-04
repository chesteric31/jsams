package be.jsams.pdf.impl;

import be.jsams.pdf.PdfService;

import com.lowagie.text.Document;
import com.lowagie.text.Header;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class PdfServiceImpl implements PdfService {

	@Override
	public PdfPCell createCell(final Phrase value) {
		PdfPCell cell = new PdfPCell(value);
		return cell;
	}

	@Override
	public Document createDocument() {
		Document document = new Document(PageSize.A4);
		return document;
	}

	@Override
	public Header createHeader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PdfPTable createTable(final int columnNumber) {
		PdfPTable table = new PdfPTable(columnNumber);
		return table;
	}

	@Override
	public void populateTable() {
		// TODO Auto-generated method stub
		
	}

}
