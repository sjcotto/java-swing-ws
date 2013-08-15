
package Interface;
import Objetos.InfoEquipoCampeonato;
import java.util.List;
import java.util.Map;
public interface IOrdenarLiga {
    public void ordenar(Map<Integer,InfoEquipoCampeonato> lista,int []tabla,List<Integer> igualados);
    public int [] getTabla();
    public List<Integer> getIgualados();
}

