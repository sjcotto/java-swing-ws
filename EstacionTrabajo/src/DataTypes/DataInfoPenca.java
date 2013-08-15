

package DataTypes;
import java.util.List;
import java.util.ArrayList;

public class DataInfoPenca {

    private  List<DataInfoUsuarioPenca> tablaPosiciones;
    private boolean participaUsuario ;
    private float pozo;
    private float montoMin;
    private boolean finalizada;
    private int idComp;
    private List<DataMensajePenca> mensajes;
    private String nombre;
    
    public DataInfoPenca() {}

    public DataInfoPenca(List<DataInfoUsuarioPenca> tabla, boolean part,float pozo,float monto,boolean fin){
        
        this.participaUsuario=part;
        this.pozo=pozo;
        this.montoMin=monto;
        this.tablaPosiciones=tabla;
        this.finalizada=fin;
        this.mensajes = new ArrayList<DataMensajePenca>();
     }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    public List<DataInfoUsuarioPenca> getTablaPosiciones(){
        return tablaPosiciones;
    }

    public int getIdComp(){
        return idComp;
    }
    public boolean getParticipaUsuario(){
        return participaUsuario;
    }
    public boolean getFinalizada(){
        return finalizada;
    }
    public float getMontoMin(){
        return montoMin;
    }
    public float getPozo(){
        return pozo;
    }

    public void setParticipaUsuario(boolean p){
        participaUsuario=p;
    }
    public void setFinalizada(boolean f){
        finalizada=f;
    }

    public void setMontoMin(float m){
        montoMin=m;
    }

    public void setPozo(float p){
        pozo=p;
    }
    
    public void setTablaPosiciones(List<DataInfoUsuarioPenca> t){
         this.tablaPosiciones = t;
    }
    public void setIdComp(int id){
        idComp=id;
    }

    public List<DataMensajePenca> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<DataMensajePenca> mensajes) {
        this.mensajes = mensajes;
    }


}
