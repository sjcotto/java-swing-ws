
package WebServices.UsuarioWS;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dataPartido complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataPartido">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="apuestas" type="{http://WebServices/}dataApuestaPersistencia" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="dataInfoPart" type="{http://WebServices/}dataInfoPartido" minOccurs="0"/>
 *         &lt;element name="dividendos" type="{http://WebServices/}dividendos" minOccurs="0"/>
 *         &lt;element name="estaFinalizado" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="eventos" type="{http://WebServices/}dataEvento" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="golesDivs">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="entry" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="key" type="{http://WebServices/}golesKey" minOccurs="0"/>
 *                             &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="golesL" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="golesV" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="jugLocales" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="jugVisitante" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="penalesL" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="penalesV" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataPartido", propOrder = {
    "apuestas",
    "dataInfoPart",
    "dividendos",
    "estaFinalizado",
    "eventos",
    "golesDivs",
    "golesL",
    "golesV",
    "jugLocales",
    "jugVisitante",
    "penalesL",
    "penalesV"
})
public class DataPartido {

    @XmlElement(nillable = true)
    protected List<DataApuestaPersistencia> apuestas;
    protected DataInfoPartido dataInfoPart;
    protected Dividendos dividendos;
    protected boolean estaFinalizado;
    @XmlElement(nillable = true)
    protected List<DataEvento> eventos;
    @XmlElement(required = true)
    protected DataPartido.GolesDivs golesDivs;
    protected int golesL;
    protected int golesV;
    @XmlElement(nillable = true)
    protected List<Integer> jugLocales;
    @XmlElement(nillable = true)
    protected List<Integer> jugVisitante;
    protected int penalesL;
    protected int penalesV;

    /**
     * Gets the value of the apuestas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the apuestas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApuestas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataApuestaPersistencia }
     * 
     * 
     */
    public List<DataApuestaPersistencia> getApuestas() {
        if (apuestas == null) {
            apuestas = new ArrayList<DataApuestaPersistencia>();
        }
        return this.apuestas;
    }

    /**
     * Gets the value of the dataInfoPart property.
     * 
     * @return
     *     possible object is
     *     {@link DataInfoPartido }
     *     
     */
    public DataInfoPartido getDataInfoPart() {
        return dataInfoPart;
    }

    /**
     * Sets the value of the dataInfoPart property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataInfoPartido }
     *     
     */
    public void setDataInfoPart(DataInfoPartido value) {
        this.dataInfoPart = value;
    }

    /**
     * Gets the value of the dividendos property.
     * 
     * @return
     *     possible object is
     *     {@link Dividendos }
     *     
     */
    public Dividendos getDividendos() {
        return dividendos;
    }

    /**
     * Sets the value of the dividendos property.
     * 
     * @param value
     *     allowed object is
     *     {@link Dividendos }
     *     
     */
    public void setDividendos(Dividendos value) {
        this.dividendos = value;
    }

    /**
     * Gets the value of the estaFinalizado property.
     * 
     */
    public boolean isEstaFinalizado() {
        return estaFinalizado;
    }

    /**
     * Sets the value of the estaFinalizado property.
     * 
     */
    public void setEstaFinalizado(boolean value) {
        this.estaFinalizado = value;
    }

    /**
     * Gets the value of the eventos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eventos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEventos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataEvento }
     * 
     * 
     */
    public List<DataEvento> getEventos() {
        if (eventos == null) {
            eventos = new ArrayList<DataEvento>();
        }
        return this.eventos;
    }

    /**
     * Gets the value of the golesDivs property.
     * 
     * @return
     *     possible object is
     *     {@link DataPartido.GolesDivs }
     *     
     */
    public DataPartido.GolesDivs getGolesDivs() {
        return golesDivs;
    }

    /**
     * Sets the value of the golesDivs property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataPartido.GolesDivs }
     *     
     */
    public void setGolesDivs(DataPartido.GolesDivs value) {
        this.golesDivs = value;
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
     * Gets the value of the jugLocales property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the jugLocales property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getJugLocales().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     */
    public List<Integer> getJugLocales() {
        if (jugLocales == null) {
            jugLocales = new ArrayList<Integer>();
        }
        return this.jugLocales;
    }

    /**
     * Gets the value of the jugVisitante property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the jugVisitante property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getJugVisitante().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     */
    public List<Integer> getJugVisitante() {
        if (jugVisitante == null) {
            jugVisitante = new ArrayList<Integer>();
        }
        return this.jugVisitante;
    }

    /**
     * Gets the value of the penalesL property.
     * 
     */
    public int getPenalesL() {
        return penalesL;
    }

    /**
     * Sets the value of the penalesL property.
     * 
     */
    public void setPenalesL(int value) {
        this.penalesL = value;
    }

    /**
     * Gets the value of the penalesV property.
     * 
     */
    public int getPenalesV() {
        return penalesV;
    }

    /**
     * Sets the value of the penalesV property.
     * 
     */
    public void setPenalesV(int value) {
        this.penalesV = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="entry" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="key" type="{http://WebServices/}golesKey" minOccurs="0"/>
     *                   &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "entry"
    })
    public static class GolesDivs {

        protected List<DataPartido.GolesDivs.Entry> entry;

        /**
         * Gets the value of the entry property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the entry property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEntry().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DataPartido.GolesDivs.Entry }
         * 
         * 
         */
        public List<DataPartido.GolesDivs.Entry> getEntry() {
            if (entry == null) {
                entry = new ArrayList<DataPartido.GolesDivs.Entry>();
            }
            return this.entry;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="key" type="{http://WebServices/}golesKey" minOccurs="0"/>
         *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "key",
            "value"
        })
        public static class Entry {

            protected GolesKey key;
            protected Float value;

            /**
             * Gets the value of the key property.
             * 
             * @return
             *     possible object is
             *     {@link GolesKey }
             *     
             */
            public GolesKey getKey() {
                return key;
            }

            /**
             * Sets the value of the key property.
             * 
             * @param value
             *     allowed object is
             *     {@link GolesKey }
             *     
             */
            public void setKey(GolesKey value) {
                this.key = value;
            }

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link Float }
             *     
             */
            public Float getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link Float }
             *     
             */
            public void setValue(Float value) {
                this.value = value;
            }

        }

    }

}
