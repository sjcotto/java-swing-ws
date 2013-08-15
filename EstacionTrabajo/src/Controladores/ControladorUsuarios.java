/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import Objetos.Usuario;
import Interface.IControladorUsuarios;
import Interface.IAdminUsuarios;
import Interface.IControladorUsuariosDAO;
import Interface.XLSFabricaDAO;
import Objetos.Competicion;
import Objetos.Partido;
import Objetos.Campeonato;
import Objetos.PartidoIndividual;
import Objetos.Apuesta;
import Objetos.ApuestaPartido;
import DataTypes.*;
import Excepciones.*;
import Objetos.Copa;

import Objetos.PaqueteApuestas;
import Objetos.Jugador;
import Objetos.Liga;
import Objetos.Mensaje;
import Mensajes.EnviarMail;
import org.jasypt.util.password.BasicPasswordEncryptor;

public class ControladorUsuarios implements IControladorUsuarios, IAdminUsuarios {
    private String mensaje="";
    private Usuario usuActual = null;
    private Competicion comp;
    private DataApuesta da;
    private TipoDividendo tipo_div;
    private int id_equipo_mem;
    private Partido p;
    private TipoCampeonatoApuesta tipo_camp_a;
    EnviarMail envios=new EnviarMail();
	//************ PAQUETE APUESTAS *********************//
    private DataPaqueteApuestas paqueteApuestas;
    private PaqueteApuestas paq;
    private DataApuesta dataApMem;
    //************ PAQUETE APUESTAS *********************//
	
     // participar en penca
    private Campeonato auxCamp;
    public ControladorUsuarios(){
        comp = null;
        da = null;
        tipo_div = null;
        p = null;
        paqueteApuestas = null;
        paq = null;
    }

    /*********************************************/
    /*************** PARTE WEB *******************/
    /*********************************************/

    // REGISTRO USUARIO
    public boolean registrarUsuario(String nombre,String nick, String mail, String dir,
        String pass, DataTypes.TipoSexo sexo, String pais, String ci,DataFechaHora fechaNac) {

        ManejadorUsuarios mu = ManejadorUsuarios.getInstance();

        if (mu.existenDatos(nick, mail))
            return false;

        Usuario usu = new Usuario(nombre,mail,nick,dir,pass,fechaNac,ci,pais,sexo);
        mu.setUsuario(usu);

        return true;
    }

    // INICIAR SESION
    public boolean iniciarSesion(String nick, String pass) {
        if (!ManejadorUsuarios.getInstance().getUsuariosNick().containsKey(nick))
            return false;
        else{
            Usuario u = ManejadorUsuarios.getInstance().getUsuariosNick().get(nick);

           BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();

            if ( passwordEncryptor.checkPassword(pass,u.getPass())){
                usuActual = ManejadorUsuarios.getInstance().getUsuario(nick);
                if (this.dispMovil)
                    ManejadorUsuarios.getInstance().getUsuariosConectadosDisp().put(nick, usuActual);
                else
                    ManejadorUsuarios.getInstance().getUsuariosConectados().put(nick, usuActual);
                
                return true;
            }else{
                return false;
            }
        }//se guarda en la memoria de seccion informacion sobre la misma, el nick
    }

    // CERRAR SESION
    public void cerrarSesion() throws Exception{

        if (this.dispMovil){
            if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectadosDisp().containsKey(usuActual.getNick()))){
                usuActual = null;
                throw new Exception("Error: No ha iniciado sesion");
            }
            ManejadorUsuarios.getInstance().getUsuariosConectadosDisp().remove(usuActual.getNick());
            usuActual = null;
        }else{
            if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectados().containsKey(usuActual.getNick()))){
                usuActual = null;
                throw new Exception("Error: No ha iniciado sesion");
            }
            ManejadorUsuarios.getInstance().getUsuariosConectados().remove(usuActual.getNick());
            usuActual = null;
        }
    }

    // VER PERFIL USUARIO
    public DataUsuario verPerfilUsuarioLogueado()throws Exception{
        if (this.dispMovil){
            if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectadosDisp().containsKey(usuActual.getNick()))){
                usuActual = null;
                throw new Exception("Error: No ha iniciado sesion");
            }
        }else{
            if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectados().containsKey(usuActual.getNick()))){
                usuActual = null;
                throw new Exception("Error: No ha iniciado sesion");
            }
        }

        Usuario u = usuActual;
        return u.getDataUsuario();
    }

    // INGRESAR SALDO MONEDERO
    public float mostrarSaldoUsuario()throws Exception {
        if (this.dispMovil){
            if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectadosDisp().containsKey(usuActual.getNick()))){
                usuActual = null;
                throw new Exception("Error: No ha iniciado sesion");
            }
        }else{
            if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectados().containsKey(usuActual.getNick()))){
                usuActual = null;
                throw new Exception("Error: No ha iniciado sesion");
            }
        }

        return this.usuActual.getSaldo();
    }

    public void ingresarSaldoUsuario(float monto) throws Exception{
        if (this.dispMovil){
            if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectadosDisp().containsKey(usuActual.getNick()))){
                usuActual = null;
                throw new Exception("Error: No ha iniciado sesion");
            }
        }else{
            if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectados().containsKey(usuActual.getNick()))){
                usuActual = null;
                throw new Exception("Error: No ha iniciado sesion");
            }
        }

        usuActual = ManejadorUsuarios.getInstance().getUsuario(usuActual.getNick());

        if (monto <= 0)
            throw new ExMontoInvalido("El monto ingresado no es valido");

        usuActual.setSaldo(usuActual.getSaldo()+monto);
    }

    // APUESTAS// APUESTAS
    public void seleccionarCompeticion (int id)
            throws ExNoExisteCompeticion, ExCompeticionFinalizada, ExDivsNoAsignados{

        ControladorCompeticiones cc = new ControladorCompeticiones();
        comp = cc.buscarCompeticion(id);

        if (comp == null)
            throw new ExNoExisteCompeticion("No existe competicion");

        /*if (!comp.getEstaDefinido())
            throw new ExCompeticionIndefinida("La competicion no esta definida");*/

        if (comp.getFinalizado())
            throw new ExCompeticionFinalizada("La competencia esta finalizada");

        p = comp.obtenerPartidoApuesta(id);

        if ((comp instanceof PartidoIndividual)){
            if (p == null || !p.estanAsignadosDividendos()){
                p = null;
                throw new ExDivsNoAsignados("El partido individual no tiene divs asignados");
            }
        }

    }

    public void seleccionarCampApuesta (TipoCampeonatoApuesta tipoA){
        tipo_camp_a = tipoA;
    }

    public void seleccionarPartidoCamp (int id)
            throws ExPartidoCampInvalido, ExDivsNoAsignados, Exception{
        p = ((Campeonato)comp).obtenerPartidoApuesta(id);
        if (p==null)
            throw new ExPartidoCampInvalido("El partido no existe en el campeonato");
        if (!p.getDividendos().getEstanAsignados()){
            p = null;
            throw new ExDivsNoAsignados("Los dividendos del partido no estan asignados");
        }
        if (p.getFinalizo()) {
            p = null;
            throw new Exception("El partido esta finalizado");
        }
    }

    public void apostarPartido (TipoDividendo tipo, float monto) throws Exception{
        
        //vemos si existe su sesion
        if (this.dispMovil){
            if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectadosDisp().containsKey(usuActual.getNick()))){
                usuActual = null;
                throw new Exception("Error: No ha iniciado sesion");
            }
        }else{
            if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectados().containsKey(usuActual.getNick()))){
                usuActual = null;
                throw new Exception("Error: No ha iniciado sesion");
            }
        }


        if (usuActual.getSaldo() < monto || monto <= 0)
            throw new ExMontoInvalido("El monto es invalido");

        da = p.obtenerDataApuestaPart(tipo,monto,usuActual.getSaldo(),
                comp.getId(),comp.getNombre(),comp.getTipo());
        tipo_div = tipo;
    }

    public void apostarCampeonato(int idEq, float monto)
            throws Exception{

        //vemos si existe su sesion
        if (this.dispMovil){
            if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectadosDisp().containsKey(usuActual.getNick()))){
                usuActual = null;
                throw new Exception("Error: No ha iniciado sesion");
            }
        }else{
            if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectados().containsKey(usuActual.getNick()))){
                usuActual = null;
                throw new Exception("Error: No ha iniciado sesion");
            }
        }


        if (usuActual.getSaldo() < monto || monto <= 0)
            throw new ExMontoInvalido("El monto es invalido");

        // No puede ser competicion de PartidoIndividual
        if (comp instanceof PartidoIndividual)
            throw new Exception("No es posible realizar este tipo de apuesta para Partido Individual");

        da = ((Campeonato)comp).obtenerDatosApuesta(idEq,monto,usuActual.getSaldo(),comp.getDataCompeticion());
        if (da == null)
              System.out.println("da es null");
        id_equipo_mem = idEq;
        p = null;
    }

	public void apostarGoleador(int idJug, float monto)
            throws Exception{

            //vemos si existe su sesion
            if (this.dispMovil){
                if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectadosDisp().containsKey(usuActual.getNick()))){
                    usuActual = null;
                    throw new Exception("Error: No ha iniciado sesion");
                }
            }else{
                if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectados().containsKey(usuActual.getNick()))){
                    usuActual = null;
                    throw new Exception("Error: No ha iniciado sesion");
                }
            }

            if (usuActual.getSaldo() < monto || monto <= 0)
                throw new ExMontoInvalido("El monto es invalido");

        // No puede ser competicion de PartidoIndividual
            if (comp instanceof PartidoIndividual)
                throw new Exception("No es posible realizar este tipo de apuesta para Partido Individual");

            // Tiene que existir el jugador
            ControladorJugadores contJug = new ControladorJugadores();
            Jugador jug = contJug.getJugador(idJug);
            if (jug==null)
                throw new Exception("No existe el jugador");

            /**/
            float dividendo = ((Campeonato)comp).obtenerDividendoJugadorCampeonato(idJug);
            float beneficio = dividendo*monto;
            float saldoNuevo = this.usuActual.getSaldo()+beneficio;

            da =new DataApuestaGoleador(monto,saldoNuevo,beneficio,
                    dividendo,jug.getDataJugador(),comp.getDataCompeticion(),EstadoApuesta.Pendiente,1);
            p = null;
            id_equipo_mem = -1;
    }

    public void apostarResultadoExacto(int golL, int golV, float monto)
            throws Exception{

        //vemos si existe su sesion
        if (this.dispMovil){
            if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectadosDisp().containsKey(usuActual.getNick()))){
                usuActual = null;
                throw new Exception("Error: No ha iniciado sesion");
            }
        }else{
            if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectados().containsKey(usuActual.getNick()))){
                usuActual = null;
                throw new Exception("Error: No ha iniciado sesion");
            }
        }

        // Ya tengo el comp, p(Si se necesitan)

        tipo_div = null;// PARA SABER QUE EL TIPO DE APUESTA ES RESULTADO EXACTO

        if (usuActual.getSaldo() < monto || monto <= 0)
            throw new ExMontoInvalido("El monto es invalido");

        da = p.obtenerDataApuestaResExactoPart(golL,golV,monto,usuActual.getSaldo(),
                comp.getId(),comp.getNombre(),comp.getTipo());

    }
	
    public DataApuesta mostrarApuesta (){
        return da;
    }


    public void confirmarAltaApuesta(boolean ok, boolean paquete) throws Exception{  // cambiado
        mensaje="";
        //vemos si existe su sesion
        if (this.dispMovil){
            if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectadosDisp().containsKey(usuActual.getNick()))){
                usuActual = null;
                throw new Exception("Error: No ha iniciado sesion");
            }
        }else{
            if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectados().containsKey(usuActual.getNick()))){
                usuActual = null;
                throw new Exception("Error: No ha iniciado sesion");
            }
        }

        if (ok){
            boolean esPartido = (p != null);

            usuActual = ManejadorUsuarios.getInstance().getUsuario(usuActual.getNick());

            Apuesta a = usuActual.crearApuesta(da, esPartido, tipo_div);

             Interface.IControladorFecha icf = (new Interface.Fabrica()).getIControladorFecha();
             //hay que verificar que la apuesta sea valida aun
             //la competicion no este finalizada

             boolean error=false;

             if(a instanceof Objetos.ApuestaCampeonato){
                 if(comp.getFinalizado()){
                     error=true;
                 }
             }

             if(a instanceof Objetos.ApuestaGoleador){
                 if(comp.getFinalizado()){
                     error=true;
                 }
             }
             //partido finalizado
             if(a instanceof Objetos.ApuestaResultadoExacto||a instanceof Objetos.ApuestaPartido){
                 if(p.getFinalizo()){
                    error=true;
                 }
             }
             if(error){
                this.notificarAltaApuesta("Lamentablemente su apuesta no fue aceptada, intentelo nuevamente");   
                throw new Exception("Error: la apuesta no fue aceptada");
             }

            if (esPartido) {
                ((ApuestaPartido)a).agregarApuestaPartidoCamp(p);
            } else {
                if (this.id_equipo_mem!=-1) {
                    comp.agregarApuestaEquipoCamp(a,id_equipo_mem);
                }
            }

            // Crear link de PaqueteApuesta a Apuesta
            // Crear link de Apuesta a PaqueteApuestas
            // Crear PaqueteApuestas
            // Agregar el paquete al Usuario

            if (paquete){
                this.paq.agregarApuestaPaquete(a);
                a.agregarPaqueteApuesta(this.paq);
            }
            String aux=a.getMensaje(false,false);
            if(!paquete){
                mensaje="Su apuesta ha sido aceptada\n\n"+aux;
                this.notificarAltaApuesta(mensaje);
                mensaje="";
            }
            else{
                mensaje+=aux;
            }
        

        }
        da = null;
        p = null;
        tipo_div = null;
        id_equipo_mem = -1;
        comp = null;
        
    }

    // VER DETALLES APUESTA
    public List<DataApuesta> mostrarHistorialApuestas (DataFechaHora fechaIni, DataFechaHora fechaFin)
            throws ExFechaInvalida, ExRangoInvalido{


        if (fechaIni == null ||
           (fechaIni .getAnio()<=0)||(fechaIni .getDia()> 31) || (fechaIni .getDia()<=0) ||
           (fechaIni .getMes()<=0) || (fechaIni .getMes()>12)||
           (fechaIni .getHora()< 0) || (fechaIni .getHora()>23) ||
           (fechaIni .getMinuto()<0) || (fechaIni .getMinuto()>59)) {
               throw (new ExFechaInvalida("La Fecha y Hora ingresadas para el Partido no es Correcta"));
        }

        if (fechaFin == null ||
           (fechaFin .getAnio()<=0)||(fechaFin .getDia()> 31) || (fechaFin .getDia()<=0) ||
           (fechaFin .getMes()<=0) || (fechaFin .getMes()>12)||
           (fechaFin .getHora()< 0) || (fechaFin .getHora()>23) ||
           (fechaFin .getMinuto()<0) || (fechaFin .getMinuto()>59)) {
               throw (new ExFechaInvalida("La Fecha y Hora ingresadas para el Partido no es Correcta"));
        }

        if (fechaIni.compareTo(fechaFin) > 0)
            throw new ExRangoInvalido("El rango es invalido");
        usuActual = ManejadorUsuarios.getInstance().getUsuario(this.usuActual.getNick());

        return usuActual.mostrarHistorialApuestas(fechaIni, fechaFin);
    }


    // VALIDACION DE NICK Y MAIL USADO AJAX
    public boolean existeNick(String nick){
        return (ManejadorUsuarios.getInstance().getUsuariosNick().containsKey(nick));
    }

    public boolean existeMail(String mail){
        return (ManejadorUsuarios.getInstance().getUsuariosMail().containsKey(mail));
    }

    // NOSE
    public DataInfoPartDividendos mostrarDatosPartido(){ // cambiada
        return p.getDataInfoPartIndividual();
    }

    public List<DataDivEquipo> listarEquiposDiv (){
        return ((Campeonato)comp).obtenerDivsPartCamp();
    }


    /*********************************************/
    /*************** PARTE ADMIN *****************/
    /*********************************************/

    // VER DETALLES USUARIO
    public List<String> listarNickUsuarios(){

        List<String> list = new ArrayList();
        for(Map.Entry<String,Usuario> entry : ManejadorUsuarios.getInstance().getUsuariosNick().entrySet()){
            list.add(entry.getValue().getNick());
        }
        return list;
    }

    public DataUsuario seleccionarUsuario(String nick){
        Usuario u = ManejadorUsuarios.getInstance().getUsuariosNick().get(nick);
        return u.getDataUsuario();
    }


    /*********************************************/
    /*************** PERSISTENCIA ****************/
    /*********************************************/

   public void guardarUsuarios(String ruta)throws Exception{
        //recorro los usuarios tomo los datos de los usuarios
        //agrego en otra lista las apuestas
        List<DTOUsuario> lU=new ArrayList<DTOUsuario>();

        for(Map.Entry<String,Usuario> entry: ManejadorUsuarios.getInstance().getUsuariosNick().entrySet()) {
            Usuario u = entry.getValue();
            DataUsuario dU=u.getDataUsuario();
            boolean logeado=ManejadorUsuarios.getInstance().getEstaLogeado(u.getNick());
            DTOUsuario dto=new DTOUsuario(dU,u.getPass(),logeado,u.getDataPaqueteApuestasPersistencia());
            lU.add(dto);
        }
        XLSFabricaDAO f=new XLSFabricaDAO();
        IControladorUsuariosDAO cu=f.getIControladorUsuariosDAO();
        cu.guardarUsuarios(ruta,lU);
        ControladorFecha cf = ControladorFecha.getInstance();
        DataFechaHora fecha=cf.getFecha();
        cu.guardarFecha(ruta, fecha);
    }


   public void cargarUsuarios(String ruta)throws Exception{

        ManejadorUsuarios.getInstance().getUsuariosConectados().clear();
        ManejadorUsuarios.getInstance().getUsuariosMail().clear();
        ManejadorUsuarios.getInstance().getUsuariosNick().clear();

        XLSFabricaDAO f=new XLSFabricaDAO();
        IControladorUsuariosDAO cu=f.getIControladorUsuariosDAO();
        List<DTOUsuario> lista=cu.cargarUsuarios(ruta);
        ManejadorUsuarios mu = ManejadorUsuarios.getInstance();
        for(int i=0;i<lista.size();i++){
            //registro directamente los usuarios
            DTOUsuario dto=lista.get(i);
            DataUsuario dU=dto.getUsuario();
            Usuario usu = new Usuario(dU.getNombre(),dU.getEmail(),dU.getNick(),dU.getDir(),
            dto.getPass(),dU.getFechaNac(),dU.getCI(),dU.getPais(),dU.getSexo());
            usu.setNotApuesta(dU.isNotApuesta());
            usu.setNotForo(dU.isNotForo());
            usu.setNotPartido(dU.isNotPartido());
            mu.setUsuario(usu);
            if(dto.getLogeado()){
               mu.setLogeado(usu);
            }
            usu.setSaldo(dU.getSaldo());
            usu.agregarPaqueteApuestasPersistencia(dto.getPaquetes());
        }

        ControladorFecha cf = ControladorFecha.getInstance();
        DataFechaHora fecha=cu.cargarFecha(ruta);
        cf.setFecha(fecha);
    }

    //hay que ver como se cargan las apuestas
    public void cargaAC(Apuesta apuesta,String nick,int idP){
        ManejadorUsuarios mu = ManejadorUsuarios.getInstance();
        Usuario usu = mu.getUsuario(nick);
        usu.agregarApuestaCargar(apuesta,idP);
        System.out.println(" ENTRO EN CARGA APU USUARIO");
    }

     //cotrolador usuarios---ademas agregar a interfaz
	public void setearSaldo(String nick, float saldo){
        ManejadorUsuarios mu = ManejadorUsuarios.getInstance();
        Usuario usu = mu.getUsuario(nick);
            usu.setSaldo(saldo);
    }
    

	public DataTypes.DataUsuario obtenerUsuarioLogueado(){
		if (this.usuActual == null)
			return null;
		return usuActual.getDataUsuario();
	}


  /////////PARTICIPAR EN PENCA////////////////

	public boolean participarEnPenca(int idComp) throws ExNoExisteCompeticion,
		 ExCampeonatoIncorrecto,Exception{
	 ManejadorCompeticiones mc = ManejadorCompeticiones.getInstance();
	 Map<Integer,Competicion> comps = mc.getCompeticiones();
	 Competicion c = comps.get(idComp);



	 if (c==null)
		 throw (new ExNoExisteCompeticion("No Existe la Competicion con id "+ Integer.toString(idComp)));
	 if (c instanceof PartidoIndividual)
		  throw (new ExCampeonatoIncorrecto("El Campeonato Seleccionado es un Partido Individual" ) );

	 //vemos si existe su sesion
         if (this.dispMovil){
            if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectadosDisp().containsKey(usuActual.getNick()))){
                usuActual = null;
                throw new Exception("Error: No ha iniciado sesion");
            }
         }else{
            if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectados().containsKey(usuActual.getNick()))){
                usuActual = null;
                throw new Exception("Error: No ha iniciado sesion");
            }
         }

	 if (c.getFinalizado())
		 throw new ExCompeticionFinalizada("La competicion con id "+ Integer.toString(idComp)+" ya ha finalizado");

         usuActual = ManejadorUsuarios.getInstance().getUsuario(usuActual.getNick());

	 Campeonato camp=(Campeonato)c;
	 boolean ok=!camp.participaEnPenca(usuActual) && (camp.getMontoPenca()<=usuActual.getSaldo());
	 	
         auxCamp=camp;
	 return ok;

	}

    public void confirmarParticipacion(boolean ok) throws ExDatosNoAsignados,Exception,ExCompeticionFinalizada{

        if (auxCamp==null)
                      throw new ExDatosNoAsignados("No existe en la memoria Temporal ninguna Compeonato al cual Participar");

             //vemos si existe su sesion
            if (this.dispMovil){
                if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectadosDisp().containsKey(usuActual.getNick()))){
                    usuActual = null;
                    throw new Exception("Error: No ha iniciado sesion");
                }
            }else{
                if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectados().containsKey(usuActual.getNick()))){
                    usuActual = null;
                    throw new Exception("Error: No ha iniciado sesion");
                }
            }

             if (auxCamp.getFinalizado())
                     throw new ExCompeticionFinalizada("La competicion ya ha finalizado");

             usuActual = ManejadorUsuarios.getInstance().getUsuario(usuActual.getNick());

             auxCamp = (Campeonato)ManejadorCompeticiones.getInstance().getCompeticiones().get(auxCamp.getId());

             if (ok) {
                     auxCamp.confirmarParticipacion(usuActual);
              }
             auxCamp=null;
          }



		/// APOSTAR PARTIDO PENCA////////////////
	 public List<DataInfoPartido> obtenerPartidosNoFinalizadosPenca(int idComp) throws ExNoExisteCompeticion,
			 ExCampeonatoIncorrecto,Exception, ExUsuarioNoParticipaEnPenca{
		ManejadorCompeticiones mc = ManejadorCompeticiones.getInstance();
		 Map<Integer,Competicion> comps = mc.getCompeticiones();
		 Competicion c = comps.get(idComp);

		 if (c==null)
			 throw (new ExNoExisteCompeticion("No Existe la Competicion con id "+ Integer.toString(idComp)));
		 if (c instanceof PartidoIndividual)
			  throw (new ExCampeonatoIncorrecto("El Campeonato Seleccionado es un Partido Individual" ) );

		 //vemos si existe su sesion
                if (this.dispMovil){
                    if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectadosDisp().containsKey(usuActual.getNick()))){
                        usuActual = null;
                        throw new Exception("Error: No ha iniciado sesion");
                    }
                }else{
                    if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectados().containsKey(usuActual.getNick()))){
                        usuActual = null;
                        throw new Exception("Error: No ha iniciado sesion");
                    }
                }

		if (c.getFinalizado())
			 throw new ExCompeticionFinalizada("La competicion con id "+ Integer.toString(idComp)+" ya ha finalizado");
		 Campeonato camp=(Campeonato)c;
		 if (!camp.participaEnPenca(usuActual))
			 throw new ExUsuarioNoParticipaEnPenca("El Usuario no Participa en esta Penca");
		 List<DataInfoPartido> parts = camp.obtenerPartidosNoFinalizadosPenca(usuActual);
		 auxCamp= camp;

	 return parts;

	 }

	

	 ////////VER TABLA POSICIONES PENCA///////////

	 public DataInfoPenca verTablaPosicionesPenca(int idComp) throws Exception,ExNoExisteCompeticion,ExCampeonatoIncorrecto{

               ManejadorCompeticiones mc = ManejadorCompeticiones.getInstance();
               Map<Integer,Competicion> comps = mc.getCompeticiones();
               Competicion c = comps.get(idComp);

               if (c==null)
                     throw (new ExNoExisteCompeticion("No Existe la Competicion con id "+ Integer.toString(idComp)));
               if (c instanceof PartidoIndividual)
                      throw (new ExCampeonatoIncorrecto("El Campeonato Seleccionado es un Partido Individual" ) );

               //vemos si existe su sesion
                if (this.dispMovil){
                    if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectadosDisp().containsKey(usuActual.getNick()))){
                        usuActual = null;
                        throw new Exception("Error: No ha iniciado sesion");
                    }
                }else{
                    if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectados().containsKey(usuActual.getNick()))){
                        usuActual = null;
                        throw new Exception("Error: No ha iniciado sesion");
                    }
                }


               Campeonato camp=(Campeonato)c;
               DataInfoPenca inf =  camp.obtenerDatosPenca(usuActual.getNick());
               List<Mensaje> mens=camp.getForo().getMensajes();
               List<DataMensajePenca> lista = new ArrayList<DataMensajePenca>();
               int i =0;
               for(Mensaje m:mens){
                  DataMensajePenca dmp= m.getDataMensajePenca();
                  dmp.setIndex(i);
                  lista.add(dmp);
                  i++;
               }
               inf.setMensajes(lista);
               return inf;


	 }

        // Enviar Mensaje
        public void enviarMensaje(int idComp,String emisor,String receptor,boolean publico,String mensaje)
            throws Exception{
            ManejadorCompeticiones mc = ManejadorCompeticiones.getInstance();
            Map<Integer,Competicion> comps = mc.getCompeticiones();
            Competicion c = comps.get(idComp);

            if (c==null)
                throw (new ExNoExisteCompeticion("No Existe la Competicion con id "+ Integer.toString(idComp)));
            if (c instanceof PartidoIndividual)
                throw (new ExCampeonatoIncorrecto("El Campeonato Seleccionado es un Partido Individual" ) );

            Campeonato camp = (Campeonato) c;
            camp.agregarMensaje(emisor,receptor,publico,mensaje);

        }
 
	/*
	*************** PAQUETE APUESTAS *********************
	*/

    // Crea una instancia de DataPaqueteApuestas(si no existia una) y se lo setea al ControladorUsuario
    public void crearPaqueteApuestas() throws Exception{
        //vemos si existe su sesion
        if (this.dispMovil){
            if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectadosDisp().containsKey(usuActual.getNick()))){
                usuActual = null;
                throw new Exception("Error: No ha iniciado sesion");
            }
        }else{
            if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectados().containsKey(usuActual.getNick()))){
                usuActual = null;
                throw new Exception("Error: No ha iniciado sesion");
            }
        }

        if (this.paqueteApuestas == null){
            this.paqueteApuestas = new DataPaqueteApuestas();
        }
    }

    // Muestra el DataPaqueteApuestas del usuario
    // NO MODIFICAR LOS DATATYPES!!!!!!
    public DataPaqueteApuestas mostrarPaqueteApuestas() throws Exception {
        //vemos si existe su sesion
        if (this.dispMovil){
            if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectadosDisp().containsKey(usuActual.getNick()))){
                usuActual = null;
                throw new Exception("Error: No ha iniciado sesion");
            }
        }else{
            if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectados().containsKey(usuActual.getNick()))){
                usuActual = null;
                throw new Exception("Error: No ha iniciado sesion");
            }
        }

        if (this.paqueteApuestas==null)
            throw new ExPaqueteNoActivado("No se ha activado el paquete de apuestas");

        return this.paqueteApuestas;
    }

    // Crea un DataApuesta y lo guarda en memoria temporal
    public void apostarPartidoPaquete(TipoDividendo tipo, float monto) throws Exception{
        //vemos si existe su sesion
        if (this.dispMovil){
            if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectadosDisp().containsKey(usuActual.getNick()))){
                usuActual = null;
                throw new Exception("Error: No ha iniciado sesion");
            }
        }else{
            if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectados().containsKey(usuActual.getNick()))){
                usuActual = null;
                throw new Exception("Error: No ha iniciado sesion");
            }
        }

        //p = ((Partido)ManejadorCompeticiones.getInstance().getCompeticiones().get(comp.getId()).obtenerPartidoApuesta(p.getId()));

        // Ya tengo el comp y tipo_camp_a, p(Si se necesitan)
        this.dataApMem = p.obtenerDataApuestaPart(tipo,monto,usuActual.getSaldo(),
                comp.getId(),comp.getNombre(),comp.getTipo());
        // da= p.obtenerDatosApuesta(..);
        //tipo_div = tipo;

    }

    // Crea un DataApuesta y lo guarda en memoria temporal
    public void apostarCampeonatoPaquete(int idEq, float monto) throws Exception{
        //vemos si existe su sesion
        if (this.dispMovil){
            if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectadosDisp().containsKey(usuActual.getNick()))){
                usuActual = null;
                throw new Exception("Error: No ha iniciado sesion");
            }
        }else{
            if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectados().containsKey(usuActual.getNick()))){
                usuActual = null;
                throw new Exception("Error: No ha iniciado sesion");
            }
        }

        // No puede ser competicion de PartidoIndividual
        if (comp instanceof PartidoIndividual)
            throw new Exception("No es posible realizar este tipo de apuesta para Partido Individual");

        this.dataApMem = ((Campeonato)comp).obtenerDatosApuesta(idEq,monto,usuActual.getSaldo(),comp.getDataCompeticion());
        // da= comp.obtenerDatosApuesta(..);
        //id_equipo_mem = idEq;
        //p = null;
    }

    // Crea un DataApuesta y lo guarda en memoria temporal
    public void apostarGoleadorPaquete(int idJug, float monto) throws Exception{

        //vemos si existe su sesion
        if (this.dispMovil){
            if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectadosDisp().containsKey(usuActual.getNick()))){
                usuActual = null;
                throw new Exception("Error: No ha iniciado sesion");
            }
        }else{
            if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectados().containsKey(usuActual.getNick()))){
                usuActual = null;
                throw new Exception("Error: No ha iniciado sesion");
            }
        }

        // No puede ser competicion de PartidoIndividual
        if (comp instanceof PartidoIndividual)
            throw new Exception("No es posible realizar este tipo de apuesta para Partido Individual");

        // Tiene que existir el jugador
        ControladorJugadores contJug = new ControladorJugadores();
        Jugador jug = contJug.getJugador(idJug);
        if (jug==null)
            throw new Exception("No existe el jugador");

        float dividendo = ((Campeonato)comp).obtenerDividendoJugadorCampeonato(idJug);
        float beneficio = dividendo*monto;
        float saldoNuevo = this.usuActual.getSaldo()+beneficio;

        this.dataApMem =new DataApuestaGoleador(monto,saldoNuevo,beneficio,
                dividendo,jug.getDataJugador(),comp.getDataCompeticion(),EstadoApuesta.Pendiente,1);

        // da= comp.obtenerDatosApuesta(..);
        //p = null;
        //tipo_div = null;

    }

    

    // Crea un DataApuesta y lo guarda en memoria temporal
    public void apostarResultadoExactoPaquete(int golL, int golV, float monto) throws Exception{
        //vemos si existe su sesion
        if (this.dispMovil){
            if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectadosDisp().containsKey(usuActual.getNick()))){
                usuActual = null;
                throw new Exception("Error: No ha iniciado sesion");
            }
        }else{
            if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectados().containsKey(usuActual.getNick()))){
                usuActual = null;
                throw new Exception("Error: No ha iniciado sesion");
            }
        }

        // Ya tengo el comp y tipo_camp_a, p(Si se necesitan)
        this.dataApMem = p.obtenerDataApuestaResExactoPart(golL,golV,monto,usuActual.getSaldo(),
                comp.getId(),comp.getNombre(),comp.getTipo());
        // da= p.obtenerDatosApuesta(..);
        //tipo_div = tipo;

    }

    //Agrega la apuesta en memoria al DataPaqueteApuestas en memoria temporal
    public void agregarApuestaPaquete() {
        if (this.paqueteApuestas==null)
            this.paqueteApuestas = new DataPaqueteApuestas();
        this.paqueteApuestas.getApuestas().add(this.dataApMem);
        this.paqueteApuestas.setMontoTotal(this.paqueteApuestas.getMontoTotal()+this.dataApMem.getMonto()*this.dataApMem.getDividendo());

    }

    // Muestra la apuesta a agregar al paquete
    public DataTypes.DataApuesta mostrarApuestaPaquete(){
        return this.dataApMem;
    }

    // Se valida el PaqueteApuesta del Usuario
    // Si es invalido se eliminan las instancias de DataApuesta asociadas
    public boolean validarPaqueteApuestas() throws Exception {
        //vemos si existe su sesion
        if (this.dispMovil){
            if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectadosDisp().containsKey(usuActual.getNick()))){
                usuActual = null;
                throw new Exception("Error: No ha iniciado sesion");
            }
        }else{
            if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectados().containsKey(usuActual.getNick()))){
                usuActual = null;
                throw new Exception("Error: No ha iniciado sesion");
            }
        }

        if (this.paqueteApuestas==null)
            throw new ExPaqueteNoActivado("No se ha activado el paquete de apuestas");

        // Tiene que tener de 3 a 5 apuestas
        // Monto total tiene que ser menor al saldo del usuario
        // Tiene que haber 2 apuestas por Competicion o Partido distintos
        boolean paqueteValido = true;
        int nroApuestas = this.paqueteApuestas.getApuestas().size();

        if (!(3 <= nroApuestas && nroApuestas <= 5))
            paqueteValido = false;

        // Obtengo el monto total de las apuestas
        float monto = 0;
        for (DataApuesta dataA: this.paqueteApuestas.getApuestas()){
            monto += dataA.getMonto();
        }

        if(paqueteValido && !(monto<=this.usuActual.getSaldo()))
            paqueteValido = false;

        if (paqueteValido) {
            paqueteValido = false;

            // Para cada apuesta me fijo en todas las otras
            // si encuentro alguna distinta termino!

            for (DataApuesta apuesta1: this.paqueteApuestas.getApuestas()) {
                if (!paqueteValido) {
                    int idCompeticion1 = -1;
                    int idPartido1 = -1;

                    if (apuesta1 instanceof DataApuestaCampeon) {
                        DataApuestaCampeon apuestaCampeon = (DataApuestaCampeon) apuesta1;
                        idCompeticion1 = apuestaCampeon.getCompeticion().getId();
                    }
                    else if(apuesta1 instanceof DataApuestaGoleador) {
                        DataApuestaGoleador apuestaGoleador = (DataApuestaGoleador) apuesta1;
                        idCompeticion1 = apuestaGoleador.getCompeticion().getId();
                    }
                    else if(apuesta1 instanceof DataApuestaPartido) {
                        DataApuestaPartido apuestaPartido = (DataApuestaPartido) apuesta1;
                        idCompeticion1 = apuestaPartido.getPartido().getDataInfoPart().getIdComp();
                        idPartido1 = apuestaPartido.getPartido().getDataInfoPart().getIdPar();
                    }
                    else if(apuesta1 instanceof DataApuestaResExacto) {
                        DataApuestaResExacto apuestaResExacto = (DataApuestaResExacto) apuesta1;
                        idCompeticion1 = apuestaResExacto.getPartido().getDataInfoPart().getIdComp();
                        idPartido1 = apuestaResExacto.getPartido().getDataInfoPart().getIdPar();
                    }

                    for (DataApuesta apuesta2: this.paqueteApuestas.getApuestas()) {
                        if (!paqueteValido) {
                            int idCompeticion2 = -1;
                            int idPartido2 = -1;

                            if (apuesta2 instanceof DataApuestaCampeon) {
                                DataApuestaCampeon apuestaCampeon = (DataApuestaCampeon) apuesta2;
                                idCompeticion2 = apuestaCampeon.getCompeticion().getId();
                            }
                            else if(apuesta2 instanceof DataApuestaGoleador) {
                                DataApuestaGoleador apuestaGoleador = (DataApuestaGoleador) apuesta2;
                                idCompeticion2 = apuestaGoleador.getCompeticion().getId();
                            }
                            else if(apuesta2 instanceof DataApuestaPartido) {
                                DataApuestaPartido apuestaPartido = (DataApuestaPartido) apuesta2;
                                idCompeticion2 = apuestaPartido.getPartido().getDataInfoPart().getIdComp();
                                idPartido2 = apuestaPartido.getPartido().getDataInfoPart().getIdPar();
                            }
                            else if(apuesta2 instanceof DataApuestaResExacto) {
                                DataApuestaResExacto apuestaResExacto = (DataApuestaResExacto) apuesta2;
                                idCompeticion2 = apuestaResExacto.getPartido().getDataInfoPart().getIdComp();
                                idPartido2 = apuestaResExacto.getPartido().getDataInfoPart().getIdPar();
                            }

                            if (idCompeticion2 != idCompeticion1)
                                paqueteValido = true;

                            if (idPartido1 != -1 && idPartido2!=-1 && idPartido1!=idPartido2)
                                paqueteValido = true;

                        }
                    }
                }
            }
        }

        if (!paqueteValido) {
	    //String aux="IBet le notifica que no ha sido aceptado su paquete de apuestas realizado el :"+
              //      this.paqueteApuestas.getApuestas().get(0).getFecha().toString();

	    String aux="IBet le notifica que no ha sido aceptado su paquete de apuestas realizado el :";

            this.notificarAltaApuesta(aux);
	    this.paqueteApuestas.getApuestas().clear();
            this.paqueteApuestas.setMontoTotal(0);
        }

        return paqueteValido;

    }

    // Crea instancias de Apuesta y de PaqueteApuestas y los asocia al Usuario
    // actualiza su saldo y lo desreferencia de la memoria temporal.
   // Crea instancias de Apuesta y de PaqueteApuestas y los asocia al Usuario
    // actualiza su saldo y lo desreferencia de la memoria temporal.
    public void confirmarPaqueteApuestas() throws Exception {
        // Para cada apuesta la confirmo como antes
        // Si se termina una apuesta que estaba en el paquete se la trata como una comun pero
        // se agrega que le avise al paquete si pertenece a a uno.
        // Si es la ultima y se cumple que todas fueron exitosas se calcula un 5%
        // de monto total de las apuestas y se lo suma al usuario.
        if (this.dispMovil){
            if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectadosDisp().containsKey(usuActual.getNick()))){
                usuActual = null;
                throw new Exception("Error: No ha iniciado sesion");
            }
        }else{
            if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectados().containsKey(usuActual.getNick()))){
                usuActual = null;
                throw new Exception("Error: No ha iniciado sesion");
            }
        }
        if (this.paqueteApuestas==null){
            mensaje="";
            throw new ExPaqueteNoActivado("No se ha activado el paquete de apuestas");
        }

        try{
            preVerificarPaqueteApuestas();//hace la recorrida sin setear nada solo verificando que este todo bien
        }
        catch(Exception e){
            
            String aux="IBet le notifica que no ha sido aceptado su paquete de apuestas realizado el :"+
                    this.paqueteApuestas.getApuestas().get(0).getFecha().toString();

	    this.paqueteApuestas = null;
            this.paq = null;
            this.notificarAltaApuesta(aux);
            mensaje="";
            throw e;
        }

        paq = new PaqueteApuestas(this.usuActual);

        for (DataApuesta dataAp: this.paqueteApuestas.getApuestas()) {

            if (dataAp instanceof DataApuestaCampeon) {
                DataApuestaCampeon apuestaCampeon = (DataApuestaCampeon) dataAp;

                this.seleccionarCompeticion (apuestaCampeon.getCompeticion().getId());
                this.seleccionarCampApuesta(TipoCampeonatoApuesta.Equipo);

		da = apuestaCampeon;
                id_equipo_mem = apuestaCampeon.getEquipo().getId();
                p = null;

		this.confirmarAltaApuesta(true,true);

            }
            else if(dataAp instanceof DataApuestaGoleador) {

                DataApuestaGoleador apuestaGoleador = (DataApuestaGoleador) dataAp;

                this.seleccionarCompeticion (apuestaGoleador.getCompeticion().getId());
                this.seleccionarCampApuesta(TipoCampeonatoApuesta.Goleador);

		da = apuestaGoleador;
                id_equipo_mem = -1;
                p = null;

		this.confirmarAltaApuesta(true,true);

            }
            else if(dataAp instanceof DataApuestaPartido) {
                DataApuestaPartido apuestaPartido = (DataApuestaPartido) dataAp;

                this.seleccionarCompeticion(apuestaPartido.getPartido().getDataInfoPart().getIdComp());

                if (!(apuestaPartido.getPartido().getDataInfoPart().getTipoC()==TipoCompeticion.PartidoIndividual)) {
                    this.seleccionarCampApuesta(TipoCampeonatoApuesta.Partido);
                    // Apuesta por partido
                    this.seleccionarPartidoCamp(apuestaPartido.getPartido().getDataInfoPart().getIdPar());
                }

                da= apuestaPartido;
                tipo_div = apuestaPartido.getTipoResultado();

                this.confirmarAltaApuesta(true,true);
            }
            else if(dataAp instanceof DataApuestaResExacto) {
                DataApuestaResExacto apuestaResExacto = (DataApuestaResExacto) dataAp;

                this.seleccionarCompeticion(apuestaResExacto.getPartido().getDataInfoPart().getIdComp());

                if (!(apuestaResExacto.getPartido().getDataInfoPart().getTipoC()==TipoCompeticion.PartidoIndividual)) {
                    this.seleccionarCampApuesta(TipoCampeonatoApuesta.ResExacto);
                    // Apuesta por partido
                    this.seleccionarPartidoCamp(apuestaResExacto.getPartido().getDataInfoPart().getIdPar());
                }

                da= apuestaResExacto;
                tipo_div=null;

                this.confirmarAltaApuesta(true,true);

            }
        }


        usuActual.agregarPaqueteApuestas(this.paq);
        String aux="IBet le notifica que su paquete de apuestas con id: "+paq.getId()+ " ha sido aceptado\n"+mensaje;
        this.notificarAltaApuesta(aux);
        mensaje="";
        this.paqueteApuestas = null;
        this.paq = null;

    }



    /// VER DETALLES APUESTAS
    public List<DataPaqueteApuestas> verPaqueteApuestas() throws Exception {
        //vemos si existe su sesion
        if (this.dispMovil){
            if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectadosDisp().containsKey(usuActual.getNick()))){
                usuActual = null;
                throw new Exception("Error: No ha iniciado sesion");
            }
        }else{
            if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectados().containsKey(usuActual.getNick()))){
                usuActual = null;
                throw new Exception("Error: No ha iniciado sesion");
            }
        }

        return usuActual.obtenerPaquetesDeApuestas();
    }

    //*********************************USUARIOS LOGUEADOS

    private boolean dispMovil;

    public void setDispMovil(boolean value){
        this.dispMovil = value;
    }
    
    public boolean getDispMovil(){
        return dispMovil;
    }

    //*********************************USUARIOS LOGUEADOS


    public List<DataInfoPenca> obtenerInfoPencas(){
        ManejadorCompeticiones mc = ManejadorCompeticiones.getInstance();
        Map<Integer,Competicion> comps = mc.getCompeticiones();
        List<DataInfoPenca> list = new ArrayList<DataInfoPenca>();
        try{
          for(Map.Entry<Integer,Competicion> entry : comps.entrySet()){
              if (entry.getValue() instanceof Liga || entry.getValue() instanceof Copa){
                 DataInfoPenca dip = this.verTablaPosicionesPenca(entry.getValue().getId());
                 dip.setIdComp(entry.getValue().getId());
                 dip.setNombre(entry.getValue().getNombre());
                 Campeonato camp = (Campeonato) entry.getValue();
                 dip.setMensajes(camp.obtenerMensajesForo());
                 list.add(dip);
              }
          }
      }
        catch(Exception e){
        System.out.println(e.getMessage());}
      return list;
     }

//modificar tambien los datausuarios como u.getdataUsuario
    //import Mensajes.EnviarMail;
    //EnviarMail envios=new EnviarMail();

    

    public boolean preVerificarPaqueteApuestas()throws Exception{
        for (DataApuesta dataAp: this.paqueteApuestas.getApuestas()) {
            if (dataAp instanceof DataApuestaCampeon) {
                DataApuestaCampeon apuestaCampeon = (DataApuestaCampeon) dataAp;
                this.seleccionarCompeticion (apuestaCampeon.getCompeticion().getId());
                this.seleccionarCampApuesta(TipoCampeonatoApuesta.Equipo);
            }
            else if(dataAp instanceof DataApuestaGoleador) {
                DataApuestaGoleador apuestaGoleador = (DataApuestaGoleador) dataAp;
                this.seleccionarCompeticion (apuestaGoleador.getCompeticion().getId());
                this.seleccionarCampApuesta(TipoCampeonatoApuesta.Goleador);
            }
            else if(dataAp instanceof DataApuestaPartido) {
                DataApuestaPartido apuestaPartido = (DataApuestaPartido) dataAp;
                this.seleccionarCompeticion(apuestaPartido.getPartido().getDataInfoPart().getIdComp());
                if (!(apuestaPartido.getPartido().getDataInfoPart().getTipoC()==TipoCompeticion.PartidoIndividual)) {
                    this.seleccionarCampApuesta(TipoCampeonatoApuesta.Partido);
                    // Apuesta por partido
                    this.seleccionarPartidoCamp(apuestaPartido.getPartido().getDataInfoPart().getIdPar());
                }
            }
            else if(dataAp instanceof DataApuestaResExacto) {
                DataApuestaResExacto apuestaResExacto = (DataApuestaResExacto) dataAp;
                this.seleccionarCompeticion(apuestaResExacto.getPartido().getDataInfoPart().getIdComp());
                if (!(apuestaResExacto.getPartido().getDataInfoPart().getTipoC()==TipoCompeticion.PartidoIndividual)) {
                    this.seleccionarCampApuesta(TipoCampeonatoApuesta.ResExacto);
                    // Apuesta por partido
                    this.seleccionarPartidoCamp(apuestaResExacto.getPartido().getDataInfoPart().getIdPar());
                }
            }
        }
        return true;
    }



    







    //notificaciones de mail

    public void notificarPartido(String mensaje)throws Exception{
         Map<String,Usuario> mapa=ManejadorUsuarios.getInstance().getUsuariosNick();
         for (java.util.Map.Entry<String,Usuario>  entry: mapa.entrySet()) {
            Usuario u=entry.getValue();
            boolean notificar=u.isNotPartido();
            if(notificar){
                envios.mail(u.getEmail(),"Nuevo partido",mensaje);
            }
         }
         System.out.println(mensaje);
    }

    public void notificarResultadoApuesta(String nick,String mensaje)throws Exception{
        Usuario u=ManejadorUsuarios.getInstance().getUsuariosNick().get(nick);
        if (null==u){
            throw new Exception("Error: No existe usuario");
        }
        boolean notificar=u.isNotApuesta();
        if(notificar){
            envios.mail(u.getEmail(),"Resultado de Apuestas",mensaje);
        }
        System.out.println(mensaje);
    }

    public void notificarForo(String nickReceptor,String mensaje)throws Exception{
        Usuario u=ManejadorUsuarios.getInstance().getUsuariosNick().get(nickReceptor);
        if (null==u){
            throw new Exception("Error: No existe usuario");
        }
        String email=u.getEmail();
        boolean notificar=u.isNotForo();
        if(notificar){
            envios.mail(email,"Mensaje foro penca",mensaje);
        }
        System.out.println(mensaje);
    }

    public void setNotificacion(boolean notA,boolean notF,boolean notP)throws Exception{
        if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectados().containsKey(usuActual.getNick()))){
            usuActual = null;
            throw new Exception("Error: No ha iniciado sesion");
         }
         usuActual.setNotApuesta(notA);
         usuActual.setNotForo(notF);
         usuActual.setNotPartido(notP);
    }

	//15-11

    private boolean notificarAltaApuestas=true;

    public boolean isNotificarAltaApuestas() {
        return notificarAltaApuestas;
    }

    public void setNotificarAltaApuestas(boolean notificarAltaApuestas) {
        this.notificarAltaApuestas = notificarAltaApuestas;
    }

     public void notificarAltaApuesta(String mensaje)throws Exception{
        if(notificarAltaApuestas){
            if (this.dispMovil){
                if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectadosDisp().containsKey(usuActual.getNick()))){
                        usuActual = null;
                        throw new Exception("Error: No ha iniciado sesion");
                    }
            }else{
                if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectados().containsKey(usuActual.getNick()))){
                    usuActual = null;
                    throw new Exception("Error: No ha iniciado sesion");
                }
            }
            String email=ManejadorUsuarios.getInstance().getUsuariosNick().get(this.usuActual.getNick()).getEmail();
            envios.mail(email,"Notificacion de Apuesta",mensaje);
            System.out.println(mensaje);
        }
    }

     public void apostarPartidoPenca(int idPart,int golesLoc,int golesVis) throws Exception,ExCompeticionFinalizada,ExUsuarioNoParticipaEnPenca,
             ExUsuarioYaApostoPartido,Excepciones.ExFechaPosteriorPartido{
                 if (auxCamp==null){
                      throw new ExDatosNoAsignados("No existe en la memoria Temporal ninguna Compeonato al cual Participar");
                 }

                 if (this.dispMovil){
                    if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectadosDisp().containsKey(usuActual.getNick()))){
                            usuActual = null;
                            throw new Exception("Error: No ha iniciado sesion");
                        }
                }else{
                    if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectados().containsKey(usuActual.getNick()))){
                        usuActual = null;
                        throw new Exception("Error: No ha iniciado sesion");
                    }
                }

                 usuActual = ManejadorUsuarios.getInstance().getUsuario(usuActual.getNick());

                 auxCamp = (Campeonato)ManejadorCompeticiones.getInstance().getCompeticiones().get(auxCamp.getId());

		 if (auxCamp.getFinalizado()){
			 throw new ExCompeticionFinalizada("La competicion ya ha finalizado");
                 }
		 if (!auxCamp.participaEnPenca(usuActual)){
			 throw new ExUsuarioNoParticipaEnPenca("El Usuario no Participa en esta Penca");
                 }
		 if (auxCamp.usuarioApostoPartido(usuActual.getNick(),idPart)){
			 throw new ExUsuarioYaApostoPartido("El Usuario ya ha Apostado este Partido");
                 }

                 ControladorFecha icf =ControladorFecha.getInstance();
                 DataTypes.DataFechaHora fechaP=auxCamp.getFechaPartidoPenca(idPart);
                 String mPenca;

		 if(icf.getFecha().compareTo(fechaP)==1){
                      mPenca="Lamentablemente no fue aceptada su apuesta a penca\n\n";
                      mPenca+="Nombre Campeonato: "+auxCamp.getNombre()+"\n";
                      mPenca+="Id Partido: "+idPart+"\n";
                      mPenca+="Resultado apostado: "+golesLoc+"-"+golesVis+"\n";
                      mPenca+="Su apuesta fue recibida el: "+icf.getFecha().toString()+"\n";
                      mPenca+="Tenia tiempo de apostar hasta: "+fechaP.toString()+"\n";
                      this.notificarAltaApuesta(mPenca);
                      auxCamp=null;
                      throw new Excepciones.ExFechaPosteriorPartido("Se podia apostar hasta: "+fechaP.toString());
                 }
                 else{
                     auxCamp.apostarPartidoPenca(usuActual.getNick(),idPart,golesLoc,golesVis);
                     mPenca="Fue aceptada su apuesta a penca \n\n";
                     mPenca+="Nombre Campeonato: "+auxCamp.getNombre()+"\n";
                     mPenca+="Id Partido: "+idPart+"\n";
                     mPenca+="Fecha a disputarse: "+fechaP.toString()+"\n";
                     mPenca+="Resultado apostado: "+golesLoc+"-"+golesVis+"\n";
                     this.notificarAltaApuesta(mPenca);
                     auxCamp=null;
                 }
	 }

public void retirarSaldoUsuario(float monto) throws Exception{
        if ((usuActual==null)||(!ManejadorUsuarios.getInstance().getUsuariosConectados().containsKey(usuActual.getNick()))){
            throw new Exception("Error: No ha iniciado sesion");
        }

        usuActual = ManejadorUsuarios.getInstance().getUsuario(usuActual.getNick());

        if (monto <= 0 || (usuActual.getSaldo()-monto) <0){
            throw new ExMontoInvalido("El monto ingresado no es valido");
        }
        usuActual.setSaldo(usuActual.getSaldo()-monto);
    }
}
