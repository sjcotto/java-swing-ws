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
public class ListBeanApuesta {
    private List<DataApuestaWS> list;

    public ListBeanApuesta() {}

    public ListBeanApuesta(List<DataApuestaWS> l) {
        this.list = l;
    }

    public List<DataApuestaWS> getList() {
        return this.list;
    }

    public void setList(List<DataApuestaWS> l) {
        this.list = l;
    }
}
