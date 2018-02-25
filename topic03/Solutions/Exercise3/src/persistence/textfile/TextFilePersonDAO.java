package persistence.textfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Person;
import persistence.PersistenceException;
import persistence.PersonDAO;

public class TextFilePersonDAO implements PersonDAO {
	private File file;

	public TextFilePersonDAO(File file) {
		this.file = file;
	}

	/** 
	 * Stores the given list of Person objects to the text file of this TextFilePersonDAO.
	 * @param persons The list of persons to store.
	 * @throws PersistenceException in case there is a problem with the file (cannot be opened, etc.)
	 */
	@Override
	public void save(List<Person> persons) throws PersistenceException {
		try (PrintWriter out = new PrintWriter(new FileWriter(file))) {
			for (Person p : persons)
				out.println(p.toString());
		} catch (IOException e) {
			throw new PersistenceException(e);
		}
	}

	/** 
	 * Loads a list of Person objects from the text file of this TextFilePersonDAO.
	 * @returns The list of persons just loaded.
	 * @throws PersistenceException in case there is a problem with the file (cannot be opened, etc.)
	 * @throws TextFileParseException in case a problem occurs while parsing the text file.
	 */
	@Override
	public List<Person> load() throws PersistenceException, TextFileParseException {
		ArrayList<Person> persons = new ArrayList<>();
		try (Scanner in = new Scanner(new FileReader(file))) {
			parseFile(persons, in);
		} catch (FileNotFoundException e) {
			throw new PersistenceException(e);
		}
		return persons;
	}

	private void parseFile(ArrayList<Person> persons, Scanner in) throws TextFileParseException {
		int lineNb = 0;
		while (in.hasNext()) {                              // prevent NoSuchElementException in nextLine()
			String line = in.nextLine();   
			lineNb++;
			if (!line.isEmpty()) {			                // do basic validation
				try {
					Person person = new Person(line);
					persons.add(person);
				} catch (NullPointerException | IllegalArgumentException | DateTimeException e) {
					throw new TextFileParseException(file.getAbsolutePath(), lineNb, line, e);
				}
			}
		}
	}
}
