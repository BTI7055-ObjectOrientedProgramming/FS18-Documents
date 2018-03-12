import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Persons", namespace="https://github.com/BTI7055-ObjectOrientedProgramming/FS18-Documents/topic04")
public class Persons {
	@XmlAttribute
	private String version = "FS2018";
	
	@XmlElement(name="Person")
	private List<Person> persons = new ArrayList<>();
	
	public void addPerson(Person person) {
		persons.add(person);
	}

	List<Person> getPersons() {
		return persons;
	}
}
