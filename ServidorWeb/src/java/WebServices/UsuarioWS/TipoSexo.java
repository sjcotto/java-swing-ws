
package WebServices.UsuarioWS;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tipoSexo.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="tipoSexo">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Hombre"/>
 *     &lt;enumeration value="Mujer"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "tipoSexo")
@XmlEnum
public enum TipoSexo {

    @XmlEnumValue("Hombre")
    HOMBRE("Hombre"),
    @XmlEnumValue("Mujer")
    MUJER("Mujer");
    private final String value;

    TipoSexo(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TipoSexo fromValue(String v) {
        for (TipoSexo c: TipoSexo.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
