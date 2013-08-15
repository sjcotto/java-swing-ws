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
public class ListBeanFiltro {
    private List<DataFiltroWS> list;

    public ListBeanFiltro() {}

    public ListBeanFiltro(List<DataFiltroWS> l) {
        this.list = l;
    }

    public List<DataFiltroWS> getList() {
        return this.list;
    }

    public void setList(List<DataFiltroWS> l) {
        this.list = l;
    }
}
