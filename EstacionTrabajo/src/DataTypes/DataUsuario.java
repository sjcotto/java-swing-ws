
package DataTypes;

public class DataUsuario {

private String nombre;
private String email;
private String nick;
private String dir;
private DataFechaHora fechaNac;
private String ci;
private String pais;
private TipoSexo sexo;
private float saldo;
private boolean notPartido;
private boolean notApuesta;
private boolean notForo;



public DataUsuario() {}

 public DataUsuario (String nom,String nick,String email,String dir,String ci,DataFechaHora fNac, String pais,TipoSexo sex, float Saldo,boolean notA,boolean notF,boolean notP){

    this.nombre=nom;
    this.ci=ci;
    this.dir=dir;
    this.email=email;
    this.fechaNac=fNac;
    this.saldo=Saldo;
    this.nick=nick;
    this.pais=pais;
    this.sexo= sex;
    this.notApuesta=notA;
    this.notForo=notF;
    this.notPartido=notP;
    }

    public boolean isNotApuesta() {
        return notApuesta;
    }

    public void setNotApuesta(boolean notApuesta) {
        this.notApuesta = notApuesta;
    }

    public boolean isNotForo() {
        return notForo;
    }

    public void setNotForo(boolean notForo) {
        this.notForo = notForo;
    }

    public boolean isNotPartido() {
        return notPartido;
    }

    public void setNotPartido(boolean notPartido) {
        this.notPartido = notPartido;
    }


 public String getNombre(){
    return nombre;
}

public String getEmail(){
    return email;
}

public String getNick(){
    return nick;
}

public String getDir(){
    return dir;
}

public String getCI(){
    return ci;
}

public String getPais(){
    return pais;
}

public TipoSexo getSexo(){
    return sexo;
}

public DataFechaHora getFechaNac(){
    return fechaNac;
 }

public float getSaldo(){
    return saldo;
}

public void setNombre(String n){
    nombre=n;
}

public void setEmail(String e){
    email=e;
}

public void setNick(String n){
    nick=n;
}

public void setDir(String d){
    dir=d;
}

public void setCI(String c){
    ci=c;
}

public void setPais(String p){
    pais=p;
}

public void setSexo(TipoSexo s){
    sexo=s;
}

public void setEFechaNac(DataFechaHora f){
    fechaNac=f;
}

public void setSaldo(float s){
    saldo=s;
}

}

