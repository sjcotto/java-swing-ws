/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Interface;

import Controladores.ControladorCompeticiones;
import Controladores.ControladorUsuarios;
import Controladores.ControladorAccesos;

/**
 *
 * @author gonzalo
 */
public class FabricaWeb {
    public FabricaWeb(){
    
    }

    public IControladorWebCompeticiones getIControladorWebCompeticiones() {
        return (IControladorWebCompeticiones) new ControladorCompeticiones();
    }

    public IControladorUsuarios getIControladorUsuarios() {
        return (IControladorUsuarios) new ControladorUsuarios();
    }

    public IControladorAccesosWeb getIControladorAccesosWeb() {
        return ControladorAccesos.getInstance();
    }
}
