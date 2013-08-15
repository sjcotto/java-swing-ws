/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataTypes;

import java.util.List;

/**
 *
 * @author Santiago
 */
public class ListBeanDataPartidoComp {
    private List<DataPartidoComp> list;

    public ListBeanDataPartidoComp() {}

    public ListBeanDataPartidoComp(List<DataPartidoComp> l) {
        this.list = l;
    }

    public List<DataPartidoComp> getList() {
        return this.list;
    }

    public void setList(List<DataPartidoComp> l) {
        this.list = l;
    }

    
}
