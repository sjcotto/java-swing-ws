
package WebServices.DispMovilWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dataJugador complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataJugador">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="altura" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="edad" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fechaDeNacimiento" type="{http://WebServices/}dataFechaHora" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="lugarNacimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombreCompleto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pathImage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="peso" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="posicion" type="{http://WebServices/}tipoPosicion" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataJugador", propOrder = {
    "altura",
    "edad",
    "fechaDeNacimiento",
    "id",
    "lugarNacimiento",
    "nombre",
    "nombreCompleto",
    "pathImage",
    "peso",
    "posicion"
})
public class DataJugador {

    protected double altura;
    protected int edad;
    protected DataFechaHora fechaDeNacimiento;
    protected int id;
    protected String lugarNacimiento;
    protected String nombre;
    protected String nombreCompleto;
    protected String pathImage;
    protected double peso;
    protected TipoPosicion posicion;

    /**
     * Gets the value of the altura property.
     * 
     */
    public double getAltura() {
        return altura;
    }

    /**
     * Sets the value of the altura property.
     * 
     */
    public void setAltura(double value) {
        this.altura = value;
    }

    /**
     * Gets the value of the edad property.
     * 
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Sets the value of the edad property.
     * 
     */
    public void setEdad(int value) {
        this.edad = value;
    }

    /**
     * Gets the value of the fechaDeNacimiento property.
     * 
     * @return
     *     possible object is
     *     {@link DataFechaHora }
     *     
     */
    public DataFechaHora getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    /**
     * Sets the value of the fechaDeNacimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataFechaHora }
     *     
     */
    public void setFechaDeNacimiento(DataFechaHora value) {
        this.fechaDeNacimiento = value;
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
     * Gets the value of the lugarNacimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    /**
     * Sets the value of the lugarNacimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLugarNacimiento(String value) {
        this.lugarNacimiento = value;
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
     * Gets the value of the nombreCompleto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * Sets the value of the nombreCompleto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreCompleto(String value) {
        this.nombreCompleto = value;
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
     * Gets the value of the peso property.
     * 
     */
    public double getPeso() {
        return peso;
    }

    /**
     * Sets the value of the peso property.
     * 
     */
    public void setPeso(double value) {
        this.peso = value;
    }

    /**
     * Gets the value of the posicion property.
     * 
     * @return
     *     possible object is
     *     {@link TipoPosicion }
     *     
     */
    public TipoPosicion getPosicion() {
        return posicion;
    }

    /**
     * Sets the value of the posicion property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoPosicion }
     *     
     */
    public void setPosicion(TipoPosicion value) {
        this.posicion = value;
    }

}
