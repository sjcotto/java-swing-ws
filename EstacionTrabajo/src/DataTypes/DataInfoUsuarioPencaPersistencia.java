package DataTypes;
import java.util.List;
public class DataInfoUsuarioPencaPersistencia {
    private List<DataIUPPPersistencia> listaPencaPartido;
    private String nick;
    int puntos;
    public DataInfoUsuarioPencaPersistencia(String nick,int puntos,List<DataIUPPPersistencia> listaPencaPartido){
        this.nick=nick;
        this.listaPencaPartido=listaPencaPartido;
        this.puntos=puntos;
    }

    public List<DataIUPPPersistencia> getListaPartidosPencaPersistencia(){
        return this.listaPencaPartido;
    }
    public String getNick(){
        return this.nick;
    }
    public int getPuntos(){
        return this.puntos;
    }
}