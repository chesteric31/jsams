package be.jsams;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import be.jsams.server.model.Person;
import be.jsams.server.service.PersonService;

public class Test {

	public static void main(String[] args) throws ParseException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		Person newPerson = new Person();
		newPerson.setName("BAUER");
		Timestamp timestamp = new Timestamp(new Date().getTime());
		newPerson.setCreationDate(timestamp);
		PersonService personService = (PersonService) context.getBean("personService");
		personService.persist(newPerson);
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

	}
}
