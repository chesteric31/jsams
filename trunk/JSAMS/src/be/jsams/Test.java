package be.jsams;

import java.text.ParseException;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import be.jsams.server.model.Person;
import be.jsams.server.service.PersonService;

public class Test {

	public static void main(String[] args) throws ParseException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"ApplicationContext.xml");
		PersonService personService = (PersonService) context
				.getBean("personService");
		// Person newPerson = new Person();
		// newPerson.setName("BAUER");
		// Timestamp timestamp = new Timestamp(new Date().getTime());
		// newPerson.setCreationDate(timestamp);
		// personService.add(newPerson);
		List<Person> persons = personService.findByName("BAUER");

		for (Person person : persons) {
			personService.remove(person);
		}

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
