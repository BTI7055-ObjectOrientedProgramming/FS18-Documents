package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Person;

public class TextFilePersonDAO implements PersonDAO {
	private File file;

	public TextFilePersonDAO(File file) {
		this.file = file;
	}

	@Override
	public void save(List<Person> persons) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(new FileWriter(file));
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
			in = new Scanner(new FileReader(file));
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
