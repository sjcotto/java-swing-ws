/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Archivo;

import Interface.IControladorEquipoDAO;
import DataTypes.DataEquipo;
import java.util.List;
import java.util.ArrayList;

public class XLSControladorEquiposDAO implements IControladorEquipoDAO {

    private ManejoDeArchivos arch = null;

    public XLSControladorEquiposDAO() {
        arch = ManejoDeArchivos.getInstance();
    }

  
    
    public List<DataEquipo> cargarEquipos(String ruta)throws Exception{
        List<DataEquipo> salida=new ArrayList<DataEquipo>();

        arch.abrirLibro(ruta);
        int fila=0;
        int tamanio=Integer.parseInt(arch.leerString("Equipos", 0,0));

        for(fila=1;fila<tamanio+1;fila++){
            String strIdEq = arch.leerString("Equipos",0,fila);
            int idE=Integer.parseInt(strIdEq);
            String nombre=arch.leerString("Equipos", 1,fila);
            String path=arch.leerString("Equipos", 2,fila);
            DataEquipo dataE=new DataEquipo(idE,nombre,path);
            salida.add(dataE);
        }
        //arch.cerrarLibroLec();
        return salida;
    }
    
    public void guardarEquipos(String ruta,List<DataEquipo> lisEQ)throws Exception{

        arch.crearlibroEscYhojas(ruta);//verificar


        int fila;
        arch.agregarString("Equipos",0,0,Integer.toString(lisEQ.size()));
        for (fila=0;fila<lisEQ.size();fila++) {
            //se ingresan los datos de uno
            arch.agregarString("Equipos",0,fila+1,Integer.toString(lisEQ.get(fila).getId()));
            arch.agregarString("Equipos",1,fila+1,lisEQ.get(fila).getNombre());
            arch.agregarString("Equipos",2,fila+1,lisEQ.get(fila).getImagePath());

        }
        //arch.cerrarLibroEsc();//verificar
               
    }
}