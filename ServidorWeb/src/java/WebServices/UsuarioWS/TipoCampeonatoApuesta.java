
package WebServices.UsuarioWS;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tipoCampeonatoApuesta.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="tipoCampeonatoApuesta">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Partido"/>
 *     &lt;enumeration value="Equipo"/>
 *     &lt;enumeration value="Goleador"/>
 *     &lt;enumeration value="ResExacto"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "tipoCampeonatoApuesta")
@XmlEnum
public enum TipoCampeonatoApuesta {

    @XmlEnumValue("Partido")
    PARTIDO("Partido"),
    @XmlEnumValue("Equipo")
    EQUIPO("Equipo"),
    @XmlEnumValue("Goleador")
    GOLEADOR("Goleador"),
    @XmlEnumValue("ResExacto")
    RES_EXACTO("ResExacto");
    private final String value;

    TipoCampeonatoApuesta(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TipoCampeonatoApuesta fromValue(String v) {
        for (TipoCampeonatoApuesta c: TipoCampeonatoApuesta.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
