/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

/**
 *
 * @author santiago
 */
import DataTypes.DataFechaHora;
import Interface.IControladorFecha;
import Excepciones.ExFechaInvalida;

public class ControladorFecha implements IControladorFecha {
    private DataFechaHora fecha;
    private static ControladorFecha instanciaF=null;

    // SINGLETON
    public static ControladorFecha getInstance(){
        if (instanciaF == null)
            instanciaF = new ControladorFecha();
        return instanciaF;
    }

    // CONSTRUCTOR
    private ControladorFecha(){
        fecha = new DataFechaHora(25,8,2011,16,28);
    }

    public DataFechaHora getFecha(){
        return fecha;
    }
    public void  setFecha(DataFechaHora fechaHora) throws ExFechaInvalida{
        if ((fechaHora.getAnio()<=0)||(fechaHora.getDia()> 31) || (fechaHora.getDia()<=0) || (fechaHora.getMes()<=0) || (fechaHora.getMes()>12)||
                 (fechaHora.getHora()< 0) || (fechaHora.getHora()>23) || (fechaHora.getMinuto()<0)
                || (fechaHora.getMinuto()>59) || (fechaHora.getSegundos()<0) || (fechaHora.getSegundos()>59)) {
               throw (new ExFechaInvalida("La Fecha y Hora: " + fechaHora.toString() + " ingresada no es Correcta"));
        }
        fecha=fechaHora;
    }
}
