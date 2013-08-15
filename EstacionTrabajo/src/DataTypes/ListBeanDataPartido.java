/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataTypes;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author tprog083
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ListBeanDataPartido {
    private List<DataPartido> list;

    public ListBeanDataPartido(){}

    public ListBeanDataPartido(List<DataPartido> a){
        list = a;
    }

    public List<DataPartido> getList() {
        return this.list;
    }

    public void setList(List<DataPartido> l){
        this.list = l;
    }
}
