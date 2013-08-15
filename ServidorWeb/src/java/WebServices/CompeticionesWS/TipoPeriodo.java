
package WebServices.CompeticionesWS;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tipoPeriodo.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="tipoPeriodo">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="PrimerTiempo"/>
 *     &lt;enumeration value="SegundoTiempo"/>
 *     &lt;enumeration value="PrimerAlargue"/>
 *     &lt;enumeration value="SegundoAlargue"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "tipoPeriodo")
@XmlEnum
public enum TipoPeriodo {

    @XmlEnumValue("PrimerTiempo")
    PRIMER_TIEMPO("PrimerTiempo"),
    @XmlEnumValue("SegundoTiempo")
    SEGUNDO_TIEMPO("SegundoTiempo"),
    @XmlEnumValue("PrimerAlargue")
    PRIMER_ALARGUE("PrimerAlargue"),
    @XmlEnumValue("SegundoAlargue")
    SEGUNDO_ALARGUE("SegundoAlargue");
    private final String value;

    TipoPeriodo(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TipoPeriodo fromValue(String v) {
        for (TipoPeriodo c: TipoPeriodo.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
