package Objetos;

import DataTypes.DataIdNombre;
import DataTypes.DataCompeticion;
import DataTypes.TipoCompeticion;
import DataTypes.DataPartido;
import DataTypes.DataInfoPartido;
import DataTypes.DataEquipo;
import DataTypes.DataDivEquipo;
import DataTypes.DataJugPartido;
import DataTypes.Dividendos;
import DataTypes.DataPartidoComp;
import DataTypes.DataFiltro;
import DataTypes.DataResumen;
import java.util.Map;
import java.util.List;

public abstract class Competicion {	
    //Atributos
    protected int id;
    protected String nombre;
    protected boolean estaDefinido;
    protected int contadorIdPar;
    protected String pathArchivo = "";
    protected boolean finalizado;

   
	
	
    public Competicion(int id, String nom, String path) {
	this.id = id;
	this.nombre = nom;
	this.estaDefinido = false;
        this.contadorIdPar = 1;
	     pathArchivo = path;
    }


    //Funciones get y set
    public int getId(){
	    return id;
    }

    public String getNombre(){
	    return nombre;
    }

    public boolean getEstaDefinido(){
        return estaDefinido;
    }

    public boolean getFinalizado() {
        return finalizado;
    }

    public void setEstaDefinido(boolean estaDef) {
        this.estaDefinido = estaDef;
    }

    //eliel
    public void setContadorPartidos(int cont){
            this.contadorIdPar=cont;
        }

    public abstract TipoCompeticion getTipo();

    public abstract boolean getFinalizable();

    public abstract List<DataInfoPartido> getDataPartidosFinalizables();

    public abstract void finalizarPartido(DataInfoPartido dIP,DataResumen dataR);

    public abstract DataCompeticion getDataCompeticion();

    public abstract Dividendos getDividendos(DataInfoPartido dIP);

    public DataIdNombre getDataBasica(){
        return new DataIdNombre(this.id,this.nombre,this.pathArchivo);
    }
    //eliel

    /// VER DETALLES DE COMPETICION
    public abstract List<DataPartido> infoPartidosCompeticion();
    public abstract DataJugPartido obtenerDataJugPartido(int idP);
    /// FIN VER DETALLES DE COMPETICION

    // ALTA COMPETICION
    public abstract void agregarEquipos(Map<Integer,DataEquipo> equipos,
                                        List<DataDivEquipo> dividendos);
    
    /// FIN ALTA COMPETICION

    /// ASIGNAR DIVIDENDOS
    public abstract List<DataInfoPartido> obtenerPartidosDivNoAsignados();
    public abstract void asignarDividendos(int idPart,float divL,
                                           float divV, float divE);
    /// FIN ASIGNAR DIVIDENDOS

    public abstract DataIdNombre getNombreIdCompeticionApostar();

    // Ver Ultimos 10 Partidos

    public abstract List<DataPartido> obtenerUltimosPartidosFinalizados();

    //ver info Partido
    public abstract List<DataPartidoComp> partidosCumplenFiltro(List<DataFiltro> filtros);

    public abstract DataPartido obtenerDataPartido(int idPartido);

    public abstract void agregarApuestaEquipoCamp (Apuesta a, int idEq);

    public abstract Partido obtenerPartidoApuesta(int id);
	
	/// RESULTADO EXACTO
	public abstract void asignarDividendoResultados(int idPart, Map<DataTypes.GolesKey,Float> golesRes);
        public abstract float[][] obtenerDividendosResultadoExacto(int idPartido);
	/// RESULTADO EXACTO

    public abstract boolean partidoPerteneceCompeticion(Partido p);
    public abstract void cargarResExaPersistencia(int idPartido,String nomLlave, java.util.Map<DataTypes.GolesKey,Float> golesDiv);
}
