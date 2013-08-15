
package WebServices.CompeticionesWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dataPartidoComp complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataPartidoComp">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idComp" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idPart" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nombreComp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataPartidoComp", propOrder = {
    "idComp",
    "idPart",
    "nombreComp"
})
public class DataPartidoComp {

    protected int idComp;
    protected int idPart;
    protected String nombreComp;

    /**
     * Gets the value of the idComp property.
     * 
     */
    public int getIdComp() {
        return idComp;
    }

    /**
     * Sets the value of the idComp property.
     * 
     */
    public void setIdComp(int value) {
        this.idComp = value;
    }

    /**
     * Gets the value of the idPart property.
     * 
     */
    public int getIdPart() {
        return idPart;
    }

    /**
     * Sets the value of the idPart property.
     * 
     */
    public void setIdPart(int value) {
        this.idPart = value;
    }

    /**
     * Gets the value of the nombreComp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreComp() {
        return nombreComp;
    }

    /**
     * Sets the value of the nombreComp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreComp(String value) {
        this.nombreComp = value;
    }

}
