package be.jsams;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import be.jsams.client.i18n.I18nManager;

import com.lowagie.text.DocumentException;

@Deprecated
public class Test {

	public static void main(String[] args) throws ParseException,
			DocumentException, IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		// PersonService personService = (PersonService) context
		// .getBean("personService");
		// Person newPerson = new Person();
		// newPerson.setName("BAUER");
		// Timestamp timestamp = new Timestamp(new Date().getTime());
		// newPerson.setCreationDate(timestamp);
		// personService.add(newPerson);
		// List<Person> persons = personService.findByName("BAUER");
		//
		// for (Person person : persons) {
		// personService.remove(person);
		// }

		// - Chargement et compilation du rapport
		// try {
		// JasperDesign jasperDesign = JRXmlLoader.load("reports\\BTS.jrxml");
		// JasperReport jasperReport = JasperCompileManager
		// .compileReport(jasperDesign);
		//
		// // - Paramètres à envoyer au rapport
		// Map<String, String> parameters = new HashMap<String, String>();
		// parameters.put("Field", "Info");
		//
		// // - Execution du rapport
		// JasperPrint jasperPrint = JasperFillManager.fillReport(
		// jasperReport, parameters);
		// // - Création du rapport au format PDF
		// JasperExportManager.exportReportToPdfFile(jasperPrint,
		// "reports\\BTS.pdf");
		// } catch (JRException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// PdfService pdf = new PdfServiceImpl();
		// Document document = pdf.createDocument();
		// PdfWriter.getInstance(document, new FileOutputStream("file.pdf"));
		// document.open();
		// PdfPTable table = pdf.createTable(2);
		// PdfPCell cell = pdf.createCell(new Phrase("value"));
		// PdfPCell cell2 = pdf.createCell(new Phrase("value2"));
		// table.addCell(cell);
		// table.addCell(cell2);
		// document.add(table);
		// document.close();
		//
		// Runtime.getRuntime().exec(
		// "rundll32 url.dll,FileProtocolHandler file.pdf");

		I18nManager manager = (I18nManager) context.getBean("i18nManager");
		String title = manager.translate("application.title");
		System.out.println(title);

	}
}
