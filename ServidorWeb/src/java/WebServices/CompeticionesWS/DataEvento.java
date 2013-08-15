
package WebServices.CompeticionesWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dataEvento complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataEvento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amarilla" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="jugador1" type="{http://WebServices/}dataIdNombre" minOccurs="0"/>
 *         &lt;element name="jugador2" type="{http://WebServices/}dataIdNombre" minOccurs="0"/>
 *         &lt;element name="minuto" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="periodo" type="{http://WebServices/}tipoPeriodo" minOccurs="0"/>
 *         &lt;element name="tipoevento" type="{http://WebServices/}tipoEvento" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataEvento", propOrder = {
    "amarilla",
    "jugador1",
    "jugador2",
    "minuto",
    "periodo",
    "tipoevento"
})
public class DataEvento {

    protected boolean amarilla;
    protected DataIdNombre jugador1;
    protected DataIdNombre jugador2;
    protected int minuto;
    protected TipoPeriodo periodo;
    protected TipoEvento tipoevento;

    /**
     * Gets the value of the amarilla property.
     * 
     */
    public boolean isAmarilla() {
        return amarilla;
    }

    /**
     * Sets the value of the amarilla property.
     * 
     */
    public void setAmarilla(boolean value) {
        this.amarilla = value;
    }

    /**
     * Gets the value of the jugador1 property.
     * 
     * @return
     *     possible object is
     *     {@link DataIdNombre }
     *     
     */
    public DataIdNombre getJugador1() {
        return jugador1;
    }

    /**
     * Sets the value of the jugador1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataIdNombre }
     *     
     */
    public void setJugador1(DataIdNombre value) {
        this.jugador1 = value;
    }

    /**
     * Gets the value of the jugador2 property.
     * 
     * @return
     *     possible object is
     *     {@link DataIdNombre }
     *     
     */
    public DataIdNombre getJugador2() {
        return jugador2;
    }

    /**
     * Sets the value of the jugador2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataIdNombre }
     *     
     */
    public void setJugador2(DataIdNombre value) {
        this.jugador2 = value;
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
     * Gets the value of the periodo property.
     * 
     * @return
     *     possible object is
     *     {@link TipoPeriodo }
     *     
     */
    public TipoPeriodo getPeriodo() {
        return periodo;
    }

    /**
     * Sets the value of the periodo property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoPeriodo }
     *     
     */
    public void setPeriodo(TipoPeriodo value) {
        this.periodo = value;
    }

    /**
     * Gets the value of the tipoevento property.
     * 
     * @return
     *     possible object is
     *     {@link TipoEvento }
     *     
     */
    public TipoEvento getTipoevento() {
        return tipoevento;
    }

    /**
     * Sets the value of the tipoevento property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoEvento }
     *     
     */
    public void setTipoevento(TipoEvento value) {
        this.tipoevento = value;
    }

}
