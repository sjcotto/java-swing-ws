package Interface;

import DataTypes.DataJugador;
import java.util.List;

public interface IControladorJugadoresDAO {
    public List<DataJugador> cargarJugadores(String path)throws Exception;
    public void guardarJugadores(String path,List<DataJugador> datos)throws Exception;
}
