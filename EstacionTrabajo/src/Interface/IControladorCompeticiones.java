package Interface;

import java.util.List;
import DataTypes.*;
import Excepciones.*;

public interface IControladorCompeticiones {
    // ALTA COMPETENCIA
    public void ingresarCompeticion(String nombre,TipoCompeticion tipo, String path);
    public List<DataEquipo> listarEquipos();
    public void seleccionarEquipo(int id)
            throws ExDatosNoAsignados,ExNoExisteEquipo,ExEquipoYaSeleccionado ;
    public boolean seleccionCorrecta();
    public void ingresarOrdenLiga(TipoCriterio[] dc)
            throws ExCriteriosInvalidos;
    public void ingresarDividendoEquipo(int id,float dividendo)
            throws ExDatosNoAsignados,ExDividendosInvalidos,ExEquipoInvalido,
                ExDividendosYaAsignados;
    public DataCompeticion mostrarInfoCompetencia();
    public int darDeAltaCompetencia()
            throws ExDatosNoAsignados;
    public void finalizar();

    /// ALTA PARTIDO INDIVIDUAL
    public List<DataPartidoIndividualNoDefinido> listarPartidosIndividualesNoDefinidos();
    public boolean ingresarDatosPartidoIndividual(int idComp,int idEqLocal, int idEqVisi,
        DataFechaHora fechaHora, String lugar)
            throws ExNoExisteCompeticion,ExNoExistePartidoIndividual,
                ExPartidoYaDefinido,ExFechaInvalida,ExEquipoInvalido;

    /// ALTA PARTIDO DE LIGA
    public List<DataIdNombre> listarLigasNoDefinidas();
    public List<DataPartidoNoDefinidoLiga> ingresarIdLiga(int idLiga)
            throws ExNoExisteCompeticion,ExNoExisteLiga,ExLigaYaDefinida;
    public DataResultadoAltaPartido seleccionarEncuentroADefinir (
            int idEqLocal,int idEqVisi, DataFechaHora fechaHora, String lugar)
                throws ExCompetenciaNoAsignada,ExFechaInvalida,ExEquipoInvalido,ExPartidoYaDefinido;

    List<DataPartidoNoDefinidoLigaInterfaz> getEncuentrosNoFormadosDeLiga();

    // ALTA LLAVE DE COPA
    public List<DataIdNombre> listarCopaNoDefinida();
    public  List<DataLlave> ingresarDatosLlaveDeCopa(int idCopa,String nombreLlave)
            throws ExNoExisteCompeticion,ExCopaYaDefinida,ExYaExisteLLave;
    public List<DataIdNombre> listarEquiposNoAsignadosCopa()
            throws ExDatosNoAsignados,ExCopaYaDefinida;
    public void ingresarDatosPartida(int idEqL,int idEqV)
            throws ExDatosNoAsignados,ExCopaYaDefinida,ExNoExisteEquipo,
                ExEquipoInvalido;
    public void ingresarLlavesPredecesoras(String llaveLocal,String llaveVisita)
            throws ExLLavesInvalidas,ExNoExisteLaLlave, ExLlavesDeDistintasFases,
                ExLlavesDifierenEnSucesoras;
    public void ingresarTipoLlave(boolean tieneSucesora,boolean esFinal)
            throws ExParametrosInvalidos;
    public boolean confirmarAltaLlaveCopa(DataFechaHora fechaHora,String lugar);
    public void limpiarMemoriaAuxiliar();


    // ASIGNAR DIVIDENDOS
    public List<DataInfoPartido> listarPartidosDivNoAsig();
    public void asignarDividendos(int idPart,int idComp, float divL, float divV, float divE)
            throws ExNoExisteCompeticion, ExNoExistePartidoEnCompeticion,
                ExDividendosYaAsignados,ExDividendosInvalidos;

    // VER DETALLES DE COMPETICION
    public List<DataIdNombre> listarCompetencias();
    public DataCompeticion verInfoCompeticion(int id)
            throws ExNoExisteCompeticion;
    public List<DataPartido> infoPartidosCompeticion()
            throws ExDatosNoAsignados;
    public DataJugPartido listarJugadoresPorBando(int idPart)
            throws ExDatosNoAsignados, ExNoExistePartidoEnCompeticion;
    public void finalizarVerDetallesComp();

    // FINALIZAR PARTIDO
    public List<DataIdNombre> listarCompeticionesNoFinalizadas();
    public List<DataInfoPartido> listarPartidosConDivAsignado(int idCompeticion)
            throws ExNoExisteCompeticion;
    public DataPartido seleccionarPartido(DataInfoPartido dataIP)
            throws ExDatosNoAsignados,ExPartidoNoFinalizable;//cambio
    public void ingresarResultado(int golesLocal,int penalLocal,
            int golesVisitante,int penalVisita)
                throws ExDatosNoAsignados,ExGolesInvalidos;
    public List<DataIdNombre> listaJugadores();
    public void ingresarJugador(int idJugador,int idEquipo)
            throws ExNoExisteEquipo,ExNoExisteJugador,ExJugadorYaIngresado;
    public DataResumen listarIngresos()
            throws ExDatosNoAsignados;
    public void confirmarIngreso(boolean confirmar)
            throws ExDatosNoAsignados;


    // PERSISTENCIA?
    public TipoCriterio[] getCriterio(int idLiga);
    public  List<DataPartido> getPartidosLiga(int idLiga);
    public List<DataIdNombre> getDataIdNombreEquiposPI(int idPInd);
    public  List<DataLlave> getDataLlaves(int idCopa);
    public  DataPartido getPartidoInd(int idPI);
    public void setearIdPartido(DataInfoPartido dIP);
public void cargarCompeticiones(String ruta)throws Exception;
    public void guardarCompeticiones(String ruta)throws Exception;
      //controlador competiciones hacer publica y agregar interfaz
    public void cargarApuesta(List<DataApuestaPersistencia> apuestas);

    //9-10
    public void agregarIJC(int idJug,float dividendo) throws Exception;//agrega sin competicion usado en alta comp
    public void setMontoPenca(float monto)throws Exception;
    public List<DataTypes.DataGoleador> get5MaxGoleadores(int idC)throws Exception;
    public List<DataTypes.DataGoleador> getJugadoresApostables();
	
	//Eventos
		public void ingresarEventos(DataTypes.DataEvento dataE) throws Exception;
		public void confirmarEventos();
    //Eventos

    //miguel...
                public void asignarDividendoPartidoResultado (int idPart, int idComp)
                                        throws ExNoExisteCompeticion, ExNoExistePartidoEnCompeticion,
                                               ExDividendosYaAsignados,ExDividendosInvalidos;
                public void almacenarAsigDividendoRes (int golesLocal, int golesVisita, float div);
                public void finalizarAsigDivsResultados();
                public void limpiarMemoriaDivs();
                public boolean getDivsResultsAsignados();

                 public List<DataTypes.DataGoleador> getJugadoresCampeonato(int idC) throws Exception;
                 public void mailPartidoApostable(int idPart,int idComp,float divL,float divV,float divE)throws Exception;
}
