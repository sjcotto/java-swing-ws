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
//import WebServices.*;
/**
 *
 * @author gonzalo
 */
public class Partido extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //public DataJugPartido listarJugadoresPorBando(int idPart)
        //    throws ExDatosNoAsignados, ExNoExistePartidoEnCompeticion;
         String idCompe = request.getParameter("idComp");
         String idPartido = request.getParameter("idPart");

         int idComp = Integer.valueOf((String)idCompe);
         int idPart = Integer.valueOf((String)idPartido);
                
         CompeticionesWSService serv = new CompeticionesWSService(new java.net.URL("http://"+(String)request.getSession().getAttribute("DIR")+":8280/competicionesWS?wsdl"),
                 new javax.xml.namespace.QName("http://WebServices/", "CompeticionesWSService"));
         CompeticionesWS icw = serv.getCompeticionesWSPort();

         javax.servlet.http.HttpSession objSesion = request.getSession(true);

        try {
            DataPartido dp = icw.seleccionarPartido(objSesion.getId(),idComp, idPart);
            DataJugPartido jugadoresPartido = icw.verInfoPartidoFinalizado(objSesion.getId());
            String imgLocal = icw.getImagePath(objSesion.getId(),dp.getDataInfoPart().getIdLocal());
            String imgVis = icw.getImagePath(objSesion.getId(),dp.getDataInfoPart().getIdVisita());
            icw.finalizarDetallesPartido(objSesion.getId());

            // Dividendos Res Exacto
            request.setAttribute("divResEx",icw.obtenerDividendosResultadoExacto(objSesion.getId(),idComp, idPart));
            // Dividendos Res Exacto

            request.setAttribute("imgLoc", imgLocal);
            request.setAttribute("imgVis", imgVis);
            request.setAttribute("jugsLocal", jugadoresPartido.getJugadoresLocal());
            request.setAttribute("jugsVisita", jugadoresPartido.getJugadoresVisita());
            request.setAttribute("detalles", dp);
            request.getRequestDispatcher("/WEB-INF/home/Partido.jsp").forward(request,response);
        }
        catch (ExNoExisteCompeticion_Exception exc){
            /// Redirigir a error
            request.setAttribute("mensaje",exc.getMessage());
            request.getRequestDispatcher("/WEB-INF/home/error.jsp").forward(request, response);
        }
         catch (ExNoExistePartidoEnCompeticion_Exception exc){
            /// Redirigir a error
            request.setAttribute("mensaje",exc.getMessage());
            request.getRequestDispatcher("/WEB-INF/home/error.jsp").forward(request, response);
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
