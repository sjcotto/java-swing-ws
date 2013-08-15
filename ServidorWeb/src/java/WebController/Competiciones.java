/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WebController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
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
public class Competiciones extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlFabricaWeb f = new FabricaWeb();
        IControladorWebCompeticiones icwc = f.getIControladorWebCompeticiones();et request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String strIdComp = request.getParameter("idComp");

        javax.servlet.http.HttpSession objSesion = request.getSession();
        CompeticionesWSService ser = new CompeticionesWSService(new URL("http://"+(String)request.getSession().getAttribute("DIR")+":8280/competicionesWS?wsdl"),
           new QName("http://WebServices/", "CompeticionesWSService"));
        CompeticionesWS icw = ser.getCompeticionesWSPort();

        if (strIdComp==null) {
           
            
            // Listo las competiciones
            ListBean listBC = icw.listarCompetencias(objSesion.getId());
            List<DataIdNombre> competiciones = (List) listBC.getList();
            
            request.setAttribute("competiciones", competiciones);
            request.getRequestDispatcher("/WEB-INF/home/Competiciones.jsp").forward(request,response);
        } else {
            // Redirijo segun el tipo de competicion
            try {
                
                int idComp = Integer.valueOf((String)strIdComp);
                DataCompeticion dataComp = icw.verInfoCompeticion(objSesion.getId(),idComp);
                HttpSession session = request.getSession();
                session.setAttribute("controladorComp", icw);

                request.setAttribute("idComp", Integer.toString(dataComp.getId()));
                request.setAttribute("nomComp", dataComp.getNombre());
                request.setAttribute("dividendos", dataComp.getDividendos());
                request.setAttribute("estaDefinida", dataComp.isEstaDefinida());
                request.setAttribute("tipoComp", dataComp.getTipo());
                request.setAttribute("pathImage", dataComp.getPathImage());
                request.setAttribute("fin",icw.competicionEstaFinalizada(objSesion.getId(),dataComp.getId()));
                if (dataComp.getTipo()!=TipoCompeticion.PARTIDO_INDIVIDUAL) {
                    request.setAttribute("maxGoleadores", icw.get5MaxGoleadores(objSesion.getId(),idComp).getGoleadores());
                    request.setAttribute("goleadores",icw.getJugadoresCampeonato(objSesion.getId(),idComp).getGoleadores());
                }

                ListBean listBP = icw.infoPartidosCompeticion(objSesion.getId());
                List<DataPartido> partidos = (List)listBP.getList();
                request.setAttribute("partidos",partidos);
                request.setAttribute("tabla",null);

                if (dataComp.getTipo()==TipoCompeticion.LIGA) {
                    ListBeanDataEquipoLiga listBEL = icw.obtenerTablaDePosiciones(objSesion.getId(),dataComp.getId());
                    List<DataEquipoLiga> tabla = listBEL.getListDEL();
                    request.setAttribute("tabla",tabla);
                }
                

            }
            catch (ExNoExisteCompeticion_Exception exc) {
                request.setAttribute("mensaje", exc.getMessage());
                request.getRequestDispatcher("/WEB-INF/home/error.jsp").forward(request,response);
            }
            catch (ExDatosNoAsignados_Exception exc) {
                request.setAttribute("mensaje", exc.getMessage());
                request.getRequestDispatcher("/WEB-INF/home/error.jsp").forward(request,response);
            }
            catch (Exception_Exception exc) {
                request.setAttribute("mensaje", exc.getMessage());
                request.getRequestDispatcher("/WEB-INF/home/error.jsp").forward(request,response);
            }


            request.getRequestDispatcher("/WEB-INF/home/Competicion.jsp").forward(request,response);
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
