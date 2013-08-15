/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WebController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import WebServices.CompeticionesWS.*;
import WebServices.UsuarioWS.*;

/**
 *
 * @author CAPablito
 */
public class ListarPencas extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

     
     String strIdComp = request.getParameter("idComp");
     String nombreComp= (String)request.getParameter("nomComp");

     javax.servlet.http.HttpSession objSesion = request.getSession();

     if (strIdComp==null){
         CompeticionesWSService serv = new CompeticionesWSService(new java.net.URL("http://"+(String)request.getSession().getAttribute("DIR")+":8280/competicionesWS?wsdl"),
                 new javax.xml.namespace.QName("http://WebServices/", "CompeticionesWSService"));
         CompeticionesWS icw = serv.getCompeticionesWSPort();

        WebServices.CompeticionesWS.ListBean pencas = icw.listarPencasDisponibles(objSesion.getId());
        request.setAttribute("pencas", pencas.getList());
        request.getRequestDispatcher("/WEB-INF/home/Pencas.jsp").forward(request,response);

        }
      else{
         try {
         int idComp = Integer.valueOf(strIdComp);
         UsuarioWS icU = (UsuarioWS)objSesion.getAttribute("ControladorUsuario");
         DataInfoPenca dip = icU.verTablaPosicionesPenca(objSesion.getId(),idComp);

         request.setAttribute("infoPenca", dip);
         WebServices.UsuarioWS.ListBean parts = null;
         if (dip.isParticipaUsuario() && !dip.isFinalizada()){
            parts = icU.obtenerPartidosNoFinalizadosPenca(objSesion.getId(),idComp);
             request.setAttribute("partidos",parts.getList());
        }
        request.setAttribute("infoPenca", dip);
        request.setAttribute("nomComp",nombreComp);
       
        request.setAttribute("idComp",strIdComp);
        request.getRequestDispatcher("/WEB-INF/home/Penca.jsp").forward(request,response);
        }
         catch (WebServices.UsuarioWS.Exception_Exception exc) {
                request.setAttribute("mensaje", exc.getMessage());
                request.getRequestDispatcher("/WEB-INF/home/error.jsp").forward(request,response);
            }
         
         }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
