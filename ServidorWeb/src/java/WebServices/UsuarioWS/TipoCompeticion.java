
package WebServices.UsuarioWS;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tipoCompeticion.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="tipoCompeticion">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Copa"/>
 *     &lt;enumeration value="Liga"/>
 *     &lt;enumeration value="PartidoIndividual"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "tipoCompeticion")
@XmlEnum
public enum TipoCompeticion {

    @XmlEnumValue("Copa")
    COPA("Copa"),
    @XmlEnumValue("Liga")
    LIGA("Liga"),
    @XmlEnumValue("PartidoIndividual")
    PARTIDO_INDIVIDUAL("PartidoIndividual");
    private final String value;

    TipoCompeticion(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TipoCompeticion fromValue(String v) {
        for (TipoCompeticion c: TipoCompeticion.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
