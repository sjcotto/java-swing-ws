package Interface;
import DataTypes.DTOUsuario;
import java.util.List;
import DataTypes.DataFechaHora;

public interface IControladorUsuariosDAO {
    public List<DTOUsuario> cargarUsuarios(String ruta)throws Exception;
    public void guardarUsuarios(String ruta,List<DTOUsuario> datos)throws Exception;
    public void guardarFecha(String ruta,DataFechaHora fecha)throws Exception;
    public DataFechaHora cargarFecha(String ruta)throws Exception;
}
