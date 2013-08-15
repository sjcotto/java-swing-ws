package Interface;

import java.util.List;
import DataTypes.DataInfoAcceso;

public interface IControladorAccesosAdmin {
    public List<DataInfoAcceso> obtenerRegistroAccesos();
    public void guardarAccesos(String ruta)throws Exception;
    public void cargarAccesos(String ruta)throws Exception;
}