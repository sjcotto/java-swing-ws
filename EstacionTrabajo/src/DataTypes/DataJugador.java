/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataTypes;

/**
 *
 * @author santiago
 */

public class DataJugador {
    private int id;
    private String nombre;
    private String nombreCompleto;
    private TipoPosicion posicion;
    private DataFechaHora fechaDeNacimiento;
    private int edad;
    private String lugarNacimiento;
    private double  altura;
    private double  peso;
    private String pathImage="";

    public DataJugador() {}

    public DataJugador(int i,String n){
        id = i;
        nombre = n;
    }

    //contructor 1, para el retorno del sistema los datos de los jugadores
    public DataJugador(int i,String n,String nc,TipoPosicion p,DataFechaHora df,int e,String l,double  a,double pe,String direccionImagen){
        this.id = i;
        this.nombre = n;
        this.nombreCompleto = nc;
        this.posicion=p;
        this.fechaDeNacimiento = df;
        this.edad = e;
        this.lugarNacimiento = l;
        this.altura = a;
        this.peso = pe;
        this.pathImage = direccionImagen;
    }//contructor

    public int getId(){
        return id;
    }
    public String getNombre(){
        return nombre;
    }
    public String getNombreCompleto(){
        return nombreCompleto;
    }
    public TipoPosicion getPosicion(){
        return posicion;
    }
    public DataFechaHora getFechaDeNacimiento(){
        return fechaDeNacimiento;
    }
    public int getEdad(){
        return edad;
    }
    public String getLugarNacimiento(){
        return lugarNacimiento;
    }
    public double getAltura(){
        return altura;
    }
    public double getPeso(){
        return peso;
    }

    public String getPathImage(){
        return pathImage;
    }

        public void setId(int i){
        id = i;//seteamos la variable id
    }

    public void setNombre(String nom){
        nombre=nom;
    }

    public void setNombreCompleto(String nom){
        nombreCompleto=nom;
    }

    public void setPosicion(TipoPosicion t){
        posicion=t;
    }

    public void setFechaDeNacimiento(DataFechaHora f){
        fechaDeNacimiento = f;
    }

    public void setEdad(int e){
        edad=e;
    }

    public void setLugarNacimiento(String l){
        lugarNacimiento=l;
    }

    public void setAltura(double alt){
        altura=alt;
    }

    public void setPeso(double p){
        peso=p;
    }

    public void setPathImage(String img){
        pathImage=img;
    }
}
