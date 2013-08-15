/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataTypes;

/**
 *
 * @author gonzalo
 */

public class DataJugadorCampeonato implements Comparable  {

    private DataJugador jugador;
    private float dividendo;
    private int goles;

    public DataJugadorCampeonato() {}

    public DataJugadorCampeonato(DataJugador jugador, float dividendo, int goles) {
        this.jugador = jugador;
        this.dividendo = dividendo;
        this.goles = goles;
    }

    public void setDividendo(float dividendo) {
        this.dividendo = dividendo;
    }

    public void setGoles(int goles) {
        this.goles = goles;
    }

    public void setJugador(DataJugador jugador) {
        this.jugador = jugador;
    }

    public float getDividendo() {
        return dividendo;
    }

    public int getGoles() {
        return goles;
    }

    public DataJugador getJugador() {
        return jugador;
    }

     @Override
        public int compareTo (Object i){
             DataJugadorCampeonato data = (DataJugadorCampeonato) i;
             if (this.goles> data.getGoles()){
                return -1;
             }
             if (this.goles< data.getGoles()){
                return 1;
             }
             return 0;
         }


}
