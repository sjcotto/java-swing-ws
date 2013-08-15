/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WebServices;
import DataTypes.DataCompeticion;
import Interface.FabricaWeb;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.xml.ws.Endpoint;

import Interface.IControladorWebCompeticiones;
import Interface.IControladorUsuarios;
import DataTypes.ListBeanDataIdNombre;
import DataTypes.ListBeanDataInfoPenca;
import DataTypes.ListBeanDataPartido;
import DataTypes.ListBeanGoleadores;
import Excepciones.ExDatosNoAsignados;
import Excepciones.ExNoExisteCompeticion;
import Interface.IControladorCompeticiones;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

/**
 *
 * @author tprog084
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class DispMovilWS {


    private Endpoint endpoint = null;

    private Logger log = Logger.getLogger(CompeticionesWS.class.getName());

    private Map<String,IControladorUsuarios> controladores = new HashMap<String,IControladorUsuarios>();
    private Map<String,IControladorWebCompeticiones> controladoresComp = new HashMap<String,IControladorWebCompeticiones>();


    //SESSION
    @WebMethod
    public boolean iniciarSesion(@WebParam String nick,@WebParam String pass){

        System.out.print("Web Service - Inicio Sesion Dispositivo Movil " + nick);

	controladores.put(nick,new Interface.Fabrica().getIControladorUsuarios());
        
        FabricaWeb fw = new FabricaWeb();
        IControladorWebCompeticiones icwc = fw.getIControladorWebCompeticiones();
        controladoresComp.put(nick, icwc);

        controladores.get(nick).setDispMovil(true);

        if (!controladores.get(nick).iniciarSesion(nick, pass)){
            controladores.remove(nick);
            return false;
        }else{
            return true;
        }

    }

    @WebMethod
    public void cerrarSesion(@WebParam String nick)throws Exception{
        System.out.print("Web Service - Cerrar Sesion"+nick);
        //set sigue usando el mismo controlador, hay q sacarlo cuando se cierra la sesion?
        controladores.get(nick).cerrarSesion();
    }
    //SESSION

    @WebMethod
    public void ingresarSaldoUsuario(@WebParam String nick, @WebParam float monto) throws Exception{
        System.out.print("Web Service - Ingresar Monto"+nick);
        controladores.get(nick).ingresarSaldoUsuario(monto);
    }


    //VER APUESTAS

    @WebMethod
    public DataTypes.ListBeanApuesta mostrarHistorialApuestas (
            @WebParam String nick,
            @WebParam DataTypes.DataFechaHora fechaIni,
            @WebParam DataTypes.DataFechaHora fechaFin) throws Exception{
        System.out.print("Web Service - Mostrar Historial Apuestas - DispMovil "+nick);


        java.util.List<DataTypes.DataApuesta> list = controladores.get(nick).mostrarHistorialApuestas(fechaIni, fechaFin);

        java.util.List<DataTypes.DataApuestaWS> ll = new java.util.ArrayList<DataTypes.DataApuestaWS>();

        for (DataTypes.DataApuesta data : list){
            DataTypes.DataApuestaWS d = new DataTypes.DataApuestaWS();

            d.setBeneficio(data.getBeneficio());
            d.setMonto(data.getMonto());
            d.setDividendo(data.getDividendo());
            d.setSaldoNuevo(data.getSaldoNuevo());
            d.setFecha(data.getFecha());
            d.setEstadoApuesta(data.getEstadoApuesta());
            d.setPertenecePaquete(data.getPertenecePaquete());

            if (data instanceof DataTypes.DataApuestaCampeon){
                d.setTipo(0);
                d.setEquipo(((DataTypes.DataApuestaCampeon)data).getEquipo());
                d.setCompeticion(((DataTypes.DataApuestaCampeon)data).getCompeticion());
            }else if (data instanceof DataTypes.DataApuestaGoleador){
                d.setTipo(2);
                d.setCompeticion(((DataTypes.DataApuestaGoleador)data).getCompeticion());
                d.setJugador(((DataTypes.DataApuestaGoleador)data).getJugador());
            }else if (data instanceof DataTypes.DataApuestaResExacto){
                d.setTipo(1);
                d.setPartido(((DataTypes.DataApuestaResExacto)data).getPartido());
                d.setGolesL(((DataTypes.DataApuestaResExacto)data).getGolesL());
                d.setGolesV(((DataTypes.DataApuestaResExacto)data).getGolesV());
            }else if (data instanceof DataTypes.DataApuestaPartido){
                d.setTipo(3);
                d.setPartido(((DataTypes.DataApuestaPartido)data).getPartido());
                d.setTipoResultado(((DataTypes.DataApuestaPartido)data).getTipoResultado());
            }

            ll.add(d);
        }

        return new DataTypes.ListBeanApuesta(ll);
    }


    // APOSTAR //
    @WebMethod
    public void seleccionarCompeticion (@WebParam String nick, @WebParam int id)
        throws Excepciones.ExNoExisteCompeticion, Excepciones.ExCompeticionFinalizada, Excepciones.ExDivsNoAsignados{
        controladores.get(nick).seleccionarCompeticion(id);
    }

    @WebMethod
    public void  seleccionarCampApuesta(@WebParam String nick,@WebParam DataTypes.TipoCampeonatoApuesta tipoA) {
        controladores.get(nick).seleccionarCampApuesta(tipoA);
    }


    @WebMethod
    public void seleccionarPartidoCamp(@WebParam String nick, @WebParam int id)
        throws Excepciones.ExPartidoCampInvalido, Excepciones.ExDivsNoAsignados, Exception{
        controladores.get(nick).seleccionarPartidoCamp(id);
    }

    @WebMethod
    public void apostarPartido (@WebParam String nick, @WebParam DataTypes.TipoDividendo tipo,@WebParam float monto) throws Exception{
        controladores.get(nick).apostarPartido(tipo, monto);
    }

    @WebMethod
    public void apostarCampeonato(@WebParam String nick, @WebParam int idEq,@WebParam float monto) throws Exception{
        controladores.get(nick).apostarCampeonato(idEq, monto);
    }

    @WebMethod
    public void apostarGoleador(@WebParam String nick, @WebParam int idJug,@WebParam  float monto) throws Exception {

        controladores.get(nick).apostarGoleador(idJug, monto);
    }

    @WebMethod
    public void apostarResultadoExacto(@WebParam String nick, @WebParam int golL,@WebParam int golV,@WebParam float monto) throws Exception {
        controladores.get(nick).apostarResultadoExacto(golL, golV, monto);
    }

    @WebMethod
    public DataTypes.DataApuestaWS mostrarApuesta(@WebParam String nick){

        DataTypes.DataApuesta dataAp =  controladores.get(nick).mostrarApuesta();
        DataTypes.DataApuestaWS apWS = new DataTypes.DataApuestaWS();

        apWS.setBeneficio(dataAp.getBeneficio());
        apWS.setMonto(dataAp.getMonto());
        apWS.setDividendo(dataAp.getDividendo());
        apWS.setSaldoNuevo(dataAp.getSaldoNuevo());
        apWS.setFecha(dataAp.getFecha());
        apWS.setEstadoApuesta(dataAp.getEstadoApuesta());
        apWS.setPertenecePaquete(dataAp.getPertenecePaquete());

        if (dataAp instanceof DataTypes.DataApuestaCampeon) {
            DataTypes.DataApuestaCampeon dataApCamp = (DataTypes.DataApuestaCampeon) dataAp;
            apWS.setTipo(0);
            apWS.setEquipo(dataApCamp.getEquipo());
            apWS.setCompeticion(dataApCamp.getCompeticion());
        } else if (dataAp instanceof DataTypes.DataApuestaResExacto) {
            DataTypes.DataApuestaResExacto dataApResExacto = (DataTypes.DataApuestaResExacto) dataAp;
            apWS.setTipo(1);
            apWS.setPartido(dataApResExacto.getPartido());
            apWS.setGolesL(dataApResExacto.getGolesL());
            apWS.setGolesV(dataApResExacto.getGolesV());
        } else if (dataAp instanceof DataTypes.DataApuestaGoleador) {
            DataTypes.DataApuestaGoleador dataApGoleador = (DataTypes.DataApuestaGoleador) dataAp;
            apWS.setTipo(2);
            apWS.setJugador(dataApGoleador.getJugador());
            apWS.setCompeticion(dataApGoleador.getCompeticion());
        } else if (dataAp instanceof DataTypes.DataApuestaPartido) {
            DataTypes.DataApuestaPartido dataApPartido = (DataTypes.DataApuestaPartido) dataAp;
            apWS.setTipo(3);
            apWS.setPartido(dataApPartido.getPartido());
            apWS.setTipoResultado(dataApPartido.getTipoResultado());
        }

        return apWS;
    }

    @WebMethod
    public void confirmarAltaApuesta(@WebParam String nick, @WebParam boolean ok,
            @WebParam boolean paquete) throws Exception{
        
        controladores.get(nick).confirmarAltaApuesta(ok, paquete);
    }

    //public DataInfoPartDividendos mostrarDatosPartido();

    //PARTICIPAR EN PENCA//
    @WebMethod
    public boolean participarEnPenca(@WebParam String nick, @WebParam int idComp)
        throws Exception{
        return controladores.get(nick).participarEnPenca(idComp);
    }

    @WebMethod
    public void confirmarParticipacion(@WebParam String nick, @WebParam boolean ok)
        throws Exception{
        controladores.get(nick).confirmarParticipacion(ok);
    }

    //APOSTAR  PARTIDO EN PENCA//
    @WebMethod
    public DataTypes.ListBeanDataInfoPartido obtenerPartidosNoFinalizadosPenca(@WebParam String nick, @WebParam int idComp)
        throws Exception {
        return new DataTypes.ListBeanDataInfoPartido((java.util.List<DataTypes.DataInfoPartido>)controladores.get(nick).obtenerPartidosNoFinalizadosPenca(idComp));
    }

    @WebMethod
    public void apostarPartidoPenca(@WebParam String nick, @WebParam int idPart,
            @WebParam int golesLoc,@WebParam int golesVis) throws Exception{
        controladores.get(nick).apostarPartidoPenca(idPart, golesLoc, golesVis);
    }

    //VER TABLA POSICIONES PENCA//
    @WebMethod
    public DataTypes.DataInfoPenca verTablaPosicionesPenca(@WebParam String nick, @WebParam int idComp)
        throws Exception {
        return controladores.get(nick).verTablaPosicionesPenca(idComp);
    }

    @WebMethod
    public void crearPaqueteApuestas(@WebParam String nick) throws Exception {

        controladores.get(nick).crearPaqueteApuestas();
    }

    @WebMethod
    public DataTypes.DataPaqueteApuestasWS mostrarPaqueteApuestas(@WebParam String nick)
            throws Excepciones.ExPaqueteNoActivado,Exception{



        DataTypes.DataPaqueteApuestas dataPaqAp = controladores.get(nick).mostrarPaqueteApuestas();
        java.util.List<DataTypes.DataApuesta> listAp = dataPaqAp.getApuestas();

        java.util.List<DataTypes.DataApuestaWS> listRet = new java.util.ArrayList<DataTypes.DataApuestaWS>();

        for (DataTypes.DataApuesta dataAp: listAp) {

            DataTypes.DataApuestaWS apWS = new DataTypes.DataApuestaWS();

            apWS.setBeneficio(dataAp.getBeneficio());
            apWS.setMonto(dataAp.getMonto());
            apWS.setDividendo(dataAp.getDividendo());
            apWS.setSaldoNuevo(dataAp.getSaldoNuevo());
            apWS.setFecha(dataAp.getFecha());
            apWS.setEstadoApuesta(dataAp.getEstadoApuesta());
            apWS.setPertenecePaquete(dataAp.getPertenecePaquete());

            if (dataAp instanceof DataTypes.DataApuestaCampeon) {
                DataTypes.DataApuestaCampeon dataApCamp = (DataTypes.DataApuestaCampeon) dataAp;
                apWS.setTipo(0);
                apWS.setEquipo(dataApCamp.getEquipo());
                apWS.setCompeticion(dataApCamp.getCompeticion());
            } else if (dataAp instanceof DataTypes.DataApuestaResExacto) {
                DataTypes.DataApuestaResExacto dataApResExacto = (DataTypes.DataApuestaResExacto) dataAp;
                apWS.setTipo(1);
                apWS.setPartido(dataApResExacto.getPartido());
                apWS.setGolesL(dataApResExacto.getGolesL());
                apWS.setGolesV(dataApResExacto.getGolesV());
            } else if (dataAp instanceof DataTypes.DataApuestaGoleador) {
                DataTypes.DataApuestaGoleador dataApGoleador = (DataTypes.DataApuestaGoleador) dataAp;
                apWS.setTipo(2);
                apWS.setJugador(dataApGoleador.getJugador());
                apWS.setCompeticion(dataApGoleador.getCompeticion());
            } else if (dataAp instanceof DataTypes.DataApuestaPartido) {
                DataTypes.DataApuestaPartido dataApPartido = (DataTypes.DataApuestaPartido) dataAp;
                apWS.setTipo(3);
                apWS.setPartido(dataApPartido.getPartido());
                apWS.setTipoResultado(dataApPartido.getTipoResultado());
            }

            listRet.add(apWS);
        }

        DataTypes.DataPaqueteApuestasWS ret = new DataTypes.DataPaqueteApuestasWS();
        ret.setApuestas(listRet);
        ret.setEstadoPaquete(dataPaqAp.getEstadoPaquete());
        ret.setMontoTotal(dataPaqAp.getMontoTotal());

        return ret;
    }

    @WebMethod
    public void apostarPartidoPaquete(@WebParam String nick, @WebParam DataTypes.TipoDividendo tipo, @WebParam float monto) throws Exception{
        controladores.get(nick).apostarPartidoPaquete(tipo,monto);
    }

    @WebMethod
    public void apostarCampeonatoPaquete(@WebParam String nick, @WebParam int idEq, @WebParam float monto) throws Exception{
        controladores.get(nick).apostarCampeonatoPaquete(idEq,monto);
    }

    @WebMethod
    public void apostarGoleadorPaquete(@WebParam String nick, @WebParam int idJug, @WebParam  float monto) throws Exception{
        controladores.get(nick).apostarGoleadorPaquete(idJug, monto);
    }


    @WebMethod
    public void apostarResultadoExactoPaquete(@WebParam String nick, @WebParam int golL, @WebParam int golV, @WebParam  float monto) throws Exception{
	controladores.get(nick).apostarResultadoExactoPaquete(golL,golV,monto);
    }

    @WebMethod
    public DataTypes.DataApuestaWS mostrarApuestaPaquete(@WebParam String nick) {
        DataTypes.DataApuesta dataAp = controladores.get(nick).mostrarApuestaPaquete();
        DataTypes.DataApuestaWS apWS = new DataTypes.DataApuestaWS();

        apWS.setBeneficio(dataAp.getBeneficio());
        apWS.setMonto(dataAp.getMonto());
        apWS.setDividendo(dataAp.getDividendo());
        apWS.setSaldoNuevo(dataAp.getSaldoNuevo());
        apWS.setFecha(dataAp.getFecha());
        apWS.setEstadoApuesta(dataAp.getEstadoApuesta());
        apWS.setPertenecePaquete(dataAp.getPertenecePaquete());

        if (dataAp instanceof DataTypes.DataApuestaCampeon) {
            DataTypes.DataApuestaCampeon dataApCamp = (DataTypes.DataApuestaCampeon) dataAp;
            apWS.setTipo(0);
            apWS.setEquipo(dataApCamp.getEquipo());
            apWS.setCompeticion(dataApCamp.getCompeticion());
        } else if (dataAp instanceof DataTypes.DataApuestaResExacto) {
            DataTypes.DataApuestaResExacto dataApResExacto = (DataTypes.DataApuestaResExacto) dataAp;
            apWS.setTipo(1);
            apWS.setPartido(dataApResExacto.getPartido());
            apWS.setGolesL(dataApResExacto.getGolesL());
            apWS.setGolesV(dataApResExacto.getGolesV());
        } else if (dataAp instanceof DataTypes.DataApuestaGoleador) {
            DataTypes.DataApuestaGoleador dataApGoleador = (DataTypes.DataApuestaGoleador) dataAp;
            apWS.setTipo(2);
            apWS.setJugador(dataApGoleador.getJugador());
            apWS.setCompeticion(dataApGoleador.getCompeticion());
        } else if (dataAp instanceof DataTypes.DataApuestaPartido) {
            DataTypes.DataApuestaPartido dataApPartido = (DataTypes.DataApuestaPartido) dataAp;
            apWS.setTipo(3);
            apWS.setPartido(dataApPartido.getPartido());
            apWS.setTipoResultado(dataApPartido.getTipoResultado());
        }

        return apWS;
    }

    @WebMethod
    public void agregarApuestaPaquete(@WebParam String nick){
        controladores.get(nick).agregarApuestaPaquete();
    }

    @WebMethod
    public boolean validarPaqueteApuestas(@WebParam String nick) throws Exception{
	return controladores.get(nick).validarPaqueteApuestas();
    }

    @WebMethod
    public void confirmarPaqueteApuestas(@WebParam String nick) throws Exception{
	controladores.get(nick).confirmarPaqueteApuestas();
    }

    @WebMethod
    public DataTypes.ListBean verPaqueteApuestas(@WebParam String nick) throws Exception{
	return new DataTypes.ListBean(controladores.get(nick).verPaqueteApuestas());
    }


    /// Listar Informacion
    @WebMethod
    public ListBeanDataIdNombre listarCompetencias(){
        FabricaWeb fw = new FabricaWeb();
        IControladorWebCompeticiones icwc = fw.getIControladorWebCompeticiones();
        return new ListBeanDataIdNombre(icwc.listarCompetencias());
    }

    @WebMethod
    public ListBeanDataInfoPenca obtenerInfoPencas(@WebParam String nick){
          return new  ListBeanDataInfoPenca(controladores.get(nick).obtenerInfoPencas());
     } 

    @WebMethod
    public void enviarMensaje(@WebParam int idComp,@WebParam String emisor,@WebParam String receptor,@WebParam boolean publico,@WebParam String mensaje) throws Exception{
        controladores.get(emisor).enviarMensaje(idComp, emisor, receptor, publico, mensaje);
     }

    
    @WebMethod
    public ListBeanGoleadores obtenerJugadoresCampeonato(@WebParam int idC)
            throws Exception {
        
        FabricaWeb f = new FabricaWeb();
        IControladorWebCompeticiones icwc = f.getIControladorWebCompeticiones();
        return new ListBeanGoleadores(icwc.getJugadoresCampeonato(idC));
    }

    //VER INFO USUARIO//
    @WebMethod
    public DataTypes.DataUsuario verPerfilUsuarioLogueado(@WebParam String nick) throws Exception{
        System.out.print("Web Service - Ver Perfil");
        return controladores.get(nick).verPerfilUsuarioLogueado();
    }


    // APOSTAR
    @WebMethod
    public DataCompeticion verInfoCompeticion(@WebParam String nick, @WebParam int id)
            throws ExNoExisteCompeticion{
            return controladoresComp.get(nick).verInfoCompeticion(id);
    }

    @WebMethod
    public ListBeanDataPartido infoPartidosCompeticion(@WebParam String nick)
            throws ExDatosNoAsignados{

        return new ListBeanDataPartido(controladoresComp.get(nick).infoPartidosCompeticion());
    }

    @WebMethod
    public void finalizarVerDetallesComp(@WebParam String nick){
        controladoresComp.get(nick).finalizarVerDetallesComp();
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

        endpoint = Endpoint.publish("http://"+dir+":8280/dispMovilWS?wsdl", this);
        log.info("endpoint DispMovio publicado en http://"+dir+":8280/dispMovilWS");
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }


}
