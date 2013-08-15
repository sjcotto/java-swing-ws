package Objetos;

import DataTypes.DataFechaHora;
import DataTypes.DataLlave;
import DataTypes.DataFiltro;
import DataTypes.DataPartido;
import DataTypes.TipoCompeticion;
import DataTypes.DataJugPartido;
import DataTypes.DataInfoPartido;
import DataTypes.DataResumen;
import DataTypes.Dividendos;

import java.util.List;

public class Llave {
    //Atributos
    private String nombre;
    private int fase;
    private boolean esFinal;
    private boolean esTercerPuesto;
    private boolean tieneSucesora;

    private Partido partido=null;

    private Llave sucesoraComun=null;
    private Llave sucesora3er4toPuesto=null;
    
    private Llave prevLocal=null;
    private Llave prevVisita=null;


    //Contructores
    //contructor de primera fase
    public Llave(String nombre,int idPar, DataFechaHora fechaHora,
                 String lugar,Equipo eLocal, Equipo eVisita,
                 boolean tieneSucesora,boolean esFinal){
        this.nombre = nombre;
        this.tieneSucesora=tieneSucesora;
        this.esFinal=esFinal;
        esTercerPuesto=!tieneSucesora&&!esFinal;
        fase=1;

        partido=new Partido(idPar,fechaHora,lugar,eLocal,eVisita);

    }

    //constructor de fases siguientes partido no define equipos
    public Llave(String nombre,int idPar,DataFechaHora fecha,String lugar,
            Llave lLocal,Llave lVisita,boolean tieneSucesora,boolean esFinal){

        this.nombre = nombre;
        this.tieneSucesora=tieneSucesora;
        this.esFinal=esFinal;
        esTercerPuesto=!tieneSucesora&&!esFinal;
        
        //se agregan las llaves previas
        prevLocal=lLocal;
        prevVisita=lVisita;
        //se crea el partido sin equipos definidos
        partido=new Partido(idPar,fecha,lugar);
        //se asigna la fase como una mas de las previas
        fase=lLocal.getFase()+1;
         //se asigan a las previas la llave actual como sucesora
        lLocal.agregarSucesora(this);
        lVisita.agregarSucesora(this);
    }


    //Funciones set y get
    public String getNombre(){
            return nombre;
    }

    public int getFase(){
        return fase;
    }
    public boolean getEsFinal(){
        return esFinal;
    }
    public void setFase(int i){
        fase=i;
    }

    public boolean getTieneSucesores(){
        return sucesoraComun!=null || this.sucesora3er4toPuesto!=null;
    }

    public int getNroSucesores(){
        int n = 0;
        if(sucesoraComun!=null){
            n++;
        }
        if (this.sucesora3er4toPuesto!=null) {
            n++;
        }
        return n;
    }

    public boolean esTercerPuesto(){
        return this.esTercerPuesto;
    }

    public void agregarSucesora(Llave sucesora){
        if (sucesora.esTercerPuesto()) {
           sucesora3er4toPuesto=sucesora;
           return;
        }
        sucesoraComun = sucesora;
    }


    /// FINALIZAR PARTIDO
    public boolean getFinalizable(){
        return partido.getFinalizable();
    }

    public DataInfoPartido getDataInfoPartido(int id,String nomC,TipoCompeticion tipoC){
        return partido.getDataInfoPartido(id,nomC,tipoC,this.nombre) ;
    }

    public boolean getFinalizoPartido(){
        return this.partido.getFinalizo();
    }

    public void finalizarPartido(DataResumen dataR){
        //se avisa a los sucesores que ha finalizado el partido y
        // agrega el equipo segun corresponda
        partido.finalizarPartido(dataR);
       if (this.sucesoraComun!=null) {
            Equipo e=partido.getEqGanador();
            this.sucesoraComun.avisoDeFinPrevio(e, this.nombre);
        }
        if (this.sucesora3er4toPuesto!=null){
            Equipo e=partido.getEqPerdedor(); 
            this.sucesora3er4toPuesto.avisoDeFinPrevio(e,this.nombre);
        }
    }

    /// FIN FINALIZAR PARTIDO

    //ALTA LLAVE

   

    public Equipo getGanador(){
        return partido.getEqGanador();
    }

    public Equipo getPerdedor(){
        return partido.getEqPerdedor();
    }



    public void avisoDeFinPrevio(Equipo equipo,String nomLlavePrevia){
        //avisa al partido que equipo se setea el local o el visita
        if(nomLlavePrevia.equals(this.prevLocal.getNombre())){
            partido.setEquipo(equipo, true);
        }
         if(nomLlavePrevia.equals(this.prevVisita.getNombre())){
            partido.setEquipo(equipo,false);
         }
    }

     public Dividendos getDividendos(){
        return partido.getDividendos();
    }
   

    /// VER DETALLES COMPETENCIA
    public DataPartido obtenerDataPartido(int idC, String nomC, TipoCompeticion tipoC) {
        String local = "";
        if (this.prevLocal!=null)
            local=this.prevLocal.nombre;

        String vis = "";
        if (this.prevVisita!=null)
            vis=this.prevVisita.nombre;

        return this.partido.getDataPartido2(idC, nomC, tipoC,this.getNombre(),
                local,vis,this.esTercerPuesto);

    }

    public boolean esLlaveAsociadaPartido(int idP) {
        return this.partido.getId() == idP;
    }

    public DataJugPartido obtenerDataJugPartido() {
        return this.partido.getDataJugPartido();
    }
    /// FIN VER DETALLES COMPETENCIA

    /// ASIGNAR DIVIDENDOS
    public boolean estaDefinidoPartidoAsociado() {
        return this.partido.estanAsignadosEquipos(); 
    }
    
    public boolean estanAsignadosDividendos() {
       return this.partido.estanAsignadosDividendos();
    }
    
    DataInfoPartido obtenerDataInfoPartido(int idC,String nombC, TipoCompeticion tipo) {
        // Si o si tipo = TipoCompeticion.Copa
        return this.partido.getDataInfoPartido(idC,nombC,tipo);

    }

    public void asignarDividendos(float divL, float divV, float divE) {
        this.partido.asignarDividendos(divL, divV, divE);
    }
    /// FIN ASIGNAR DIVIDENDOS

    //eliel
    public DataLlave getDataLlave(int idComp){
        DataPartido dP=partido.getDataPartido(idComp, "", TipoCompeticion.Copa, this.nombre);
        if(this.fase==1){
              return new DataLlave(nombre,fase,0 ,0,this.tieneSucesora,this.esFinal,dP);
        }
        else{
            return new DataLlave(this.nombre,this.fase,this.prevLocal.getNombre(),
                    this.prevVisita.getNombre(),this.tieneSucesora,this.esFinal,dP);
        }
    }
    public void setIdP(int idP){
        this.partido.setId(idP);
    }
    public int getIdPart(){
        return partido.getId();
    }

    public boolean partidoCumpleFiltro(List<DataFiltro> filtros,int idComp,String nomComp, TipoCompeticion tipo){
        return partido.estanAsignadosDividendos() && partido.cumpleFiltro(filtros, idComp, nomComp, tipo);
    }


    public Partido darPartidoBuscado (int id){
        if (partido != null && partido.getId()==id)
            return partido;
        return null;
    }
	
	/// RESULTADO EXACTO
	public void asignarDividendoResultados( java.util.Map<DataTypes.GolesKey,Float> golesRes){
        this.partido.asignarDividendoResultados(golesRes); 
    }
	/// RESULTADO EXACTO

    public Partido getPartido() {
        return this.partido;
    }
    
}
