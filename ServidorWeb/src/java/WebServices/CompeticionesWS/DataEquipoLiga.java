
package WebServices.CompeticionesWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dataEquipoLiga complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataEquipoLiga">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="golesContra" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="golesFavor" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idEquipo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nombreEquipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nroPartidosEmpatados" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nroPartidosGanados" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nroPartidosPerdidos" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="posicion" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="puntosEquipo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataEquipoLiga", propOrder = {
    "golesContra",
    "golesFavor",
    "idEquipo",
    "nombreEquipo",
    "nroPartidosEmpatados",
    "nroPartidosGanados",
    "nroPartidosPerdidos",
    "posicion",
    "puntosEquipo"
})
public class DataEquipoLiga {

    protected int golesContra;
    protected int golesFavor;
    protected int idEquipo;
    protected String nombreEquipo;
    protected int nroPartidosEmpatados;
    protected int nroPartidosGanados;
    protected int nroPartidosPerdidos;
    protected int posicion;
    protected int puntosEquipo;

    /**
     * Gets the value of the golesContra property.
     * 
     */
    public int getGolesContra() {
        return golesContra;
    }

    /**
     * Sets the value of the golesContra property.
     * 
     */
    public void setGolesContra(int value) {
        this.golesContra = value;
    }

    /**
     * Gets the value of the golesFavor property.
     * 
     */
    public int getGolesFavor() {
        return golesFavor;
    }

    /**
     * Sets the value of the golesFavor property.
     * 
     */
    public void setGolesFavor(int value) {
        this.golesFavor = value;
    }

    /**
     * Gets the value of the idEquipo property.
     * 
     */
    public int getIdEquipo() {
        return idEquipo;
    }

    /**
     * Sets the value of the idEquipo property.
     * 
     */
    public void setIdEquipo(int value) {
        this.idEquipo = value;
    }

    /**
     * Gets the value of the nombreEquipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreEquipo() {
        return nombreEquipo;
    }

    /**
     * Sets the value of the nombreEquipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreEquipo(String value) {
        this.nombreEquipo = value;
    }

    /**
     * Gets the value of the nroPartidosEmpatados property.
     * 
     */
    public int getNroPartidosEmpatados() {
        return nroPartidosEmpatados;
    }

    /**
     * Sets the value of the nroPartidosEmpatados property.
     * 
     */
    public void setNroPartidosEmpatados(int value) {
        this.nroPartidosEmpatados = value;
    }

    /**
     * Gets the value of the nroPartidosGanados property.
     * 
     */
    public int getNroPartidosGanados() {
        return nroPartidosGanados;
    }

    /**
     * Sets the value of the nroPartidosGanados property.
     * 
     */
    public void setNroPartidosGanados(int value) {
        this.nroPartidosGanados = value;
    }

    /**
     * Gets the value of the nroPartidosPerdidos property.
     * 
     */
    public int getNroPartidosPerdidos() {
        return nroPartidosPerdidos;
    }

    /**
     * Sets the value of the nroPartidosPerdidos property.
     * 
     */
    public void setNroPartidosPerdidos(int value) {
        this.nroPartidosPerdidos = value;
    }

    /**
     * Gets the value of the posicion property.
     * 
     */
    public int getPosicion() {
        return posicion;
    }

    /**
     * Sets the value of the posicion property.
     * 
     */
    public void setPosicion(int value) {
        this.posicion = value;
    }

    /**
     * Gets the value of the puntosEquipo property.
     * 
     */
    public int getPuntosEquipo() {
        return puntosEquipo;
    }

    /**
     * Sets the value of the puntosEquipo property.
     * 
     */
    public void setPuntosEquipo(int value) {
        this.puntosEquipo = value;
    }

}
