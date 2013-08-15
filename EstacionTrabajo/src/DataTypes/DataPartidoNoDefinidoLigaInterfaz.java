
package DataTypes;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;


public class DataPartidoNoDefinidoLigaInterfaz {
    private DataEquipo equipoLocal;
    private Map<Integer,DataEquipo> encuentros;

    public DataPartidoNoDefinidoLigaInterfaz(DataEquipo local, Map<Integer,DataEquipo> equipos){
        equipoLocal = local;
        encuentros = new HashMap();
        for (Map.Entry<Integer,DataEquipo> e : equipos.entrySet())
            if (e.getValue() != local)
                encuentros.put(e.getValue().getId(),e.getValue());
    }


    public DataEquipo getEquipoLocal(){
        return equipoLocal;
    }

    public Map<Integer,DataEquipo> getEncuentros(){
        return encuentros;
    }

    public void setEncuentros(Map<Integer,DataEquipo> d){
        encuentros=d;
    }
    public List<DataEquipo> getEncuentrosVisit(){
        List<DataEquipo> ret = new ArrayList(encuentros.size());
        for (Map.Entry<Integer,DataEquipo> e : encuentros.entrySet())
            ret.add(e.getValue());

        return ret;
    }

    public boolean quitarEncuentro(int idEqVisit){
        DataEquipo l = encuentros.remove(new Integer(idEqVisit));    
        if (encuentros.isEmpty())
            return true;
        return false;
    }

}