
package WebServices.CompeticionesWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dataInfoPartido complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataInfoPartido">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fechaHora" type="{http://WebServices/}dataFechaHora" minOccurs="0"/>
 *         &lt;element name="idComp" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idLocal" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idPar" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idVisita" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="lugar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomComp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomLlave" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomLlavePreLoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomLlavePreVis" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomLocal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomVisita" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tercer" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="tipoC" type="{http://WebServices/}tipoCompeticion" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataInfoPartido", propOrder = {
    "fechaHora",
    "idComp",
    "idLocal",
    "idPar",
    "idVisita",
    "lugar",
    "nomComp",
    "nomLlave",
    "nomLlavePreLoc",
    "nomLlavePreVis",
    "nomLocal",
    "nomVisita",
    "tercer",
    "tipoC"
})
public class DataInfoPartido {

    protected DataFechaHora fechaHora;
    protected int idComp;
    protected int idLocal;
    protected int idPar;
    protected int idVisita;
    protected String lugar;
    protected String nomComp;
    protected String nomLlave;
    protected String nomLlavePreLoc;
    protected String nomLlavePreVis;
    protected String nomLocal;
    protected String nomVisita;
    protected boolean tercer;
    protected TipoCompeticion tipoC;

    /**
     * Gets the value of the fechaHora property.
     * 
     * @return
     *     possible object is
     *     {@link DataFechaHora }
     *     
     */
    public DataFechaHora getFechaHora() {
        return fechaHora;
    }

    /**
     * Sets the value of the fechaHora property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataFechaHora }
     *     
     */
    public void setFechaHora(DataFechaHora value) {
        this.fechaHora = value;
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
     * Gets the value of the idLocal property.
     * 
     */
    public int getIdLocal() {
        return idLocal;
    }

    /**
     * Sets the value of the idLocal property.
     * 
     */
    public void setIdLocal(int value) {
        this.idLocal = value;
    }

    /**
     * Gets the value of the idPar property.
     * 
     */
    public int getIdPar() {
        return idPar;
    }

    /**
     * Sets the value of the idPar property.
     * 
     */
    public void setIdPar(int value) {
        this.idPar = value;
    }

    /**
     * Gets the value of the idVisita property.
     * 
     */
    public int getIdVisita() {
        return idVisita;
    }

    /**
     * Sets the value of the idVisita property.
     * 
     */
    public void setIdVisita(int value) {
        this.idVisita = value;
    }

    /**
     * Gets the value of the lugar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * Sets the value of the lugar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLugar(String value) {
        this.lugar = value;
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
     * Gets the value of the nomLlave property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomLlave() {
        return nomLlave;
    }

    /**
     * Sets the value of the nomLlave property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomLlave(String value) {
        this.nomLlave = value;
    }

    /**
     * Gets the value of the nomLlavePreLoc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomLlavePreLoc() {
        return nomLlavePreLoc;
    }

    /**
     * Sets the value of the nomLlavePreLoc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomLlavePreLoc(String value) {
        this.nomLlavePreLoc = value;
    }

    /**
     * Gets the value of the nomLlavePreVis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomLlavePreVis() {
        return nomLlavePreVis;
    }

    /**
     * Sets the value of the nomLlavePreVis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomLlavePreVis(String value) {
        this.nomLlavePreVis = value;
    }

    /**
     * Gets the value of the nomLocal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomLocal() {
        return nomLocal;
    }

    /**
     * Sets the value of the nomLocal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomLocal(String value) {
        this.nomLocal = value;
    }

    /**
     * Gets the value of the nomVisita property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomVisita() {
        return nomVisita;
    }

    /**
     * Sets the value of the nomVisita property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomVisita(String value) {
        this.nomVisita = value;
    }

    /**
     * Gets the value of the tercer property.
     * 
     */
    public boolean isTercer() {
        return tercer;
    }

    /**
     * Sets the value of the tercer property.
     * 
     */
    public void setTercer(boolean value) {
        this.tercer = value;
    }

    /**
     * Gets the value of the tipoC property.
     * 
     * @return
     *     possible object is
     *     {@link TipoCompeticion }
     *     
     */
    public TipoCompeticion getTipoC() {
        return tipoC;
    }

    /**
     * Sets the value of the tipoC property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoCompeticion }
     *     
     */
    public void setTipoC(TipoCompeticion value) {
        this.tipoC = value;
    }

}
