package WebServices;

import DataTypes.TipoDividendo;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.xml.ws.Endpoint;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.Map;
import Interface.IControladorUsuarios;
import Interface.FabricaWeb;
import java.io.FileInputStream;
import java.util.Properties;


@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class UsuarioWS {
    private Endpoint endpoint = null;

    private Logger log = Logger.getLogger(UsuarioWS.class.getName());
    
    private Map<String,IControladorUsuarios> controladores = new HashMap<String,IControladorUsuarios>();

    @WebMethod
    public boolean registrarUsuario(@WebParam String sessionID, @WebParam String nombre,
            @WebParam String nick,@WebParam String mail,@WebParam String dir,
            @WebParam String pass,@WebParam DataTypes.TipoSexo sexo,
            @WebParam String pais,@WebParam String ci,
            @WebParam DataTypes.DataFechaHora fechaNac){

        System.out.print("Web Service - Registro Usuario");
        
        if (!controladores.containsKey(sessionID))
            controladores.put(sessionID, new FabricaWeb().getIControladorUsuarios());

        return controladores.get(sessionID).registrarUsuario(nombre, nick, mail, dir, pass, sexo, pais, ci, fechaNac);
    }

    @WebMethod
    public void agregarUsuarioSesion (@WebParam String sessionID) {
        controladores.put(sessionID, new FabricaWeb().getIControladorUsuarios());
    }

    @WebMethod
    public boolean existeNick(@WebParam String sessionID, @WebParam String nick){
        return controladores.get(sessionID).existeNick(nick);
    }
    @WebMethod
    public boolean existeMail(@WebParam String sessionID, @WebParam String mail){
       return controladores.get(sessionID).existeMail(mail);
    }

    @WebMethod
    public boolean iniciarSesion(@WebParam String sessionID, @WebParam String nick,@WebParam String pass){
        
        
         System.out.print("Web Service - Inicio Sesion********************");
         //System.out.println(controladores.get(sessionID).seleccionarUsuario(nick).getNick());
	  
	 controladores.put(sessionID,new Interface.Fabrica().getIControladorUsuarios());

         controladores.get(sessionID).setDispMovil(false);//lo nuevo, para identificar de los de dispmovil

         return controladores.get(sessionID).iniciarSesion(nick, pass);
    }

    @WebMethod
    public DataTypes.DataUsuario seleccionarUsuario(@WebParam String sessionID, @WebParam String nick){
        return controladores.get(sessionID).seleccionarUsuario(nick);
    }

    @WebMethod
    public void cerrarSesion(@WebParam String sessionID)throws Exception{
        System.out.print("Web Service - Cerrar Sesion");
        controladores.get(sessionID).cerrarSesion();
    }

    //INGRESAR SALDO MONEDERO//
    @WebMethod
    public float mostrarSaldoUsuario(@WebParam String sessionID)throws Exception{
        System.out.print("Web Service - Mostar Saldo");
        return controladores.get(sessionID).mostrarSaldoUsuario();
    }

    @WebMethod
    public DataTypes.DataUsuario obtenerUsuarioLogueado(@WebParam String sessionID) {
        return controladores.get(sessionID).obtenerUsuarioLogueado();
    }

    @WebMethod
    public void ingresarSaldoUsuario(@WebParam String sessionID, @WebParam float monto) throws Exception{
        System.out.print("Web Service - Ingresar Monto");
        controladores.get(sessionID).ingresarSaldoUsuario(monto);
    }

    //VER INFO USUARIO//
    @WebMethod
    public DataTypes.DataUsuario verPerfilUsuarioLogueado(@WebParam String sessionID) throws Exception{
        System.out.print("Web Service - Ver Perfil");
        return controladores.get(sessionID).verPerfilUsuarioLogueado();
    }

    @WebMethod
    public DataTypes.ListBeanApuesta mostrarHistorialApuestas (
            @WebParam String sessionID,
            @WebParam DataTypes.DataFechaHora fechaIni,
            @WebParam DataTypes.DataFechaHora fechaFin) throws Exception{
        System.out.print("Web Service - Mostrar Historial Apuestas");

        java.util.List<DataTypes.DataApuesta> list = controladores.get(sessionID).mostrarHistorialApuestas(fechaIni, fechaFin);

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
    public void seleccionarCompeticion (@WebParam String sessionID, @WebParam int id)
        throws Excepciones.ExNoExisteCompeticion, Excepciones.ExCompeticionFinalizada, Excepciones.ExDivsNoAsignados{
        controladores.get(sessionID).seleccionarCompeticion(id);
    }

    @WebMethod
    public void  seleccionarCampApuesta(@WebParam String sessionID,@WebParam DataTypes.TipoCampeonatoApuesta tipoA) {
        controladores.get(sessionID).seleccionarCampApuesta(tipoA);
    }


    @WebMethod
    public void seleccionarPartidoCamp(@WebParam String sessionID, @WebParam int id)
        throws Excepciones.ExPartidoCampInvalido, Excepciones.ExDivsNoAsignados, Exception{
        controladores.get(sessionID).seleccionarPartidoCamp(id);
    }

    @WebMethod
    public void apostarPartido (@WebParam String sessionID, @WebParam DataTypes.TipoDividendo tipo,@WebParam float monto) throws Exception{
        controladores.get(sessionID).apostarPartido(tipo, monto);
    }

    @WebMethod
    public void apostarCampeonato(@WebParam String sessionID, @WebParam int idEq,@WebParam float monto) throws Exception{
        controladores.get(sessionID).apostarCampeonato(idEq, monto);
    }

    @WebMethod
    public void apostarGoleador(@WebParam String sessionID, @WebParam int idJug,@WebParam  float monto) throws Exception {

        controladores.get(sessionID).apostarGoleador(idJug, monto);
    }

    @WebMethod
    public void apostarResultadoExacto(@WebParam String sessionID, @WebParam int golL,@WebParam int golV,@WebParam float monto) throws Exception {
        controladores.get(sessionID).apostarResultadoExacto(golL, golV, monto);
    }

    @WebMethod
    public DataTypes.DataApuestaWS mostrarApuesta(@WebParam String sessionID){
        
        DataTypes.DataApuesta dataAp =  controladores.get(sessionID).mostrarApuesta();
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
    public void confirmarAltaApuesta(@WebParam String sessionID, @WebParam boolean ok,
            @WebParam boolean paquete) throws Exception{
        
        controladores.get(sessionID).confirmarAltaApuesta(ok, paquete);
    }

    //public DataInfoPartDividendos mostrarDatosPartido();

    //PARTICIPAR EN PENCA//
    @WebMethod
    public boolean participarEnPenca(@WebParam String sessionID, @WebParam int idComp)
        throws Exception{
        return controladores.get(sessionID).participarEnPenca(idComp);
    }

    @WebMethod
    public void confirmarParticipacion(@WebParam String sessionID, @WebParam boolean ok)
        throws Exception{
        controladores.get(sessionID).confirmarParticipacion(ok);
    }

    //APOSTAR  PARTIDO EN PENCA//
    @WebMethod
    public DataTypes.ListBean obtenerPartidosNoFinalizadosPenca(@WebParam String sessionID, @WebParam int idComp)
        throws Exception {
        return new DataTypes.ListBean((java.util.List<DataTypes.DataInfoPartido>)controladores.get(sessionID).obtenerPartidosNoFinalizadosPenca(idComp));
    }

    @WebMethod
    public void apostarPartidoPenca(@WebParam String sessionID, @WebParam int idPart,
            @WebParam int golesLoc,@WebParam int golesVis) throws Exception{
        controladores.get(sessionID).apostarPartidoPenca(idPart, golesLoc, golesVis);
    }

    //VER TABLA POSICIONES PENCA//
    @WebMethod
    public DataTypes.DataInfoPenca verTablaPosicionesPenca(@WebParam String sessionID, @WebParam int idComp)
        throws Exception {
        return controladores.get(sessionID).verTablaPosicionesPenca(idComp);
    }

    @WebMethod
    public void enviarMensaje(@WebParam String emisor, @WebParam int idComp, @WebParam String receptor,
            @WebParam String mensaje, @WebParam boolean publico) throws Exception{
        controladores.get(emisor).enviarMensaje(idComp,emisor,receptor,publico,mensaje);
    }

    @WebMethod
    public void crearPaqueteApuestas(@WebParam String sessionID) throws Exception {

        controladores.get(sessionID).crearPaqueteApuestas();
    }

    @WebMethod
    public DataTypes.DataPaqueteApuestasWS mostrarPaqueteApuestas(@WebParam String sessionID)
            throws Excepciones.ExPaqueteNoActivado,Exception{
        
        

        DataTypes.DataPaqueteApuestas dataPaqAp = controladores.get(sessionID).mostrarPaqueteApuestas();
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
    public void apostarPartidoPaquete(@WebParam String sessionID, @WebParam DataTypes.TipoDividendo tipo, @WebParam float monto) throws Exception{
        controladores.get(sessionID).apostarPartidoPaquete(tipo,monto);
    }

    @WebMethod
    public void apostarCampeonatoPaquete(@WebParam String sessionID, @WebParam int idEq, @WebParam float monto) throws Exception{
        controladores.get(sessionID).apostarCampeonatoPaquete(idEq,monto);
    }

    @WebMethod
    public void apostarGoleadorPaquete(@WebParam String sessionID, @WebParam int idJug, @WebParam  float monto) throws Exception{
        controladores.get(sessionID).apostarGoleadorPaquete(idJug, monto);
    }



    @WebMethod
    public void apostarResultadoExactoPaquete(@WebParam String sessionID, @WebParam int golL, @WebParam int golV, @WebParam  float monto) throws Exception{
	controladores.get(sessionID).apostarResultadoExactoPaquete(golL,golV,monto);
    }

    @WebMethod
    public DataTypes.DataApuestaWS mostrarApuestaPaquete(@WebParam String sessionID) {
        DataTypes.DataApuesta dataAp = controladores.get(sessionID).mostrarApuestaPaquete();
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
    public void agregarApuestaPaquete(@WebParam String sessionID){
        controladores.get(sessionID).agregarApuestaPaquete();
    }

    @WebMethod
    public boolean validarPaqueteApuestas(@WebParam String sessionID) throws Exception{
	return controladores.get(sessionID).validarPaqueteApuestas();
    }

    @WebMethod
    public void confirmarPaqueteApuestas(@WebParam String sessionID) throws Exception{
	controladores.get(sessionID).confirmarPaqueteApuestas();
    }

    @WebMethod
    public DataTypes.ListBean verPaqueteApuestas(@WebParam String sessionID) throws Exception{
	return new DataTypes.ListBean(controladores.get(sessionID).verPaqueteApuestas());
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


        endpoint = Endpoint.publish("http://"+dir+":8280/usuarioWS", this);
        log.info("endpoint calculator publicado en http://"+dir+":8280/usuarioWS");
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

     @WebMethod
    public void setNotificaciones(@WebParam String sessionID, @WebParam boolean notA,
           @WebParam boolean notF,@WebParam boolean notP) throws Exception{
        controladores.get(sessionID).setNotificacion(notA,notF, notP);
    }
    @WebMethod
    public void retirarSaldoUsuario(@WebParam String sessionID, @WebParam float monto) throws Exception{
        controladores.get(sessionID).retirarSaldoUsuario(monto);
    }
}