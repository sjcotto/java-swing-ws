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
public class ListBeanDataInfoPenca {
    private List<DataInfoPenca> list;

 public ListBeanDataInfoPenca(){}

 public ListBeanDataInfoPenca(List<DataInfoPenca> info){
     this.list = info;
 }

 public List<DataInfoPenca> getList(){
     return list;
 }

 public void setList(List<DataInfoPenca> info){
     list=info;
 }

}
