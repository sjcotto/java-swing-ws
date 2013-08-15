package Controladores;

import Interface.IControladorCompeticiones;
import Interface.IControladorWebCompeticiones;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import Objetos.Competicion;
import Objetos.Copa;
import Objetos.Liga;
import Objetos.PartidoIndividual;
import Objetos.Equipo;
import DataTypes.*;
import Excepciones.*;

// PERSISTENCIA
import Interface.IControladorCompeticionesDAO;
import Interface.XLSFabricaDAO;
import Objetos.Apuesta;
import Objetos.ApuestaCampeonato;
import Objetos.ApuestaPartido;
import Objetos.Campeonato;

//9-10
import Objetos.InfoJugadorCampeonato;

public class ControladorCompeticiones
        implements IControladorCompeticiones, IControladorWebCompeticiones {

//9-10     
    private Map<Integer,InfoJugadorCampeonato> listaInfoJC=new HashMap<Integer,InfoJugadorCampeonato>();
    private float montoPenca;
    
    // Memoria temporal ALTA LLAVE COPA
    private String nombreLlave;
    private String llavePrevLocal,llavePrevVisita;
    private Copa copa;
    private Competicion c; // finalizar o alta llave
    private int idLocal,idVisita;
    private int fase;
    int golesLocal;
    int golesVisita;
    int penalLocal;
    int penalVisita;
    List<DataIdNombre> jugadoresLocal=new ArrayList<DataIdNombre>();
    List<DataIdNombre> jugadoresVisita=new ArrayList<DataIdNombre>();
    private boolean esLaFinal,tieneSucesora;
    private DataResumen dataR;
    private DataInfoPartido dataIP;
    // *******************************************

    // Memoria temporal VER DETALLES COMPETICION
    private Competicion compVerDetalles;
    // *******************************************

    // Memoria temporal ALTA COMPETICION
    private String nombreCompeticion;
    private TipoCompeticion tipo;
    private String pahtImage;
    private Map<Integer,DataEquipo> equipos;//a medida que se asignan a la
				   //competicion se sacan de esta coleccion
    private Liga memComp;
    private TipoCriterio[] dc;
    private List<DataDivEquipo> dividendosCampeonato;
    private List<DataPartidoNoDefinidoLiga> partidosNoDefinidosLiga;
    // **********************************


    //OBTENER INFO PARTIDO

    private int compe;
    private int part;

    /// CONSTRUCTOR
    public ControladorCompeticiones(){
	this.equipos = new HashMap<Integer,DataEquipo>();
	dividendosCampeonato= new ArrayList<DataDivEquipo>();
        partidosNoDefinidosLiga = new ArrayList<DataPartidoNoDefinidoLiga>();
        dc = new TipoCriterio[3];

        DataEventos = new ArrayList<DataTypes.DataEvento>();
    }

    /************ ALTA PARTIDO INDIVIDUAL ************/ 
    /****************************************************/
    public List<DataPartidoIndividualNoDefinido> listarPartidosIndividualesNoDefinidos(){
        Map<Integer,Competicion> competiciones = ManejadorCompeticiones.getInstance().getCompeticiones();
        List<DataPartidoIndividualNoDefinido> listDPIND = new ArrayList<DataPartidoIndividualNoDefinido>();

        for(Map.Entry<Integer,Competicion> entry: competiciones.entrySet()) {
            Competicion comp = entry.getValue();
            if (comp instanceof PartidoIndividual) {
                PartidoIndividual partInd = (PartidoIndividual) comp;
                boolean ok = partInd.estaDefinidoPartInd();
                if (!ok)
                    listDPIND.add(partInd.getDataPartidoIndNoDef());
            }
        }
        return listDPIND;
    }

    public boolean ingresarDatosPartidoIndividual(int idComp,int idEqLocal, int idEqVisi,
            DataFechaHora fechaHora, String lugar) throws ExNoExisteCompeticion,
                ExNoExistePartidoIndividual,ExPartidoYaDefinido,ExFechaInvalida,
                    ExEquipoInvalido {

        Map<Integer,Competicion> competiciones = ManejadorCompeticiones.getInstance().getCompeticiones();

        if (!competiciones.containsKey(idComp)) {
             throw (new ExNoExisteCompeticion("No Existe una Competicion Con Id "+ idComp));
        }

        if (!(competiciones.get(idComp) instanceof PartidoIndividual)) {
            throw (new ExNoExistePartidoIndividual("La Competicion con Id "+ idComp +" no es un Partido Individual"));
        }

        if (competiciones.get(idComp).getEstaDefinido()){
            throw (new ExPartidoYaDefinido("El Partido a ingresar ya fue ingresado anteriormente"));
        }
       
        if (fechaHora == null ||
           (fechaHora.getAnio()<=0)||(fechaHora.getDia()> 31) || (fechaHora.getDia()<=0) ||
           (fechaHora.getMes()<=0) || (fechaHora.getMes()>12)||
           (fechaHora.getHora()< 0) || (fechaHora.getHora()>23) ||
           (fechaHora.getMinuto()<0) || (fechaHora.getMinuto()>59)) {

               throw (new ExFechaInvalida("La Fecha y Hora ingresadas para el Partido no es Correcta"));
        }

        PartidoIndividual partInd = (PartidoIndividual) competiciones.get(idComp);
        DataPartidoIndividualNoDefinido dataPIND = partInd.getDataPartidoIndNoDef();
        int id1 = dataPIND.getDataEquipo1().getId();
        int id2 = dataPIND.getDataEquipo2().getId();

        if (!((idEqLocal!=idEqVisi) &&((idEqLocal==id1 && idEqVisi==id2) || (idEqLocal==id2 && idEqVisi==id1))))
            throw new ExEquipoInvalido("Los Equipos asignados son invalidos");


        ControladorEquipos ce = new ControladorEquipos();
        Equipo el = ce.buscarEquipo(idEqLocal);
	Equipo ev = ce.buscarEquipo(idEqVisi);

	PartidoIndividual comp = (PartidoIndividual)competiciones.get(idComp);

        return comp.ingresarPartidoAPartIndiv(el,ev,fechaHora,lugar);

    }
    
    /****************************************************/
    /****************************************************/

    /************ ALTA PARTIDO DE LIGA ************/
    /****************************************************/
    public List<DataIdNombre> listarLigasNoDefinidas(){
        Map<Integer,Competicion> competiciones = ManejadorCompeticiones.getInstance().getCompeticiones();
        List<DataIdNombre> ligasNoDefinidas=new ArrayList<DataIdNombre>();
	for (Map.Entry<Integer, Competicion> entry : competiciones.entrySet()) {
	    Competicion l = entry.getValue();
	    if(l.getTipo()==TipoCompeticion.Liga){
		if(!l.getEstaDefinido()){
		    DataIdNombre dl=l.getDataBasica();
		    ligasNoDefinidas.add(dl);
		}
	    }
	}
	return ligasNoDefinidas;
   }
   
    public List<DataPartidoNoDefinidoLiga>  ingresarIdLiga(int id)
            throws ExNoExisteCompeticion,ExNoExisteLiga,ExLigaYaDefinida{

        Map<Integer,Competicion> competiciones = ManejadorCompeticiones.getInstance().getCompeticiones();

        if (!competiciones.containsKey(id)) {
             throw (new ExNoExisteCompeticion("No Existe una Competicion Con Id "+ Integer.toString(id)));
        }

        if (!(competiciones.get(id) instanceof Liga)) {
            throw (new ExNoExisteLiga("La Competicion con Id "+ Integer.toString(id)+" no es una Liga"));
        }

         if (competiciones.get(id).getEstaDefinido()){
            throw (new ExLigaYaDefinida("La Liga con Id "+ Integer.toString(id)+ " ya esta Definida"));
        }

	List<DataPartidoNoDefinidoLiga> partidosNoDefinidos= new ArrayList<DataPartidoNoDefinidoLiga>();

        Liga l = (Liga)competiciones.get(id);
	partidosNoDefinidos = l.getPartidosNoDefinidosLiga();
	memComp = l;

        return partidosNoDefinidos;
    }

    public DataResultadoAltaPartido seleccionarEncuentroADefinir (int idEqLocal,
                    int idEqVisi, DataFechaHora fechaHora, String lugar)
                        throws ExCompetenciaNoAsignada,ExFechaInvalida,
                               ExEquipoInvalido, ExPartidoYaDefinido{




        if (memComp == null) {
           throw (new ExCompetenciaNoAsignada("No existe en la memoria temporal del sistema una Liga a la cual ingresar el Partido"));
       }

       if (fechaHora==null ||
          (fechaHora.getAnio()<=0)||(fechaHora.getDia()>= 31) || (fechaHora.getDia()<=0) ||
          (fechaHora.getMes()<=0) || (fechaHora.getMes()>31)||
          (fechaHora.getHora()< 0) || (fechaHora.getHora()>23) ||
          (fechaHora.getMinuto()<0) || (fechaHora.getMinuto()>59)) {

               throw (new ExFechaInvalida("La Fecha y Hora ingresadas para el Partido no es Correcta"));
        }

       DataResultadoAltaPartido dr;
       // Esto lanza la excepcion de los Equipos validos
       dr = memComp.crearPartido(idEqLocal,idEqVisi,fechaHora,lugar);

       if (dr.getCompeticionQuedoDefinida())
	    memComp.setEstaDefinido(true);
      
       memComp=null;

       return dr;
    }

    public List<DataPartidoNoDefinidoLigaInterfaz> getEncuentrosNoFormadosDeLiga (){
        return memComp.getPartidosNoDefinidosLigaInterfaz();
    }

    /****************************************************/
    /****************************************************/

    /************ CASO DE USO ALTA COMPETICION ************/
    /****************************************************/
    public void ingresarCompeticion(String nombre,TipoCompeticion tipo,
                                    String imagen){
	this.nombreCompeticion=nombre;
	this.tipo=tipo;
        this.pahtImage = imagen;
    }

    public List<DataEquipo> listarEquipos(){
	ControladorEquipos ce=new ControladorEquipos();
	equipos.clear();//se limpia la lista para agregar nuevos equipos
	List listaEquipos = ce.listarEquipos();
        return listaEquipos;
    }

    public void seleccionarEquipo(int id)
        throws ExDatosNoAsignados, ExNoExisteEquipo, ExEquipoYaSeleccionado {

        if(this.nombreCompeticion==null){
            throw (new ExDatosNoAsignados("El nombre de la Competicion a ingresar no fue asignado"));
        }

        if(this.tipo==null){
            throw (new ExDatosNoAsignados("El Tipo de la Competicion a ingresar no fue asignado"));
        }

        ControladorEquipos ce=new ControladorEquipos();
        
        if (ce.buscarEquipo(id)==null){
            throw (new ExNoExisteEquipo("No Existe un Equipo con Id "+ Integer.toString(id)));

        }
        if (equipos.containsKey(id)) {
            throw (new ExEquipoYaSeleccionado("El Equipo con Id "+ Integer.toString(id)+" ya ha sido Seleccionado"));
        }
        
        DataEquipo dE=ce.getDataEquipo(id);
        equipos.put(id,dE);//se agregan los equipos en el mapa su id
                   //es la clave
    }

    public boolean seleccionCorrecta(){
	 int tamanio=equipos.size();
	 if (this.tipo==TipoCompeticion.PartidoIndividual){
	     return tamanio==2;
	 }
	 if (this.tipo==TipoCompeticion.Liga){
	     return tamanio>1;
	 }
	 if (this.tipo==TipoCompeticion.Copa){

	     if (tamanio<2){
		 return false;
	     }
	     //se multiplica aux por 2 hasta igualar
	     //o superar el tamanio, en caso de igualar esta bien formada
	     //en caso se superar no lo esta
	     int aux=2;
	     while(aux<tamanio){
		 aux=aux*2;
	     }
	     return tamanio==aux;
	 }
	 return false;
    }

    public void ingresarOrdenLiga(TipoCriterio[] ldc)
        throws ExCriteriosInvalidos{

        if (ldc==null || ldc.length!=3)
            throw (new ExCriteriosInvalidos("Numero de criterios invalidos"));

        int n1 = ldc[0].ordinal();
        int n2 = ldc[1].ordinal();
        int n3 = ldc[2].ordinal();

        if (!(n1!=n2 && n1!=n3 && n2!=n3)) {
            throw (new ExCriteriosInvalidos("Criterios repetidos"));
        }
            this.dc=ldc;

    }

    public void ingresarDividendoEquipo(int id,float dividendo)
        throws ExDatosNoAsignados, ExEquipoInvalido,ExDividendosInvalidos,
            ExDividendosYaAsignados{

        if (this.nombreCompeticion==null || this.tipo==null || ((this.tipo==TipoCompeticion.Liga) && (this.dc==null))){
                throw (new ExDatosNoAsignados("El Criterio a tomar para la Liga a ingresar no fue asignado"));
        }

        if(!equipos.containsKey(id)){
            throw (new ExEquipoInvalido("El Equipo con id "+ Integer.toString(id)+" no fue seleccionado para la LIga"));
        }

        if (dividendo<=1) {
            throw (new ExDividendosInvalidos("El dividendo ingresado es incorrecto. Debe ser mayor que 1"));
        }

         DataEquipo dE = equipos.get(id);
         DataDivEquipo dDE=new DataDivEquipo(dE.getId(),dE.getNombre(),dividendo);
         boolean encontre = false;
         int i=0;
         while (i<dividendosCampeonato.size() && !encontre) {
            DataDivEquipo dde = dividendosCampeonato.get(i);
            encontre = dde.getId()==id;
            i++;
         }
         if (encontre)
               throw (new ExDividendosYaAsignados("Los Dividendos para el Equipo con Id "+ Integer.toString(id)+" ya fueron ingresados"));

         dividendosCampeonato.add(dDE);
    }

   



   
    /****************************************************/
    /****************************************************/
    
    /************ CASO DE USO ALTA LLAVE ************/
    /****************************************************/

    public List<DataIdNombre> listarCopaNoDefinida(){
        Map<Integer,Competicion> competiciones = ManejadorCompeticiones.getInstance().getCompeticiones();

        List<DataIdNombre> copasNoDefinidas=new ArrayList<DataIdNombre>();
        for (Map.Entry<Integer, Competicion> entry : competiciones.entrySet()) {
            Competicion comp = entry.getValue();
            if(comp.getTipo()==TipoCompeticion.Copa){
                if(!comp.getEstaDefinido()){
                    DataIdNombre dC=comp.getDataBasica();
                    copasNoDefinidas.add(dC);
                }
            }
        }
        return copasNoDefinidas;
    }

    public  List<DataLlave> ingresarDatosLlaveDeCopa(int idCopa,String nombreLlave)
            throws ExNoExisteCompeticion,ExCopaYaDefinida,ExYaExisteLLave{

        Map<Integer,Competicion> competiciones = ManejadorCompeticiones.getInstance().getCompeticiones();

        Competicion copaAux = competiciones.get(idCopa);
        if(copaAux==null || !(copaAux instanceof Copa)){
            throw new ExNoExisteCompeticion("No existe una Copa con Id "+ Integer.toString(idCopa));
        }

        if( ((Copa)copaAux).getEstaDefinido()==true){
            throw new ExCopaYaDefinida("La Copa con Id "+Integer.toString(idCopa)+" ya fue Definida");
        }

        if(((Copa)copaAux).getLlave(nombreLlave)!=null){
            throw new ExYaExisteLLave("Ya Existe una LLave con nombre" + nombreLlave);
        }

        this.copa = (Copa)copaAux;
        this.nombreLlave = nombreLlave;

        return copa.getDataLlaves();
    }

    public List<DataIdNombre> listarEquiposNoAsignadosCopa()
            throws ExDatosNoAsignados,ExCopaYaDefinida{
       if( copa==null || this.nombreLlave.equals("")){
            throw new ExDatosNoAsignados("No Existe en la Memoria temporal del Sistema una Copa a la cual ingresar la LLave");
        }
        if( copa.getEstaDefinido()==true){
            throw new ExCopaYaDefinida("La Copa a la cual se quiere ingresar la LLave ya fue Definida");
        }
       return copa.listarEquiposNoAsignadosCopa();
   }

    public void ingresarDatosPartida(int idEqL,int idEqV)
        throws ExDatosNoAsignados,ExCopaYaDefinida,ExNoExisteEquipo,
            ExEquipoInvalido{
        
        if( copa==null || this.nombreLlave.equals("")){
            throw new ExDatosNoAsignados("No Existe en la Memoria temporal del Sistema una Copa a la cual ingresar la LLave");
        }
        
        if( copa.getEstaDefinido()==true){
            throw new ExCopaYaDefinida("La Copa a la cual se quiere ingresar la LLave ya fue Definida");
        }
        
        if(!copa.perteneceEquipoACopa(idEqL)||!copa.perteneceEquipoACopa(idEqV)){
            throw new ExNoExisteEquipo("Un Equipo ingresado no participa en la Copa");
        }
        
        if(copa.equipoYaAsigado(idEqL))
            throw new ExEquipoInvalido("El Equipo con Id "+ Integer.toString(idEqL)+" ya fue asignado a una LLave");
        
        if (copa.equipoYaAsigado(idEqV))
            throw new ExEquipoInvalido("El Equipo con Id "+ Integer.toString(idEqV)+" ya fue asignado a una LLave");
        
         if (idEqL==idEqV)
            throw new ExEquipoInvalido("Equipos invalidos");//equipo ya asignado
        
       this.idLocal=idEqL;
       this.idVisita=idEqV;
       fase=1;
   }

   public void ingresarLlavesPredecesoras(String llaveLocal, String llaveVisita)
           throws ExLLavesInvalidas,ExNoExisteLaLlave,
                ExLlavesDeDistintasFases, ExLlavesDifierenEnSucesoras{

        fase =copa.obtenerNumeroDeFaseLlave(llaveLocal, llaveVisita);
        
        llavePrevLocal=llaveLocal;
        llavePrevVisita=llaveVisita;
   }

   public void ingresarTipoLlave(boolean tieneSucesora,boolean esFinal)
           throws ExParametrosInvalidos{
       
       if(!copa.bienFormada(tieneSucesora,esFinal,this.fase)){
            throw new ExParametrosInvalidos("Los Parametros ingresados no son correctos");
       }

       esLaFinal=esFinal;
       this.tieneSucesora=tieneSucesora;
       
   }

   public boolean confirmarAltaLlaveCopa(DataFechaHora fechaHora,String lugar){
        if(fase==1){
            copa.crearFaseInicial(nombreLlave,fechaHora,lugar,idLocal,idVisita,tieneSucesora,esLaFinal);
        }
        else{
            copa.crearFaseSiguiente(nombreLlave,fechaHora,lugar, llavePrevLocal,llavePrevVisita,tieneSucesora,esLaFinal);
        }

        boolean estaDef = copa.getEstaDefinido();
        this.limpiarMemoriaAuxiliar();
        //si es la final la copa se dio por definida
        return estaDef;
   }

   ///public void limpiarMemoriaAuxiliar();
   
   /****************************************************/
   /****************************************************/

    /************ CASO DE USO FINALIZAR PARTIDO ************/
    /****************************************************/
    
   //retorna una coleccion de campeonatos con algun partido finalizable
   //es decir con asignacion de dividendo y no finalizado
   public List<DataIdNombre> listarCompeticionesNoFinalizadas(){
       Map<Integer,Competicion> competiciones = ManejadorCompeticiones.getInstance().getCompeticiones();

       List<DataIdNombre> salida= new ArrayList<DataIdNombre>();
       for (Map.Entry<Integer, Competicion> entry : competiciones.entrySet()){
            Competicion comp=entry.getValue();
            if(comp.getFinalizable()){
                DataIdNombre dC=comp.getDataBasica();
                salida.add(dC);
            }
        }
       return salida;
   }

   public List<DataInfoPartido> listarPartidosConDivAsignado(int idCompeticion)
           throws ExNoExisteCompeticion{

       Map<Integer,Competicion> competiciones = ManejadorCompeticiones.getInstance().getCompeticiones();

       if (!competiciones.containsKey(idCompeticion)){
           throw (new ExNoExisteCompeticion("No Existe una Competicion con Id "+ Integer.toString(idCompeticion)));
       }
       this.c=competiciones.get(idCompeticion);
       List<DataInfoPartido> ret = (List)c.getDataPartidosFinalizables();
       Collections.sort(ret);
       return ret;
   }

   public DataPartido seleccionarPartido(DataInfoPartido dataIP)
           throws ExDatosNoAsignados,ExPartidoNoFinalizable{
       if (this.c==null || dataIP==null){
           throw (new ExDatosNoAsignados("No existen datos suficientes"));
       }

       int idPart = dataIP.getIdPar();
       List<DataInfoPartido> listPDivAsig = null;
       try {
            listPDivAsig = listarPartidosConDivAsignado(this.c.getId());
       }
       catch (ExNoExisteCompeticion exc){}
       
       boolean encontre = false;
       for (DataInfoPartido data: listPDivAsig) {
            if (data.getIdPar() == idPart)
                encontre = true;
       }
       if (!encontre)
            throw new ExPartidoNoFinalizable("El partido ingresado no es finalizable");

       this.idLocal=dataIP.getIdLocal();
       this.idVisita=dataIP.getIdVisita();
       this.dataIP=dataIP;

       DataPartido dp = new DataPartido(dataIP,c.getDividendos(dataIP));
       
       return dp;
   }

   public void ingresarResultado(int golesL,int penalL,
           int golesV,int penalV)
           throws ExDatosNoAsignados,ExGolesInvalidos{

       //verificaciones
       if (this.c==null || this.dataIP==null){
           throw (new ExDatosNoAsignados("No existen datos en memoria suficientes"));
       }
       if ((golesL<0) || (penalL<0)||(golesV<0)||(penalV<0)){
           throw (new ExGolesInvalidos("Los goles ingresados no son validos"));
       }
       
       if (this.c.getTipo()==TipoCompeticion.Copa && golesL==golesV && penalL==penalV) {
            throw new ExGolesInvalidos("Los goles ingresados no son validos");
       }

       this.golesLocal=golesL;
       this.penalLocal=penalL;
       this.golesVisita=golesV;
       this.penalVisita=penalV;
   }

   public List<DataIdNombre> listaJugadores(){
       ControladorJugadores cj= new ControladorJugadores();
       return cj.listaJugadores();
   }

   public void ingresarJugador(int idJugador,int idEquipo)
           throws ExNoExisteEquipo,ExNoExisteJugador,ExJugadorYaIngresado{
       //se agregan a las colecciones de dataIdNombre
       ControladorEquipos ce = new ControladorEquipos();
       ControladorJugadores cj= new ControladorJugadores();
       DataIdNombre dJ= cj.getDataBasica(idJugador);
       Equipo e = ce.buscarEquipo(idEquipo);

       if (e==null || (!(idEquipo==this.dataIP.getIdLocal()) && !(idEquipo==this.dataIP.getIdVisita()))){
           throw (new ExNoExisteEquipo("No Existe un Equipo con id "+ Integer.toString(idEquipo)));
       }
       if (dJ==null){
           throw (new ExNoExisteJugador("No Existe un Jugador con id "+ Integer.toString(idJugador)));
       }
       for (int i=0;i < jugadoresLocal.size();i++){
           if (jugadoresLocal.get(i).getId()==idJugador) {
               throw ( new ExJugadorYaIngresado("El Jugador con Id "+ Integer.toString(idJugador)+" ya fue ingresado"));
           }
       }
        for (int i=0;i < jugadoresVisita.size();i++){
           if (jugadoresVisita.get(i).getId()==idJugador){
               throw ( new ExJugadorYaIngresado("El Jugador con Id "+ Integer.toString(idJugador)+" ya fue ingresado"));
            }
       }
       if(idEquipo==idLocal){
          this.jugadoresLocal.add(dJ);
       }
       if(idEquipo==idVisita){
           this.jugadoresVisita.add(dJ);
       }
   }

   public DataResumen listarIngresos()
           throws ExDatosNoAsignados{
        if (this.c==null || this.dataIP==null){
           throw (new ExDatosNoAsignados("No Existe en la Memoria los datos necesarios"));
       }

       dataR= new DataResumen(golesLocal,golesVisita,penalLocal,penalVisita,jugadoresLocal,jugadoresVisita);
       dataR.setEventos(DataEventos);
       return dataR;
   }

   public void confirmarIngreso(boolean confirmar)
           throws ExDatosNoAsignados{
       if (dataIP==null || dataR==null)
           throw new ExDatosNoAsignados("No existen los datos necesarios");
       if(confirmar){
           c.finalizarPartido(dataIP, dataR);
       }
       limpiarMemoriaAuxiliar();
   }

   public void limpiarMemoriaAuxiliar(){
       this.DataEventos = new ArrayList<DataTypes.DataEvento>();

       // Alta llave
       copa=null;
       this.nombreLlave = "";
       this.idLocal=0;
       this.idVisita=0;
       fase=0;
       llavePrevLocal="";
       llavePrevVisita="";
       esLaFinal=false;
       this.tieneSucesora=false;

       // Finalizar partido
       jugadoresLocal.clear();
       jugadoresVisita.clear();
       c=null;
       this.dataIP = null;
       golesLocal=-1;
       golesVisita=-1;
       penalLocal=-1;
       penalVisita=-1;

   }

    /****************************************************/
    /****************************************************/

    /************ VER DETALLES DE COMPETICION ************/
    /****************************************************/
    // Operacion del sistema
    public List<DataIdNombre> listarCompetencias() {
    // Listamos la info de todas las Competencias creadas
	// No tiene excepciones

        Map<Integer,Competicion> competiciones = ManejadorCompeticiones.getInstance().getCompeticiones();

	DataIdNombre dataNomC;
	List<DataIdNombre> colDataNomComp = new ArrayList<DataIdNombre>();

	for (Map.Entry<Integer, Competicion> competicion : competiciones.entrySet()) {
	    Competicion comp = competicion.getValue();
	    dataNomC = comp.getDataBasica();
	    colDataNomComp.add(dataNomC);
	}
	return colDataNomComp;
    }

    // Operacion del sistema
    public DataCompeticion verInfoCompeticion(int id) throws ExNoExisteCompeticion{
    // Mostramos la informacion de cierta Competicion.

        Map<Integer,Competicion> competiciones = ManejadorCompeticiones.getInstance().getCompeticiones();

        if (!competiciones.containsKey(id)){
           throw (new ExNoExisteCompeticion("No Existe una Competicion con Id "+ Integer.toString(id)));
        }
	Competicion comp = competiciones.get(id);
        if (comp == null)
            System.out.print("es null la competiciones, mal");

	DataCompeticion dataComp = comp.getDataCompeticion();
	// Se recuerda la competicion!!!!!
	// Se agrega un atributo Competicion compVerDetalles al ControladorCompeticion
	this.compVerDetalles = comp;

	return dataComp;
    }

    // Operacion del sistema
    public List<DataPartido> infoPartidosCompeticion() throws ExDatosNoAsignados {
    // Se muestra la informacion de los partidos de la competicion guardada en memoria

        if(compVerDetalles==null) {
            System.out.print("me niego..");
            throw (new ExDatosNoAsignados("No Existe en la Memoria temporal del Sistema una Competicion Asignada"));
        }
       List<DataPartido> ret = this.compVerDetalles.infoPartidosCompeticion();
       Collections.sort(ret);
       return ret;
    }

    // Operacion del sistema
    public DataJugPartido listarJugadoresPorBando(int idPart) throws ExDatosNoAsignados, ExNoExistePartidoEnCompeticion{
         if(compVerDetalles==null)
              throw (new ExDatosNoAsignados("No Existe en la Memoria temporal del Sistema una Competicion Asignada"));

         boolean error = true;
         List<DataPartido> parts=compVerDetalles.infoPartidosCompeticion();
         for(int i=0;i<parts.size()&& error;i++){
                if (parts.get(i).getDataInfoPart().getIdPar()==idPart)
                     error = false;
         }

         if (error)
             throw ( new ExNoExistePartidoEnCompeticion("No Existe un Partido con Id "+ Integer.toString(idPart) +" para la Competencia seleccionada"));
        return this.compVerDetalles.obtenerDataJugPartido(idPart);
    }

    // Operacion del sistema
    public void finalizarVerDetallesComp() {
        this.compVerDetalles = null;
    }
    /****************************************************/
    /****************************************************/

    /************ CASO DE USO ASIGNAR DIVIDENDOS ************/
    /****************************************************/
    public List<DataInfoPartido> listarPartidosDivNoAsig() {
        Map<Integer,Competicion> competiciones = ManejadorCompeticiones.getInstance().getCompeticiones();

        List<DataInfoPartido> listDataInfPar = new ArrayList<DataInfoPartido>();

        for (Map.Entry<Integer,Competicion> entry: competiciones.entrySet()) {
            Competicion comp = entry.getValue();
            List<DataInfoPartido> listAuxDataInfPar =
                comp.obtenerPartidosDivNoAsignados();

            listDataInfPar.addAll(listAuxDataInfPar);
        }

        Collections.sort(listDataInfPar);
        return listDataInfPar;
    }

    // Operacion del sistema
    public void asignarDividendos(int idPart,int idComp, float divL, float divV, float divE)
            throws ExNoExisteCompeticion, ExNoExistePartidoEnCompeticion,
                ExDividendosYaAsignados,ExDividendosInvalidos{

        Map<Integer,Competicion> competiciones = ManejadorCompeticiones.getInstance().getCompeticiones();

        if (!competiciones.containsKey(idComp)){
           throw (new ExNoExisteCompeticion("No Existe una Competicion con Id "+ Integer.toString(idComp)));
        }

        boolean error = true;
        boolean asig= false;
         List<DataPartido> parts=competiciones.get(idComp).infoPartidosCompeticion();
         for(int i=0;i<parts.size()&& error;i++){
            if (parts.get(i).getDataInfoPart().getIdPar()==idPart){
                     error = false;
                     if (parts.get(i).getDividendos().getEstanAsignados())
                         asig = true;
             }
         }

         if (error)
             throw ( new ExNoExistePartidoEnCompeticion("No Existe un Partido con Id "+ Integer.toString(idPart) +" para la Competencia seleccionada"));

         if (asig)
            throw ( new ExDividendosYaAsignados("Los Dividendos para el Partido Seleccionado ya han sido Asignados"));

            if ((divL<=1)|| (divV<=1) || (divE<=1)){
            throw (new ExDividendosInvalidos("Los Dividendos ingresados no son validos"));
        }

        Competicion comp = competiciones.get(idComp);
        comp.asignarDividendos(idPart,divL,divV,divE);
    }
    /****************************************************/
    /****************************************************/



    /**********************************************************/
    /**********************************************************/
    /**********************************************************/
    /*********************** PARTE WEB ************************/
    /**********************************************************/
    /**********************************************************/
    /**********************************************************/

    /********** VER DETALLES DE PARTIDO *****************/
    /****************************************************/
    public List<DataPartidoComp> listarPartidos(List<DataFiltro> filtros){
        ManejadorCompeticiones mc = ManejadorCompeticiones.getInstance();
        Map<Integer,Competicion> comps = mc.getCompeticiones();
        List<DataPartidoComp> list = new ArrayList<DataPartidoComp>();

        for (Map.Entry<Integer,Competicion> entry : comps.entrySet()){
            Competicion comp = entry.getValue();
            List<DataPartidoComp> aux = comp.partidosCumplenFiltro(filtros);

            list.addAll(aux);
        }

        return list;
    }


    public DataPartido seleccionarPartido(int idComp,int idPart) throws ExNoExisteCompeticion, ExNoExistePartidoEnCompeticion {
        // obtener el info partido del partido dataP.id en competicion dataP.idComp
        // luego retornar this.seleccionarPartido(DataInfoPartido)
        ManejadorCompeticiones mc = ManejadorCompeticiones.getInstance();
        Map<Integer,Competicion> comps = mc.getCompeticiones();
        Competicion comp = comps.get(idComp);
        if (comp==null)
            throw new ExNoExisteCompeticion("No existe la competicion con id = "+idComp);
        DataPartido dp = comp.obtenerDataPartido(idPart);
        if (dp==null)
            throw new ExNoExistePartidoEnCompeticion("No existe el Partido con id = "+idPart+" en la competicion con id = "+idComp);

        this.compe= idComp;
        this.part=idPart;
        return dp ;
    }

    public DataJugPartido verInfoPartidoFinalizado() throws ExNoExisteCompeticion {
        // se puede utilizar DataJugPartido listarJugadoresPorBando(int idPart)
        // para tener una idea pero no utilizar esa funcion!
        ManejadorCompeticiones mc = ManejadorCompeticiones.getInstance();
        Map<Integer,Competicion> comps = mc.getCompeticiones();
        Competicion comp = comps.get(compe);
        if (comp==null)
            throw new ExNoExisteCompeticion("No existe la competicion con id = "+compe);
        DataJugPartido djp = comp.obtenerDataJugPartido(part);
       return djp;
    }

    public void finalizarDetallesPartido() {
        // liberar memoria
        compe=-1;
        part=-1;
    }

    /****************************************************/
    /****************************************************/

    /********** CASO DE USO VER TABLA POSICIONES ************/
    /****************************************************/
    public List<DataIdNombre> listarLigasDefinidas(){
        Map<Integer,Competicion> competiciones = ManejadorCompeticiones.getInstance().getCompeticiones();
        List<DataIdNombre> ligasDefinidas=new ArrayList<DataIdNombre>();
	for (Map.Entry<Integer, Competicion> entry : competiciones.entrySet()) {
	    Competicion l = entry.getValue();
	    if(l.getTipo()==TipoCompeticion.Liga){
		if(l.getEstaDefinido()){
		    DataIdNombre dl=l.getDataBasica();
		    ligasDefinidas.add(dl);
		}
	    }
	}
	return ligasDefinidas;
   }

    public List<DataEquipoLiga> obtenerTablaDePosiciones(int idLiga) {
        Map<Integer,Competicion> comps = ManejadorCompeticiones.getInstance().getCompeticiones();
        Liga liga = (Liga) comps.get(idLiga);
        
        return liga.obtenerTablaDePosiciones();
    }
    /****************************************************/
    /****************************************************/

    /********** CASO DE USO APOSTAR ************/
    /****************************************************/

    public List<DataIdNombre> listarCompeticionDefNoFinDivAsig(){
        Competicion c1;
        List<DataIdNombre> res = new LinkedList();
        int i = 0;
        DataIdNombre d;
        Map<Integer,Competicion> comps = ManejadorCompeticiones.getInstance().getCompeticiones();

        for (Map.Entry<Integer,Competicion> comp : comps.entrySet()){
            c1 = comp.getValue();
            if (!c1.getFinalizado() && c1.getEstaDefinido()){
                d = c1.getNombreIdCompeticionApostar();
                if (d!=null)
                    res.add(i++,d);
            }
        }

        return res;
    }

    public Competicion buscarCompeticion(int id){
        return ManejadorCompeticiones.getInstance().getCompeticiones().get(id);
    }



    /********** LISTAR ULTIMOS PARTIDOS *****************/
    /****************************************************/
    public  List<DataPartido> obtenerUltimosPartidosFinalizados(){
        ManejadorCompeticiones mc = ManejadorCompeticiones.getInstance();
        List<DataPartido> list = new ArrayList<DataPartido>();
        Map<Integer,Competicion> comps = mc.getCompeticiones();

        for (Map.Entry<Integer,Competicion> entry : comps.entrySet()){
           
            Competicion comp = entry.getValue();
           List<DataPartido> aux = comp.obtenerUltimosPartidosFinalizados();

            list.addAll(aux);
        }
        Collections.sort(list);
        List<DataPartido> l = new ArrayList<DataPartido>();

        //probar Noc si agrega ordenado
        for(int i=0;i<list.size() && i<10;i++){
           l.add(list.get(i));
        }

        // ordeno x las dudas
        Collections.sort(l);
        return l;
    }
    /****************************************************/
    /****************************************************/


    /********** VER DETALLE DE EQUIPO *******************/
    /****************************************************/
    //public List<DataEquipo> listarEquipos()
    public List<DataIdNombre> listarJugEquipo(int id)
            throws ExNoExisteEquipo {
        
        ControladorEquipos ce = new ControladorEquipos();
        List<DataIdNombre> jugadores = null;
        jugadores = ce.seleccionarEquipo(id);
        
        return jugadores;
    }
    /****************************************************/
    /****************************************************/

    /*********** VER DETALLES JUGADOR *******************/
    /****************************************************/
    //public List<DataIdNombre> listaJugadores();
    public DataJugador seleccionarJugador(int id)
            throws ExNoExisteJugador {

        ControladorJugadores cj = new ControladorJugadores();
        return cj.seleccionarJugador(id);
    }
    /****************************************************/
    /****************************************************/


    // PERSISTENCIA?
    public TipoCriterio[] getCriterio(int idLiga){
        Map<Integer,Competicion> competiciones = ManejadorCompeticiones.getInstance().getCompeticiones();
        Liga liga=(Liga)competiciones.get(idLiga);
        return liga.getCriterio();
    }

    public List<DataIdNombre> getDataIdNombreEquiposPI(int idPInd){
        Map<Integer,Competicion> competiciones = ManejadorCompeticiones.getInstance().getCompeticiones();
        PartidoIndividual pI=(PartidoIndividual)competiciones.get(idPInd);
        return pI.getDataIdNombreEquipos();
    }

    public  List<DataLlave> getDataLlaves(int idCopa){
        Map<Integer,Competicion> competiciones = ManejadorCompeticiones.getInstance().getCompeticiones();
        Copa copa1=(Copa)competiciones.get(idCopa);
        List<DataLlave> salida=copa1.getDataLlaves();
        Collections.sort(salida);
        return salida;
    }

    public  List<DataPartido> getPartidosLiga(int idLiga){
        Map<Integer,Competicion> competiciones = ManejadorCompeticiones.getInstance().getCompeticiones();
        Liga liga=(Liga)competiciones.get(idLiga);
        return liga.infoPartidosCompeticion();
    }

    public  DataPartido getPartidoInd(int idPI){
        Map<Integer,Competicion> competiciones = ManejadorCompeticiones.getInstance().getCompeticiones();
        PartidoIndividual pI=(PartidoIndividual)competiciones.get(idPI);
        if(pI.infoPartidosCompeticion()!=null&&pI.infoPartidosCompeticion().size()==1){
            return pI.infoPartidosCompeticion().get(0);
        }
        else {
            return null;
        }
    }

    public void setearIdPartido(DataInfoPartido dIP){
        //busca el partido y le setea el id
        //ademas en la competicion en caso de ser un id mayor al actual lo aumenta el idgeneral
        Map<Integer,Competicion> competiciones = ManejadorCompeticiones.getInstance().getCompeticiones();
        Competicion co=competiciones.get(dIP.getIdComp());
        if(co.getTipo()==TipoCompeticion.Copa){
            Copa cop=(Copa)co;
            cop.setIdPartido(dIP.getNomLlave(),dIP.getIdPar());
        }
        else{
            Liga lig=(Liga) co;
            lig.setIdPartido(dIP.getIdLocal(),dIP.getIdVisita(),dIP.getIdPar());
        }
    }


    /// PERSISITENCIAAAAAAAAAAAAAAAAAAAA!!!
    /// PERSISITENCIAAAAAAAAAAAAAAAAAAAA!!!
    /// PERSISITENCIAAAAAAAAAAAAAAAAAAAA!!!
     private void setContadorPartidos(int idComp,int idContP){
        Map<Integer,Competicion> competiciones = ManejadorCompeticiones.getInstance().getCompeticiones();
        Competicion co=competiciones.get(idComp);
        co.setContadorPartidos(idContP);
    }

    public void cargarCompeticiones(String ruta)throws Exception{

        ManejadorCompeticiones.getInstance().getCompeticiones().clear();
        ManejadorCompeticiones.getInstance().setIdCompetencia(1);

        XLSFabricaDAO f=new XLSFabricaDAO();
        IControladorCompeticionesDAO cDTO=f.getIControladorCompeticionesDAO();
        List<DTOCompeticiones> lista=cDTO.cargarCompeticiones(ruta);
        int tope=0;
        for(int i=0;i<lista.size();i++){
            TipoCompeticion tipoC=lista.get(i).getDataCompeticion().getTipo();
            int id=lista.get(i).getDataCompeticion().getId();
            if(id>tope){
                tope=id;
            }
            if(tipoC==TipoCompeticion.Copa){
                this.cargarCopa(lista.get(i));
            }
            else{
                if(tipoC==TipoCompeticion.Liga){
                    this.cargarLiga(lista.get(i));
                }
                else{
                    if(tipoC==TipoCompeticion.PartidoIndividual){
                        this.cargarIndividual(lista.get(i));
                    }
                }
            }
        }
        ManejadorCompeticiones.getInstance().setIdCompetencia(tope+1);//seteo id
    }

    

    private void cargarCopa(DTOCompeticiones dto)throws Exception{
        /*/primera fila se agregan datos de la copa
        arch.agregarString("Competiciones",0,fila,dC.getTipo().toString());*/
        DataCompeticion dC=dto.getDataCompeticion();
        int idC=dC.getId();
        String nomC=dC.getNombre();
        String pathC=dC.getPathImage();
        ingresarCompeticion(nomC,TipoCompeticion.Copa,pathC);

        List<DataDivEquipo> listaDDE=dC.getDividendos();

        for(int i=0;i<listaDDE.size();i++){
            DataDivEquipo dDE=listaDDE.get(i);
            int idE=dDE.getId();
            seleccionarEquipo(idE);
            float div=dDE.getDividendo();
            ingresarDividendoEquipo(idE,div);
        }
        //18-10
        List<DataTypes.DataInfoJugCampeonatoPersistencia> listaDIJCP=dto.getListaInfoJugCampeonatoYApuestas();
        //se agregan los infojugadores
        for(int i=0;i<listaDIJCP.size();i++){
            DataTypes.DataInfoJugCampeonatoPersistencia dIJCP= listaDIJCP.get(i);
            DataGoleador dG=dIJCP.getDataGoleador();
            int idJug=dG.getId();
            float div=dG.getDividendo();
            agregarIJC(idJug,div);
        }
        //18-10 fin
        ManejadorCompeticiones.getInstance().setIdCompetencia(idC);//seteo id
        darDeAltaCompetencia();
        finalizar();


//llaves
        List<DataLlave> listaDL=dto.getDataLlaves();
        int tope=0;
        for(int l=0;l<listaDL.size();l++){

            DataLlave dL=listaDL.get(l);
            int idP=dL.getDataPartido().getDataInfoPart().getIdPar();
            setContadorPartidos(idC,idP);
            if(idP>tope){
                tope=idP;
            }

            String nomL=dL.getNombre();
            int fas=dL.getFase();
            ingresarDatosLlaveDeCopa(idC,nomL);

            if(fas==1){
                int idEqL=dL.getIdLocal();
                int idEqV=dL.getIdVisita();
                ingresarDatosPartida(idEqL,idEqV);
            }
            else{
                String llaveLocal=dL.getPreLocal();
                String llaveVisita=dL.getPreVisita();
                ingresarLlavesPredecesoras(llaveLocal,llaveVisita);
            }
            boolean tieneSuc,esFin;
            tieneSuc=dL.getTieneSucesora();
            esFin=dL.getEsFinal();
            ingresarTipoLlave(tieneSuc,esFin);
            cargarPartido(idC,TipoCompeticion.Copa,dL.getDataPartido());
            //ver si conviene agregar apuestas aqui
        }
        setContadorPartidos(idC,tope+1);

        this.cargarApuesta(dto.getApuestasCampeon());
        //18-10
        Campeonato cam=(Campeonato)ManejadorCompeticiones.getInstance().getCompeticiones().get(idC);//seteo id
        for(int i=0;i<listaDIJCP.size();i++){

            DataTypes.DataInfoJugCampeonatoPersistencia dIJCP= listaDIJCP.get(i);
            List<DataApuestaPersistencia> apuestasJug=dIJCP.getApuestasAGoleador();
            DataGoleador dG=dIJCP.getDataGoleador();
            int goles=dG.getGoles();
            int idJug=dG.getId();
            cam.setGolesJugCampeonato(idJug, goles);
            this.cargarApuesta(apuestasJug);
		}
		
		//this.cargarPencas(cam,dto.getPpencas());
                this.cargarPencas(cam,dC.getMontoPenca(),dto.getPpencas());
cam.setForo(dto.getForo());
    }

    private void cargarLiga(DTOCompeticiones dto)throws Exception{
        DataCompeticion dC=dto.getDataCompeticion();
        int idC=dC.getId();
        String nomC=dC.getNombre();
        String pathC=dC.getPathImage();
        ingresarCompeticion(nomC,TipoCompeticion.Liga,pathC);


        TipoCriterio[] criter=dto.getCriterios();
        ingresarOrdenLiga(criter);
        //agrego equipos y dividendos
        List<DataDivEquipo> listaDDE=dC.getDividendos();

        for(int i=0;i<listaDDE.size();i++){
            DataDivEquipo dDE=listaDDE.get(i);
            int idE=dDE.getId();
            seleccionarEquipo(idE);
            float div=dDE.getDividendo();
            ingresarDividendoEquipo(idE,div);
        }
         //18-10
        List<DataTypes.DataInfoJugCampeonatoPersistencia> listaDIJCP=dto.getListaInfoJugCampeonatoYApuestas();
        //se agregan los infojugadores
        for(int i=0;i<listaDIJCP.size();i++){
            DataTypes.DataInfoJugCampeonatoPersistencia dIJCP= listaDIJCP.get(i);
            DataGoleador dG=dIJCP.getDataGoleador();
            int idJug=dG.getId();
            float div=dG.getDividendo();
            agregarIJC(idJug,div);
        }
        //18-10 fin
        ManejadorCompeticiones.getInstance().setIdCompetencia(idC);//seteo id
        darDeAltaCompetencia();
        finalizar();

        

        int tope=0;
        List<DataPartido> listaDP=dto.getPartidos();
        for(int p=0;p<listaDP.size();p++){
            int idP=listaDP.get(p).getDataInfoPart().getIdPar();
            setContadorPartidos(idC,idP);
            if(idP>tope){
                tope=idP;
            }
           cargarPartido(idC,TipoCompeticion.Liga,listaDP.get(p));
        }
        setContadorPartidos(idC,tope+1);

        this.cargarApuesta(dto.getApuestasCampeon());
        //se setean los goles de los infoJ y apuestas
         //18-10
        Campeonato cam=(Campeonato)ManejadorCompeticiones.getInstance().getCompeticiones().get(idC);//seteo id
        for(int i=0;i<listaDIJCP.size();i++){

            DataTypes.DataInfoJugCampeonatoPersistencia dIJCP= listaDIJCP.get(i);
            List<DataApuestaPersistencia> apuestasJug=dIJCP.getApuestasAGoleador();
            DataGoleador dG=dIJCP.getDataGoleador();
            int goles=dG.getGoles();
            int idJug=dG.getId();
            cam.setGolesJugCampeonato(idJug, goles);
            this.cargarApuesta(apuestasJug);
		}
		
		//this.cargarPencas(cam,dto.getPpencas());
            this.cargarPencas(cam,dC.getMontoPenca(),dto.getPpencas());
            cam.setForo(dto.getForo());
    }

    private void cargarIndividual(DTOCompeticiones dto)throws Exception{
        DataCompeticion dC=dto.getDataCompeticion();
        int idC=dC.getId();
        String nomC=dC.getNombre();
        String pathC=dC.getPathImage();
        ingresarCompeticion(nomC,TipoCompeticion.PartidoIndividual,pathC);

        boolean hayPartido=(dto.getPartidos()!=null);

        int idE=dC.getDividendos().get(0).getId();
        seleccionarEquipo(idE);
        idE=dC.getDividendos().get(1).getId();
        seleccionarEquipo(idE);

        ManejadorCompeticiones.getInstance().setIdCompetencia(idC);//seteo id
        darDeAltaCompetencia();
        finalizar();

        if(hayPartido){
            cargarPartido(idC,TipoCompeticion.PartidoIndividual,dto.getPartidos().get(0));//verif
        }
    }

    public void cargarApuesta(List<DataApuestaPersistencia> apuestas){
        ControladorUsuarios cu=new ControladorUsuarios();
        Map<Integer,Competicion> competiciones = ManejadorCompeticiones.getInstance().getCompeticiones();
        for(int a=0;a<apuestas.size();a++){
            DataApuestaPersistencia dAP=apuestas.get(a);
            Apuesta apu=null;

	    Objetos.Usuario u = ManejadorUsuarios.getInstance().getUsuario(dAP.getNick());

            if(dAP.getTipoApuesta()==TipoApuesta.Partido){//es apuesta partido
                apu=new ApuestaPartido(dAP.getMontoApostado(), dAP.getTipoDividendo(),
                        dAP.getFecha(),dAP.getEstado(),u);
                Competicion co=competiciones.get(dAP.getIdCampeonato());
                Objetos.Partido p = co.obtenerPartidoApuesta(dAP.getIdPartido());
                ((ApuestaPartido)apu).agregarApuestaPartidoCamp(p);
            }
            else if(dAP.getTipoApuesta()==TipoApuesta.ResExacto)
            {//es apuesta res exacto
                apu=new Objetos.ApuestaResultadoExacto(dAP.getMontoApostado(),dAP.getFecha(),
                        dAP.getEstado(),u, dAP.getGolesLoc(),dAP.getGolesVis());

                Competicion co=competiciones.get(dAP.getIdCampeonato());
                Objetos.Partido p = co.obtenerPartidoApuesta(dAP.getIdPartido());
                ((ApuestaPartido)apu).agregarApuestaPartidoCamp(p);
            }
            else if (dAP.getTipoApuesta()==TipoApuesta.Campeonato){
                apu=new ApuestaCampeonato(dAP.getMontoApostado(),dAP.getFecha(), dAP.getEstado(),u);
                Competicion co=competiciones.get(dAP.getIdCampeonato());
                co.agregarApuestaEquipoCamp(apu,dAP.getIdEquipo());
            }
            else if (dAP.getTipoApuesta()==TipoApuesta.Goleador){
                Competicion co=competiciones.get(dAP.getIdCampeonato());
                Campeonato camp = (Campeonato) co;
                InfoJugadorCampeonato infJugCamp = camp.getInfoJugadorCampeonato(dAP.getIdGoleador());

                apu=new Objetos.ApuestaGoleador(dAP.getMontoApostado(),dAP.getFecha(),
                        dAP.getEstado(),u,infJugCamp);

                infJugCamp.agregarNavGoleadorApuesta(apu);
            }
            cu.cargaAC(apu,dAP.getNick(),dAP.getIdPaquete());
        }
     }

     //guarda en la sig fila/devuelve fila en la ultima fila escrita
     private void cargarPartido(int idC,TipoCompeticion tipo,DataPartido dP)throws Exception{
        DataInfoPartido dIP=dP.getDataInfoPart();
        int idP=dIP.getIdPar();

        DataFechaHora fecha=dIP.getFechaHora();
        String lugar=dIP.getLugar();

        if(tipo==TipoCompeticion.Copa){
            confirmarAltaLlaveCopa(fecha,lugar);
            limpiarMemoriaAuxiliar();
        }
        int idEqL=dIP.getIdLocal();
        int idEqV=dIP.getIdVisita();

        //hay equipos
        if(idEqL!=-1 &&idEqV!=-1){
            if(tipo!=TipoCompeticion.PartidoIndividual){
                //setearIdPartido(dIP);
                if(tipo==TipoCompeticion.Liga){
                    ingresarIdLiga(idC);
                    seleccionarEncuentroADefinir(idEqL,idEqV,fecha,lugar);
                }
            }
            else{
                ingresarDatosPartidoIndividual(idC,idEqL,idEqV,fecha,lugar);
            }


            if(dP.getDividendos().getEstanAsignados()){
                Float divL=dP.getDividendos().getDividendoLocal();
                Float divV=dP.getDividendos().getDividendoVisita();
                Float divE=dP.getDividendos().getDividendoEmpate();
                asignarDividendos(idP,idC,divL,divV,divE);

                finalizar();
                listarPartidosConDivAsignado(idC);
                seleccionarPartido(dIP);

                boolean finalizo=dP.getEstaFinalizado();
                if(finalizo){
                    int gL=dP.getGolesL();
                    int pL=dP.getPenalesL();
                    if(pL<0){
                        pL=0;
                    }
                    int gV=dP.getGolesV();
                    int pV=dP.getPenalesV();
                    if(pV<0){
                        pV=0;
                    }
                    ingresarResultado(gL,pL,gV,pV);

                    List<Integer> idJugL=dP.getJugLocales();
                    for(int j=0;j<idJugL.size();j++){
                        int idJL=idJugL.get(j);
                        ingresarJugador(idJL,idEqL);
                    }

                    List<Integer> idJugV=dP.getJugVisitante();
                    for(int j=0;j<idJugV.size();j++){
                        int idJV=idJugV.get(j);
                        ingresarJugador(idJV,idEqV);
                    }
                    listarIngresos();
                    //asignarr eventos
                    this.dataR.setEventos(dP.getEventos());
                    confirmarIngreso(true);
                }
            }
        }
        Competicion cResExa=ManejadorCompeticiones.getInstance().getCompeticiones().get(idC);
        cResExa.cargarResExaPersistencia(dP.getDataInfoPart().getIdPar(),dP.getDataInfoPart().getNomLlave(),dP.getGolesDivs());
        cargarApuesta(dP.getApuestas()); 
    }

	// Persistencia Penca
    private void cargarPencas(Campeonato cam,float monto,List<DataInfoUsuarioPencaPersistencia> lista){
        ManejadorUsuarios mu=ManejadorUsuarios.getInstance();
        cam.setMontoPenca(monto);
        for (int i=0;i<lista.size();i++){
            DataInfoUsuarioPencaPersistencia dataPencaU=lista.get(i);
            List<DataIUPPPersistencia> listaP=dataPencaU.getListaPartidosPencaPersistencia();
            Objetos.Usuario u = mu.getUsuario(dataPencaU.getNick());
            cam.cargarInfoUsuario (u,dataPencaU.getPuntos());
            for (int p=0;p<listaP.size();p++){
                DataIUPPPersistencia dataPP=listaP.get(p);
                cam.apostarPartidoPenca(u.getNick(),dataPP.getIdPartido(),
                        dataPP.getGolesLocal(),dataPP.getGolesVisita());
            }
        }
    }
	

     public String getImagePath(int idEquipo){
         ManejadorEquipos me = ManejadorEquipos.getInstance();
         Equipo e = me.getEquipos().get(idEquipo);
         return e.getImagePath();
     }

     public boolean competicionEstaFinalizada(int idComp) {
        Map<Integer,Competicion> competiciones = ManejadorCompeticiones.getInstance().getCompeticiones();
        return competiciones.get(idComp).getFinalizado();
     }


     //9-10
     
     public void agregarIJC(int idJug,float dividendo) throws Exception{
        if (dividendo<=1) {
            throw (new ExDividendosInvalidos("El dividendo ingresado es incorrecto. Debe ser mayor que 1"));
        }
        ControladorJugadores cj=new ControladorJugadores();
        Objetos.Jugador jugador=cj.getJugador(idJug);
        if (jugador ==null){
            throw (new ExNoExisteJugador("No Existe un Jugador con Id "+ Integer.toString(idJug)));
        }
        if (listaInfoJC.containsKey(idJug)) {
            throw (new ExJugadorYaIngresado("El Jugador con Id "+ Integer.toString(idJug)+" ya ha sido Seleccionado"));
        }
        
        InfoJugadorCampeonato iJC=new InfoJugadorCampeonato(jugador,dividendo,null);
        this.listaInfoJC.put(idJug,iJC);
     }
     
     public void setMontoPenca(float monto)throws Exception{
         if(!(monto>0)){
             throw (new ExMontoInvalido("El monto debe ser mayor a 0"));
         }
         this.montoPenca=monto;
     }
     
     @Override
     public int darDeAltaCompetencia() throws ExDatosNoAsignados{

        Map<Integer,Competicion> competiciones = ManejadorCompeticiones.getInstance().getCompeticiones();

	if (nombreCompeticion==null || tipo== null || (tipo==TipoCompeticion.Liga && dc ==null))
            throw (new ExDatosNoAsignados("Faltan ingresar datos"));

        if (!(this.tipo == TipoCompeticion.PartidoIndividual)) {
            if(equipos.size()!=dividendosCampeonato.size()){
                throw (new ExDatosNoAsignados("Hay Equipos con Dividendos por asignar"));
            }
        }

	int id = ManejadorCompeticiones.getInstance().getIdCompetencia();
        ManejadorCompeticiones.getInstance().setIdCompetencia(id+1);

        
        Competicion comp;
        List<DataPartidoNoDefinidoLigaInterfaz> partidosNoDefinidosLiga_AUX = new ArrayList(); 
	if(this.tipo==TipoCompeticion.Liga){
            DataPartidoNoDefinidoLigaInterfaz di;
            DataEquipo dEV;
            DataEquipo dEL;
	    for (Map.Entry<Integer,DataEquipo> entry : equipos.entrySet()) {
		for (Map.Entry<Integer,DataEquipo> entry2 : equipos.entrySet()) {
		    dEL=entry.getValue();
		    dEV=entry2.getValue();
		    if(dEL!=dEV){
			DataPartidoNoDefinidoLiga dataPNDL =
				new DataPartidoNoDefinidoLiga(dEL.getId(),dEV.getId(),dEL.getNombre(),dEV.getNombre());
			partidosNoDefinidosLiga.add(dataPNDL);
		    }
		}

                dEL = entry.getValue();
                di = new DataPartidoNoDefinidoLigaInterfaz(dEL,equipos);
                partidosNoDefinidosLiga_AUX.add(di);

	    }
	    comp = new Liga(id,nombreCompeticion,partidosNoDefinidosLiga,partidosNoDefinidosLiga_AUX,dc,this.pahtImage);

	} else if(this.tipo==TipoCompeticion.Copa) {
	    int nroEquipos = equipos.size();
	    int topeFase = 0;
            int i = nroEquipos;
            while (i != 1) {
                i = i/2;
                topeFase++;
	    }
	    comp = new Copa(id, nombreCompeticion,topeFase,this.pahtImage);

	} else {
	    comp = new PartidoIndividual(id,nombreCompeticion,this.pahtImage);
	}

	comp.agregarEquipos(equipos,dividendosCampeonato);
         //9-10             
        if(this.tipo!=TipoCompeticion.PartidoIndividual){
            ((Objetos.Campeonato)comp).setListaInfoJugCam(listaInfoJC);  
            ((Objetos.Campeonato)comp).setMontoPenca(montoPenca);
        }
        //
	competiciones.put(id,comp);
        System.out.print("se dio de alta con " + id);
	return id;
    }
    
    public List<DataTypes.DataGoleador> get5MaxGoleadores(int idC)throws Exception{
        Map<Integer,Competicion> competiciones = ManejadorCompeticiones.getInstance().getCompeticiones();
        if (!competiciones.containsKey(idC)){
           throw (new ExNoExisteCompeticion("No Existe una Competicion con Id "+ Integer.toString(idC)));
        }
	return ((Objetos.Campeonato)competiciones.get(idC)).get5MaxGoleadores();
    }

    //caso de uso dar de alta
    public List<DataTypes.DataGoleador> getJugadoresApostables(){
        List<DataTypes.DataGoleador> salida=new java.util.ArrayList<DataTypes.DataGoleador>();
        for (Map.Entry<Integer,InfoJugadorCampeonato> entry : this.listaInfoJC.entrySet()) {
            salida.add(entry.getValue().getDataDivGoleador());
        }
        return salida;
    }         
    public DataCompeticion mostrarInfoCompetencia(){
        int idCompetencia = ManejadorCompeticiones.getInstance().getIdCompetencia();
        DataCompeticion dC=new DataCompeticion(idCompetencia, nombreCompeticion,
                tipo,dividendosCampeonato,false,this.pahtImage);
        dC.setMontoPenca(this.montoPenca);
        return dC;
    }       
     public void finalizar() {
	nombreCompeticion = null;
        tipo = null;
        this.pahtImage = null;
	equipos.clear();
	dc = null;
        dividendosCampeonato.clear();
	partidosNoDefinidosLiga.clear();
        this.montoPenca=-1;
        this.listaInfoJC.clear();

    }

//EVENTOS
        /*ingresa los eventos a la memoria temporal, para el caso finalizar partido
         * los inserta en una lista para luego crear las instancias de la misma y 
		 setearselas al Finalizado de partido
         */
        private List<DataTypes.DataEvento> DataEventos;
        
        
        public void ingresarEventos(DataTypes.DataEvento dataE) throws Exception{
            //se tiran excepciones, ver
            DataEventos.add(dataE);
        }

        public void confirmarEventos(){
            //le seteamos los eventos al dataresumen
            this.dataR.setEventos(this.DataEventos);
        }
     //EVENTOS

        private Map<GolesKey,Float> golesDivs=null;
        private boolean golesDivsAsig;

	 /// DIVIDENDOS RESULTADO EXACTO
	public void almacenarAsigDividendoRes (int golesLocal, int golesVisita, float div){
            if (golesDivs == null){
                golesDivsAsig=false;
                golesDivs = new HashMap();
            }
            if (null == golesDivs.put(new GolesKey(golesLocal,golesVisita), div))
                System.out.println("Asocio con exito");
        
        }
    
    public void limpiarMemoriaDivs (){
        golesDivs = null;
    }
    
    public void asignarDividendoPartidoResultado (int idPart, int idComp)
                                        throws ExNoExisteCompeticion, ExNoExistePartidoEnCompeticion,
                                               ExDividendosYaAsignados,ExDividendosInvalidos{
         
        if (golesDivs != null){
            Map<Integer,Competicion> comps =  ManejadorCompeticiones.getInstance().getCompeticiones();
            if (!comps.containsKey(idComp)){
               throw (new ExNoExisteCompeticion("No Existe una Competicion con Id "+ Integer.toString(idComp)));
            }
            
            
            boolean error = true;
            boolean asig= false;
             List<DataPartido> parts=comps.get(idComp).infoPartidosCompeticion();
             for(int i=0;i<parts.size()&& error;i++){
                if (parts.get(i).getDataInfoPart().getIdPar()==idPart){
                         error = false;
                         if (parts.get(i).getDividendos().getEstanAsignados())
                             asig = true;
                 }
             }

             

             if (error)
                 throw ( new ExNoExistePartidoEnCompeticion("No Existe un Partido con Id "+ Integer.toString(idPart) +" para la Competencia seleccionada"));
             Competicion comp = comps.get(idComp);

             //System.out.println("2");
             comp.asignarDividendoResultados(idPart,golesDivs);
             golesDivs = null;
         }
         
    }
	/// DIVIDENDOS RESULTADO EXACTO
	
          //LISTAR PENCAS DISPONIBLES
     public List<DataIdNombre> listarPencasDisponibles() {

         List<DataIdNombre> pencas = new ArrayList<DataIdNombre>();
         ManejadorCompeticiones mc = ManejadorCompeticiones.getInstance();
        Map<Integer,Competicion> comps = mc.getCompeticiones();

        for (Map.Entry<Integer,Competicion> entry : comps.entrySet()){
             Competicion comp= entry.getValue();
             if ((comp instanceof Liga || comp instanceof Copa)){
                      DataIdNombre data= new DataIdNombre(comp.getId(),comp.getNombre());
                      pencas.add(data);
            }
        }
      return pencas;
     }


     public boolean getDivsResultsAsignados(){
         return golesDivsAsig;
     }

     public void finalizarAsigDivsResultados(){
         golesDivsAsig=true;
     }

     /// DIVIDENDOS RESULTADO EXACTO****************************
    // Obtiene los dividendos asignados para el partido guardado en memoria
    public float[][] obtenerDividendosResultadoExacto(int idComp, int idPart) {
        Competicion comp = ManejadorCompeticiones.getInstance().getCompeticiones().get(new Integer(idComp));
        return comp.obtenerDividendosResultadoExacto(idPart);
    }
    /// DIVIDENDOS RESULTADO EXACTO****************************

    /// GOLEADORES ******
    public List<DataTypes.DataGoleador> getJugadoresCampeonato(int idC)
            throws ExNoExisteCompeticion, Exception {
        Map<Integer,Competicion> competiciones = ManejadorCompeticiones.getInstance().getCompeticiones();
        if (!competiciones.containsKey(idC)){
           throw (new ExNoExisteCompeticion("No Existe una Competicion con Id "+ Integer.toString(idC)));
}
	return ((Objetos.Campeonato)competiciones.get(idC)).getJugadoresCampeonato();
    }
    /// GOLEADORES ******

    public void mailPartidoApostable(int idPart,int idComp,float divL,float divV,float divE)throws Exception{
        Map<Integer,Competicion> competiciones = ManejadorCompeticiones.getInstance().getCompeticiones();
        List<DataPartido> parts=competiciones.get(idComp).infoPartidosCompeticion();
        boolean buscar=true;
        String mensaje="IBet le comunica que esta disponible la apuesta de un nuevo partido\n\n";
        for(int i=0;i<parts.size()&& buscar;i++){
            if (parts.get(i).getDataInfoPart().getIdPar()==idPart){
                 buscar = false;
                 mensaje+="Equipo Local: "+parts.get(i).getDataInfoPart().getNomLocal()+"\n"+
                     "Equipo Visitante: "+parts.get(i).getDataInfoPart().getNomVisita()+"\n"+
                     "Dividendo Local: "+divL+"\n"+
                     "Dividendo Visitante: "+divV+"\n"+
                     "Dividendo Empate: "+divE+"\n"+
                     "Fecha del Partido: "+parts.get(i).getDataInfoPart().getFechaHora().toString()+"\n"+
                     "Lugar: "+parts.get(i).getDataInfoPart().getLugar()+"\n";
                     if(null!=golesDivs && !golesDivs.isEmpty()){
                         for (java.util.Map.Entry<GolesKey,Float>  entry: golesDivs.entrySet()) {

                            Float div = entry.getValue();
                            GolesKey gk=entry.getKey();
                            String gL=Integer.toString(gk.getGolesLocal());
                            String gV=Integer.toString(gk.getGolesVisita());
                            mensaje+="Resultado:"+gL+"-"+gV+"  Dividendo:"+div+"\n";
                         }
                     }
             }
        }
        ControladorUsuarios cu=new ControladorUsuarios();
        cu.notificarPartido(mensaje);
    }


     public void guardarCompeticiones(String ruta)throws Exception{
        List<DTOCompeticiones> listaDTO=new ArrayList<DTOCompeticiones>();

        ManejadorCompeticiones mc = ManejadorCompeticiones.getInstance();
        Map<Integer,Competicion> comps = mc.getCompeticiones();
        System.out.println(comps.size());
        for (Map.Entry<Integer,Competicion> entry : comps.entrySet()){
           Competicion comp = entry.getValue();
           System.out.println(comp.getId());
            int idC=comp.getId();
            DataCompeticion dC=verInfoCompeticion(idC);
            if(dC.getTipo()==TipoCompeticion.Copa){
                List<DataLlave> listDL=getDataLlaves(idC);
                listaDTO.add(new DTOCompeticiones(dC,listDL,
                  ((Copa)comp).getApuestas(),((Copa)comp).getDataInfoJugadorCampeonato(),
                     ((Copa)comp).getDataIUsuarioPencaPersistencia(),((Copa)comp).getForo().getMensajes()));

            }
            else{
                if(dC.getTipo()==TipoCompeticion.Liga){
                    List<DataPartido> listaP=getPartidosLiga(idC);
                    TipoCriterio[] criter=getCriterio(dC.getId());
                    listaDTO.add(new DTOCompeticiones(dC,criter,listaP,
                      ((Liga)comp).getApuestas(),((Liga)comp).getDataInfoJugadorCampeonato(),
                         ((Liga)comp).getDataIUsuarioPencaPersistencia(),((Liga)comp).getForo().getMensajes() ));

                }
                else{
                     DataPartido dP=getPartidoInd(idC);
                     listaDTO.add(new DTOCompeticiones(dC,dP));
                }
            }
        }
        XLSFabricaDAO f=new XLSFabricaDAO();
        IControladorCompeticionesDAO ce=f.getIControladorCompeticionesDAO();
        ce.guardarCompeticiones(ruta, listaDTO);
    }


}
