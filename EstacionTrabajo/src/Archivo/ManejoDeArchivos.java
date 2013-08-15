package Archivo;


import jxl.*;
import jxl.write.*;
import java.io.File;

public class ManejoDeArchivos {
    
    private WritableWorkbook libroEsc;
    private Workbook libroLec; 

    private static ManejoDeArchivos instancia = null;

    private ManejoDeArchivos(){
      
    }

    public static ManejoDeArchivos getInstance() {
        if(instancia == null)
            instancia = new ManejoDeArchivos();
        return instancia;
    }

    public void crearlibroEscYhojas(String ruta)  throws Exception{

            //se crea el libroEsc
            libroEsc=Workbook.createWorkbook(new File(ruta));
            //se crean las hojas
            libroEsc.createSheet ("Jugadores", 1);
            libroEsc.createSheet ("Equipos", 2);
            libroEsc.createSheet ("Competiciones",3);
            libroEsc.createSheet ("Usuarios",4);
            libroEsc.createSheet ("FechaSistema",5);
            libroEsc.createSheet ("RegistroAcceso",6);
    }
    
    
     public void agregarString(String nomHoja,int columna, int fila,String frase)
          throws Exception{
       
        Label label = new Label(columna,fila, frase);  
        WritableSheet hoja = libroEsc.getSheet(nomHoja);
        hoja.addCell(label);       
    }
    
     public void cerrarLibroEsc() throws Exception{  
        libroEsc.write ();  
        libroEsc.close ();
     }
     
   
     public void abrirLibro(String ruta)throws Exception{
          libroLec=Workbook.getWorkbook(new File(ruta));
     }
     
    
    public String leerString(String nomHoja,int columna, int fila)
          throws Exception{
        jxl.Sheet hoja=libroLec.getSheet(nomHoja);
        jxl.Cell celda=hoja.getCell(columna,fila);
        return celda.getContents();
    }
    
    
    public void cerrarLibroLec()throws Exception{
        libroLec.close();
    }
}
