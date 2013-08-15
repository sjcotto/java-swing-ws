/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Objetos;

import DataTypes.DataInfoAcceso;
import DataTypes.DataFechaHora;

/**
 *
 * @author gonzalo
 */
public class InfoAcceso {

    private String browser_SO;
    private String ip;
    private String url;
    private DataFechaHora fecha;

    public InfoAcceso(String bro_SO, String ip, String url, DataFechaHora f) {
        this.browser_SO = bro_SO;
        this.ip = ip;
        this.url = url;
        this.fecha = f;
    }

    public DataInfoAcceso getDataInfoAcceso() {
        return new DataInfoAcceso(this.browser_SO,this.ip,this.url,this.fecha);
    }

    public DataFechaHora getFecha() {
        return this.fecha;
    }

}
