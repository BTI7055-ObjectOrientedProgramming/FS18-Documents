import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Person;
import persistence.PersistenceException;
import persistence.textfile.TextFilePersonDAO;

public class TestTextFilePersonDAO {
	private List<Person> persons = new ArrayList<Person>();
	
	@Before
	public void setup() {
		persons.add(new Person("Donald Duck, 1920-04-13, SINGLE"));
	}

	@Test(expected = PersistenceException.class)
	public void testSaveWithIllegalFileName() throws PersistenceException {
		TextFilePersonDAO dao = new TextFilePersonDAO(new File(""));
		dao.save(persons);
	}

	@Test(expected = PersistenceException.class)
	public void testLoadWithIllegalFileName() throws PersistenceException {
		TextFilePersonDAO dao = new TextFilePersonDAO(new File(""));
		dao.load();
	}

}
