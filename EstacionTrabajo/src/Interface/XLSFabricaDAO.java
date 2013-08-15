package Interface;
import Archivo.XLSControladorEquiposDAO;
import Archivo.XLSControladorJugadoresDAO;
import Archivo.XLSControladorUsuariosDAO;
import Archivo.XLSControladorCompeticionesDAO;
import Archivo.XLSControladorAccesosDAO;
public class XLSFabricaDAO {

    public XLSFabricaDAO(){

    }
    public IControladorEquipoDAO getIControladorEquiposDAO(){
        XLSControladorEquiposDAO cEDAO = new XLSControladorEquiposDAO();
        return cEDAO;
    }
    public IControladorJugadoresDAO getIControladorJugadoresDAO(){
        XLSControladorJugadoresDAO cJDAO = new XLSControladorJugadoresDAO();
        return cJDAO;
    }
    public IControladorUsuariosDAO getIControladorUsuariosDAO(){
        XLSControladorUsuariosDAO cJDAO = new XLSControladorUsuariosDAO();
        return cJDAO;
    }
    public IControladorCompeticionesDAO getIControladorCompeticionesDAO(){
        XLSControladorCompeticionesDAO cCDAO = new XLSControladorCompeticionesDAO();
        return cCDAO;
    }

     public IControladorAccesosDAO getIControladorAccesosDAO(){
        XLSControladorAccesosDAO cCDAO = new XLSControladorAccesosDAO();
        return cCDAO;
    }
}