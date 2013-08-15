/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Interface;

import DataTypes.DataUsuario;
import DataTypes.DataFechaHora;
import DataTypes.DataDetalleApuesta;
import DataTypes.DataInfoPartDividendos;
import DataTypes.DataInfoPenca;
import DataTypes.DataPaqueteApuestas;
import Excepciones.*;
import java.util.List;

import DataTypes.TipoDividendo;

/**
 *
 * @author tprog082
 */
public interface IControladorUsuarios {

    // REGISTRO USUARIO
    public boolean registrarUsuario(String nombre,String nick, String mail, String dir,
        String pass, DataTypes.TipoSexo sexo, String pais, String ci,DataFechaHora fechaNac);

    // INICIAR SESION
    public boolean iniciarSesion(String nick, String pass);
    public DataUsuario seleccionarUsuario(String nick);//NO BORRAR!!!! firma:el puto del cotto

    // CERRAR SESION
    public void cerrarSesion()throws Exception;

    // VER PERFIL USUARIO
    public DataUsuario verPerfilUsuarioLogueado() throws Exception;

    // INGRESAR SALDO MONEDERO
    public float mostrarSaldoUsuario()throws Exception;
    public void ingresarSaldoUsuario(float monto) throws Exception;

    // VER DETALLES APUESTAS
    public List<DataTypes.DataApuesta> mostrarHistorialApuestas (DataFechaHora fechaIni, DataFechaHora fechaFin)
        throws ExFechaInvalida, ExRangoInvalido;


    // VALIDACION DE NICK Y MAIL USADO AJAX
    public boolean existeNick(String nick);
    public boolean existeMail(String mail);


    // APOSTAR
    public void seleccionarCompeticion (int id)
            throws ExNoExisteCompeticion, ExCompeticionFinalizada, ExDivsNoAsignados;

    /* si es apuesta por partido */
        /*si es campeonato */ public void  seleccionarCampApuesta(DataTypes.TipoCampeonatoApuesta tipoA);
                              public void seleccionarPartidoCamp(int id) throws ExPartidoCampInvalido, ExDivsNoAsignados,Exception;


            public void apostarPartido (DataTypes.TipoDividendo tipo, float monto) throws Exception;

            public void apostarResultadoExacto(int golL, int golV, float monto) throws Exception;
    //////////////////////////////////

   /*si es apuesta por ganador campeonato */
        //public void  seleccionarCampApuesta(DataTypes.TipoCampeonatoApuesta tipoA);
        public void apostarCampeonato(int idEq, float monto)
            throws Exception;

        public void apostarGoleador(int idJug, float monto)
            throws Exception;

    //pa los 2...
    public DataTypes.DataApuesta mostrarApuesta();
    public void confirmarAltaApuesta(boolean ok, boolean paquete) throws Exception;
    // FIN APOSTAR

    // ADMINISTRADOS: LISTAR USUARIOS
    public List<String> listarNickUsuarios();
    //public DataUsuario seleccionarUsuario(String nick);

    // NUEVO CARGAR DATOS DE PRUEBA
    public void setearSaldo(String nick, float saldo);

    public DataTypes.DataUsuario obtenerUsuarioLogueado();

    // NOSE
    public DataInfoPartDividendos mostrarDatosPartido();

    // PERSISTENCIA
    public void cargarUsuarios(String ruta)throws Exception;
    public void guardarUsuarios(String ruta)throws Exception;


         //PARTICIPAR EN PENCA//
    public boolean participarEnPenca(int idComp) throws ExNoExisteCompeticion,
         ExCampeonatoIncorrecto,Exception;

    public void confirmarParticipacion(boolean ok) throws ExDatosNoAsignados,Exception,ExCompeticionFinalizada;
           
       //APOSTAR  PARTIDO EN PENCA//
    
     public List<DataTypes.DataInfoPartido> obtenerPartidosNoFinalizadosPenca(int idComp) throws ExNoExisteCompeticion,
			 ExCampeonatoIncorrecto,Exception, ExUsuarioNoParticipaEnPenca;

    public void apostarPartidoPenca(int idPart,int golesLoc,int golesVis) throws Exception,ExCompeticionFinalizada,ExUsuarioNoParticipaEnPenca,ExUsuarioYaApostoPartido;
     
         //VER TABLA POSICIONES PENCA//

     public DataTypes.DataInfoPenca verTablaPosicionesPenca(int idComp) throws Exception,ExNoExisteCompeticion,ExCampeonatoIncorrecto;


     // Mensajes
     public void enviarMensaje(int idComp,String emisor,String receptor,boolean publico,String mensaje) throws Exception;
	/*
      *************** PAQUETE APUESTAS *********************
      */

    // Crea una instancia de DataPaqueteApuestas(si no existia una) y se lo setea al ControladorUsuario
    public void crearPaqueteApuestas() throws Exception;

    // Muestra el DataPaqueteApuestas del usuario, cuidado no modificar
    public DataPaqueteApuestas mostrarPaqueteApuestas() throws Exception;

    // Crea un DataApuesta y lo guarda en memoria temporal
    public void apostarPartidoPaquete(TipoDividendo tipo, float monto) throws Exception;

    // Crea un DataApuesta y lo guarda en memoria temporal
    public void apostarCampeonatoPaquete(int idEq, float monto) throws Exception;

    // Crea un DataApuesta y lo guarda en memoria temporal
    public void apostarGoleadorPaquete(int idJug, float monto) throws Exception;


    // Crea un DataApuesta y lo guarda en memoria temporal
    public void apostarResultadoExactoPaquete(int golL, int golV, float monto) throws Exception;

    // Muestra la apuesta a agregar al paquete
    public DataTypes.DataApuesta mostrarApuestaPaquete();

    //Agrega la apuesta en memoria al DataPaqueteApuestas en memoria temporal
    public void agregarApuestaPaquete();

    // Se valida el PaqueteApuesta del Usuario
    // Si es invalido se eliminan las instancias de DataApuesta asociadas
    public boolean validarPaqueteApuestas() throws Exception;

    // Crea instancias de Apuesta y asocia el DataPaqueteApuestas al Usuario
    // actualiza su saldo y lo desreferencia de la memoria temporal.
    public void confirmarPaqueteApuestas() throws Exception;
	
	// VER DETALLES APUESTAS

    // Muestra los paquetes de apuestas creados por el usuario autenticado
    public List<DataPaqueteApuestas> verPaqueteApuestas() throws Exception;

    public void setDispMovil(boolean value);
    public boolean getDispMovil();

    public List<DataInfoPenca> obtenerInfoPencas();
    public boolean isNotificarAltaApuestas();
    public void setNotificarAltaApuestas(boolean notificarAltaApuestas);
    public void notificarAltaApuesta(String mensaje)throws Exception;
    public void setNotificacion(boolean notA,boolean notF,boolean notP)throws Exception;
    public void notificarResultadoApuesta(String nick,String mensaje)throws Exception;
    public void notificarForo(String nickReceptor,String mensaje)throws Exception;
    public void notificarPartido(String mensaje)throws Exception;
    public void retirarSaldoUsuario(float monto) throws Exception;
    
}
