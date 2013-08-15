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
public class ListBean {
    private List list;

    public ListBean(){
    
    }

    public ListBean(List a){
        list = a;
    }

    public List getList() {
        return this.list;
    }

    public void setList(List l){
        this.list = l;
    }
}
