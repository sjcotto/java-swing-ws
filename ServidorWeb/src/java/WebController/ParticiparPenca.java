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
import java.util.List;
import WebServices.UsuarioWS.*;
/**
 *
 * @author CAPablito
 */
public class ParticiparPenca extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        javax.servlet.http.HttpSession s = request.getSession();
        UsuarioWS icU = (UsuarioWS)s.getAttribute("ControladorUsuario");
         String strIdComp = (String)request.getParameter("idComp");
        String nombreComp= (String)request.getParameter("nomComp");
        String golesL = (String)request.getParameter("golesLoc");
        String golesV = (String)request.getParameter("golesVis");
        String part = (String)request.getParameter("idPart");
        if ((golesL==null) || (golesV==null)){
        try{
            int id= Integer.valueOf(strIdComp);
            boolean ok = icU.participarEnPenca(s.getId(),id);
            icU.confirmarParticipacion(s.getId(),ok);

            DataUsuario dataU = icU.obtenerUsuarioLogueado(s.getId());
            
            s.setAttribute("usuario",dataU);
            
            DataInfoPenca dip = icU.verTablaPosicionesPenca(s.getId(),id);
        ListBean parts = null;
        if (dip.isParticipaUsuario()){
            parts = icU.obtenerPartidosNoFinalizadosPenca(s.getId(),id);
        }
             

        request.setAttribute("infoPenca", dip);
        request.setAttribute("nomComp",nombreComp);
        request.setAttribute("partidos",parts.getList());
        request.setAttribute("idComp",strIdComp);
        request.getRequestDispatcher("/WEB-INF/home/Penca.jsp").forward(request,response);
        }
        catch(Exception_Exception e){
            request.setAttribute("mensaje", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/home/error.jsp").forward(request,response);
        }
        }
        else if (!golesL.equals("")&& !golesV.equals("")){
            try{
               int id1= Integer.valueOf(strIdComp);
                int gL= Integer.valueOf(golesL);
               int gV= Integer.valueOf(golesV);
               int idP =Integer.valueOf(part);
               icU.apostarPartidoPenca(s.getId(),idP, gL, gV);
               DataInfoPenca dip1 = icU.verTablaPosicionesPenca(s.getId(),id1);
              
               request.setAttribute("infoPenca", dip1);

               ListBean parts1 = icU.obtenerPartidosNoFinalizadosPenca(s.getId(),id1);
               request.setAttribute("nomComp",nombreComp);
               request.setAttribute("partidos",parts1.getList());
               request.setAttribute("idComp",strIdComp);
               request.getRequestDispatcher("/WEB-INF/home/Penca.jsp").forward(request,response);
            }
            catch (java.lang.Exception e){
                request.setAttribute("mensaje", e.getMessage());
                      request.getRequestDispatcher("/WEB-INF/home/error.jsp").forward(request,response);
             }
            }
          else{
            try{
             int id1= Integer.valueOf(strIdComp);
                DataInfoPenca dip1 = icU.verTablaPosicionesPenca(s.getId(),id1);
               ListBean parts1 = icU.obtenerPartidosNoFinalizadosPenca(s.getId(),id1);
               request.setAttribute("infoPenca", dip1);
               request.setAttribute("nomComp",nombreComp);
               request.setAttribute("partidos",parts1.getList());
               request.setAttribute("idComp",strIdComp);
               request.getRequestDispatcher("/WEB-INF/home/Penca.jsp").forward(request,response);
            }
            catch (java.lang.Exception e){
               request.setAttribute("mensaje", e.getMessage());
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
