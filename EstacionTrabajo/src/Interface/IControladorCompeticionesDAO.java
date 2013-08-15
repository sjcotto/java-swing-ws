/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import DataTypes.DTOCompeticiones;
import java.util.List;

public interface IControladorCompeticionesDAO {
    public List<DTOCompeticiones> cargarCompeticiones(String path)throws Exception;
    public void guardarCompeticiones(String path,List<DTOCompeticiones> datos)throws Exception;
}
