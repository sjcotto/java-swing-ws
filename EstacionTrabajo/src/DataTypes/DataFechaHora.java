
package DataTypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataFechaHora implements Comparable{
    int dia,mes,anio,hora,minuto,segundos;
    String str;

    public DataFechaHora(){

    }
    
    public DataFechaHora(int d, int m, int a, int h, int min){
        dia = d;
        mes = m;
        anio = a;
        hora = h;
        minuto = min;
        segundos = 0;
        str = this.toString();
    }

    public DataFechaHora(int d, int m, int a, int h, int min, int seg){
        dia = d;
        mes = m;
        anio = a;
        hora = h;
        minuto = min;
        segundos = seg;
        str = this.toString();
    }

    public DataFechaHora (String fecha){
        String sub= fecha.substring(0,2);
        dia=Integer.parseInt(sub);

        sub= fecha.substring(3,5);
        mes=Integer.parseInt(sub);

        sub= fecha.substring(6,10);
        anio=Integer.parseInt(sub);

        sub= fecha.substring(11,13);
        hora=Integer.parseInt(sub);

        sub= fecha.substring(14,16);
        minuto=Integer.parseInt(sub);

        sub= fecha.substring(17,19);
        segundos=Integer.parseInt(sub);

        str = this.toString();

    }

    public int getDia(){
        return dia;
    }
    public int getMes(){
        return mes;
    }
    public int getAnio(){
        return anio;
    }
    public int getHora(){
        return hora;
    }
    public int getMinuto(){
        return minuto;
    }

    public int getSegundos(){
        return this.segundos;
    }

    public int compareTo (Object d){
        DataFechaHora dfh = (DataFechaHora) d;
        if (this.anio > dfh.anio)
            return 1;
        if (this.anio < dfh.anio)
            return -1;
        if (this.mes > dfh.mes)
            return 1;
        if (this.mes < dfh.mes)
            return -1;
        if (this.dia > dfh.dia)
            return 1;
        if (this.dia < dfh.dia)
            return -1;
        if (this.hora > dfh.hora)
            return 1;
        if (this.hora < dfh.hora)
            return -1;
         if (this.minuto > dfh.minuto)
            return 1;
        if (this.minuto < dfh.minuto)
            return -1;
        if (this.segundos > dfh.segundos)
            return 1;
        if (this.segundos < dfh.segundos)
            return -1;
        return 0;
    }

    @Override
    public String toString(){
        String a = "";
        String min = "";
        String d="",m="";
        String seg = "";
        String h = "";
        
        if(anio < 1000){
            a = "0" + a;
            if (anio < 100)
                a = "0" + a;
            if (anio < 10)
                a = "0" +a;
        }


        if (minuto>=0 && minuto <=9)
            min = "0";
        if (segundos>=0 && segundos <=9)
            seg = "0";
        if (dia>=0 && dia <=9)
            d = "0";
        if (mes>=0 && mes <=9)
            m = "0";
        if (hora>=0 && hora <=9)
            h = "0";
        
        return (d+dia+"/"+m+mes+"/"+a+anio+" "+h+hora+":"+min+minuto+":"+seg+segundos);
    }

    public String toStringFecha(){
        return (dia+"/"+mes+"/"+anio);
    }

    public void setDia(int d){
        dia=d;
    }

    public void setMes(int m){
        mes=m;
    }

    public void setAnio(int a){
        anio=a;
    }

    public void setHora(int h){
        hora=h;
    }

    public void setMinuto(int m){
        minuto=m;
    }

    public void setSegundos(int s){
        segundos=s;
    }

    public String getStr() {
        return this.str;
    }

    public void setStr(String s){
        this.str = s;
    }
}
