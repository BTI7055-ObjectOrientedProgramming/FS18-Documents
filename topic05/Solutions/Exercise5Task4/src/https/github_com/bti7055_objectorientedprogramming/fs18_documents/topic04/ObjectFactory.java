//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2018.03.11 um 05:49:21 PM CET 
//


package https.github_com.bti7055_objectorientedprogramming.fs18_documents.topic04;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the https.github_com.bti7055_objectorientedprogramming.fs18_documents.topic04 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Persons_QNAME = new QName("https://github.com/BTI7055-ObjectOrientedProgramming/FS18-Documents/topic04", "Persons");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: https.github_com.bti7055_objectorientedprogramming.fs18_documents.topic04
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PersonsType }
     * 
     */
    public PersonsType createPersonsType() {
        return new PersonsType();
    }

    /**
     * Create an instance of {@link PersonType }
     * 
     */
    public PersonType createPersonType() {
        return new PersonType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersonsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://github.com/BTI7055-ObjectOrientedProgramming/FS18-Documents/topic04", name = "Persons")
    public JAXBElement<PersonsType> createPersons(PersonsType value) {
        return new JAXBElement<PersonsType>(_Persons_QNAME, PersonsType.class, null, value);
    }

}
