package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public final class Person implements Serializable {
	private static final long serialVersionUID = -1563771350254661623L;
	
	private String firstname;
	private String lastname;
	private LocalDate birthdate;
	private MaritalStatus maritalStatus;

	public Person(String firstname, String lastname, LocalDate birthdate, MaritalStatus maritalStatus) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthdate = birthdate;
		this.maritalStatus = maritalStatus;
	}
	
	public Person(String line) {
		String[] values = line.split(",?\\s");
		firstname = values[0];
		lastname = values[1];
		birthdate = LocalDate.parse(values[2]);
		maritalStatus = MaritalStatus.valueOf(values[3]);
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	@Override
	public boolean equals(Object other){
		if (this == other) return true;
		if (other == null) return false;
		if (this.getClass() != other.getClass()) return false;
		Person otherPerson = (Person)other;
		return
			Objects.equals(firstname, otherPerson.firstname) &&
			Objects.equals(lastname, otherPerson.lastname) &&
			Objects.equals(birthdate, otherPerson.birthdate) &&
			Objects.equals(maritalStatus, otherPerson.maritalStatus);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(firstname, lastname, birthdate, maritalStatus);
	}
	
	@Override
	public String toString() {
		return firstname + " " + lastname + ", " + birthdate + ", " + maritalStatus;
	}
}
