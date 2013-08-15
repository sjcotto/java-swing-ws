/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WebServices;
import Interface.FabricaWeb;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.xml.ws.Endpoint;
import java.util.logging.Logger;
import DataTypes.*;
import Excepciones.*;
import java.util.HashMap;
import java.util.Map;
import Interface.IControladorWebCompeticiones;
import java.io.FileInputStream;
import java.util.Properties;

/**
 *
 * @author Santiago
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, parameterStyle = ParameterStyle.WRAPPED)

public class CompeticionesWS {

    private Endpoint endpoint = null;

    private Logger log = Logger.getLogger(CompeticionesWS.class.getName());

    private Map<String,IControladorWebCompeticiones> controladores = new HashMap<String,IControladorWebCompeticiones>();

    @WebMethod
    public void agregarCompeticionSesion(@WebParam String sessionID) {
        controladores.put(sessionID, new FabricaWeb().getIControladorWebCompeticiones());
    }

    @WebMethod
    public ListBeanDataPartidoComp listarPartidos(@WebParam String sessionID, @WebParam ListBeanFiltro filtros){

        java.util.List<DataFiltroWS> list = filtros.getList();
        java.util.List<DataFiltro> listFiltros = new java.util.ArrayList<DataFiltro>();

        if (list != null) {
            for (DataFiltroWS filtro: list) {
                DataFiltro dataF= null;
                if (filtro.getTipoFiltro()==0) {
                    dataF = new FiltroCompeticion(filtro.getNomComp());
                } else if (filtro.getTipoFiltro()==1) {
                    dataF = new FiltroEquipo(filtro.getNomEq());
                } else if (filtro.getTipoFiltro()==2) {
                    dataF = new FiltroEstadoPartido(filtro.getEstado());
                } else if (filtro.getTipoFiltro()==3) {
                    dataF = new FiltroLugarEncuentro(filtro.getLugarEnc());
                } else if (filtro.getTipoFiltro()==4) {
                    if (filtro.getTipoDividendo()==TipoDividendo.Local) {
                        dataF = new FiltroRangoDividendosLocal(filtro.getRangoInicial(),filtro.getRangoFinal());
                    } else if (filtro.getTipoDividendo() == TipoDividendo.Empate) {
                        dataF = new FiltroRangoDividendosEmpate(filtro.getRangoInicial(),filtro.getRangoFinal());
                    } else {
                        dataF = new FiltroRangoDividendosVisitante(filtro.getRangoInicial(),filtro.getRangoFinal());
                    }
                } else if (filtro.getTipoFiltro()==5) {
                    dataF = new FiltroRangoFecha(filtro.getFechaIni(),filtro.getFechaFin());
                }

                listFiltros.add(dataF);
            }
        }

        return new ListBeanDataPartidoComp(controladores.get(sessionID).listarPartidos(listFiltros));
    }


    //juan
    @WebMethod
    public DataPartido seleccionarPartido(@WebParam String sessionID, @WebParam int idComp,@WebParam int idPart)
            throws ExNoExisteCompeticion,ExNoExistePartidoEnCompeticion{

            return controladores.get(sessionID).seleccionarPartido(idComp, idPart);
    }

    @WebMethod
    public float[][] obtenerDividendosResultadoExacto(@WebParam String sessionID, @WebParam int idComp, @WebParam int idPart) {
        return controladores.get(sessionID).obtenerDividendosResultadoExacto(idComp,idPart);
    }

    //juan
    @WebMethod
    public DataJugPartido verInfoPartidoFinalizado(@WebParam String sessionID)
            throws ExNoExisteCompeticion{
            return controladores.get(sessionID).verInfoPartidoFinalizado();
    }


    @WebMethod
    public void finalizarDetallesPartido(@WebParam String sessionID){
        controladores.get(sessionID).finalizarDetallesPartido();
    }


    // VER TABLA POSICIONES
    @WebMethod
    public ListBean listarLigasDefinidas(@WebParam String sessionID){
        return new ListBean(controladores.get(sessionID).listarLigasDefinidas());
    }

    @WebMethod
    public ListBeanDataEquipoLiga obtenerTablaDePosiciones(@WebParam String sessionID, @WebParam int idLiga){
        return new ListBeanDataEquipoLiga(controladores.get(sessionID).obtenerTablaDePosiciones(idLiga));
    }


    // VER ULTIMOS PARTIDOS
    @WebMethod
    public  ListBean obtenerUltimosPartidosFinalizados(@WebParam String sessionID){
        return new ListBean(controladores.get(sessionID).obtenerUltimosPartidosFinalizados());
    }

    // VER DETALLES COMPETICION
    @WebMethod
    public ListBean listarCompetencias(@WebParam String sessionID){
        return new ListBean(controladores.get(sessionID).listarCompetencias());
    }

    @WebMethod
    public DataCompeticion verInfoCompeticion(@WebParam String sessionID, @WebParam int id)
            throws ExNoExisteCompeticion{
            return controladores.get(sessionID).verInfoCompeticion(id);
    }

    @WebMethod
    public ListBean infoPartidosCompeticion(@WebParam String sessionID)
            throws ExDatosNoAsignados{
            return new ListBean(controladores.get(sessionID).infoPartidosCompeticion());
    }

    @WebMethod
    public DataJugPartido listarJugadoresPorBando(@WebParam String sessionID, @WebParam int idPart)
            throws ExDatosNoAsignados, ExNoExistePartidoEnCompeticion{
            return controladores.get(sessionID).listarJugadoresPorBando(idPart);
    }

    @WebMethod
    public void finalizarVerDetallesComp(@WebParam String sessionID){
        controladores.get(sessionID).finalizarVerDetallesComp();
    }

    // VER DETALLES EQUIPOS

    @WebMethod
    public ListBeanEquipo listarEquipos(@WebParam String sessionID){
        return new ListBeanEquipo(controladores.get(sessionID).listarEquipos());
    }

    @WebMethod
    public ListBean listarJugEquipo(@WebParam String sessionID, @WebParam int id)
            throws ExNoExisteEquipo{
            return new ListBean(controladores.get(sessionID).listarJugEquipo(id));
    }

    // VER DETALLES JUGADOR
    public ListBean listaJugadores(@WebParam String sessionID){
        return new ListBean(controladores.get(sessionID).listaJugadores());
    }

    @WebMethod
    public DataJugador seleccionarJugador(@WebParam String sessionID, @WebParam int id)
            throws ExNoExisteJugador{
            return controladores.get(sessionID).seleccionarJugador(id);
    }

    @WebMethod
    public String getImagePath(@WebParam String sessionID, @WebParam int idEquipo){
        return controladores.get(sessionID).getImagePath(idEquipo);
    }

    // CHANCHADA
    @WebMethod
    public boolean competicionEstaFinalizada(@WebParam String sessionID, @WebParam int idComp){
        return controladores.get(sessionID).competicionEstaFinalizada(idComp);
    }

    //pablo
    @WebMethod
    public ListBean listarPencasDisponibles(@WebParam String sessionID){
        return new ListBean(controladores.get(sessionID).listarPencasDisponibles());
    }

    // Jugadores de una comp
    @WebMethod
    public ListBeanGoleadores getJugadoresCampeonato(@WebParam String sessionID, @WebParam int idC)
            throws ExNoExisteCompeticion, Exception {
        return new ListBeanGoleadores(controladores.get(sessionID).getJugadoresCampeonato(idC));
    }

    // 5 Maximos goleadores
    @WebMethod
    public ListBeanGoleadores get5MaxGoleadores(@WebParam String sessionID,@WebParam int idC)
            throws ExNoExisteCompeticion, Exception {
        return new ListBeanGoleadores(controladores.get(sessionID).get5MaxGoleadores(idC));
    }
            

    @WebMethod(exclude = true)
    public void publicar() {
        log.info("publicando el endpoint mf");

         String dir = null;

        try {
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream("web_services.xml");
            prop.loadFromXML(fis);
            prop.list(System.out);
            dir = prop.getProperty("dir_publicar");
            fis.close();

        }
        catch (Exception exc){
            System.out.println("Error leyendo archivo properties: "+exc.getMessage());
        }

        endpoint = Endpoint.publish("http://"+dir+":8280/competicionesWS", this);
        log.info("endpoint Competiciones publicado en http://"+dir+":8280/competicionesWS");
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }
}
