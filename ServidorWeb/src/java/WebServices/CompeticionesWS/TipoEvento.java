
package WebServices.CompeticionesWS;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tipoEvento.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="tipoEvento">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Gol"/>
 *     &lt;enumeration value="Tarjeta"/>
 *     &lt;enumeration value="Sustitucion"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "tipoEvento")
@XmlEnum
public enum TipoEvento {

    @XmlEnumValue("Gol")
    GOL("Gol"),
    @XmlEnumValue("Tarjeta")
    TARJETA("Tarjeta"),
    @XmlEnumValue("Sustitucion")
    SUSTITUCION("Sustitucion");
    private final String value;

    TipoEvento(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TipoEvento fromValue(String v) {
        for (TipoEvento c: TipoEvento.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
