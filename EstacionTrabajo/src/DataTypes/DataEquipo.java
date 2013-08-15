package DataTypes;

public class DataEquipo {

    private String nombre;
    private int id;
    private String imagePath;

    public DataEquipo(int i,String n,String ip){
        nombre = n;
        id=i;
        imagePath = ip;
    }

    public DataEquipo(){
    
    }

    public DataEquipo(int i,String n){
        nombre = n;
        id=i;
    }
    
    public int getId(){
        return id;
    }
    public String getNombre(){
        return nombre;
    }
    public String getImagePath(){
        return imagePath;
    }

     public void setId(int i){
        id=i;
     }
    public void setNombre(String nom){
        nombre=nom;
    }

    public void setImagePath(String img){
        imagePath=img;
    }
}
