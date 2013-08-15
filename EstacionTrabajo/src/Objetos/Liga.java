package Objetos;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import DataTypes.*;
import Controladores.ControladorEquipos;
import Excepciones.ExEquipoInvalido;
import Excepciones.ExPartidoYaDefinido;

/// NUEVO
import Interface.IOrdenarLiga;


public class Liga extends Campeonato {
    //Atributos
    private Map<Integer, Partido> partidos=new HashMap<Integer,Partido>();
    private TipoCriterio[] criterios;

    private List<DataPartidoNoDefinidoLiga> partidosNoDefinidos;
    private List<DataPartidoNoDefinidoLigaInterfaz> partNoDefinidosInterfaz; // atributo agregado a interfaz

    private int [] tabla;
    private IOrdenarLiga oL;
    private int partidosPorJugar;

    //Contructores
    public Liga(int id, String nombre,
                List<DataPartidoNoDefinidoLiga> partNoDefinidos,
                List<DataPartidoNoDefinidoLigaInterfaz> partNoDefinidosInterfaz, // modificado
                TipoCriterio[] listTC, String pathImage ) {

	super(id,nombre,pathImage);
	this.partidosNoDefinidos = new ArrayList<DataPartidoNoDefinidoLiga>();

        for (DataPartidoNoDefinidoLiga dataPIND: partNoDefinidos) {
            this.partidosNoDefinidos.add(dataPIND);
        }
        this.partNoDefinidosInterfaz = new ArrayList<DataPartidoNoDefinidoLigaInterfaz>();
        for (DataPartidoNoDefinidoLigaInterfaz dataPINDI: partNoDefinidosInterfaz) {
            this.partNoDefinidosInterfaz.add(dataPINDI);
        }
	criterios = listTC;

        partidosPorJugar=partNoDefinidos.size();/////eliel
        tabla = null; // se crea cuando se agregan los eqipos
        
 }


    //Funciones get y set
    public TipoCriterio[] getCriterio(){
	    return criterios;
    }

    public void setCriterio(TipoCriterio[] sdc) {
	criterios = sdc;
    }

    public TipoCompeticion getTipo(){
	return TipoCompeticion.Liga;
    }

    public boolean getTermino(){
	return finalizado;
    }

    public DataIdNombre getDataNombreCompetencia() {
	return new DataIdNombre(this.getId(),this.getNombre());
    }

    public List<DataPartidoNoDefinidoLiga> getPartidosNoDefinidosLiga() {
        return partidosNoDefinidos;
    }

    public  List<DataPartidoNoDefinidoLigaInterfaz> getPartidosNoDefinidosLigaInterfaz() {
        return partNoDefinidosInterfaz;
    }

    /// FINALIZAR PARTIDO
    public boolean getFinalizable(){
        if (finalizado){ //!estaDefinido||
            return false;
        }
        //hay que ver si hay al menos un partido finalizable
        for (Map.Entry<Integer,Partido> entry: this.partidos.entrySet()){
            Partido p= entry.getValue();
            if(p.getFinalizable()){
                return true;
            }
        }
        return false;
    }

    public  List<DataInfoPartido>  getDataPartidosFinalizables(){
         List<DataInfoPartido> salida=new ArrayList<DataInfoPartido>();
         for (Map.Entry<Integer,Partido> entry: this.partidos.entrySet()){
            Partido p=entry.getValue();
            if(p.getFinalizable()){
                DataInfoPartido dataInfo=p.getDataInfoPartido(id,nombre,TipoCompeticion.Liga,"") ;
                salida.add(dataInfo);
            }
         }

         Collections.sort(salida);
         return salida;
     }


    /// FIN FINALIZAR PARTIDO
    
    // CASO DE USO:ALTA PARTIDO DE LIGA
    public DataResultadoAltaPartido crearPartido(int eqlocal,int eqVisitante,
                                        DataFechaHora fechaHora,String lugar) throws ExEquipoInvalido,
                                                                                     ExPartidoYaDefinido{

        Partido p;
        ControladorEquipos ce= new ControladorEquipos();
        Equipo eqL,eqV;

        eqL=ce.buscarEquipo(eqlocal);
        eqV=ce.buscarEquipo(eqVisitante);

        if (eqL==null || eqV==null || eqlocal == eqVisitante ||
            !this.listInfoEqCamp.containsKey(eqlocal) ||
            !this.listInfoEqCamp.containsKey(eqVisitante))

            throw (new ExEquipoInvalido("Los equipos ingresados son invalidos"));

        
        //List<DataPartidoNoDefinidoLiga> partidosNoDefinidos;
        boolean partNoDef = false;
        int j=0;
        
        while (!partNoDef && j<this.partidosNoDefinidos.size()) {
            DataPartidoNoDefinidoLiga dataPNDL = this.partidosNoDefinidos.get(j);
         
            partNoDef = dataPNDL.getIdEquipoLocal()==eqlocal && dataPNDL.getIdEquipoVisitante()==eqVisitante;
            j++;
        }
        if (!partNoDef)
            throw (new ExPartidoYaDefinido("El partido ya esta definido"));
         

        p = new Partido(this.contadorIdPar,fechaHora,lugar,eqL,eqV);
        partidos.put(this.contadorIdPar,p);
        this.contadorIdPar++;

        DataPartidoNoDefinidoLiga d = null;
        boolean encontre = false;
        int i;
        for (i=0; i<partidosNoDefinidos.size() &&  !encontre; i++){
            d =(DataPartidoNoDefinidoLiga)partidosNoDefinidos.get(i);
            if ((d.getIdEquipoLocal()==eqlocal) &&
                        (d.getIdEquipoVisitante()==eqVisitante))
                encontre = true;
        }



        // PARA INTERFAZ
        boolean enc = false;
        i = 0;
        DataPartidoNoDefinidoLigaInterfaz di=null;
        while (!enc && i < partNoDefinidosInterfaz.size()){
            if ((partNoDefinidosInterfaz.get(i)).getEquipoLocal().getId() == eqlocal){
                di = (DataPartidoNoDefinidoLigaInterfaz)partNoDefinidosInterfaz.get(i);
                enc = true;
            }
            else
                i++;
        }



        if (di.quitarEncuentro(eqVisitante))
            partNoDefinidosInterfaz.remove(i);

        DataResultadoAltaPartido dr;
        partidosNoDefinidos.remove(d);

        if (partidosNoDefinidos.isEmpty()){
            dr = new DataResultadoAltaPartido(true,true);
        }
        else{
            dr = new DataResultadoAltaPartido(false,true);
        }

        return dr;

 }

    public void setPartidoNoDefinido(List<DataPartidoNoDefinidoLiga> data){
      partidosNoDefinidos = data;
    }

    /// VER DETALLES DE COMPETICION
    public List<DataPartido> infoPartidosCompeticion() {
        List<DataPartido> listDataPart = new ArrayList<DataPartido>();

        DataPartido dataP;
        
        for (Map.Entry<Integer,Partido> entry: partidos.entrySet()) {
            Partido p = entry.getValue();
            dataP = p.getDataPartido(this.id,this.nombre,TipoCompeticion.Liga,"");
            listDataPart.add(dataP);
        }

        return listDataPart; /// TENDRIA QUE RETORNARSE UNA LISTA ORDENADA POR FECHA
    }

    public DataJugPartido obtenerDataJugPartido(int idP) {
        Partido p = this.partidos.get(idP);
        return p.getDataJugPartido();
    }
    /// FIN VER DETALLES DE COMPETICION

    /// ALTA COMPETICION
    public void agregarEquipos(Map<Integer,DataEquipo> equipos,List<DataDivEquipo> dividendos){
        //mio
        tabla=new int[equipos.size()];
        int indTabla=0;
        //end

        ControladorEquipos ce = new ControladorEquipos();
        for (DataDivEquipo entry : dividendos) {
	    Equipo e = ce.buscarEquipo(entry.getId());
	    InfoEquipoCampeonato iel= new InfoEquipoLiga(entry.getDividendo(),e,this);
            ((InfoEquipoLiga)iel).cambiarPosicion(1);
	    listInfoEqCamp.put(entry.getId(), iel);
            tabla[indTabla]=entry.getId();
            indTabla++;
	}
        
    }

    /// ASIGNAR DIVIDENDOS
    public List<DataInfoPartido> obtenerPartidosDivNoAsignados() {
        DataInfoPartido dataInfPart;
        List<DataInfoPartido> listDataInfPart = new ArrayList<DataInfoPartido>();
        for (Map.Entry<Integer,Partido> entry: this.partidos.entrySet()) {
            Partido p = entry.getValue();
            if (!p.estanAsignadosDividendos()) {
                dataInfPart = p.getDataInfoPartido(this.id,this.nombre,
                                                   TipoCompeticion.Liga);
                listDataInfPart.add(dataInfPart);
            }
        }

        /// Deberia retornarse esta lista ordenada por fecha!
        return listDataInfPart;
    }

    public void asignarDividendos(int idPart,float divL,
                                  float divV, float divE) {
        Partido p = this.partidos.get(idPart);
        p.asignarDividendos(divL,divV,divE);
    }

    /// FIN ASIGNAR DIVIDENDOS
	
	    public List<DataInfoPartido> obtenerPartidosNoFinalizadosPenca(Usuario u){
        List<DataInfoPartido> list = new ArrayList<DataInfoPartido>();
        InfoUsuarioPenca info = usuarios.get(u.getNick());
        for (Map.Entry<Integer,Partido> entry : partidos.entrySet() ){
           Partido par = entry.getValue();
           if (!par.getFinalizo()&& !info.apostoPartido(par.getId())){
               list.add(par.getDataInfoPartido(id, nombre,TipoCompeticion.Liga) );
           }
           }

          return list;
       }


    public Dividendos getDividendos(DataInfoPartido dataIP){
         Partido partido= this.partidos.get(dataIP.getIdPar());
         return partido.getDividendos();
     }

     //eliel
    public void setIdPartido(int idEqL,int idEqV,int idP){
        //asigno el maximo valor al id del partido para que no se puedan solapar id
        if(idP>this.contadorIdPar){
            this.contadorIdPar=idP+1;
        }
        //encuentro el partido
        for (Map.Entry<Integer,Partido> entry: this.partidos.entrySet()){
            Partido p=entry.getValue();
            if(p.getIdLocal()==idEqL&&p.getIdVisita()==idEqV){
                p.setId(idP);
                return;
            }
        }
    }

    public List<DataEquipoLiga> obtenerTablaDePosiciones() {
        int tamanio=this.listInfoEqCamp.size();
        List<DataEquipoLiga> salida=new ArrayList<DataEquipoLiga>();
        
        for(int i=0;i<tamanio;i++){
            InfoEquipoLiga iel= (InfoEquipoLiga) this.listInfoEqCamp.get(tabla[i]);
            DataEquipoLiga iEL= iel.getDataEquipoLiga();
            salida.add(iEL);
        }
        return salida;
    }


    public void finalizarPartido(DataInfoPartido dIP,DataResumen dataR){

        Partido partido=partidos.get(dIP.getIdPar());
        partido.finalizarPartido(dataR);

        //se actualizan los puntos
        int golLocal,golVisita;
        golLocal=partido.getGolesLocal();
        golVisita=partido.getGolesVisitante();
        //local
        InfoEquipoLiga iELLocal=(InfoEquipoLiga)listInfoEqCamp.get(dIP.getIdLocal());
        iELLocal.actualizarPuntos(golLocal,golVisita);
        //visita
        InfoEquipoLiga iELVisita=(InfoEquipoLiga)listInfoEqCamp.get(dIP.getIdVisita());
        iELVisita.actualizarPuntos(golVisita,golLocal);

        //NUEVO

        //ordenar por puntos
        if(tabla==null){
            int indice=0;
            for (Map.Entry<Integer,InfoEquipoCampeonato> entry: listInfoEqCamp.entrySet()){
                InfoEquipoLiga iEL=(InfoEquipoLiga)entry.getValue();
                tabla[indice++]=iEL.getEquipo().getId();
            }
        }
        this.oL=new OrdenarPorPuntos();
        List<Integer> igualados=new ArrayList<Integer>();
        igualados.add(0);
        igualados.add(tabla.length-1);
        oL.ordenar(listInfoEqCamp,tabla,igualados);
        tabla=oL.getTabla();
        igualados=oL.getIgualados();
        for (int c=0;c<this.criterios.length&&igualados.size()>1;c++){
            //creo el tipo para comparacion
            //listar();
            if(this.criterios[c]==TipoCriterio.DiferenciaGoles){
                this.oL=new OrdenarDiferencia();
            }
            if(this.criterios[c]==TipoCriterio.GolesFavor){
                this.oL=new OrdenarGolesAFavor();
            }
            if(this.criterios[c]==TipoCriterio.Resultado){
                this.oL=new OrdenarPorResultado(this.partidos);
            }
            oL.ordenar(listInfoEqCamp, tabla, igualados);
            tabla=oL.getTabla();
            igualados=oL.getIgualados();
        }
        //ajuste de posiciones
        tabla=oL.getTabla();
        igualados=oL.getIgualados();

        //se ajustan en orden lineal
        for (int p=0;p<tabla.length;p++){
            InfoEquipoLiga iEL=(InfoEquipoLiga)this.listInfoEqCamp.get(tabla[p]);
            iEL.cambiarPosicion(p+1);
        }

        //se igualan las que terminaron empatadas
        int iguales=igualados.size()/2;
        for(int g=0;g<iguales;g++){
            int inicio=igualados.get(2*g);
            int fin=igualados.get(2*g+1);
            int mejorPos=((InfoEquipoLiga)this.listInfoEqCamp.get(tabla[inicio])).getPosicion();
            for (int i=inicio+1;i<=fin;i++){
                InfoEquipoLiga iEL=(InfoEquipoLiga)this.listInfoEqCamp.get(tabla[i]);
                iEL.cambiarPosicion(mejorPos);
            }
        }
        
        //Actualizar puntos penca
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

       //se deberia avisar en caso de que terminara el campeonato a las apuestas
        
        if(partidosPorJugar==0){
            finalizado=true;
            for(Map.Entry<Integer,InfoEquipoCampeonato> entry: this.listInfoEqCamp.entrySet()) {
                InfoEquipoLiga iEL = (InfoEquipoLiga)entry.getValue();
                if(iEL.getPosicion()==1){
                    iEL.notificar(true);
                }
                else{
                    iEL.notificar(false);
                }
            }
			
			// Avisamos a los goleadores
            this.notificarGoleadores();

        }
    }
	

    // VER ULTIMOS 10 PARTIDOS
    public List<DataPartido> obtenerUltimosPartidosFinalizados(){
   List<DataPartido> listPar = new ArrayList<DataPartido>();
   for (Map.Entry<Integer,Partido> entry: this.partidos.entrySet()){
            Partido p = entry.getValue();
            if (p.getFinalizo()){
                   DataPartido d = p.getDataPartido(id, nombre, TipoCompeticion.Liga, "");
                   listPar.add(d);
           }
        }
    return listPar;
    }
    // VER DETALLES PARTIDO

    public List<DataPartidoComp> partidosCumplenFiltro(List<DataFiltro> filtros){
        List<DataPartidoComp> list = new ArrayList<DataPartidoComp>();
        for (Map.Entry<Integer,Partido> entry : partidos.entrySet() ){
           Partido par = entry.getValue();
           if (par.estanAsignadosDividendos()&& par.cumpleFiltro(filtros, id, nombre, TipoCompeticion.Liga)){
               DataPartidoComp dc = new DataPartidoComp(id,nombre,par.getId());
               list.add(dc);
           }

        }
        return list;
    }

   public DataPartido obtenerDataPartido(int idPartido){
       DataPartido dp= null;
       Partido p = partidos.get(idPartido);
       if (p!=null)
            dp = p.getDataPartido(id, nombre, TipoCompeticion.Liga,"");
       return dp;
   }

    public  Partido obtenerPartidoApuesta (int id){
        return partidos.get(new Integer(id));
    }
	
	/// RESULTADO EXACTO
    public void asignarDividendoResultados(int idPart, Map<GolesKey,Float> golesRes){
        Partido p = this.partidos.get(idPart);
        p.asignarDividendoResultados(golesRes);
    }

    public float[][] obtenerDividendosResultadoExacto(int idPartido) {
        return this.partidos.get(new Integer(idPartido)).getDividendosResExacto();
    }
	/// RESULTADO EXACTO


        public void apostarPartidoPenca(String nick,int idPart,int golesLoc,int golesVis){
            Partido P= this.partidos.get(idPart);
            InfoUsuarioPenca inf= usuarios.get(nick);
            inf.aportar(P, golesLoc, golesVis);

    }

    public boolean partidoPerteneceCompeticion(Partido p) {
        for (Map.Entry<Integer,Partido> entry : partidos.entrySet() ){
           Partido par = entry.getValue();
           if (par == p)
               return true;
        }

        return false;
    }

    public void cargarResExaPersistencia(int idPartido,String nomLlave, java.util.Map<DataTypes.GolesKey,Float> golesDiv){
        this.partidos.get(idPartido).asignarDividendoResultados(golesDiv);
    }
	public DataTypes.DataFechaHora getFechaPartidoPenca(int idPart){
        Partido part = partidos.get(idPart);
        return part.getFechaHora();
    }

	
}

