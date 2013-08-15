
package WebServices.UsuarioWS;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tipoPosicion.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="tipoPosicion">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Delantero"/>
 *     &lt;enumeration value="Volante"/>
 *     &lt;enumeration value="Golero"/>
 *     &lt;enumeration value="Defensa"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "tipoPosicion")
@XmlEnum
public enum TipoPosicion {

    @XmlEnumValue("Delantero")
    DELANTERO("Delantero"),
    @XmlEnumValue("Volante")
    VOLANTE("Volante"),
    @XmlEnumValue("Golero")
    GOLERO("Golero"),
    @XmlEnumValue("Defensa")
    DEFENSA("Defensa");
    private final String value;

    TipoPosicion(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TipoPosicion fromValue(String v) {
        for (TipoPosicion c: TipoPosicion.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
