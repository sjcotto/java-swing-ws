
package DataTypes;

public class DataInfoAcceso implements Comparable {

    private String browser_SO;
    private String ip;
    private String url;
    private DataFechaHora fecha;

    public DataInfoAcceso(){}

    public DataInfoAcceso(String bro_SO, String ip, String url, DataFechaHora f) {
        this.browser_SO = bro_SO;
        this.ip = ip;
        this.url = url;
        this.fecha = f;
    }

    public String getBrowser_SO() {
        return this.browser_SO;
    }

    public String getIp() {
        return this.ip;
    }

    public String getUrl() {
        return this.url;
    }

    public DataFechaHora getFecha() {
        return this.fecha;
    }

    public int compareTo(Object o) {
        DataInfoAcceso data  = (DataInfoAcceso) o;
        return this.fecha.compareTo(data.getFecha());
    }
}