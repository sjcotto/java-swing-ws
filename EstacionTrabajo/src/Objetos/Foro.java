/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Objetos;

import DataTypes.DataMensajePenca;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author tprog084
 */
public class Foro {

    private List<Mensaje> mensajes;

    public Foro() {
        mensajes = new ArrayList<Mensaje>();
    }

    public Foro(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    public List<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    public void agregarMensaje(String emisor,String receptor,boolean publico,String mensaje) {
        Mensaje m = new Mensaje(emisor,receptor,publico,mensaje);
        this.mensajes.add(m);
    }

    public List<DataMensajePenca> obtenerMensajesForo() {
        List<DataMensajePenca> mensajesRet = new ArrayList<DataMensajePenca>();
        for (Mensaje m : this.mensajes){
            DataMensajePenca dataMens = m.getDataMensajePenca();
            mensajesRet.add(dataMens);
        }
        Collections.sort(mensajesRet);
        return mensajesRet;
    }






}
