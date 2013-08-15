package Objetos;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import Controladores.ControladorEquipos;
import DataTypes.*;


public class PartidoIndividual extends Competicion {

    // ATRIBUTOS
    private Partido partido;
    private Equipo eq1;
    private Equipo eq2;

    // CONSTRUCTOR
    public PartidoIndividual(int id, String nombre, String pathImage) {
	super(id,nombre,pathImage);
	partido = null;
	this.eq1 = null;
        this.eq2 = null;
    }

    public TipoCompeticion getTipo(){
	return TipoCompeticion.PartidoIndividual;
    }

    // ALTA PARTIDO INDIVIDUAL
    public boolean estaDefinidoPartInd() {
        return this.partido!=null;
    }

    public DataPartidoIndividualNoDefinido getDataPartidoIndNoDef() {
        DataPartidoIndividualNoDefinido dataPIND;
        DataEquipo del = this.eq1.getDataEquipo();
        DataEquipo dev = this.eq2.getDataEquipo();
        dataPIND = new DataPartidoIndividualNoDefinido(this.id,this.nombre,
                del.getNombre(),dev.getNombre(),del.getId(),dev.getId());

        return dataPIND;

    }

    public boolean ingresarPartidoAPartIndiv(Equipo eqL, Equipo eqV,
                                    DataFechaHora fechaHora,String lugar){

        // Los equipos eqL tiene que ser igual a uno de los asociados
        // al partido y tambien eqV, y tienen que ser distintos
//        if (this.eq1 != eqL && this.eq2 != eqL || this.eq1 != eqV && this.eq2 != eqV || eqL == eqV)
//            throw ExEquiposInvalidos();
        
        partido = new Partido(this.contadorIdPar, fechaHora,lugar,eqL,eqV);
	this.estaDefinido=true;
        return true;
    }
    // FIN ALTA PARTIDO INDIVIDUAL


    // VER DETALLES DE COMPETICION
    public DataCompeticion getDataCompeticion() {
	TipoCompeticion tipoC = null;
	tipoC = tipoC.PartidoIndividual;
	// listaDataDivEq es vacia ya que no se conocen los dividendos de los equipos por salir campeon
        List<DataDivEquipo> listaDataDivEq = new ArrayList<DataDivEquipo>();
        DataDivEquipo ddE1=new DataDivEquipo(this.eq1.getId(),this.eq1.getNombre(),(float)0);
        DataDivEquipo ddE2=new DataDivEquipo(this.eq2.getId(),this.eq2.getNombre(),(float)0);
        listaDataDivEq.add(ddE1);
        listaDataDivEq.add(ddE2);
	DataCompeticion dataComp = new DataCompeticion(this.id,this.nombre,tipoC,listaDataDivEq,this.estaDefinido,this.pathArchivo);
	return dataComp;
    }
    
    public List<DataPartido> infoPartidosCompeticion() {
        List<DataPartido> listDataPar = new ArrayList<DataPartido>();
        if (this.partido!=null) {
            DataPartido dp = this.partido.getDataPartido(this.id,this.nombre, TipoCompeticion.PartidoIndividual,"");
            listDataPar.add(dp);
        }else
            System.out.print("es null el partido");

        return listDataPar;
    }

    public DataJugPartido obtenerDataJugPartido(int idP) {
        // Si el partido no es finalizado retorna el DataJugPartido
        // con el id del equipo y los conjuntos de jugadores vacios
        return this.partido.getDataJugPartido();
    }
    // FIN VER DETALLES DE COMPETICION

    // ALTA COMPETICION
    public void agregarEquipos(Map<Integer,DataEquipo> equipos ,List<DataDivEquipo> dividendos){
	ControladorEquipos ce = new ControladorEquipos();

	//List<DataEquipo> listEq = (List<DataEquipo>) equipos.values();
        Object[] listEq = equipos.values().toArray();
        DataEquipo dataEq1 = (DataEquipo) listEq[0];
        DataEquipo dataEq2 = (DataEquipo) listEq[1];
        
        this.eq1 = ce.buscarEquipo(dataEq1.getId());
	this.eq2 = ce.buscarEquipo(dataEq2.getId());
     }
    // FIN ALTA COMPETICION

    /// ASIGNAR DIVIDENDOS
    public List<DataInfoPartido> obtenerPartidosDivNoAsignados() {
        List<DataInfoPartido> listDataInfPart = new ArrayList<DataInfoPartido>();
        if (this.partido!=null) {
            if (!this.partido.estanAsignadosDividendos()) {
                DataInfoPartido dataInfPart;
                dataInfPart= this.partido.getDataInfoPartido(this.id,
                               this.nombre,TipoCompeticion.PartidoIndividual);

                listDataInfPart.add(dataInfPart);
            }
        }
        
        return listDataInfPart;
    }

    public void asignarDividendos(int idPart,float divL,
                                  float divV, float divE) {
        this.partido.asignarDividendos(divL, divV, divE);
    }
    /// FIN ASIGNAR DIVIDENDOS
     

    /// FINALIZAR PARTIDO
    public boolean getFinalizable(){
	 if (!estaDefinido){
	    return false;
	}
	return partido.getFinalizable();
    }


    public  List<DataInfoPartido>  getDataPartidosFinalizables(){
        List<DataInfoPartido> salida=new ArrayList<DataInfoPartido>();
        if (this.partido!=null && this.partido.getFinalizable()) {
            salida.add(partido.getDataInfoPartido(id,nombre, TipoCompeticion.PartidoIndividual,""));
        }
        return salida;
    }

    public  void finalizarPartido(DataInfoPartido dIP,DataResumen dataR){
        
        partido.finalizarPartido(dataR);
        this.finalizado = true;
    }

    /// FIN FINALIZAR PARTIDO

    public Dividendos getDividendos(DataInfoPartido dataIP){
         return partido.getDividendos();
     }

    public DataIdNombre getNombreIdCompeticionApostar(){
        if (partido.getDividendos().getEstanAsignados())
            return new DataIdNombre(this.getId(),this.getNombre());
        return null;
    }

    public DataInfoPartDividendos devolverInfoPartIndiv(){
        return partido.getDataInfoPartIndividual();
    }

    public DataApuesta obtenerDatosApuesta(TipoDividendo tipo,float monto, float saldo){
        return partido.obtenerDataApuestaPart(tipo,monto,saldo,this.id,this.nombre,TipoCompeticion.PartidoIndividual);
    }
    public void agregarApuestaComp (Apuesta ap, int idEq){
        ApuestaPartido a = (ApuestaPartido)ap;
        System.out.println("5");
        partido.agregarApuestaPartIndiv(a);
    }

    public void asignarDividendoResultados(int idPart, Map<GolesKey,Float> golesRes){
        Partido p = this.partido;
        p.asignarDividendoResultados(golesRes);
    }



//eliel
   public List<DataIdNombre> getDataIdNombreEquipos(){
       List<DataIdNombre> salida=new ArrayList<DataIdNombre>();
       DataIdNombre aux=new DataIdNombre(eq1.getId(),eq1.getNombre());
       salida.add(aux);
       aux=new DataIdNombre(eq2.getId(),eq2.getNombre());
       salida.add(aux);
       return salida;
   }

   public List<DataPartido> obtenerUltimosPartidosFinalizados(){
       List<DataPartido> list = new ArrayList<DataPartido>();
       if (partido!=null){
           if (partido.getFinalizo()){
            DataPartido d = partido.getDataPartido(id, nombre, TipoCompeticion.PartidoIndividual, "");
            list.add(d);
           }
       }
       return list;
}

   public List<DataPartidoComp> partidosCumplenFiltro(List<DataFiltro> filtros){
       List<DataPartidoComp> list = new ArrayList<DataPartidoComp>();
       if (partido!=null && partido.estanAsignadosDividendos()){
          if (partido.cumpleFiltro(filtros, id, nombre, TipoCompeticion.PartidoIndividual)){
              DataPartidoComp dc = new DataPartidoComp(id,nombre,partido.getId());
              list.add(dc);
          }
       }
       return list;
    }

   public DataPartido obtenerDataPartido(int idPartido){
       DataPartido dp = null;
       if (partido!=null)
           dp=partido.getDataPartido(id, nombre, TipoCompeticion.PartidoIndividual, "");
       return dp;
   }

    public void agregarApuestaEquipoCamp (Apuesta ap, int idEq){
    }

    public Partido obtenerPartidoApuesta(int idPar) {
        return this.partido;
    }

    public boolean partidoPerteneceCompeticion(Partido p) {
        return this.partido!=null && p==this.partido;
    }

    public float[][] obtenerDividendosResultadoExacto(int idPartido) {
        return this.partido.getDividendosResExacto();
    }
    
    // RESULTADO EXACTO
    public void cargarResExaPersistencia(int idPartido,String nomLlave, java.util.Map<DataTypes.GolesKey,Float> golesDiv){
        this.partido.asignarDividendoResultados(golesDiv);
    }
}
