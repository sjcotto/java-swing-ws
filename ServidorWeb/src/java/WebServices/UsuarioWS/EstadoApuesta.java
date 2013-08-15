
package WebServices.UsuarioWS;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for estadoApuesta.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="estadoApuesta">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Gano"/>
 *     &lt;enumeration value="Perdio"/>
 *     &lt;enumeration value="Pendiente"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "estadoApuesta")
@XmlEnum
public enum EstadoApuesta {

    @XmlEnumValue("Gano")
    GANO("Gano"),
    @XmlEnumValue("Perdio")
    PERDIO("Perdio"),
    @XmlEnumValue("Pendiente")
    PENDIENTE("Pendiente");
    private final String value;

    EstadoApuesta(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EstadoApuesta fromValue(String v) {
        for (EstadoApuesta c: EstadoApuesta.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
