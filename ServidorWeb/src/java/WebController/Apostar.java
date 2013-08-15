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

import WebServices.UsuarioWS.*;


/**
 *
 * @author Santiago
 */
public class Apostar extends HttpServlet {
   
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
        
        javax.servlet.http.HttpSession objSesion = request.getSession();
        WebServices.UsuarioWS.UsuarioWS icU = (WebServices.UsuarioWS.UsuarioWS)objSesion.getAttribute("ControladorUsuario");

        String confirmar = request.getParameter("confirmar");
        

        if (confirmar == null){

            
            try{

                String monto = request.getParameter("monto");
                String idCom = request.getParameter("idComp");
                String tipoA = request.getParameter("tipoA"); //solo para apuesta por partido
                String tipoC = request.getParameter("tipoC"); //solo para apuesta por partido o res exacto
                String idPar = request.getParameter("idPar"); //solo para apuesta por partido o res exacto
                String golL = request.getParameter("golL"); // solo para apuesta Res exacto
                String golV = request.getParameter("golV"); // solo para apuesta res exacto
                String strIdJug = request.getParameter("idJug");

                String tipo = request.getParameter("tipo");

                String strPaquete = (String)request.getParameter("paquete");
                boolean paquete = strPaquete.equals("1");
                request.setAttribute("paquete", strPaquete);

                String idEq = request.getParameter("idEq"); //solo para apuesta por campeonato

                float ads = Float.valueOf(monto);

                //si es campeonato
                icU.seleccionarCompeticion(objSesion.getId(),Integer.valueOf(idCom));

                if ((tipo !=null)&&(tipo.equals("Partido"))){
                    //preguntamos si es campeonato o partido individual
                    //tipoC == 2 si es campeonato y apuesta ResExacto, tipoC == 1 es campeonato, 0 p ind
                    if (tipoC.equals("1")){
                        // es un campeonato
                        icU.seleccionarCampApuesta(objSesion.getId(),TipoCampeonatoApuesta.PARTIDO);
                        icU.seleccionarPartidoCamp(objSesion.getId(),Integer.valueOf(idPar));

                    } else if (tipoC.equals("2")){
                        // es un campeonato
                        icU.seleccionarCampApuesta(objSesion.getId(),TipoCampeonatoApuesta.RES_EXACTO);
                        icU.seleccionarPartidoCamp(objSesion.getId(),Integer.valueOf(idPar));
                    }

                    TipoDividendo tipoDiv = null;
                    int a = Integer.valueOf(tipoA);
                    boolean resExacto = false;

                    if (a==1)
                        tipoDiv = TipoDividendo.LOCAL;
                    else if(a==2)
                        tipoDiv = TipoDividendo.EMPATE;
                    else if (a==3)
                        tipoDiv = TipoDividendo.VISITANTE;
                    else if (a==4)
                        resExacto = true;
                    

                    

                    if (resExacto) {
                        if (!paquete)
                            icU.apostarResultadoExacto(objSesion.getId(),Integer.parseInt(golL), Integer.parseInt(golV), ads);
                        else
                            icU.apostarResultadoExactoPaquete(objSesion.getId(),Integer.parseInt(golL), Integer.parseInt(golV),ads);
                    } else {

                        if (!paquete)
                            icU.apostarPartido(objSesion.getId(),tipoDiv, ads);
                        else
                            icU.apostarPartidoPaquete(objSesion.getId(),tipoDiv, ads);
                    }

                }else{
                    if (Integer.parseInt(strIdJug) == -1) {
                        icU.seleccionarCampApuesta(objSesion.getId(),TipoCampeonatoApuesta.EQUIPO);
                        if (!paquete)
                            icU.apostarCampeonato(objSesion.getId(),Integer.valueOf(idEq),Integer.valueOf(monto));
                        else {
                            icU.apostarCampeonatoPaquete(objSesion.getId(),Integer.valueOf(idEq),Integer.valueOf(monto));
                        }
                    } else { // apesta por goleador
                        icU.seleccionarCampApuesta(objSesion.getId(),TipoCampeonatoApuesta.GOLEADOR);
                        if (!paquete)
                            icU.apostarGoleador(objSesion.getId(),Integer.parseInt(strIdJug), ads);
                        else
                            icU.apostarGoleadorPaquete(objSesion.getId(),Integer.parseInt(strIdJug), ads);

                    }
                }

            }catch(WebServices.UsuarioWS.ExCompeticionFinalizada_Exception e){
                request.setAttribute("mensaje", e.getMessage());
                request.getRequestDispatcher("/WEB-INF/home/error.jsp").forward(request,response);
            }catch(WebServices.UsuarioWS.ExDivsNoAsignados_Exception e){
                request.setAttribute("mensaje", e.getMessage());
                request.getRequestDispatcher("/WEB-INF/home/error.jsp").forward(request,response);
            }catch (WebServices.UsuarioWS.ExPartidoCampInvalido_Exception e) {
                request.setAttribute("mensaje", e.getMessage());
                request.getRequestDispatcher("/WEB-INF/home/error.jsp").forward(request,response);
            }catch (WebServices.UsuarioWS.ExNoExisteCompeticion_Exception e) {
                request.setAttribute("mensaje", e.getMessage());
                request.getRequestDispatcher("/WEB-INF/home/error.jsp").forward(request,response);
            }catch(WebServices.UsuarioWS.Exception_Exception e){
                request.setAttribute("mensaje", e.getMessage());
                request.getRequestDispatcher("/WEB-INF/home/error.jsp").forward(request,response);
            }

            request.getRequestDispatcher("/WEB-INF/home/confirmarApuesta.jsp").forward(request,response);

    }else{
        //ver si el usuario confirmo la apuesta, y sino liberar la memoria temporal de sistema
        try{
            String strPaquete = (String)request.getParameter("paquete");
            boolean paquete = strPaquete.equals("1");
            if (!paquete) {
            icU.confirmarAltaApuesta(objSesion.getId(),true,false);
            }
            else {
                icU.agregarApuestaPaquete(objSesion.getId());
                request.getSession().setAttribute("recargar","2");
                request.getRequestDispatcher("/right").forward(request,response);
                return;
            }

            DataUsuario dataU = icU.obtenerUsuarioLogueado(objSesion.getId());
            objSesion.setAttribute("usuario",dataU);

        }catch (WebServices.UsuarioWS.Exception_Exception e) {
            request.setAttribute("mensaje", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/home/error.jsp").forward(request,response);
        }

        request.getRequestDispatcher("/infoUsuario").forward(request,response);
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
