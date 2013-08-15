
package WebServices.DispMovilWS;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dividendos complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dividendos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dividendoEmpate" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="dividendoLocal" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="dividendoVisita" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="estanAsignados" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dividendos", propOrder = {
    "dividendoEmpate",
    "dividendoLocal",
    "dividendoVisita",
    "estanAsignados"
})
public class Dividendos {

    protected float dividendoEmpate;
    protected float dividendoLocal;
    protected float dividendoVisita;
    protected boolean estanAsignados;

    /**
     * Gets the value of the dividendoEmpate property.
     * 
     */
    public float getDividendoEmpate() {
        return dividendoEmpate;
    }

    /**
     * Sets the value of the dividendoEmpate property.
     * 
     */
    public void setDividendoEmpate(float value) {
        this.dividendoEmpate = value;
    }

    /**
     * Gets the value of the dividendoLocal property.
     * 
     */
    public float getDividendoLocal() {
        return dividendoLocal;
    }

    /**
     * Sets the value of the dividendoLocal property.
     * 
     */
    public void setDividendoLocal(float value) {
        this.dividendoLocal = value;
    }

    /**
     * Gets the value of the dividendoVisita property.
     * 
     */
    public float getDividendoVisita() {
        return dividendoVisita;
    }

    /**
     * Sets the value of the dividendoVisita property.
     * 
     */
    public void setDividendoVisita(float value) {
        this.dividendoVisita = value;
    }

    /**
     * Gets the value of the estanAsignados property.
     * 
     */
    public boolean isEstanAsignados() {
        return estanAsignados;
    }

    /**
     * Sets the value of the estanAsignados property.
     * 
     */
    public void setEstanAsignados(boolean value) {
        this.estanAsignados = value;
    }

}
