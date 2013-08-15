
package WebServices.CompeticionesWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for golesKey complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="golesKey">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="golesLocal" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="golesVisita" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "golesKey", propOrder = {
    "golesLocal",
    "golesVisita"
})
public class GolesKey {

    protected int golesLocal;
    protected int golesVisita;

    /**
     * Gets the value of the golesLocal property.
     * 
     */
    public int getGolesLocal() {
        return golesLocal;
    }

    /**
     * Sets the value of the golesLocal property.
     * 
     */
    public void setGolesLocal(int value) {
        this.golesLocal = value;
    }

    /**
     * Gets the value of the golesVisita property.
     * 
     */
    public int getGolesVisita() {
        return golesVisita;
    }

    /**
     * Sets the value of the golesVisita property.
     * 
     */
    public void setGolesVisita(int value) {
        this.golesVisita = value;
    }

}
