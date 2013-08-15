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
public class ListBeanJugadoresCampeonato {

    private List<DataJugadorCampeonato> jugadoresCamp;

    public ListBeanJugadoresCampeonato() {}

    public ListBeanJugadoresCampeonato(List<DataJugadorCampeonato> jugadoresCamp) {
        this.jugadoresCamp = jugadoresCamp;
    }

    public List<DataJugadorCampeonato> getJugadoresCamp() {
        return jugadoresCamp;
    }

    public void setJugadoresCamp(List<DataJugadorCampeonato> jugadoresCamp) {
        this.jugadoresCamp = jugadoresCamp;
    }
    

}
