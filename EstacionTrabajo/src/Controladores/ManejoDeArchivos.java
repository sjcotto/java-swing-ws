package Controladores;


import jxl.*;
import jxl.write.*;
import java.io.File;

public class ManejoDeArchivos {
    
    private WritableWorkbook libroEsc;
    private Workbook libroLec; 
    
    public ManejoDeArchivos(){
        
    }
    
    /*
    
    public static void main(String[] args) {
        String ruta="C:\\Users\\eliel\\Desktop\\Nueva carpeta\\punto.xls";
        JavaApplication1 nuevo=new JavaApplication1();
        try{
            nuevo.crearlibroEscYhojas(ruta);
            nuevo.agregarString("Jugadores",0,0,"prueba");
            nuevo.cerrarLibroEsc();
            nuevo.abrirLibro(ruta); 
            String salida=nuevo.leerString("Jugadores", 0,0);   
            nuevo.libroLec.close();
            System.out.println(salida);            
        }
        catch(Exception e){
             e.printStackTrace() ;
        }
    }*/
    
    
    public void crearlibroEscYhojas(String ruta)  throws Exception{
            
            //se crea el libroEsc
            libroEsc=Workbook.createWorkbook(new File(ruta));            
            //se crean las hojas
            libroEsc.createSheet ("Jugadores", 1);
            libroEsc.createSheet ("Equipos", 2);
            libroEsc.createSheet ("Competiciones",3);
             
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