
package WebServices.UsuarioWS;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dataPaqueteApuestasWS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataPaqueteApuestasWS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="apuestas" type="{http://WebServices/}dataApuestaWS" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="estadoPaquete" type="{http://WebServices/}estadoPaqueteApuestas" minOccurs="0"/>
 *         &lt;element name="montoTotal" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataPaqueteApuestasWS", propOrder = {
    "apuestas",
    "estadoPaquete",
    "montoTotal"
})
public class DataPaqueteApuestasWS {

    @XmlElement(nillable = true)
    protected List<DataApuestaWS> apuestas;
    protected EstadoPaqueteApuestas estadoPaquete;
    protected float montoTotal;

    /**
     * Gets the value of the apuestas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the apuestas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApuestas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataApuestaWS }
     * 
     * 
     */
    public List<DataApuestaWS> getApuestas() {
        if (apuestas == null) {
            apuestas = new ArrayList<DataApuestaWS>();
        }
        return this.apuestas;
    }

    /**
     * Gets the value of the estadoPaquete property.
     * 
     * @return
     *     possible object is
     *     {@link EstadoPaqueteApuestas }
     *     
     */
    public EstadoPaqueteApuestas getEstadoPaquete() {
        return estadoPaquete;
    }

    /**
     * Sets the value of the estadoPaquete property.
     * 
     * @param value
     *     allowed object is
     *     {@link EstadoPaqueteApuestas }
     *     
     */
    public void setEstadoPaquete(EstadoPaqueteApuestas value) {
        this.estadoPaquete = value;
    }

    /**
     * Gets the value of the montoTotal property.
     * 
     */
    public float getMontoTotal() {
        return montoTotal;
    }

    /**
     * Sets the value of the montoTotal property.
     * 
     */
    public void setMontoTotal(float value) {
        this.montoTotal = value;
    }

}
