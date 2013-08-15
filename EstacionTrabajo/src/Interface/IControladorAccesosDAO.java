package Interface;

import DataTypes.DataInfoAcceso;
import java.util.List;

public interface IControladorAccesosDAO {
    public List<DataInfoAcceso> cargarAccesos(String path)throws Exception;
    public void guardarAccesos(String path,List<DataInfoAcceso> datos)throws Exception;
}
