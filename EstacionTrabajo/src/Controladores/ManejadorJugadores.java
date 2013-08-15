/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

import Objetos.Jugador;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author gonzalo
 */
public class ManejadorJugadores {

    private Map<Integer,Jugador> jugadores;
    private static ManejadorJugadores instanciaMJ=null;
    private int idActual;
    
    public static ManejadorJugadores getInstance(){
        if (instanciaMJ == null)
            instanciaMJ = new ManejadorJugadores();
        return instanciaMJ;
    }
    
    private ManejadorJugadores(){
        this.jugadores  = new HashMap<Integer,Jugador>();
        idActual=1;
    }

    public Map<Integer,Jugador> getJugadores() {
        return this.jugadores;
    }

    public int getIdActual() {
        return this.idActual;
    }

    public void setIdActual(int id) {
        this.idActual = id;
    }

}



