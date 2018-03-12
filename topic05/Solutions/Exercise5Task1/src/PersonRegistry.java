import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class PersonRegistry {
	public static void main(String[] args) throws JAXBException, FileNotFoundException, IOException {
		Persons persons = new Persons();
		Person p1 = new Person("Donald", "Duck", LocalDate.of(1920, 4, 13), MaritalStatus.SINGLE);
		Person p2 = new Person("Ma", "Dalton", LocalDate.of(1958, 1, 1), MaritalStatus.WIDOWED);
		persons.addPerson(p1);
		persons.addPerson(p2);
		
		JAXBContext context = JAXBContext.newInstance(Persons.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty("jaxb.formatted.output", true);
		marshaller.marshal(persons, System.out);
		try (OutputStream out = new FileOutputStream("persons.xml")) {
			marshaller.marshal(persons, out);
		}
		
		Unmarshaller unmarshaller = context.createUnmarshaller();
		try (InputStream in = new FileInputStream("persons.xml")) {
			persons = (Persons) unmarshaller.unmarshal(in);
			System.out.println();
			System.out.println("Persons read from persons.xml");
			System.out.println("=============================");
			persons.getPersons().forEach(person -> System.out.println(person));
		}
	}
}
