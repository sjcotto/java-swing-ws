
package WebServices.DispMovilWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dataApuestaWS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataApuestaWS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="beneficio" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="competicion" type="{http://WebServices/}dataCompeticion" minOccurs="0"/>
 *         &lt;element name="dividendo" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="equipo" type="{http://WebServices/}dataEquipo" minOccurs="0"/>
 *         &lt;element name="estadoApuesta" type="{http://WebServices/}estadoApuesta" minOccurs="0"/>
 *         &lt;element name="fecha" type="{http://WebServices/}dataFechaHora" minOccurs="0"/>
 *         &lt;element name="golesL" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="golesV" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="jugador" type="{http://WebServices/}dataJugador" minOccurs="0"/>
 *         &lt;element name="monto" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="partido" type="{http://WebServices/}dataPartido" minOccurs="0"/>
 *         &lt;element name="pertenecePaquete" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="saldoNuevo" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="tipoResultado" type="{http://WebServices/}tipoDividendo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataApuestaWS", propOrder = {
    "beneficio",
    "competicion",
    "dividendo",
    "equipo",
    "estadoApuesta",
    "fecha",
    "golesL",
    "golesV",
    "jugador",
    "monto",
    "partido",
    "pertenecePaquete",
    "saldoNuevo",
    "tipo",
    "tipoResultado"
})
public class DataApuestaWS {

    protected float beneficio;
    protected DataCompeticion competicion;
    protected float dividendo;
    protected DataEquipo equipo;
    protected EstadoApuesta estadoApuesta;
    protected DataFechaHora fecha;
    protected int golesL;
    protected int golesV;
    protected DataJugador jugador;
    protected float monto;
    protected DataPartido partido;
    protected int pertenecePaquete;
    protected float saldoNuevo;
    protected int tipo;
    protected TipoDividendo tipoResultado;

    /**
     * Gets the value of the beneficio property.
     * 
     */
    public float getBeneficio() {
        return beneficio;
    }

    /**
     * Sets the value of the beneficio property.
     * 
     */
    public void setBeneficio(float value) {
        this.beneficio = value;
    }

    /**
     * Gets the value of the competicion property.
     * 
     * @return
     *     possible object is
     *     {@link DataCompeticion }
     *     
     */
    public DataCompeticion getCompeticion() {
        return competicion;
    }

    /**
     * Sets the value of the competicion property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataCompeticion }
     *     
     */
    public void setCompeticion(DataCompeticion value) {
        this.competicion = value;
    }

    /**
     * Gets the value of the dividendo property.
     * 
     */
    public float getDividendo() {
        return dividendo;
    }

    /**
     * Sets the value of the dividendo property.
     * 
     */
    public void setDividendo(float value) {
        this.dividendo = value;
    }

    /**
     * Gets the value of the equipo property.
     * 
     * @return
     *     possible object is
     *     {@link DataEquipo }
     *     
     */
    public DataEquipo getEquipo() {
        return equipo;
    }

    /**
     * Sets the value of the equipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataEquipo }
     *     
     */
    public void setEquipo(DataEquipo value) {
        this.equipo = value;
    }

    /**
     * Gets the value of the estadoApuesta property.
     * 
     * @return
     *     possible object is
     *     {@link EstadoApuesta }
     *     
     */
    public EstadoApuesta getEstadoApuesta() {
        return estadoApuesta;
    }

    /**
     * Sets the value of the estadoApuesta property.
     * 
     * @param value
     *     allowed object is
     *     {@link EstadoApuesta }
     *     
     */
    public void setEstadoApuesta(EstadoApuesta value) {
        this.estadoApuesta = value;
    }

    /**
     * Gets the value of the fecha property.
     * 
     * @return
     *     possible object is
     *     {@link DataFechaHora }
     *     
     */
    public DataFechaHora getFecha() {
        return fecha;
    }

    /**
     * Sets the value of the fecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataFechaHora }
     *     
     */
    public void setFecha(DataFechaHora value) {
        this.fecha = value;
    }

    /**
     * Gets the value of the golesL property.
     * 
     */
    public int getGolesL() {
        return golesL;
    }

    /**
     * Sets the value of the golesL property.
     * 
     */
    public void setGolesL(int value) {
        this.golesL = value;
    }

    /**
     * Gets the value of the golesV property.
     * 
     */
    public int getGolesV() {
        return golesV;
    }

    /**
     * Sets the value of the golesV property.
     * 
     */
    public void setGolesV(int value) {
        this.golesV = value;
    }

    /**
     * Gets the value of the jugador property.
     * 
     * @return
     *     possible object is
     *     {@link DataJugador }
     *     
     */
    public DataJugador getJugador() {
        return jugador;
    }

    /**
     * Sets the value of the jugador property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataJugador }
     *     
     */
    public void setJugador(DataJugador value) {
        this.jugador = value;
    }

    /**
     * Gets the value of the monto property.
     * 
     */
    public float getMonto() {
        return monto;
    }

    /**
     * Sets the value of the monto property.
     * 
     */
    public void setMonto(float value) {
        this.monto = value;
    }

    /**
     * Gets the value of the partido property.
     * 
     * @return
     *     possible object is
     *     {@link DataPartido }
     *     
     */
    public DataPartido getPartido() {
        return partido;
    }

    /**
     * Sets the value of the partido property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataPartido }
     *     
     */
    public void setPartido(DataPartido value) {
        this.partido = value;
    }

    /**
     * Gets the value of the pertenecePaquete property.
     * 
     */
    public int getPertenecePaquete() {
        return pertenecePaquete;
    }

    /**
     * Sets the value of the pertenecePaquete property.
     * 
     */
    public void setPertenecePaquete(int value) {
        this.pertenecePaquete = value;
    }

    /**
     * Gets the value of the saldoNuevo property.
     * 
     */
    public float getSaldoNuevo() {
        return saldoNuevo;
    }

    /**
     * Sets the value of the saldoNuevo property.
     * 
     */
    public void setSaldoNuevo(float value) {
        this.saldoNuevo = value;
    }

    /**
     * Gets the value of the tipo property.
     * 
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * Sets the value of the tipo property.
     * 
     */
    public void setTipo(int value) {
        this.tipo = value;
    }

    /**
     * Gets the value of the tipoResultado property.
     * 
     * @return
     *     possible object is
     *     {@link TipoDividendo }
     *     
     */
    public TipoDividendo getTipoResultado() {
        return tipoResultado;
    }

    /**
     * Sets the value of the tipoResultado property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoDividendo }
     *     
     */
    public void setTipoResultado(TipoDividendo value) {
        this.tipoResultado = value;
    }

}
