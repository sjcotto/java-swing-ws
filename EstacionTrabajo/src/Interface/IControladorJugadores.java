package Interface;

import java.util.List;
import DataTypes.DataJugador;
import DataTypes.DataIdNombre;
import Excepciones.ExNoExisteJugador;
import Excepciones.ExNoExisteJugadorEnMemoria;


public interface IControladorJugadores {
    /// ALTA DE JUGADOR
    public int ingresarJugador(DataJugador data);
    
    /// ELIMINAR JUGADOR
    public List<DataIdNombre> listaJugadores();
    public boolean eliminarJugador(int id) throws ExNoExisteJugador;
    public void confirmarEliminacion()throws ExNoExisteJugadorEnMemoria;

    /// MODIFICAR JUGADOR
    public DataJugador seleccionarJugador(int id)throws ExNoExisteJugador;
    public void modificarDatos(DataJugador datosNuevos)throws ExNoExisteJugadorEnMemoria;
      //eliel
    public void setIdJ(int idJ);
    public void cargarJugadores(String ruta)throws Exception;
    public void guardarJugadores(String ruta)throws Exception;
}
