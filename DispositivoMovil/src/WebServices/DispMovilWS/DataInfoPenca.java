
package WebServices.DispMovilWS;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dataInfoPenca complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataInfoPenca">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="finalizada" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="idComp" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="mensajes" type="{http://WebServices/}dataMensajePenca" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="montoMin" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="participaUsuario" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="pozo" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="tablaPosiciones" type="{http://WebServices/}dataInfoUsuarioPenca" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataInfoPenca", propOrder = {
    "finalizada",
    "idComp",
    "mensajes",
    "montoMin",
    "nombre",
    "participaUsuario",
    "pozo",
    "tablaPosiciones"
})
public class DataInfoPenca {

    protected boolean finalizada;
    protected int idComp;
    @XmlElement(nillable = true)
    protected List<DataMensajePenca> mensajes;
    protected float montoMin;
    protected String nombre;
    protected boolean participaUsuario;
    protected float pozo;
    @XmlElement(nillable = true)
    protected List<DataInfoUsuarioPenca> tablaPosiciones;

    /**
     * Gets the value of the finalizada property.
     * 
     */
    public boolean isFinalizada() {
        return finalizada;
    }

    /**
     * Sets the value of the finalizada property.
     * 
     */
    public void setFinalizada(boolean value) {
        this.finalizada = value;
    }

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
     * Gets the value of the mensajes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mensajes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMensajes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataMensajePenca }
     * 
     * 
     */
    public List<DataMensajePenca> getMensajes() {
        if (mensajes == null) {
            mensajes = new ArrayList<DataMensajePenca>();
        }
        return this.mensajes;
    }

    /**
     * Gets the value of the montoMin property.
     * 
     */
    public float getMontoMin() {
        return montoMin;
    }

    /**
     * Sets the value of the montoMin property.
     * 
     */
    public void setMontoMin(float value) {
        this.montoMin = value;
    }

    /**
     * Gets the value of the nombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Gets the value of the participaUsuario property.
     * 
     */
    public boolean isParticipaUsuario() {
        return participaUsuario;
    }

    /**
     * Sets the value of the participaUsuario property.
     * 
     */
    public void setParticipaUsuario(boolean value) {
        this.participaUsuario = value;
    }

    /**
     * Gets the value of the pozo property.
     * 
     */
    public float getPozo() {
        return pozo;
    }

    /**
     * Sets the value of the pozo property.
     * 
     */
    public void setPozo(float value) {
        this.pozo = value;
    }

    /**
     * Gets the value of the tablaPosiciones property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tablaPosiciones property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTablaPosiciones().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataInfoUsuarioPenca }
     * 
     * 
     */
    public List<DataInfoUsuarioPenca> getTablaPosiciones() {
        if (tablaPosiciones == null) {
            tablaPosiciones = new ArrayList<DataInfoUsuarioPenca>();
        }
        return this.tablaPosiciones;
    }

}
