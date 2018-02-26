package persistence.textfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
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
//			out = new PrintWriter(new FileWriter(path));  // Use platform standard encoding -> dangerous!
			out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(path), Charset.forName("UTF-8")));
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
//			in = new Scanner(new FileReader(path));    // Use platform standard encoding -> dangerous!
			in = new Scanner(new File(path), "UTF-8");
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
