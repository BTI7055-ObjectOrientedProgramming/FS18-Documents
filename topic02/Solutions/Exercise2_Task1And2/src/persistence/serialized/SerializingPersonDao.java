package persistence.serialized;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import model.Person;
import persistence.PersonDAO;

public class SerializingPersonDao implements PersonDAO {
	private String path;

	public SerializingPersonDao(String path) {
		this.path = path;
	}

	@Override
	public void save(List<Person> persons) {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(path));
			out.writeObject(persons);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	@Override
	public List<Person> load() {
		ArrayList<Person> persons = new ArrayList<>();
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(path));
			persons = (ArrayList<Person>)in.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return persons;
	}
}
