
package WebServices.UsuarioWS;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for estadoPaqueteApuestas.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="estadoPaqueteApuestas">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Pendiente"/>
 *     &lt;enumeration value="Ganado"/>
 *     &lt;enumeration value="Perdido"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "estadoPaqueteApuestas")
@XmlEnum
public enum EstadoPaqueteApuestas {

    @XmlEnumValue("Pendiente")
    PENDIENTE("Pendiente"),
    @XmlEnumValue("Ganado")
    GANADO("Ganado"),
    @XmlEnumValue("Perdido")
    PERDIDO("Perdido");
    private final String value;

    EstadoPaqueteApuestas(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EstadoPaqueteApuestas fromValue(String v) {
        for (EstadoPaqueteApuestas c: EstadoPaqueteApuestas.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
