package Interface;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author santiago
 */
import DataTypes.DataFechaHora;
import Excepciones.ExFechaInvalida;

public interface IControladorFecha {
    public DataFechaHora getFecha();
    public void setFecha(DataFechaHora data) throws ExFechaInvalida;
}
