
package WebServices.UsuarioWS;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dataCompeticion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataCompeticion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dividendos" type="{http://WebServices/}dataDivEquipo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="estaDefinida" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="montoPenca" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pathImage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipo" type="{http://WebServices/}tipoCompeticion" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataCompeticion", propOrder = {
    "dividendos",
    "estaDefinida",
    "id",
    "montoPenca",
    "nombre",
    "pathImage",
    "tipo"
})
public class DataCompeticion {

    @XmlElement(nillable = true)
    protected List<DataDivEquipo> dividendos;
    protected boolean estaDefinida;
    protected int id;
    protected float montoPenca;
    protected String nombre;
    protected String pathImage;
    protected TipoCompeticion tipo;

    /**
     * Gets the value of the dividendos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dividendos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDividendos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataDivEquipo }
     * 
     * 
     */
    public List<DataDivEquipo> getDividendos() {
        if (dividendos == null) {
            dividendos = new ArrayList<DataDivEquipo>();
        }
        return this.dividendos;
    }

    /**
     * Gets the value of the estaDefinida property.
     * 
     */
    public boolean isEstaDefinida() {
        return estaDefinida;
    }

    /**
     * Sets the value of the estaDefinida property.
     * 
     */
    public void setEstaDefinida(boolean value) {
        this.estaDefinida = value;
    }

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the montoPenca property.
     * 
     */
    public float getMontoPenca() {
        return montoPenca;
    }

    /**
     * Sets the value of the montoPenca property.
     * 
     */
    public void setMontoPenca(float value) {
        this.montoPenca = value;
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
     * Gets the value of the pathImage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPathImage() {
        return pathImage;
    }

    /**
     * Sets the value of the pathImage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPathImage(String value) {
        this.pathImage = value;
    }

    /**
     * Gets the value of the tipo property.
     * 
     * @return
     *     possible object is
     *     {@link TipoCompeticion }
     *     
     */
    public TipoCompeticion getTipo() {
        return tipo;
    }

    /**
     * Sets the value of the tipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoCompeticion }
     *     
     */
    public void setTipo(TipoCompeticion value) {
        this.tipo = value;
    }

}
