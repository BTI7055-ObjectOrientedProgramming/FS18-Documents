

import java.time.LocalDate;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/*
 *  There are at least three ways to annotate this class:
 *  A) Use @XmlElement annotations with each field. In this case no getters/setters are required (i.e. the fields are not
 *     properties in the sense of the Java Beans convention). The @XmlType annotation helps to specify the order of the 
 *     elements.
 *  B) Just @XmlType annotation, no @XmlAccessorType annotation and no @XmlElement annotations. In this case the fields
 *     must be Java Beans properties (i.e. getters/setters required). Additional annotations for a particular property
 *     must be placed in front of the corresponding getter/setter.
 *  C) Just @XmlType annotation and @XmlAccessorType(XmlAccessType.FIELD). In this case no setters/getters are required.
 *     Annotations for a particular field can be placed in front of the field declaration.
 *     
 *  The code below illustrates way A). Way B) and C) are commented out. Reason: We want use specific tag names for the
 *  elements (field lastname --> tag Name, etc.)
 */

@XmlType(propOrder= {"lastname", "firstname", "birthdate", "maritalStatus"})
//@XmlAccessorType(XmlAccessType.FIELD)
public final class Person {
	@XmlElement(name="Firstname", required=true)
	private String firstname;
	@XmlElement(name="Name", required=true)
	private String lastname;
	@XmlElement(name="Birthdate", required=true)
	@XmlSchemaType(name="date")
	@XmlJavaTypeAdapter(value=LocalDate2XsdDateAdapter.class)
	private LocalDate birthdate;
	@XmlElement(name="Maritalstatus", required=true)
	private MaritalStatus maritalStatus;
	
	public Person() {}

	public Person(String firstname, String lastname, LocalDate birthdate, MaritalStatus maritalStatus) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthdate = birthdate;
		this.maritalStatus = maritalStatus;
	}
	
//	public String getFirstname() {
//		return firstname;
//	}
//
//	public void setFirstname(String firstname) {
//		this.firstname = firstname;
//	}
//
//	public String getLastname() {
//		return lastname;
//	}
//
//	public void setLastname(String lastname) {
//		this.lastname = lastname;
//	}
//
//	@XmlSchemaType(name="date")
//	@XmlJavaTypeAdapter(value=LocalDate2XsdDateAdapter.class)
//	public LocalDate getBirthdate() {
//		return birthdate;
//	}
//
//	public void setBirthdate(LocalDate birthdate) {
//		this.birthdate = birthdate;
//	}
//
//	public MaritalStatus getMaritalStatus() {
//		return maritalStatus;
//	}
//
//	public void setMaritalStatus(MaritalStatus maritalStatus) {
//		this.maritalStatus = maritalStatus;
//	}

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
