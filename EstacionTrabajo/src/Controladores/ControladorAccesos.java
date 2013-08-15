
package Controladores;

import Interface.IControladorAccesosWeb;
import Interface.IControladorAccesosAdmin;
import DataTypes.DataInfoAcceso;
import DataTypes.DataFechaHora;
import Objetos.InfoAcceso;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.LinkedList;
import Interface.IControladorAccesosDAO;
import Interface.XLSFabricaDAO;
import java.io.FileInputStream;
import java.util.Properties;

// Singleton
public class ControladorAccesos implements IControladorAccesosWeb,IControladorAccesosAdmin {

    static private ControladorAccesos instance=null;

    private Queue<InfoAcceso> listAccesos;

    int tope;

    private ControladorAccesos() {
        listAccesos = new LinkedList<InfoAcceso>();

        try {
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream("web_services.xml");
            prop.loadFromXML(fis);
            prop.list(System.out);
            tope = Integer.valueOf(prop.getProperty("tope"));
            fis.close();


        }
        catch (Exception exc){
            System.out.println("Error leyendo archivo properties: "+exc.getMessage());
            tope = 10;
        }


    }

    public static ControladorAccesos getInstance() {
        if (instance == null)
            instance = new ControladorAccesos();
        return instance;
    }

    public void registrarAcceso(String browser_SO, String ip, String url) {
        ControladorFecha cFecha = ControladorFecha.getInstance();
        try {
            System.out.println("'"+browser_SO+"'");
            System.out.println("'"+ip+"'");
            System.out.println("'"+url+"'");
            InfoAcceso infoAcceso = new InfoAcceso(browser_SO,ip,url,cFecha.getFecha());
            if (listAccesos.size()<tope) {
                listAccesos.add(infoAcceso);
            } else if (listAccesos.size()>0){
                listAccesos.poll();
                listAccesos.add(infoAcceso);
            }

        }
        catch (Exception exc){
            System.out.println("***************************** ERROR "+exc.getMessage());
        }


    }

    public List<DataInfoAcceso> obtenerRegistroAccesos() {
        ControladorFecha cFecha = ControladorFecha.getInstance();
        DataFechaHora fechaActual = cFecha.getFecha();

        int anioNuevo = fechaActual.getAnio();
        int mesNuevo = fechaActual.getMes()-1;
        if (mesNuevo==0) {
            mesNuevo=12;
            anioNuevo -= 1;
        }

        DataFechaHora fechaMesAntes = new DataFechaHora();
        fechaMesAntes.setAnio(anioNuevo);
        fechaMesAntes.setMes(mesNuevo);
        fechaMesAntes.setDia(fechaActual.getDia());
        fechaMesAntes.setHora(fechaActual.getHora());
        fechaMesAntes.setMinuto(fechaActual.getMinuto());
        fechaMesAntes.setSegundos(fechaActual.getSegundos());
        
        List ret = new ArrayList<DataInfoAcceso>();
        for (InfoAcceso infoAcceso: listAccesos) {
            if (infoAcceso.getFecha().compareTo(fechaMesAntes)!=-1) {
                DataInfoAcceso dataIARet = infoAcceso.getDataInfoAcceso();
                ret.add(dataIARet);
            }
        }

        Collections.sort(ret);
        Collections.reverse(ret);

        return ret;
    }

    public void  cargarAccesos(String path) throws Exception{

        XLSFabricaDAO f=new XLSFabricaDAO();
        IControladorAccesosDAO cDTO=f.getIControladorAccesosDAO();
        List<DataInfoAcceso> lista=cDTO.cargarAccesos(path);

        listAccesos.clear();
        
        for(int i=0;i<lista.size();i++){
            DataInfoAcceso data=lista.get(i);

            InfoAcceso infoAcceso = new InfoAcceso(data.getBrowser_SO(),data.getIp(),
                    data.getUrl(),data.getFecha());

            listAccesos.add(infoAcceso);
            
        }
    }

    public void guardarAccesos(String path)throws Exception{
        XLSFabricaDAO f=new XLSFabricaDAO();
        IControladorAccesosDAO cDTO=f.getIControladorAccesosDAO();
        List<DataInfoAcceso> lista=new ArrayList<DataInfoAcceso>();
        for(InfoAcceso infoA: this.listAccesos){
            lista.add(infoA.getDataInfoAcceso());
        }
        cDTO.guardarAccesos(path,lista);
    }


}