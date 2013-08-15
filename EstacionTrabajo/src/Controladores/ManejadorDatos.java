/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Interface.*;

import Interface.IControladorCompeticiones;


import Interface.Fabrica;
import Interface.IControladorEquipos;
import Interface.IControladorCompeticiones;
import Interface.IControladorJugadores;
import Interface.IControladorUsuarios;


public class ManejadorDatos implements IControladorDatos{

    public ManejadorDatos() {
        System.out.println("ManejaorDedatos");
    }


    public void cargar(String ruta){
        try{

            Fabrica f = new Fabrica();
            IControladorEquipos ice = f.getIControladorEquipos();
            System.out.println("COMENZO CARGAR");
            ice.cargarEquipos(ruta);
            IControladorJugadores icj = f.getIControladorJugadores();
            icj.cargarJugadores(ruta);
            IControladorUsuarios icu = f.getIControladorUsuarios();
            icu.setNotificarAltaApuestas(false);
            icu.cargarUsuarios(ruta);
            IControladorAccesosAdmin ica = f.getIControladorAccesosAdmin();
            ica.cargarAccesos(ruta);

            System.out.println("COMENZO COMPETICIONES");
            IControladorCompeticiones icc = f.getIControladorCompeticiones();
            icc.cargarCompeticiones(ruta);
            System.out.println("CARGO");


            /*hay un error, cuando se cargalos datos, primero se cargan los usuarios y despues cmpeticiones
             * ya q sino, daria una excepcion por los observers, pero al pasar esto los observes le estan sumando el saldo a las competiciones finalizadas
             */
        }
        catch(Exception e){
            System.out.println("NO ANDUVO");
        }

    }

    public void guardar(String ruta){
        try{
            Fabrica f = new Fabrica();
            System.out.println("Guarda Equipos");
            IControladorEquipos ice = f.getIControladorEquipos();
            ice.guardarEquipos(ruta);
            IControladorJugadores icj = f.getIControladorJugadores();
            System.out.println("Guarda Jugadores");
            icj.guardarJugadores(ruta);
            IControladorUsuarios icu = f.getIControladorUsuarios();
            System.out.println("Guarda Usuarios");
            icu.guardarUsuarios(ruta);

            IControladorAccesosAdmin ica = f.getIControladorAccesosAdmin();
            ica.guardarAccesos(ruta);

            IControladorCompeticiones icc = f.getIControladorCompeticiones();
            System.out.println("Guarda Competiciones");
            icc.guardarCompeticiones(ruta);
            System.out.println("Fin guardar");
         }
        catch(Exception e){
            System.out.println("no guardo");
            e.printStackTrace();
        }
    }

}
