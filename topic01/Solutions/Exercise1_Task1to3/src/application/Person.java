package application;

import java.time.LocalDate;
import java.util.Objects;

public final class Person {
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
		return lastname + " " + firstname + ", " + birthdate + ", " + maritalStatus;
	}
}
