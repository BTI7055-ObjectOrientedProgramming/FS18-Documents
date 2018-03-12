package universitySolution;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public final class UniMain {

	private UniMain() {
	}

	private static final String UNIVERSITY_XML = "university.xml";

	public static void main(String[] args) throws JAXBException, IOException {

		// create some persons
		Student student = new Student("Max", "max@bfh.ch", "B");
		Student student2 = new Student("Tom", "tom@bfh.ch", "c");
		Professor prof = new Professor("Mr. X", "prof.x@bfh.ch", "IT Security");
		Employee empl = new Employee("Erni Schmidt", "erni.schmidt@bfh.ch",	"HR");

		// create uni, assigning staff and students
		University bfh = new University("bfh");

		bfh.addStaff(student);
		bfh.addStaff(prof);
		bfh.addStaff(empl);

		bfh.addStudent(student);
		bfh.addStudent(student2);

		// create JAXB context and instantiate marshaller
		JAXBContext context = JAXBContext.newInstance(University.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		// Write to System.out
		m.marshal(bfh, System.out);

		// Write to File
		m.marshal(bfh, new File(UNIVERSITY_XML));

		// get variables from our xml file, created before
		System.out.println();
		System.out.println("Output from our XML File: ");
		Unmarshaller um = context.createUnmarshaller();

		University uni = (University) um.unmarshal(new FileReader(
				UNIVERSITY_XML));
		List<Person> staff = uni.getStaff();
		if (staff != null) {
			System.out.println("STAFF");
			for (Person p : staff)
				System.out.println("Person: " + p.getName() + " ; "
						+ p.getEmail());
		}
		List<Student> students = uni.getStudents();
		if (students != null) {
			System.out.println("STUDENTSs");
			for (Student s : students)
				System.out.println("Student: " + s.getName() + " ; "
						+ s.getEmail() + " ; " + s.getGrade());
		}
	}
}
