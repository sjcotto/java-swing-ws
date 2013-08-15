/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Interface;

import java.util.List;
import Objetos.Competicion;
import DataTypes.DataPartido;
import DataTypes.DataPartidoComp;
import DataTypes.DataEquipoLiga;
import DataTypes.DataFiltro;
import DataTypes.DataJugPartido;
import DataTypes.DataIdNombre;
import DataTypes.DataInfoPartDividendos; 
import DataTypes.DataDivEquipo;
import DataTypes.DataApuesta;
import DataTypes.TipoApuesta;
import DataTypes.TipoDividendo;
import DataTypes.DataCompeticion;
import DataTypes.DataEquipo;
import DataTypes.DataJugador;
import Excepciones.ExNoExisteEquipo;
import Excepciones.ExNoExisteJugador;
import Excepciones.ExNoExisteCompeticion;
import Excepciones.ExDatosNoAsignados;
import Excepciones.ExNoExistePartidoEnCompeticion;

/**
 *
 * @author gonzalo
 */
public interface IControladorWebCompeticiones {

    /// VER DETALLES PARTIDO
    public List<DataPartidoComp> listarPartidos(List<DataFiltro> filtros);
    public DataPartido seleccionarPartido(int idComp,int idPart)
            throws ExNoExisteCompeticion,ExNoExistePartidoEnCompeticion;
    public DataJugPartido verInfoPartidoFinalizado()
            throws ExNoExisteCompeticion;
    public void finalizarDetallesPartido();

    // VER TABLA POSICIONES
    public List<DataIdNombre> listarLigasDefinidas();
    public List<DataEquipoLiga> obtenerTablaDePosiciones(int idLiga);

    
    // VER ULTIMOS PARTIDOS
    public  List<DataPartido> obtenerUltimosPartidosFinalizados();

    // VER DETALLES COMPETICION
    public List<DataIdNombre> listarCompetencias();
    public DataCompeticion verInfoCompeticion(int id)
            throws ExNoExisteCompeticion;
    public List<DataPartido> infoPartidosCompeticion()
            throws ExDatosNoAsignados;
    public DataJugPartido listarJugadoresPorBando(int idPart)
            throws ExDatosNoAsignados, ExNoExistePartidoEnCompeticion;

    /// DIVIDENDOS RESULTADO EXACTO
    // Obtiene los dividendos asignados para el partido guardado en memoria
    public float[][] obtenerDividendosResultadoExacto(int idComp, int idPart);
    /// DIVIDENDOS RESULTADO EXACTO

    public void finalizarVerDetallesComp();

    // VER DETALLES EQUIPOS
    public List<DataEquipo> listarEquipos();
    public List<DataIdNombre> listarJugEquipo(int id)
            throws ExNoExisteEquipo;

    // VER DETALLES JUGADOR
    public List<DataIdNombre> listaJugadores();
    public DataJugador seleccionarJugador(int id)
            throws ExNoExisteJugador;

    public String getImagePath(int idEquipo);

    // CHANCHADA
    public boolean competicionEstaFinalizada(int idComp);

    //pablo
    public List<DataIdNombre> listarPencasDisponibles();

    // Ver Competicion
    public List<DataTypes.DataGoleador> get5MaxGoleadores(int idC)
            throws ExNoExisteCompeticion, Exception;
    
    public List<DataTypes.DataGoleador> getJugadoresCampeonato(int idC)throws Exception;
    
    
}
