/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTypes;


public class DataDetalleApuesta implements Comparable{
    private EstadoApuesta estado;
    private float beneficioEsp;
    private float monto;
    private float dividendo;
    private TipoApuesta tipo;
    private DataFechaHora fecha;
    
    public DataDetalleApuesta (EstadoApuesta ea, float ben, float m, float div,
                               TipoApuesta t, DataFechaHora f){
        estado = ea;
        beneficioEsp = ben;
        monto = m;
        dividendo = div;
        tipo = t;
        fecha = f;
    }
    
    public float getBeneficioEsp(){
        return beneficioEsp;
    }
    public float getMonto(){
        return monto;
    }
    public float getDividendo(){
        return dividendo;
    }
    public TipoApuesta getTipoApuesta(){
        return tipo;
    }

    public TipoApuesta getTipo(){
        return tipo;
    }
    public EstadoApuesta getEstadoApuesta(){
        return estado;
    }
    public EstadoApuesta getEstado(){
        return estado;
    }
    public DataFechaHora getFechaApuesta(){
        return fecha;
    }
    public DataFechaHora getFecha(){
        return fecha;
    }

    public int compareTo (Object d){
        DataDetalleApuesta da = (DataDetalleApuesta)d;
        return fecha.compareTo(da.getFechaApuesta());
    }

    public void setBeneficionEsp(float ben){
        beneficioEsp=ben;
     }

     public void setMonto(float m){
        monto=m;
      }

     public void setDividendo(float div){
        dividendo=div;
     }

     public void setTipo(TipoApuesta t){
         tipo=t;
     }

     public void setEstado(EstadoApuesta es){
        estado=es;
        }

      public void setFecha(DataFechaHora d){
        fecha=d;
        }

}
