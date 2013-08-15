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
public class ListBeanDataEquipoLiga {

    private List<DataEquipoLiga> listDEL;
    
    public ListBeanDataEquipoLiga() {}
    
    public ListBeanDataEquipoLiga(List<DataEquipoLiga> l) {
        this.listDEL = l;
    }
    
    public List<DataEquipoLiga> getListDEL() {
        return this.listDEL;
    }
    
    public void setListDEL(List<DataEquipoLiga> l) {
        this.listDEL = l;
    }

}
