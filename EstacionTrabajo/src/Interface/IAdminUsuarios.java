
package Interface;

import java.util.List;
import DataTypes.DataUsuario;

public interface IAdminUsuarios {
    public List<String> listarNickUsuarios();
    public DataUsuario seleccionarUsuario(String nick);
}
