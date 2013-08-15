/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WebController;

import WebServices.UsuarioWS.DataApuestaWS;
import WebServices.UsuarioWS.DataFechaHora;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.util.List;




import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author CAPablito
 */
public class PDF extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
      
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doPost(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("application/pdf");
        Document doc = new Document();
        doc.addTitle("Apuestas");
        doc.addAuthor("Pablito Melo");

        HttpSession s = request.getSession();
        WebServices.UsuarioWS.UsuarioWS usu = (WebServices.UsuarioWS.UsuarioWS)s.getAttribute("ControladorUsuario");
        try{
            DataFechaHora f_inicial=null;
            DataFechaHora f_final=null;
            f_inicial = new DataFechaHora();
            f_inicial.setAnio(1911);
            f_inicial.setMes(1);
            f_inicial.setDia(1);
            f_inicial.setHora(1);
            f_inicial.setMinuto(1);
            f_inicial.setSegundos(1);
            f_final = new DataFechaHora();
            f_final.setAnio(2029);
            f_final.setMes(12);
            f_final.setDia(31);
            f_final.setHora(23);
            f_final.setMinuto(59);
            f_final.setSegundos(59);


            WebServices.UsuarioWS.ListBeanApuesta listB= usu.mostrarHistorialApuestas(request.getSession().getId(),f_inicial, f_final);
            List<DataApuestaWS> list_da = (List)listB.getList();

            PdfWriter.getInstance(doc, response.getOutputStream());
            
            doc.open();
            Paragraph p = new Paragraph("Historial de Apuestas\n");
                                 p.add("------------------------");
            p.setAlignment(1);
            doc.add(p);
            int i=1;
            for(DataApuestaWS data : list_da ){
               Paragraph p1 = new Paragraph("Apuesta "+Integer.toString(i)+"\n");
               i++;
               p1.add("   Fecha : "+data.getFecha().getStr()+"\n");
                int t = data.getTipo();
                 if (t==0) {
                    p1.add("   Tipo : Campeon\n");
                     p1.add("        Nombre Competicion : "+data.getCompeticion().getNombre()+"\n");
                     p1.add("        Apuesta : "+data.getEquipo().getNombre()+"\n");
                } else if (t==1) {
                    p1.add("   Tipo : Resultado Exacto\n");
                    p1.add("        Nombre Competicion : "+data.getPartido().getDataInfoPart().getNomComp()+"\n");
                    p1.add("        Local: "+data.getPartido().getDataInfoPart().getNomLocal()+"\n");
                    p1.add("        Visitante: "+data.getPartido().getDataInfoPart().getNomVisita()+"\n");
                    p1.add("        Apuesta   :     "+data.getGolesL()+" - "+data.getGolesV()+"\n");
                    
                } else if (t==2) {
                    p1.add("   Tipo : Goleador\n");
                    p1.add("        Nombre Competicion : "+data.getCompeticion().getNombre()+"\n");
                    p1.add("        Nombre Jugador : "+data.getJugador().getNombre()+"\n");
                } else if (t==3) {
                    p1.add("   Tipo : Partido\n");
                    p1.add("        Nombre Competicion : "+data.getPartido().getDataInfoPart().getNomComp()+"\n");
                    p1.add("        Local     :     "+data.getPartido().getDataInfoPart().getNomLocal()+"\n");
                    p1.add("        Visitante : "+data.getPartido().getDataInfoPart().getNomVisita()+"\n");
                    p1.add("        Apuesta   : "+data.getTipoResultado().value()+"\n");
                }
                p1.add("   Estado : " +data.getEstadoApuesta().value()+"\n");
                p1.add("   Beneficio : " +Float.toString(data.getBeneficio())+"\n");
                p1.add("   Monto : " +Float.toString(data.getMonto())+"\n");
                p1.add("   Dividendo : " +Float.toString(data.getDividendo())+"\n");
                if (data.getPertenecePaquete()>=1){
                p1.add("   Paquete : "+data.getPertenecePaquete()+"\n");
                }

                p1.add("---------------------------------------------------------\n\n");

               doc.add(p1);
            }

            doc.close();

        }
        catch (DocumentException e){
            e.printStackTrace();
        }
        catch (Exception e){

        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
