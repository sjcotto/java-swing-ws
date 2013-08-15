
package WebServices.UsuarioWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dataFechaHora complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataFechaHora">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dia" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="mes" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="anio" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="hora" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="minuto" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="segundos" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="str" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataFechaHora", propOrder = {
    "dia",
    "mes",
    "anio",
    "hora",
    "minuto",
    "segundos",
    "str"
})
public class DataFechaHora {

    protected int dia;
    protected int mes;
    protected int anio;
    protected int hora;
    protected int minuto;
    protected int segundos;
    protected String str;

    /**
     * Gets the value of the dia property.
     * 
     */
    public int getDia() {
        return dia;
    }

    /**
     * Sets the value of the dia property.
     * 
     */
    public void setDia(int value) {
        this.dia = value;
    }

    /**
     * Gets the value of the mes property.
     * 
     */
    public int getMes() {
        return mes;
    }

    /**
     * Sets the value of the mes property.
     * 
     */
    public void setMes(int value) {
        this.mes = value;
    }

    /**
     * Gets the value of the anio property.
     * 
     */
    public int getAnio() {
        return anio;
    }

    /**
     * Sets the value of the anio property.
     * 
     */
    public void setAnio(int value) {
        this.anio = value;
    }

    /**
     * Gets the value of the hora property.
     * 
     */
    public int getHora() {
        return hora;
    }

    /**
     * Sets the value of the hora property.
     * 
     */
    public void setHora(int value) {
        this.hora = value;
    }

    /**
     * Gets the value of the minuto property.
     * 
     */
    public int getMinuto() {
        return minuto;
    }

    /**
     * Sets the value of the minuto property.
     * 
     */
    public void setMinuto(int value) {
        this.minuto = value;
    }

    /**
     * Gets the value of the segundos property.
     * 
     */
    public int getSegundos() {
        return segundos;
    }

    /**
     * Sets the value of the segundos property.
     * 
     */
    public void setSegundos(int value) {
        this.segundos = value;
    }

    /**
     * Gets the value of the str property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStr() {
        return str;
    }

    /**
     * Sets the value of the str property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStr(String value) {
        this.str = value;
    }

}
