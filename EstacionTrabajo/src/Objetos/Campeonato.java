package Objetos;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import DataTypes.DataDivEquipo;
import DataTypes.DataEquipo;
import DataTypes.DataInfoPenca;
import DataTypes.DataPartido;
import DataTypes.TipoCompeticion;
import DataTypes.DataInfoUsuarioPenca;
import DataTypes.DataJugPartido;
import DataTypes.DataInfoPartido;
import DataTypes.DataCompeticion;
import DataTypes.DataIdNombre;
import DataTypes.DataApuestaPersistencia;
import DataTypes.DataApuesta;
import Excepciones.ExEquipoInvalido;
import Excepciones.ExNoExisteEquipo;
import Controladores.ControladorEquipos;
import DataTypes.DataMensajePenca;

public abstract class Campeonato extends Competicion {
    // ATRIBUTOS
    protected Map<Integer,InfoEquipoCampeonato> listInfoEqCamp;
    protected Map<String,InfoUsuarioPenca> usuarios;
    protected Map<Integer,InfoJugadorCampeonato> listInfoJugCamp;
    protected float montoPenca;
    protected Foro foro;

    // OPERACIONES
    public Campeonato(int id, String nombre, String path) {
	super(id,nombre,path);
	listInfoEqCamp = new HashMap<Integer,InfoEquipoCampeonato>();
        usuarios = new HashMap<String,InfoUsuarioPenca>();
        listInfoJugCamp=new HashMap<Integer,InfoJugadorCampeonato>();
        foro = new Foro(); 
    }

    // Foro!!!!!
    public Foro getForo() {
        return this.foro;
    }
   

    public List<DataMensajePenca> obtenerMensajesForo() {
        //List<DataMensajePenca> mensajes = new ArrayList<DataMensajePenca>();
        //for ()
        return this.foro.obtenerMensajesForo();
    }

    // VER DETALLES DE COMPETICION

    public abstract List<DataPartido> infoPartidosCompeticion();

    public abstract DataJugPartido obtenerDataJugPartido(int idP);

    // ALTA COMPETICION
    public abstract void agregarEquipos( Map<Integer,DataEquipo> equipos,List<DataDivEquipo> dividendos);

    /// ASIGNAR DIVIDENDOS
    public abstract List<DataInfoPartido> obtenerPartidosDivNoAsignados();



    public DataIdNombre getNombreIdCompeticionApostar(){
        return new DataIdNombre(this.getId(),this.getNombre());
    }
    public List<DataDivEquipo> obtenerDivsPartCamp(){
        List<DataDivEquipo> res = new ArrayList(listInfoEqCamp.size());
        DataDivEquipo d;

        for (Map.Entry<Integer,InfoEquipoCampeonato> infoC : listInfoEqCamp.entrySet()){
            d = (infoC.getValue()).getDataDivEquipo();
            res.add(d);
        }

        return res;
    }
    public DataApuesta obtenerDatosApuesta(int idEq, float monto, float saldo, DataCompeticion dataComp)
            throws ExEquipoInvalido, ExNoExisteEquipo{

        ControladorEquipos ce = new ControladorEquipos();

        Equipo eq = ce.buscarEquipo(idEq);

        if (eq==null)
            throw new ExNoExisteEquipo("Equipo no existe");


        float beneficio=0, saldoNuevo=0, dividendo=0;
        List<InfoEquipoCampeonato> lista_infoC = new ArrayList(listInfoEqCamp.values());
        int i=0;
        boolean enc=false;

        while (!enc && i < lista_infoC.size())
            if (lista_infoC.get(i).getEquipo().getId() != idEq)
                 i++;
            else
                 enc=true;

        if (i == lista_infoC.size())
            throw new ExEquipoInvalido("No existe el equipo para este campeonato");

        dividendo = lista_infoC.get(i).getDividendoCampeonato();
        beneficio = dividendo*monto;
        saldoNuevo = saldo+beneficio;

        return new DataTypes.DataApuestaCampeon(monto,saldoNuevo,beneficio,dividendo,eq.getDataEquipo(),dataComp,DataTypes.EstadoApuesta.Pendiente,-1);
    }

    // ver ultimos 10 partidos
     public abstract List<DataPartido> obtenerUltimosPartidosFinalizados();

    

    public  void agregarApuestaEquipoCamp (Apuesta ap, int idEq){
        ApuestaCampeonato a = (ApuestaCampeonato)ap;
        List<InfoEquipoCampeonato> lista_infoC = new ArrayList(listInfoEqCamp.values());
        int i=0;

        while (lista_infoC.get(i).getEquipo().getId() != idEq)
                i++;

        InfoEquipoCampeonato infoE = lista_infoC.get(i);
        a.agregarNavApuestaInfoCamp(infoE);
        infoE.agregarNavInfoCampApuesta(a);
    }

    /// NUEVO!!!
    public  List<DataApuestaPersistencia> getApuestas (){
        List<DataApuestaPersistencia> salida=new ArrayList<DataApuestaPersistencia>();
        for (Map.Entry<Integer,InfoEquipoCampeonato> infoC : listInfoEqCamp.entrySet()){
            InfoEquipoCampeonato iE=infoC.getValue();
            salida.addAll(iE.getDAP(id,-1));
        }
        return salida;
     }
// ALTA COMPETICION
    //public abstract void agregarEquipos( Map<Integer,DataEquipo> equipos,List<DataDivEquipo> dividendos);

    //9-10


    public void setMontoPenca(float monto){
            this.montoPenca=monto;
    }
       // System.out.println("en campeonato get5l");
    public boolean participaEnPenca(Usuario u){
        boolean participa = false;
        if(usuarios.containsKey(u.getNick()))
            participa= true;
        return participa;
    }

    public float getMontoPenca(){
        return this.montoPenca;
    }

     public List<DataTypes.DataGoleador> get5MaxGoleadores(){
        List<InfoJugadorCampeonato> aux=new java.util.ArrayList<InfoJugadorCampeonato>();
        for (Map.Entry<Integer,InfoJugadorCampeonato> entry : this.listInfoJugCamp.entrySet()) {
            aux.add(entry.getValue());
        }
        System.out.println("en campeonato get5lista gol "+aux.size());
        java.util.Collections.sort(aux);//ordena de mayor a menor por goles
        //recorta en los primeros 5 goleadores
        List<DataTypes.DataGoleador>salida=new java.util.ArrayList<DataTypes.DataGoleador>();
        for (int i=0;(i<5)&&(i<aux.size());i++){
            salida.add(aux.get(i).getDataDivGoleador());
        }
        return salida;
    }
    public void setListaInfoJugCam(Map<Integer,InfoJugadorCampeonato> listaIJC){
        for (Map.Entry<Integer,InfoJugadorCampeonato> entry : listaIJC.entrySet()) {
            InfoJugadorCampeonato iJC=entry.getValue();
            InfoJugadorCampeonato add=new InfoJugadorCampeonato(iJC.getJugador(),iJC.getDividendoGoleador(),this);
            listInfoJugCamp.put(add.getIdJugador(),add);
        }
        System.out.println("en campeonato get5lista gol "+listInfoJugCamp.size());
    }
    public final DataCompeticion getDataCompeticion() {
    // Retorno la informacion de la competicion y los dividendos para campeon de cada equipo
	DataDivEquipo dataDivEq;
	List<DataDivEquipo> listDataDivEq = new ArrayList<DataDivEquipo>();
	for (Map.Entry<Integer, InfoEquipoCampeonato> entry : listInfoEqCamp.entrySet()) {

	//for(InfoEquipoCampeonato infoEqCamp : listInfoEqCamp){
            InfoEquipoCampeonato infoEqCamp=entry.getValue();
	    dataDivEq = infoEqCamp.getDataDivEquipo();
	    listDataDivEq.add(dataDivEq);
	}

	TipoCompeticion tipoC = null;
	if (this instanceof Copa){
	    tipoC=tipoC.Copa;
        }
        else{
	    tipoC=tipoC.Liga;
        }
	DataCompeticion dataComp = new DataCompeticion(this.id,this.nombre,tipoC,listDataDivEq,this.estaDefinido,this.pathArchivo);
        dataComp.setMontoPenca(this.montoPenca);
	return dataComp;
    }
    public void confirmarParticipacion(Usuario u){
        InfoUsuarioPenca iup=new InfoUsuarioPenca(u);
        usuarios.put(u.getNick(), iup);
        u.setSaldo(u.getSaldo()-montoPenca);
          
    }

    public abstract List<DataInfoPartido> obtenerPartidosNoFinalizadosPenca(Usuario u);

    public boolean usuarioApostoPartido(String nick,int idPart){
        InfoUsuarioPenca inf= this.usuarios.get(nick);
        boolean yaAposto=false;
        if (inf.apostoPartido(idPart))
             yaAposto=true;
        return yaAposto;
    }

    public abstract void apostarPartidoPenca(String nick,int dPart,int golesLoc,int golesVis);


    public DataInfoPenca obtenerDatosPenca(String nick){
        List<DataInfoUsuarioPenca> tablaPos= new ArrayList<DataInfoUsuarioPenca>();

         for (Map.Entry<String,InfoUsuarioPenca> entry : usuarios.entrySet()){
             tablaPos.add(new DataInfoUsuarioPenca( entry.getValue().getNick(),entry.getValue().getPuntos()));
         }

         Collections.sort(tablaPos);

        boolean participa = usuarios.containsKey(nick);
        float pozo=(montoPenca*usuarios.size() - ((montoPenca*usuarios.size()*5)/100));

        DataInfoPenca inf = new DataInfoPenca( tablaPos,participa,pozo,montoPenca,this.finalizado);

        return inf;

    }
	
	
	/// APOSTAR
	public float obtenerDividendoJugadorCampeonato(int idJug) throws Exception{
        InfoJugadorCampeonato infJugCamp = listInfoJugCamp.get(idJug);
        if (infJugCamp==null)
            throw new Exception("No existen dividendos asignados para el jugador en el campeonato");

        return infJugCamp.getDividendoGoleador();
    }

    public InfoJugadorCampeonato getInfoJugadorCampeonato(int idJug) {
        return this.listInfoJugCamp.get(new Integer(idJug));
    }
	
    public void notificarGoleadores() {
        // Avisamos a los goleadores
        // Tomo el numero mayor de goles y aviso a los que tengan esa cantidad de goles
        int maxGoles = -1;
        for (Map.Entry<Integer,InfoJugadorCampeonato> entry: this.listInfoJugCamp.entrySet()) {
            InfoJugadorCampeonato infJugCamp = entry.getValue();
            if (infJugCamp.getGoles()>maxGoles)
                maxGoles = infJugCamp.getGoles();
        }
        for (Map.Entry<Integer,InfoJugadorCampeonato> entry: this.listInfoJugCamp.entrySet()) {
            InfoJugadorCampeonato infJugCamp = entry.getValue();
            if (infJugCamp.getGoles()==maxGoles)
                infJugCamp.notificar(true);
            else
                infJugCamp.notificar(false);
        }
    }

    public void actualizarGolesJugCampeonato(DataTypes.DataResumen dataR) {
        //actualizamos para cada evento de tipo gol, si hay goleadores en la copa
        //le aumentamos la cantidad de goles

        List<DataTypes.DataEvento> listE = dataR.getEventos();
        for(DataTypes.DataEvento dataE : listE){
            if (dataE.getTipoevento()==DataTypes.TipoEvento.Gol){
                //obtenemos el id del jugador
                int idJ = dataE.getJugador1().getId();
                if (this.listInfoJugCamp.containsKey(idJ)){
                    InfoJugadorCampeonato infoJ = this.listInfoJugCamp.get(idJ);
                    infoJ.setGoles(infoJ.getGoles()+1);
                }
            }

        }
    }
    public List<DataTypes.DataInfoJugCampeonatoPersistencia> getDataInfoJugadorCampeonato(){
         List<DataTypes.DataInfoJugCampeonatoPersistencia> salida= new ArrayList<DataTypes.DataInfoJugCampeonatoPersistencia>();
        for (Map.Entry<Integer,InfoJugadorCampeonato> entry: this.listInfoJugCamp.entrySet()) {
            InfoJugadorCampeonato infJugCamp = entry.getValue();
            salida.add(infJugCamp.getDIJCPersistencia(id, id));
        }
        return salida;
    }

    //18-10 para setear al cargar
    public void setGolesJugCampeonato(int idJ,int goles){
        Objetos.InfoJugadorCampeonato iJC=listInfoJugCamp.get(idJ);
        iJC.setGoles(goles);
}

    // Jugadores con div asig en un campeonato
    public List<DataTypes.DataGoleador> getJugadoresCampeonato() {
        List<DataTypes.DataGoleador> aux=new java.util.ArrayList<DataTypes.DataGoleador>();
        for (Map.Entry<Integer,InfoJugadorCampeonato> infJugCamp: this.listInfoJugCamp.entrySet()) {
            aux.add(infJugCamp.getValue().getDataJugadorCampeonato());
        }
        return aux;
    }
	
	// Persistencia Penca
    public List<DataTypes.DataInfoUsuarioPencaPersistencia> getDataIUsuarioPencaPersistencia(){
        List<DataTypes.DataInfoUsuarioPencaPersistencia> salida =
                new ArrayList<DataTypes.DataInfoUsuarioPencaPersistencia>();
        for( Map.Entry<String,InfoUsuarioPenca>  entry: usuarios.entrySet()) {
            InfoUsuarioPenca info = entry.getValue();
            DataTypes.DataInfoUsuarioPencaPersistencia data =info.getDIUPencaPersistencia();
            salida.add(data);
       } 
       return salida;
    }

      public void cargarInfoUsuario (Usuario u,int puntos){
        InfoUsuarioPenca iup=new InfoUsuarioPenca(u);
        iup.setPuntos(puntos);
        usuarios.put(u.getNick(), iup);
    }
      public void agregarMensaje(String emisor,String receptor,boolean publico,String mensaje) throws Exception{
        this.foro.agregarMensaje(emisor,receptor,publico,mensaje);
        //anexado
        String aux="Se ha enviado un mensaje al foro de penca del campeonato: "+this.nombre+"\n";
        aux+="Enviado por: "+emisor+"\n";
        Interface.IControladorUsuarios cu=new Controladores.ControladorUsuarios();
        if(!publico){
            aux+="De tipo privado\n"+"Mensaje: "+mensaje;
            cu.notificarForo(receptor,aux);
        }
        else{
            //notifica a todos los que participen en la penca
            for (Map.Entry<String,InfoUsuarioPenca> entry : usuarios.entrySet()){
                aux+="De tipo publico\n"+"Mensaje: "+mensaje;
                cu.notificarForo(entry.getValue().getNick(), aux);
            }
        }
    }

    public void setForo(List<Mensaje> mensajes){
         this.foro=new Foro(mensajes);
    }

    public abstract DataTypes.DataFechaHora getFechaPartidoPenca(int idPart);
}



