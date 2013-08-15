/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataTypes;

import java.util.List;

/**
 *
 * @author gonzalo
 */
public class ListBeanGoleadores {
    
    private List<DataGoleador> goleadores;

    public ListBeanGoleadores() {}

    public ListBeanGoleadores(List gol) {
        this.goleadores = gol;
    }

    public List<DataGoleador> getGoleadores() {
        return goleadores;
    }

    public void setGoleadores(List<DataGoleador> goleadores) {
        this.goleadores = goleadores;
    }

}
