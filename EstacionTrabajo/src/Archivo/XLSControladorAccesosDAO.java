package Archivo;

import Interface.IControladorAccesosDAO;
import DataTypes.DataInfoAcceso;
import java.util.List;
import java.util.ArrayList;
import DataTypes.DataFechaHora;

public class XLSControladorAccesosDAO implements IControladorAccesosDAO {

    private ManejoDeArchivos arch = null;

    public XLSControladorAccesosDAO (){
        arch = ManejoDeArchivos.getInstance();
    }



    public List<DataInfoAcceso> cargarAccesos(String ruta)throws Exception{
        List<DataInfoAcceso> salida=new ArrayList<DataInfoAcceso>();
        int fila=0;
        int tamanio=Integer.parseInt(arch.leerString("RegistroAcceso", 0,0));
        for(fila=1;fila<tamanio+1;fila++){
            String bro_SO = arch.leerString("RegistroAcceso",0,fila);
            String ip = arch.leerString("RegistroAcceso",1,fila);
            String url = arch.leerString("RegistroAcceso",2,fila);
            String fe = arch.leerString("RegistroAcceso",3,fila);
            DataFechaHora fecha=new DataFechaHora(fe);

            DataInfoAcceso dataA=new DataInfoAcceso(bro_SO, ip, url, fecha);
            salida.add(dataA);
        }
        return salida;
    }

    public void guardarAccesos(String ruta,List<DataInfoAcceso> lisR)throws Exception{
        int fila;
        arch.agregarString("RegistroAcceso",0,0,Integer.toString(lisR.size()));
        for (fila=0;fila<lisR.size();fila++) {
            //se ingresan los datos de uno
            arch.agregarString("RegistroAcceso",0,fila+1,(lisR.get(fila)).getBrowser_SO());
            arch.agregarString("RegistroAcceso",1,fila+1,lisR.get(fila).getIp());
            arch.agregarString("RegistroAcceso",2,fila+1,lisR.get(fila).getUrl());
            arch.agregarString("RegistroAcceso",3,fila+1,lisR.get(fila).getFecha().toString());

        }
    }
}