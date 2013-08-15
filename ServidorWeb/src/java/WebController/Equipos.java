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

import WebServices.CompeticionesWS.*;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;
import javax.xml.namespace.QName;

/**
 *
 * @author gonzalo
 */
public class Equipos extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idEquipo = request.getParameter("idEq");
        String nomEquipo = request.getParameter("nomEq");

        CompeticionesWSService ser = new CompeticionesWSService(new URL("http://"+(String)request.getSession().getAttribute("DIR")+":8280/competicionesWS?wsdl"),
                new QName("http://WebServices/", "CompeticionesWSService"));
        CompeticionesWS icw = ser.getCompeticionesWSPort();

        javax.servlet.http.HttpSession objSesion = request.getSession();

        if (idEquipo==null) {
            List<DataEquipo> equipos = icw.listarEquipos(objSesion.getId()).getList();
            request.setAttribute("equipos", equipos);

            request.getRequestDispatcher("/WEB-INF/home/Equipos.jsp").forward(request, response);
        } else {
            List<DataIdNombre> jugadores = null;
            try {
                int idEq = Integer.valueOf((String)idEquipo);
                jugadores = (List) icw.listarJugEquipo(objSesion.getId(),idEq).getList();
                String path = icw.getImagePath(objSesion.getId(),idEq);
                request.setAttribute("imgEquipo", path);
                request.setAttribute("nomEq",nomEquipo);
                request.setAttribute("jugadoresEquipo", jugadores);
                request.getRequestDispatcher("/WEB-INF/home/VerDetallesEquipo.jsp").forward(request, response);
            }
            catch (ExNoExisteEquipo_Exception exc) {
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
