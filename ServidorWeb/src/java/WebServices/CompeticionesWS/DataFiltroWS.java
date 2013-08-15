
package WebServices.CompeticionesWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dataFiltroWS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataFiltroWS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaFin" type="{http://WebServices/}dataFechaHora" minOccurs="0"/>
 *         &lt;element name="fechaIni" type="{http://WebServices/}dataFechaHora" minOccurs="0"/>
 *         &lt;element name="lugarEnc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomComp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomEq" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rangoFinal" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="rangoInicial" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="tipoDividendo" type="{http://WebServices/}tipoDividendo" minOccurs="0"/>
 *         &lt;element name="tipoFiltro" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataFiltroWS", propOrder = {
    "estado",
    "fechaFin",
    "fechaIni",
    "lugarEnc",
    "nomComp",
    "nomEq",
    "rangoFinal",
    "rangoInicial",
    "tipoDividendo",
    "tipoFiltro"
})
public class DataFiltroWS {

    protected String estado;
    protected DataFechaHora fechaFin;
    protected DataFechaHora fechaIni;
    protected String lugarEnc;
    protected String nomComp;
    protected String nomEq;
    protected float rangoFinal;
    protected float rangoInicial;
    protected TipoDividendo tipoDividendo;
    protected int tipoFiltro;

    /**
     * Gets the value of the estado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Sets the value of the estado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstado(String value) {
        this.estado = value;
    }

    /**
     * Gets the value of the fechaFin property.
     * 
     * @return
     *     possible object is
     *     {@link DataFechaHora }
     *     
     */
    public DataFechaHora getFechaFin() {
        return fechaFin;
    }

    /**
     * Sets the value of the fechaFin property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataFechaHora }
     *     
     */
    public void setFechaFin(DataFechaHora value) {
        this.fechaFin = value;
    }

    /**
     * Gets the value of the fechaIni property.
     * 
     * @return
     *     possible object is
     *     {@link DataFechaHora }
     *     
     */
    public DataFechaHora getFechaIni() {
        return fechaIni;
    }

    /**
     * Sets the value of the fechaIni property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataFechaHora }
     *     
     */
    public void setFechaIni(DataFechaHora value) {
        this.fechaIni = value;
    }

    /**
     * Gets the value of the lugarEnc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLugarEnc() {
        return lugarEnc;
    }

    /**
     * Sets the value of the lugarEnc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLugarEnc(String value) {
        this.lugarEnc = value;
    }

    /**
     * Gets the value of the nomComp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomComp() {
        return nomComp;
    }

    /**
     * Sets the value of the nomComp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomComp(String value) {
        this.nomComp = value;
    }

    /**
     * Gets the value of the nomEq property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomEq() {
        return nomEq;
    }

    /**
     * Sets the value of the nomEq property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomEq(String value) {
        this.nomEq = value;
    }

    /**
     * Gets the value of the rangoFinal property.
     * 
     */
    public float getRangoFinal() {
        return rangoFinal;
    }

    /**
     * Sets the value of the rangoFinal property.
     * 
     */
    public void setRangoFinal(float value) {
        this.rangoFinal = value;
    }

    /**
     * Gets the value of the rangoInicial property.
     * 
     */
    public float getRangoInicial() {
        return rangoInicial;
    }

    /**
     * Sets the value of the rangoInicial property.
     * 
     */
    public void setRangoInicial(float value) {
        this.rangoInicial = value;
    }

    /**
     * Gets the value of the tipoDividendo property.
     * 
     * @return
     *     possible object is
     *     {@link TipoDividendo }
     *     
     */
    public TipoDividendo getTipoDividendo() {
        return tipoDividendo;
    }

    /**
     * Sets the value of the tipoDividendo property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoDividendo }
     *     
     */
    public void setTipoDividendo(TipoDividendo value) {
        this.tipoDividendo = value;
    }

    /**
     * Gets the value of the tipoFiltro property.
     * 
     */
    public int getTipoFiltro() {
        return tipoFiltro;
    }

    /**
     * Sets the value of the tipoFiltro property.
     * 
     */
    public void setTipoFiltro(int value) {
        this.tipoFiltro = value;
    }

}
