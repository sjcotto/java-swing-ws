/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTypes;
import java.util.List;


public class DataInfoJugCampeonatoPersistencia {
    private DataGoleador dataG;
    private List<DataApuestaPersistencia> apuestas;
    public DataInfoJugCampeonatoPersistencia (DataGoleador dataG,List<DataApuestaPersistencia> apuestas){
        this.dataG=dataG;
        this.apuestas=apuestas;
    }
    public DataGoleador getDataGoleador(){
        return this.dataG;
    }
    public List<DataApuestaPersistencia> getApuestasAGoleador(){
        return this.apuestas;
    }
            
}
