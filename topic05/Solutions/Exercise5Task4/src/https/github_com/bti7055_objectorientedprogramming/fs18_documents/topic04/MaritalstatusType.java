//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2018.03.11 um 05:49:21 PM CET 
//


package https.github_com.bti7055_objectorientedprogramming.fs18_documents.topic04;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für MaritalstatusType.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * <p>
 * <pre>
 * &lt;simpleType name="MaritalstatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SINGLE"/>
 *     &lt;enumeration value="MARRIED"/>
 *     &lt;enumeration value="CIVIL_PARTNERSHIP"/>
 *     &lt;enumeration value="DIVORCED"/>
 *     &lt;enumeration value="WIDOWED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MaritalstatusType")
@XmlEnum
public enum MaritalstatusType {

    SINGLE,
    MARRIED,
    CIVIL_PARTNERSHIP,
    DIVORCED,
    WIDOWED;

    public String value() {
        return name();
    }

    public static MaritalstatusType fromValue(String v) {
        return valueOf(v);
    }

}
