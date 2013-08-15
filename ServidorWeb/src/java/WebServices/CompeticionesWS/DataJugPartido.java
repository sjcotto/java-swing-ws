
package WebServices.CompeticionesWS;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dataJugPartido complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataJugPartido">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idPart" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="jugadoresLocal" type="{http://WebServices/}dataIdNombre" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="jugadoresVisita" type="{http://WebServices/}dataIdNombre" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataJugPartido", propOrder = {
    "idPart",
    "jugadoresLocal",
    "jugadoresVisita"
})
public class DataJugPartido {

    protected int idPart;
    @XmlElement(nillable = true)
    protected List<DataIdNombre> jugadoresLocal;
    @XmlElement(nillable = true)
    protected List<DataIdNombre> jugadoresVisita;

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
     * Gets the value of the jugadoresLocal property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the jugadoresLocal property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getJugadoresLocal().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataIdNombre }
     * 
     * 
     */
    public List<DataIdNombre> getJugadoresLocal() {
        if (jugadoresLocal == null) {
            jugadoresLocal = new ArrayList<DataIdNombre>();
        }
        return this.jugadoresLocal;
    }

    /**
     * Gets the value of the jugadoresVisita property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the jugadoresVisita property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getJugadoresVisita().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataIdNombre }
     * 
     * 
     */
    public List<DataIdNombre> getJugadoresVisita() {
        if (jugadoresVisita == null) {
            jugadoresVisita = new ArrayList<DataIdNombre>();
        }
        return this.jugadoresVisita;
    }

}
