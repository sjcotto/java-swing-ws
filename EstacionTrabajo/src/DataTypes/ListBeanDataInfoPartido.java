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
public class ListBeanDataInfoPartido {
    private List<DataInfoPartido> list;

    public ListBeanDataInfoPartido(){

    }

    public ListBeanDataInfoPartido(List<DataInfoPartido> a){
        list = a;
    }

    public List<DataInfoPartido> getList() {
        return this.list;
    }

    public void setList(List<DataInfoPartido> l){
        this.list = l;
    }
}
