package persistence.textfile;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Person;
import persistence.PersonDAO;

public class TextFilePersonDAO implements PersonDAO {
	private String path;

	public TextFilePersonDAO(String path) {
		this.path = path;
	}

	@Override
	public void save(List<Person> persons) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(new FileWriter(path));
			for (Person p : persons)
				out.println(p.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				out.close();
		}
	}

	@Override
	public List<Person> load() {
		ArrayList<Person> persons = new ArrayList<>();
		Scanner in = null;
		try {
			in = new Scanner(new FileReader(path));
			while (in.hasNext()) {
				String line = in.nextLine();
				Person person = new Person(line);
				persons.add(person);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (in != null) 
				in.close();
		}
		return persons;
	}
}
