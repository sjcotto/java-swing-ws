
package WebServices.CompeticionesWS;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tipoDividendo.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="tipoDividendo">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Local"/>
 *     &lt;enumeration value="Visitante"/>
 *     &lt;enumeration value="Empate"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "tipoDividendo")
@XmlEnum
public enum TipoDividendo {

    @XmlEnumValue("Local")
    LOCAL("Local"),
    @XmlEnumValue("Visitante")
    VISITANTE("Visitante"),
    @XmlEnumValue("Empate")
    EMPATE("Empate");
    private final String value;

    TipoDividendo(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TipoDividendo fromValue(String v) {
        for (TipoDividendo c: TipoDividendo.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
