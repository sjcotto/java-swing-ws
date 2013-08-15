package Objetos;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import DataTypes.*;
import Controladores.ControladorEquipos;
import Excepciones.ExLlavesDifierenEnSucesoras;
import Excepciones.ExNoExisteLaLlave;
import Excepciones.ExLlavesDeDistintasFases;
import Excepciones.ExLLavesInvalidas;

public class Copa extends Campeonato {
    //ATRIBUTOS
    private int topeFases;//es el nuemero maximo de fases siendo 2^n equipos
			  //el tope es n+1
    private Map<String,Llave> llaves;
    
    private boolean seDefinioTercerPuesto=false;//para no ingresar 2 veces 3er puesto
    private int partidosPorJugar=0;

    // CONSTRUCTOR
    public Copa(int id, String nombre, int tope, String path) {
	super(id,nombre,path);
	this.llaves  = new HashMap<String,Llave>();
	this.topeFases = tope;
        this.finalizado = false;

    }


    /************ CASO DE USO ALTA LLAVE ************/
    /************************************************/
    public TipoCompeticion getTipo(){
	return TipoCompeticion.Copa;
    }

    public List<DataLlave> getDataLlaves(){
	List<DataLlave> salida=new ArrayList<DataLlave>();
	for (Map.Entry<String,Llave> entry : llaves.entrySet()) {
	    Llave l = entry.getValue();
	    DataLlave dl=l.getDataLlave(this.id);
	    salida.add(dl);
	}
	return salida;
    }

    
    public List<DataIdNombre> listarEquiposNoAsignadosCopa(){
        List<DataIdNombre> salida=new ArrayList<DataIdNombre>();
       for (Map.Entry<Integer,InfoEquipoCampeonato> entry : listInfoEqCamp.entrySet()) {
	    InfoEquipoCopa iEC=(InfoEquipoCopa)entry.getValue();
            if(!iEC.getPerteneceLlave()){
                salida.add(iEC.getDataIdNombreEquipo());
            }
        }
        return salida;
    }

    //en caso de ser valido retorna la fase nueva sino retorna -1
    public int obtenerNumeroDeFaseLlave(String lLocal,String lVisita)
            throws ExNoExisteLaLlave,ExLlavesDeDistintasFases,
                ExLlavesDifierenEnSucesoras,ExLLavesInvalidas{
	/*Se devuelve nroFaseLocal+1 si las fases de ambas llaves coinciden,
	 y si el numero de sucesores de ambas es 0,
	 o en caso de que su numero de fase corresponda a
	 semifinal pueden tener ambas 1 sucesor (ademas del que se quiere
	 agregar en este alta)*/

         Llave llaveVisita = llaves.get(lVisita);
         Llave llaveLocal = llaves.get(lLocal);
	
         if (llaveLocal==llaveVisita) {
             throw new ExLLavesInvalidas("Las llaves ingresadas son las mismas");
        }

         //precondicion las llaves no existen
        if( (llaveLocal==null)|| (llaveVisita==null)){
            throw new ExNoExisteLaLlave("Alguna llave no existe");
        }

	//no pueden ser predecesoras de distintas fases
	if(llaveLocal.getFase()!=llaveVisita.getFase()){
            throw new  ExLlavesDeDistintasFases("Las llaves son de fases distintas");
	}

	//tienen que tener igual numero de sucesoras 0 o 1 en caso de ser semifinales
	if(llaveLocal.getNroSucesores()!=llaveVisita.getNroSucesores()){
            throw new  ExLlavesDifierenEnSucesoras("Las llaves difieren en sucesores");
         }

	//si tiene una sucesora la llave debe ser semifinal
	//en este caso estaria agregando final con tercer ya asignado
	if((llaveLocal.getNroSucesores()==1)&& (llaveLocal.getFase()!=topeFases-1)){
	     throw new ExLLavesInvalidas("Las llaves no son semifinales");
        }

	return llaveLocal.getFase()+1;
    }

    public boolean perteneceEquipoACopa(int id){
        return this.listInfoEqCamp.containsKey(id);
    }

    public Llave getLlave(String nombre){
            return llaves.get(nombre);
    }

    public boolean equipoYaAsigado(int id){
        return ((InfoEquipoCopa)listInfoEqCamp.get(id)).getPerteneceLlave();
    }

    public boolean bienFormada(boolean tieneSuc,boolean esFinal,int fase){
        if(tieneSuc){
            //si tiene sucesor debe ser de fase inferior a la fase final
            //y no puede ser final
            return fase<topeFases && esFinal==false;
        } else{
            //se debe definir la final ya que el tercer puesto ya esta definido
            if(seDefinioTercerPuesto&&(!esFinal)){
                return false;
            }
            //si solo son 2 equipos no puede haber tercer puesto
            if(topeFases==1&&!esFinal){
               return false;
            }
             //si no tiene sucesor debe ser ultima fase
            return fase==topeFases;
        }
    }


    public void crearFaseInicial(String nombre,DataFechaHora fechaHora,String lugar,
	    int idEqLocal,int idEqVisita,boolean tieneSucesora,boolean esFinal){

        InfoEquipoCopa iEL=(InfoEquipoCopa)this.listInfoEqCamp.get(idEqLocal);
        InfoEquipoCopa iEV=(InfoEquipoCopa)this.listInfoEqCamp.get(idEqVisita);
        iEL.setPerteneceLlave(true);
        iEV.setPerteneceLlave(true);
        Equipo eLocal=iEL.getEquipo();
        Equipo eVisita=iEV.getEquipo();        

	Llave llaveNueva=new Llave(nombre,this.contadorIdPar,fechaHora,lugar,
                                  eLocal, eVisita,tieneSucesora,esFinal);
	this.contadorIdPar++;

        llaves.put(nombre,llaveNueva);
	this.estaDefinido=esFinal;
        ++partidosPorJugar;

    }

    public void crearFaseSiguiente(String nombre,DataFechaHora fechaHora,
                                   String lugar,String lLocal,String lVisita,
                                   boolean tieneSucesora, boolean esFinal)  {

        Llave llaveLocal= llaves.get(lLocal);
	Llave llaveVisita= llaves.get(lVisita);

        Llave llaveNueva=new Llave(nombre,this.contadorIdPar,fechaHora,lugar,
                                   llaveLocal, llaveVisita,tieneSucesora,esFinal);
   
        llaveLocal.agregarSucesora(llaveNueva);
        llaveVisita.agregarSucesora(llaveNueva);

        boolean finPrevLocal=llaveLocal.getFinalizoPartido();
        boolean finPrevVisita=llaveVisita.getFinalizoPartido();
        boolean esTercerPuesto=llaveNueva.esTercerPuesto();

        seDefinioTercerPuesto=esTercerPuesto;//agregado
        
        //en caso de ser tercer puesto busca en las llaves previas los equipos
        //perdedores

        if(finPrevLocal){
            Equipo eLocal=null;
            if(esTercerPuesto){
                eLocal=llaveLocal.getPerdedor();
            }
            if(!esTercerPuesto){
                eLocal=llaveLocal.getGanador();
            }
            llaveNueva.avisoDeFinPrevio(eLocal,llaveLocal.getNombre());
        }
        if(finPrevVisita){
            Equipo eVisita=null;
            if(esTercerPuesto){
                eVisita=llaveVisita.getPerdedor();
            }
            if(!esTercerPuesto){
                 eVisita=llaveVisita.getGanador();
            }
            llaveNueva.avisoDeFinPrevio(eVisita,llaveVisita.getNombre());
        }

        this.contadorIdPar++;
        llaves.put(nombre,llaveNueva);
	this.estaDefinido=esFinal;
        ++partidosPorJugar;
    }

    
    
    /************************************************/
    /************************************************/

    // VER DETALLES DE COMPETICION
    public List<DataPartido> infoPartidosCompeticion() {
    // Se muestra la informacion de los partidos de la competicion guardada en memoria
        List<DataPartido> listDatasPart = new ArrayList<DataPartido>();

        for (Map.Entry<String,Llave> entry : llaves.entrySet()) {
            Llave ll = entry.getValue();
            DataPartido dataPart = ll.obtenerDataPartido(this.id,this.nombre, TipoCompeticion.Copa);
            listDatasPart.add(dataPart);

        }
        return listDatasPart; /// TENDRIA QUE RETORNARSE UNA LISTA ORDENADA POR FECHA
    }

    public DataJugPartido obtenerDataJugPartido(int idP) {

        boolean encontreLlaveAsociada = false;
        Collection colLlaves = llaves.values();
        Llave ll = null;
        
        Iterator itr = colLlaves.iterator(); 
        while(!encontreLlaveAsociada && itr.hasNext()) {
            ll = (Llave) itr.next();
            encontreLlaveAsociada = ll.esLlaveAsociadaPartido(idP);
        }
        return ll.obtenerDataJugPartido();

    }
    // FIN VER DETALLES DE COMPETICION

    /// ASIGNAR DIVIDENDOS
    public List<DataInfoPartido> obtenerPartidosDivNoAsignados() {
        DataInfoPartido dataInfPar;
        List<DataInfoPartido> listDataInfPar = new ArrayList<DataInfoPartido>();
        for (Map.Entry<String,Llave> entry: this.llaves.entrySet()){
            Llave ll = entry.getValue();
            if (ll.estaDefinidoPartidoAsociado()) {
                if (!ll.estanAsignadosDividendos()) {
                    dataInfPar = ll.obtenerDataInfoPartido(this.id,this.nombre,
                                                        TipoCompeticion.Copa);
                    listDataInfPar.add(dataInfPar);
                }
            }
        }

        /// Deberia retornarse ordenado por fecha
        return listDataInfPar;
    }
	
	public List<DataInfoPartido> obtenerPartidosNoFinalizadosPenca(Usuario u){

       List<DataInfoPartido> list = new ArrayList<DataInfoPartido>();
       InfoUsuarioPenca info = usuarios.get(u.getNick());
       for (Map.Entry<String,Llave> entry : this.llaves.entrySet()) {
           if (!entry.getValue().getFinalizoPartido() && !info.apostoPartido(entry.getValue().getIdPart())){
               list.add(entry.getValue().getDataInfoPartido(id, nombre, TipoCompeticion.Copa));

           }
       }

       return list;
   }//pablito

    public void asignarDividendos(int idPart,float divL,
                                  float divV, float divE) {
        boolean encontreLlaveAsociada = false;
        Collection colLlaves = llaves.values();
        Llave ll = null;

        Iterator itr = colLlaves.iterator();
        while(!encontreLlaveAsociada && itr.hasNext()) {
            ll = (Llave) itr.next();
            encontreLlaveAsociada = ll.esLlaveAsociadaPartido(idPart);
        }
        ll.asignarDividendos(divL,divV,divE);
    }
    /// FIN ASIGNAR DIVIDENDOS

    /// ALTA COMPETICION
    public void agregarEquipos(Map<Integer,DataEquipo> equipos,List<DataDivEquipo> dividendos){
	ControladorEquipos ce = new ControladorEquipos();
	for (DataDivEquipo entry : dividendos) {
	    Equipo e = ce.buscarEquipo(entry.getId());
	    InfoEquipoCampeonato iel= new InfoEquipoCopa(entry.getDividendo(),e,this);
	    listInfoEqCamp.put(entry.getId(), iel);
	}
    }
    /// FIN ALTA COMPETICION

    public Dividendos getDividendos(DataInfoPartido dataIP){
        Llave llave=llaves.get(dataIP.getNomLlave());
        return llave.getDividendos();
    }

    /// FINALIZAR PARTIDO
    public boolean getFinalizable(){
	if (finalizado){ //!this.estaDefinido|| 
	    return false;
	}
	//hay que ver si hay al menos un partido finalizable en las llaves
	for (Map.Entry<String,Llave> entry : llaves.entrySet()) {
	    Llave l = entry.getValue();
	    if(l.getFinalizable()){
		return true;
	    }
	}
	return false;
    }
    
    public  List<DataInfoPartido>  getDataPartidosFinalizables(){
       List<DataInfoPartido> salida=new ArrayList<DataInfoPartido>();
        for (Map.Entry<String,Llave> entry : llaves.entrySet()) {
            Llave l = entry.getValue();
            if(l.getFinalizable()){
                DataInfoPartido dataInfo=l.getDataInfoPartido(id, nombre,
                                                    TipoCompeticion.Copa);
                salida.add(dataInfo);
            }

        }
        Collections.sort(salida);
        return salida;
    }
    
    public  void finalizarPartido(DataInfoPartido dIP,DataResumen dataR){
        Llave llave=llaves.get(dIP.getNomLlave());
        llave.finalizarPartido(dataR);

        //Actualizar puntos penca
        if (!usuarios.isEmpty()){
        for (Map.Entry<String,InfoUsuarioPenca> entry : usuarios.entrySet()){
            InfoUsuarioPenca info = entry.getValue();
            if(info.apostoPartido(dIP.getIdPar())){
               if  (info.acertoResultadoExacto(dIP.getIdPar(),dataR.getGolesLocal(),dataR.getGolesVisita())){
                 info.actualizarPuntos(true);
            }
            else if (info.acertoResultado(dIP.getIdPar(),dataR.getGolesLocal(),dataR.getGolesVisita())){
                info.actualizarPuntos(false);
            }
        }
        }
        }
        --partidosPorJugar;
        //actualizamos ganador de la penca
        
        if ((partidosPorJugar==0) && !usuarios.isEmpty()){
           List<DataInfoUsuarioPenca> tablaPos= new ArrayList<DataInfoUsuarioPenca>();

         for (Map.Entry<String,InfoUsuarioPenca> entry : usuarios.entrySet()){
             tablaPos.add(new DataInfoUsuarioPenca(entry.getValue().getNick(),entry.getValue().getPuntos()));
         }

         Collections.sort(tablaPos);

         int ptsGanador = tablaPos.get(0).getPuntos();
         int ganadores=0;
          float pozo = (montoPenca*usuarios.size() - (montoPenca*usuarios.size()*5/100));
          for (Map.Entry<String,InfoUsuarioPenca> entry : usuarios.entrySet()){
              InfoUsuarioPenca info= entry.getValue();
              if(info.getPuntos()==ptsGanador){
                  ganadores++;
              }
          }
          pozo=pozo/ganadores;
          for (Map.Entry<String,InfoUsuarioPenca> entry : usuarios.entrySet()){
              InfoUsuarioPenca info= entry.getValue();
              if(info.getPuntos()==ptsGanador){
                  info.notificarVencedor(pozo);
              }
          }
        }

        // Actualiizamos los goles de los jugadores en el campeonato
        this.actualizarGolesJugCampeonato(dataR);

        if(this.estaDefinido&&partidosPorJugar==0){
            finalizado=true;
            Llave f = null;
            for(Map.Entry<String,Llave> entry : llaves.entrySet()){
                if(entry.getValue().getEsFinal()){
                    f = entry.getValue();
                }
            }
            int idCampeon=f.getGanador().getId();

            for(Map.Entry<Integer,InfoEquipoCampeonato> entry: this.listInfoEqCamp.entrySet()) {
                InfoEquipoCopa iEC = (InfoEquipoCopa)entry.getValue();
                if(idCampeon==iEC.getEquipo().getId()){
                    iEC.notificar(true);
                }
                else{
                    iEC.notificar(false);
                }
            }            

            // Avisamos a los goleadores
            this.notificarGoleadores();

        }
    }

    /// FINFINALIZAR PARTIDO

    public void setIdPartido(String nomL,int idP){
        Llave l=this.llaves.get(nomL);
        l.setIdP(idP);
    }

    //VER ULTIMOS 10 PARTIDOS
    public List<DataPartido> obtenerUltimosPartidosFinalizados(){
       List<DataPartido> listPar = new ArrayList<DataPartido>();
       for (Map.Entry<String,Llave> entry: this.llaves.entrySet()){
                Llave ll = entry.getValue();
                if (ll.estaDefinidoPartidoAsociado()){
                   if (ll.getFinalizoPartido()){
                       DataPartido d = ll.obtenerDataPartido(id, nombre, TipoCompeticion.Copa);
                       listPar.add(d);
               }
            }
        }
        return listPar;
        }

    public List<DataPartidoComp> partidosCumplenFiltro(List<DataFiltro> filtros){
        List<DataPartidoComp> list = new ArrayList<DataPartidoComp>();
        for (Map.Entry<String, Llave> entry : llaves.entrySet() ){
            Llave ll = entry.getValue();
            if(ll.estaDefinidoPartidoAsociado()){
               if (ll.partidoCumpleFiltro(filtros,id,nombre,TipoCompeticion.Copa)) {
                   DataPartidoComp dc = new DataPartidoComp(id,nombre,ll.getIdPart());
                   list.add(dc);
                }
             }
        }
        return list;
      }

    public DataPartido obtenerDataPartido(int idPartido){
        DataPartido dp=null;
        for (Map.Entry<String,Llave> entry : llaves.entrySet()){
            Llave ll = entry.getValue();
            if (ll.estaDefinidoPartidoAsociado()){
              if(ll.esLlaveAsociadaPartido(idPartido)){
                 dp  = ll.obtenerDataPartido(id, nombre, TipoCompeticion.Copa);
              }
            }
        }
        return dp;
    }

    public Partido obtenerPartidoApuesta (int id){
		//int i = 0;
		//boolean enc = false;
		Partido p=null;
		Partido ret=null;
		//Object[] lls  = (llaves.values()).toArray();

		for (Map.Entry<String,Llave> entry : this.llaves.entrySet()) {
			p = entry.getValue().darPartidoBuscado(id);
			if (p!=null)
				ret = p;
		}


		return ret;
	}
	
    /// RESULTADO EXACTO****************************
    public  void asignarDividendoResultados(int idPart, Map<GolesKey,Float> golesRes){
        boolean encontreLlaveAsociada = false;
        Collection colLlaves = llaves.values();
        Llave ll = null;

        Iterator itr = colLlaves.iterator();
        while(!encontreLlaveAsociada && itr.hasNext()) {
            ll = (Llave) itr.next();
            encontreLlaveAsociada = ll.esLlaveAsociadaPartido(idPart);
        }

        ll.asignarDividendoResultados(golesRes);
    }

    public float[][] obtenerDividendosResultadoExacto(int idPartido) {
        Llave llRet = null;
        for (Map.Entry<String,Llave> entry : llaves.entrySet()){
            Llave ll = entry.getValue();
            if (ll.estaDefinidoPartidoAsociado()){
                if(ll.esLlaveAsociadaPartido(idPartido))
                    llRet = ll;
            }
        }
        
        if (llRet == null) {
            int topeGoles = 10;
            float[][] ret = new float[topeGoles][];
            for (int i=0; i<topeGoles;i++) {
                ret[i] = new float[topeGoles];
            }

            for (int i=0; i<topeGoles;i++)
                for (int j=0; j<topeGoles;j++)
                    ret[i][j] = 0;

            return ret;
        }
        
        return llRet.getPartido().getDividendosResExacto();
    }

    /// RESULTADO EXACTO****************************



    //de la penca

    public void apostarPartidoPenca(String nick,int idPart,int golesLoc,int golesVis){
            Partido P=null;
             for (Map.Entry<String,Llave> entry : this.llaves.entrySet()) {
                if (entry.getValue().esLlaveAsociadaPartido(idPart))
                        P= entry.getValue().darPartidoBuscado(idPart);
             }
             InfoUsuarioPenca inf= usuarios.get(nick);
             inf.aportar(P, golesLoc, golesVis);
        }

    public boolean partidoPerteneceCompeticion(Partido p) {
         for (Map.Entry<String,Llave> entry : this.llaves.entrySet()) {
            Partido part = entry.getValue().getPartido();
            if (part!= null && part==p)
                return true;
         }

         return false;
    }


    public void cargarResExaPersistencia(int idPartido,String nomLlave, java.util.Map<DataTypes.GolesKey,Float> golesDiv){
        this.llaves.get(nomLlave).getPartido().asignarDividendoResultados(golesDiv);
    }

     public DataTypes.DataFechaHora getFechaPartidoPenca(int idPart){
        DataTypes.DataFechaHora salida=null;
         for (Map.Entry<String,Llave> entry : this.llaves.entrySet()) {
            Partido part = entry.getValue().getPartido();
            if (part!= null && part.getId()==idPart){
                salida=part.getFechaHora();
            }
         }
         return salida;
    }
}
