/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

import Objetos.Equipo;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author gonzalo
 */
public class ManejadorEquipos {
    // ATRIBUTOS
    private Map<Integer,Equipo> equipos;
    private int idEquipos;

    private static ManejadorEquipos instanciaME=null;

    // SINGLETON
    public static ManejadorEquipos getInstance(){
        if (instanciaME == null)
            instanciaME = new ManejadorEquipos();
        return instanciaME;
    }

    // CONSTRUCTOR
    private ManejadorEquipos(){
        this.equipos  = new HashMap<Integer,Equipo>();
        this.idEquipos=1;
        // BORRARRRRRRRRRRRRR
//        Equipo e = new Equipo(idEquipos,"Penarol","");
//        equipos.put(idEquipos, e);
//        idEquipos++;
//        e = new Equipo(idEquipos,"Sauce","");
//        equipos.put(idEquipos, e);
//        idEquipos++;
//        e = new Equipo(idEquipos,"Neymar","");
//        equipos.put(idEquipos, e);
//        idEquipos++;
//        e = new Equipo(idEquipos,"Barca","");
//        equipos.put(idEquipos, e);
//        idEquipos++;
    }

    public Map<Integer,Equipo> getEquipos () {
        return this.equipos;
    }

    public int getIdEquipos() {
        return this.idEquipos;
    }

    public void setIdEquipos(int id) {
        this.idEquipos = id;
    }

}




