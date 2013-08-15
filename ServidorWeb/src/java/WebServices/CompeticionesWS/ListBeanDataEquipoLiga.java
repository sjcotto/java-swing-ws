
package WebServices.CompeticionesWS;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for listBeanDataEquipoLiga complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="listBeanDataEquipoLiga">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="listDEL" type="{http://WebServices/}dataEquipoLiga" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listBeanDataEquipoLiga", propOrder = {
    "listDEL"
})
public class ListBeanDataEquipoLiga {

    @XmlElement(nillable = true)
    protected List<DataEquipoLiga> listDEL;

    /**
     * Gets the value of the listDEL property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listDEL property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListDEL().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataEquipoLiga }
     * 
     * 
     */
    public List<DataEquipoLiga> getListDEL() {
        if (listDEL == null) {
            listDEL = new ArrayList<DataEquipoLiga>();
        }
        return this.listDEL;
    }

}
