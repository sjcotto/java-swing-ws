package Interface;

import java.util.List;
import DataTypes.DataEquipo;
import DataTypes.DataIdNombre;
import Excepciones.ExNoExisteEquipo;

public interface IControladorEquipos{

    // DAR ALTA EQUIPO
    public int darAltaEquipo(String nombre,String image);

    // VER INFO EQUIPO
    public List<DataEquipo> listarEquipos();
    public List<DataIdNombre> seleccionarEquipo(int id)
            throws ExNoExisteEquipo;
    public void cargarEquipos(String ruta)throws Exception;
    public void guardarEquipos(String ruta)throws Exception;
}
