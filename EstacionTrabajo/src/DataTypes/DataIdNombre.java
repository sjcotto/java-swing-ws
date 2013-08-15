/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataTypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;



/**
 *
 * @author santiago
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DataIdNombre {
    private int id;
    private String nombre;
    private String imagePath;

    public DataIdNombre() {}

    public DataIdNombre(int i,String n){
        id = i;
        nombre = n;
        imagePath = "";
    }

    public DataIdNombre(int i,String n, String ruta){
        id = i;
        nombre = n;
        imagePath = ruta;
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

    public void setNombre(String n){
        nombre=n;
    }

    public void setImagePath(String img){
        imagePath=img;
    }
}
