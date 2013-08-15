
package DataTypes;
public class DataGoleador {
    
    private int id;
    private String nombre;
    private float dividendo;  
    private int goles;

    public DataGoleador() {}

    public DataGoleador(int idJ,String nombreJ,float div,int goles){
        this.id=idJ;
        this.nombre=nombreJ;
        this.dividendo=div;
        this.goles=goles;
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
    public int getGoles(){
        return this.goles;
    }

    public void setDividendo(float dividendo) {
        this.dividendo = dividendo;
}

    public void setGoles(int goles) {
        this.goles = goles;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nom) {
        this.nombre = nom;
    }



}