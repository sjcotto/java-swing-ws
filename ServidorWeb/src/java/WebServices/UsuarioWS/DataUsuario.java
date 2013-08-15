
package WebServices.UsuarioWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dataUsuario complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataUsuario">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dir" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nick" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="notApuesta" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="notForo" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="notPartido" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="pais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="saldo" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="sexo" type="{http://WebServices/}tipoSexo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataUsuario", propOrder = {
    "ci",
    "dir",
    "email",
    "nick",
    "nombre",
    "notApuesta",
    "notForo",
    "notPartido",
    "pais",
    "saldo",
    "sexo"
})
public class DataUsuario {

    @XmlElement(name = "CI")
    protected String ci;
    protected String dir;
    protected String email;
    protected String nick;
    protected String nombre;
    protected boolean notApuesta;
    protected boolean notForo;
    protected boolean notPartido;
    protected String pais;
    protected float saldo;
    protected TipoSexo sexo;

    /**
     * Gets the value of the ci property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCI() {
        return ci;
    }

    /**
     * Sets the value of the ci property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCI(String value) {
        this.ci = value;
    }

    /**
     * Gets the value of the dir property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDir() {
        return dir;
    }

    /**
     * Sets the value of the dir property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDir(String value) {
        this.dir = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the nick property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNick() {
        return nick;
    }

    /**
     * Sets the value of the nick property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNick(String value) {
        this.nick = value;
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
     * Gets the value of the notApuesta property.
     * 
     */
    public boolean isNotApuesta() {
        return notApuesta;
    }

    /**
     * Sets the value of the notApuesta property.
     * 
     */
    public void setNotApuesta(boolean value) {
        this.notApuesta = value;
    }

    /**
     * Gets the value of the notForo property.
     * 
     */
    public boolean isNotForo() {
        return notForo;
    }

    /**
     * Sets the value of the notForo property.
     * 
     */
    public void setNotForo(boolean value) {
        this.notForo = value;
    }

    /**
     * Gets the value of the notPartido property.
     * 
     */
    public boolean isNotPartido() {
        return notPartido;
    }

    /**
     * Sets the value of the notPartido property.
     * 
     */
    public void setNotPartido(boolean value) {
        this.notPartido = value;
    }

    /**
     * Gets the value of the pais property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPais() {
        return pais;
    }

    /**
     * Sets the value of the pais property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPais(String value) {
        this.pais = value;
    }

    /**
     * Gets the value of the saldo property.
     * 
     */
    public float getSaldo() {
        return saldo;
    }

    /**
     * Sets the value of the saldo property.
     * 
     */
    public void setSaldo(float value) {
        this.saldo = value;
    }

    /**
     * Gets the value of the sexo property.
     * 
     * @return
     *     possible object is
     *     {@link TipoSexo }
     *     
     */
    public TipoSexo getSexo() {
        return sexo;
    }

    /**
     * Sets the value of the sexo property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoSexo }
     *     
     */
    public void setSexo(TipoSexo value) {
        this.sexo = value;
    }

}
