//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2018.03.11 um 05:49:21 PM CET 
//


package https.github_com.bti7055_objectorientedprogramming.fs18_documents.topic04;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java-Klasse für PersonType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="PersonType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Firstname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Birthdate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Maritalstatus" type="{https://github.com/BTI7055-ObjectOrientedProgramming/FS18-Documents/topic04}MaritalstatusType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PersonType", propOrder = {
    "name",
    "firstname",
    "birthdate",
    "maritalstatus"
})
public class PersonType {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Firstname", required = true)
    protected String firstname;
    @XmlElement(name = "Birthdate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar birthdate;
    @XmlElement(name = "Maritalstatus", required = true)
    @XmlSchemaType(name = "string")
    protected MaritalstatusType maritalstatus;

    /**
     * Ruft den Wert der name-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Legt den Wert der name-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Ruft den Wert der firstname-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Legt den Wert der firstname-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstname(String value) {
        this.firstname = value;
    }

    /**
     * Ruft den Wert der birthdate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBirthdate() {
        return birthdate;
    }

    /**
     * Legt den Wert der birthdate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBirthdate(XMLGregorianCalendar value) {
        this.birthdate = value;
    }

    /**
     * Ruft den Wert der maritalstatus-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link MaritalstatusType }
     *     
     */
    public MaritalstatusType getMaritalstatus() {
        return maritalstatus;
    }

    /**
     * Legt den Wert der maritalstatus-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link MaritalstatusType }
     *     
     */
    public void setMaritalstatus(MaritalstatusType value) {
        this.maritalstatus = value;
    }

}
