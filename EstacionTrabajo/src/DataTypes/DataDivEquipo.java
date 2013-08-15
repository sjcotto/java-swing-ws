
package DataTypes;
public class DataDivEquipo {
    
    private int id;
    private String nombre;
    private float dividendo;    
    
    public DataDivEquipo(){}

    public DataDivEquipo(int idEq,String nombreEq,float div){
        this.id=idEq;
        this.nombre=nombreEq;
        this.dividendo=div;
    }    
    public int getId(){
        return id;
    }
    public String getNombre(){
        return nombre;
    }
    public float getDividendo(){
        return dividendo;
    }

    public void setId(int i){
        id=i;
        }
    public void setNombre(String nom){
        nombre=nom;
        }

    public void setDividendo(float div){
        dividendo=div;
        }
}
