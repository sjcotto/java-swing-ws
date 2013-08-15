/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataTypes;

import java.util.List;

/**
 *
 * @author tprog084
 */
public class ListBeanDataIdNombre {

    private List<DataIdNombre> list;

    public ListBeanDataIdNombre(){

    }

    public ListBeanDataIdNombre(List<DataIdNombre> a){
        list = a;
    }

    public List<DataIdNombre> getList() {
        return this.list;
    }

    public void setList(List<DataIdNombre> l){
        this.list = l;
    }

}
