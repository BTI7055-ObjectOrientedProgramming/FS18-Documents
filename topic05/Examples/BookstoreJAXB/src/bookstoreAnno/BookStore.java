package bookstoreAnno;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "store", namespace = "ch.bfh.prog2.bookstore")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)	// public getter/setter pair, public field, all other only if annotated
//@XmlAccessorType(XmlAccessType.PROPERTY)		// every getter/setter pair, fields only if annotated
//@XmlAccessorType(XmlAccessType.FIELD)			// every field, getter/setter pairs only if annotated
//@XmlAccessorType(XmlAccessType.NONE)			// none of fields and getter/setter pairs unless annotated
// see also http://blog.bdoughan.com/2011/06/using-jaxbs-xmlaccessortype-to.html
public final class BookStore {

//	@XmlElementWrapper
//	@XmlElement(name="book")
	private ArrayList<Book> bookList;	// field, has getter only
//	@XmlAttribute
	private String name;				// property, has getter/setter, see below
//	@XmlElement
	private Address address;			// property, has getter/setter, see below
	
	public BookStore() {
		bookList = new ArrayList<Book>();
		name="unknown";
		address = new Address();
	}

	public BookStore(String aName, String aCity) {
		bookList = new ArrayList<Book>();
		name=aName;
		address = new Address("",aCity);
	}

	public ArrayList<Book> getBooksList() {	// public getter for field bookList
		return bookList;
	}

	String getName() {						// packet private getter for field name --> property
		return name;
	}

	void setName(String name) {				// packet private setter for field name --> property
		this.name = name;
	}

	public Address getAddress() {			// public getter for field address --> property
		return address;
	}

	public void setAddress(Address a) {		// public setter for field address --> property
		this.address = a;
	}

//	@XmlElement(name="nmbOfBooks")
	public int getNmbOfBooks() {			// getter, no field, no setter
		return bookList.size();
	}

	public void add(Book book) {
		bookList.add(book);
	}
}
