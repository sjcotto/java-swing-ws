/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import DataTypes.DataEquipo;
import java.util.List;

public interface IControladorEquipoDAO {
    public List<DataEquipo> cargarEquipos(String path)throws Exception;
    public void guardarEquipos(String path,List<DataEquipo> datos)throws Exception;
}