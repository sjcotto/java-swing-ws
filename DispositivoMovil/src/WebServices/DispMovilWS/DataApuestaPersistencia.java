
package WebServices.DispMovilWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dataApuestaPersistencia complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataApuestaPersistencia">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idPaquete" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataApuestaPersistencia", propOrder = {
    "idPaquete"
})
public class DataApuestaPersistencia {

    protected int idPaquete;

    /**
     * Gets the value of the idPaquete property.
     * 
     */
    public int getIdPaquete() {
        return idPaquete;
    }

    /**
     * Sets the value of the idPaquete property.
     * 
     */
    public void setIdPaquete(int value) {
        this.idPaquete = value;
    }

}
