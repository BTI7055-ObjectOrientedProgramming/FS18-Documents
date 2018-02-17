package io;

import java.util.List;

import model.Person;

public interface PersonDAO {
	void save(List<Person> persons);
	List<Person> load();
}
