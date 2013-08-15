/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Interface;
import Controladores.*;
/**
 *
 * @author santiago
 */
public class Fabrica {
   
    public Fabrica(){

    }
    public IControladorEquipos getIControladorEquipos(){
        ControladorEquipos cE = new ControladorEquipos();
        return cE;
    }
    public IControladorJugadores getIControladorJugadores(){
        ControladorJugadores cJ = new ControladorJugadores();
        return cJ;
    }
    public IControladorCompeticiones getIControladorCompeticiones(){
        return (IControladorCompeticiones) new ControladorCompeticiones();
    }
    public IControladorFecha getIControladorFecha(){
        ControladorFecha cC = ControladorFecha.getInstance();
        return cC;
    }

   public IControladorUsuarios getIControladorUsuarios(){
       ControladorUsuarios cu= new ControladorUsuarios();
       return cu;
   }

    public IAdminUsuarios getIAdminUsuarios() {
        return (IAdminUsuarios) new ControladorUsuarios();
    }

    public IControladorDatos getIControladorDatos() {
        return (IControladorDatos) new ManejadorDatos();
    }

    public IControladorAccesosAdmin getIControladorAccesosAdmin() {
        return ControladorAccesos.getInstance();
    }
}
