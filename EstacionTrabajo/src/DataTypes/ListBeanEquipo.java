/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataTypes;

import java.util.List;

/**
 *
 * @author tprog083
 */
public class ListBeanEquipo {
    private List<DataEquipo> list;

    public ListBeanEquipo(){

    }

    public ListBeanEquipo(List<DataEquipo> a){
        list = a;
    }

    public List<DataEquipo> getList() {
        return this.list;
    }

    public void setList(List<DataEquipo> l){
        this.list = l;
    }
}
