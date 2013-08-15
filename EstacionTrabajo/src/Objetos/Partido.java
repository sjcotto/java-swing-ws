package Objetos;


import DataTypes.*;
import java.util.Iterator;
import java.util.List;
import Controladores.ControladorJugadores;
import java.util.ArrayList;

public class Partido {
    //Atributos
    private int id; // Identificador del partido
    private DataFechaHora fechaHora;
    private String lugar;
    private Dividendos dividendos;

    private Equipo eqL,eqV;
    private EstadoPartido estPart;

    private List<ApuestaPartido> apuestas;
	
	/// RESULTADO EXACTO
	private java.util.Map<GolesKey,Float> golesDivs;
	/// RESULTADO EXACTO
	
    //Contructores

    //constructor para llave con equipos aun no definidos
    public Partido (int idPart, DataFechaHora fechaHora, String lugar){
        this.id = idPart;
        this.fechaHora=fechaHora;
        this.lugar=lugar;
        this.dividendos = new Dividendos();
        this.eqL = null;
        this.eqV = null;
        this.estPart = new Definido();
        apuestas = new java.util.ArrayList<ApuestaPartido>();
        golesDivs = new java.util.HashMap<GolesKey,Float>();//NO VA YA QUE EN UN MOMENTO PREGUNTA SI ES NULL PARA HACER ALGO
        //siempre que se crea un partido hay que setearle los dividendos por res exacto
    }

    public Partido (int idPart, DataFechaHora fechaHora,String lugar,
                    Equipo eLocal,Equipo eVisita){
        this.id = idPart;
        this.fechaHora=fechaHora;
        this.lugar=lugar;
        this.dividendos = new Dividendos();
        this.eqL = eLocal;
        this.eqV = eVisita;
        this.estPart = new Definido();
        apuestas = new java.util.ArrayList<ApuestaPartido>();
        golesDivs = new java.util.HashMap<GolesKey,Float>();//NO VA YA QUE EN UN MOMENTO PREGUNTA SI ES NULL PARA HACER ALGO
        //siempre que se crea un partido hay que setearle los dividendos por res exacto
    }


    //Funciones get y set
    public int getId() {
        return this.id;
    }

    public DataFechaHora getFechaHora(){
        return fechaHora;
    }

    public void setDividendo(float divLocal,float divVisita,float divEmpate){
        this.dividendos = new Dividendos(divLocal,divVisita,divEmpate);
    }

    public Dividendos getDividendos(){
        return this.dividendos;
    }


    /// ALTA PARTIDO INDIVIDUAL
    
    /// FIN ALTA PARTIDO INDIVIDUAL

    /// VER DETALLES DE COMPETICION
    

    public DataInfoPartido getDataInfoPartido(int idComp,String nomComp,
                            TipoCompeticion tipoComp,String nomLlave) {
        
        String nomEqL = "";
        int idEqL = -1;
        String nomEqV = "";
        int idEqV = -1;
        if (eqL!=null) {
            nomEqL = this.eqL.getNombre();
            idEqL = this.eqL.getId();
        }
        if (eqV!=null) {
            nomEqV = this.eqV.getNombre();
            idEqV=this.eqV.getId();
        }


        return new DataInfoPartido (this.id,idComp,nomComp,
                   tipoComp, nomEqL,nomEqV,idEqL ,idEqV,this.lugar,
                   this.fechaHora,nomLlave, "", "");
    }
    
    public DataJugPartido getDataJugPartido() {
        if (estPart instanceof Definido)
            return new DataJugPartido(this.id);

        Finalizado fin = (Finalizado) estPart;
        return fin.getDataJugPartido(this.id);
    }
    /// FIN VER DETALLES DE COMPETICION

    /// ASIGNAR DIVIDENDOS
    public boolean estanAsignadosEquipos(){
        return this.eqL!=null && this.eqV!=null;
    }

    public boolean estanAsignadosDividendos() {
        return this.dividendos.getEstanAsignados();
    }
    
    public DataInfoPartido getDataInfoPartido(int idC,String nombC,
                                              TipoCompeticion tipoC) {

        DataInfoPartido dataInfPart;
        dataInfPart = new DataInfoPartido(this.id,idC,nombC,tipoC,
                          this.eqL.getNombre(), this.eqV.getNombre(),
                          this.eqL.getId(), this.eqV.getId(),this.lugar,
                          this.fechaHora,"","","");

        return dataInfPart;
    }


    public void asignarDividendos(float divL, float divV, float divE) {
        Dividendos div = new Dividendos(divL, divV, divE);
        this.dividendos = div;
    }
    /// FIN ASIGNAR DIVIDENDOS


    /// CASO DE USO: FINALIZAR PARTIDO
    public boolean getFinalizable(){
        //no termino y tiene dividendos asignados
        return this.estPart instanceof Definido && this.dividendos.getEstanAsignados();
    }

    public  void finalizarPartido(DataResumen dataR){
        estPart=new Finalizado(dataR);
        ControladorJugadores cJ= new ControladorJugadores();
        Iterator<DataIdNombre> iter=dataR.getJugadoresLocal().iterator();
        int idJugador;
        Jugador jugador;

        while (iter.hasNext()){
            idJugador=iter.next().getId();
            jugador=cJ.getJugador(idJugador);
            jugador.setJugoAlgunPartido(true);
            this.eqL.agregarJugador(jugador);
            ((Finalizado)estPart).agregarJugadores(jugador,true);
        }
        iter=dataR.getJugadoresVisita().iterator();
        while (iter.hasNext()){
            idJugador=iter.next().getId();
            jugador=cJ.getJugador(idJugador);
            jugador.setJugoAlgunPartido(true);
            this.eqV.agregarJugador(jugador);
            ((Finalizado)estPart).agregarJugadores(jugador,false);
        }
        //nuevo
        for(int i=0;i<apuestas.size();i++){
            Apuesta a=apuestas.get(i);
            if (a instanceof ApuestaResultadoExacto) {
                ApuestaResultadoExacto aResEx = (ApuestaResultadoExacto)a;
                Finalizado f = (Finalizado)estPart;
                if (f.getGolesLocal()==aResEx.getGolesLocal() && f.getGolesVisitante()==aResEx.getGolesVisitante())
                    aResEx.notificar(true);
                else
                    aResEx.notificar(false);
            }
            else
                ((ApuestaPartido)a).notificar(((Finalizado)estPart).getResultado());
        }

                //nuevo eventos
        ((Finalizado)this.estPart).agregarEventos(dataR.getEventos());

    }

    public boolean getFinalizo(){
            return estPart instanceof Finalizado;
    }

    public Equipo getEqGanador(){
        if( estPart instanceof Finalizado){
            if( ((Finalizado)estPart).ganoLocal()){
                return this.eqL;
            }
            else{
                return this.eqV;
            }
        }
        else{
            return null;
        }
    }

    public Equipo getEqPerdedor(){
        if( estPart instanceof Finalizado){
            if( ((Finalizado)estPart).ganoLocal()){
                return this.eqV;
            }
            else{
                return this.eqL;
            }
        }
        else{
            return null;
        }
    }

    /// FIN FINALIZAR PARTIDO
    
    public int getIdLocal(){
        return this.eqL.getId();
    }

    public int getIdVisita(){
        return this.eqV.getId();
    }

    public int getGolesLocal(){
        Finalizado fin = (Finalizado) estPart;
        return fin.getGolesLocal();
    }
    public int getGolesVisitante(){
        Finalizado fin = (Finalizado) estPart;
        return fin.getGolesVisitante();
    }

    public void setEquipo(Equipo equipo,boolean esLocal){
        if(esLocal){
            this.eqL=equipo;
        }
        else{
            this.eqV=equipo;
        }
    }

    

    public void agregarApuestaPartIndiv (ApuestaPartido a){
        apuestas.add(a);
        System.out.println("6");
        a.agregarPartIndivApuesta(this);
    }


    public DataPartido getDataPartido(int idComp,String nomComp,
                            TipoCompeticion tipoComp, String nomLlave) {

        String nomEqL = "";
        int idEqL = -1;
        String nomEqV = "";
        int idEqV = -1;
        if (eqL!=null) {
            nomEqL = this.eqL.getNombre();
            idEqL = this.eqL.getId();
        }
        if (eqV!=null) {
            nomEqV = this.eqV.getNombre();
            idEqV=this.eqV.getId();
        }


        DataInfoPartido dataIP = new DataInfoPartido (this.id,idComp,nomComp,
                        tipoComp, nomEqL,nomEqV,idEqL ,idEqV,this.lugar,
                        this.fechaHora,nomLlave, "", "");

        boolean fin = false;
        int golLoc = -1;
        int golVis = -1;
        int penLoc = -1;
        int penVis = -1;

        List<DataApuestaPersistencia> apus=new java.util.ArrayList<DataApuestaPersistencia>();

        System.out.println(apuestas.size());

         for(int i=0;i<apuestas.size();i++){
             DataApuestaPersistencia dDa=null;
            ApuestaPartido ap = (ApuestaPartido)apuestas.get(i);
            if (ap==null){
                System.out.println("OJOOOOO LA APUESTA PARTIDO ES NULL!!");

            }else{
                dDa=ap.getDAP(idComp,this.id);//
                apus.add(dDa);
             }

         }

        System.out.println(apus.size());


        if (this.estPart instanceof Finalizado) {
            fin = true;
            Finalizado finalizado = (Finalizado)this.estPart;
            golLoc = finalizado.getGolesLocal();
            golVis = finalizado.getGolesVisitante();
            if (tipoComp==TipoCompeticion.Copa) {
                penLoc = finalizado.getPenalesLocal();
                penVis = finalizado.getPenalesVisitante();
            }

            DataPartido r =  new DataTypes.DataPartido(dataIP,this.dividendos,fin,golLoc,golVis,
                    penLoc,penVis,finalizado.getPartidoJugador(true),
                    finalizado.getPartidoJugador(false),apus,this.golesDivs);

            //ahora tenemos que setearle los data eventos
            //recorremos todos los eventos y les pedimos el data.

            List<DataTypes.DataEvento> list = ((Finalizado)this.estPart).obtenerDataEventos();
            r.setEventos(list);
            return r;
        }

        List<Integer> jugLocal = new ArrayList<Integer>();
        List<Integer> jugVis = new ArrayList<Integer>();

        return new DataTypes.DataPartido(dataIP,this.dividendos,fin,golLoc,golVis,
                    penLoc,penVis,jugLocal,jugVis,apus,golesDivs);


    }

     public DataPartido getDataPartido2(int idComp,String nomComp,
                            TipoCompeticion tipoComp, String nomLlave,
                            String prevL, String prevV, boolean esTercer) {

        String nomEqL = "";
        int idEqL = -1;
        String nomEqV = "";
        int idEqV = -1;
        if (eqL!=null) {
            nomEqL = this.eqL.getNombre();
            idEqL = this.eqL.getId();
        }
        if (eqV!=null) {
            nomEqV = this.eqV.getNombre();
            idEqV=this.eqV.getId();
        }


        DataInfoPartido dataIP = new DataInfoPartido (this.id,idComp,nomComp,
                        tipoComp, nomEqL,nomEqV,idEqL ,idEqV,this.lugar,
                        this.fechaHora,nomLlave, prevL, prevV,esTercer);

        boolean fin = false;
        int golLoc = -1;
        int golVis = -1;
        int penLoc = -1;
        int penVis = -1;

        if (this.estPart instanceof Finalizado) {
            fin = true;
            Finalizado finalizado = (Finalizado)this.estPart;
            golLoc = finalizado.getGolesLocal();
            golVis = finalizado.getGolesVisitante();
            if (tipoComp==TipoCompeticion.Copa) {
                penLoc = finalizado.getPenalesLocal();
                penVis = finalizado.getPenalesVisitante();
            }
        }

        DataPartido r =  new DataTypes.DataPartido(dataIP,this.dividendos,fin,golLoc,golVis,penLoc,penVis);

        //este no se si va, n ose para q se usa
         if (this.estPart instanceof Finalizado) {
            List<DataTypes.DataEvento> list = ((Finalizado)this.estPart).obtenerDataEventos();
            r.setEventos(list);
         }

        return r;
    }

     public void setId(int idP){
         this.id=idP;
     }

     // CASO VER DETALLES PARTIDO
     public boolean cumpleFiltro(List<DataFiltro> filtros,int idComp,String nomComp,TipoCompeticion tipo){
         boolean cumple = true;
           DataPartido dp= this.getDataPartido(idComp, nomComp, tipo,"");
           for (int i=0;i<filtros.size()&& cumple;i++){
               cumple=filtros.get(i).cumpleFiltro(dp);
           }

           return cumple;
     }
     // FIN VER DETALLES PARTIDO

    public DataInfoPartDividendos getDataInfoPartIndividual(){
        return new DataInfoPartDividendos(new DataIdNombre(eqL.getId(),eqL.getNombre()),
                                          new DataIdNombre(eqV.getId(),eqV.getNombre()),
                                          dividendos);
    }

    public DataApuesta obtenerDataApuestaPart(TipoDividendo tipo, float monto,
            float saldo, int idComp, String nomComp, TipoCompeticion tipoComp){
         float saldoNuevo=0, beneficio=0;
         float div=0;

         if ((TipoDividendo.Local).compareTo(tipo) == 0){
             div = dividendos.getDividendoLocal();
             beneficio = div*monto;
         }
         else{
             if ((TipoDividendo.Visitante).compareTo(tipo) == 0){
                 div = dividendos.getDividendoVisita();
                 beneficio = div*monto;
             }
             else{
                 div = div = dividendos.getDividendoEmpate();
                 beneficio = (dividendos.getDividendoEmpate())*monto;
             }
         }

         saldoNuevo = saldo+beneficio;

         return new DataApuestaPartido(monto,saldoNuevo,beneficio,div,this.getDataPartido(idComp,
                 nomComp,tipoComp,""),tipo,DataTypes.EstadoApuesta.Pendiente,-1);
    }
	
	
    public void agregarApuestaPart(ApuestaPartido a){
        apuestas.add(a);
    }
	
    public DataApuesta obtenerDataApuestaResExactoPart(int golL, int golV,
        float monto, float saldo, int idComp, String nomComp, TipoCompeticion tipoComp)
            throws Exception{

        GolesKey gk = new GolesKey(golL,golV);
        if (this.golesDivs.get(gk)==null)
            throw new Exception("No existe dividendo asignado para ese resultado");

        float saldoNuevo=0, beneficio=0;
        float div=0;

        div=(Float)this.golesDivs.get(gk);
        beneficio = div*monto;
        saldoNuevo = saldo+beneficio;

        return new DataApuestaResExacto(monto,saldoNuevo,beneficio,div,
             this.getDataPartido(idComp, nomComp,tipoComp,""),golL,golV,DataTypes.EstadoApuesta.Pendiente,-1);
    }
	
	/// DIVIDENDOS RES EXACT
	public void asignarDividendoResultados (java.util.Map<GolesKey,Float> golesRes){
        golesDivs.putAll(golesRes);
    }
	
	public float getDivResExacto(int golesLocal, int golesVisitante){
		GolesKey gk = new GolesKey(golesLocal,golesVisitante);
		return golesDivs.get(gk);
	}

        public float[][] getDividendosResExacto() {
            int topeGoles = 10;
            float[][] ret = new float[topeGoles][];
            for (int i=0; i<topeGoles;i++) {
                ret[i] = new float[topeGoles];
            }

            for (int i=0; i<topeGoles;i++)
                for (int j=0; j<topeGoles;j++)
                    ret[i][j] = 0;

            for (java.util.Map.Entry<GolesKey,Float> entry: this.golesDivs.entrySet())
                ret[entry.getKey().getGolesLocal()][entry.getKey().getGolesVisita()] = entry.getValue();

            return ret;
        }
        /// DIVIDENDOS RES EXACT

        public Competicion getCompeticionPartido() {
            Controladores.ManejadorCompeticiones manComp = Controladores.ManejadorCompeticiones.getInstance();
            java.util.Map<Integer,Competicion> comps = manComp.getCompeticiones();
            for (java.util.Map.Entry<Integer,Competicion> entry: comps.entrySet()) {
                if (entry.getValue().partidoPerteneceCompeticion(this)) {
                    return entry.getValue();
                }
            }
            return null;
        }
}
