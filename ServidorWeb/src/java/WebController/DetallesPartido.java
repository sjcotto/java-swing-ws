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

import java.util.ArrayList;
import java.util.List;

import WebServices.CompeticionesWS.*;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;
import javax.xml.namespace.QName;

/**
 *
 * @author tprog082
 */
public class DetallesPartido extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        
        
        
        String dato =request.getParameter("casoUso");

        if (dato!=null){
           CompeticionesWSService ser = new CompeticionesWSService(new URL("http://"+(String)request.getSession().getAttribute("DIR")+":8280/competicionesWS?wsdl"),
                   new QName("http://WebServices/", "CompeticionesWSService"));
           CompeticionesWS icw = ser.getCompeticionesWSPort();

           List<DataFiltroWS> l = new ArrayList<DataFiltroWS>();

           String nomEq = (String)request.getParameter("nomEquipo");
           if (!nomEq.equals("")){
               DataFiltroWS fe = new DataFiltroWS();
               fe.setTipoFiltro(1);
               fe.setNomEq(nomEq);
               l.add(fe);
           }

           String nomComp = (String)request.getParameter("nomComp");
           if(!nomComp.equals("")){
               DataFiltroWS fc =new DataFiltroWS();
               fc.setTipoFiltro(0);
               fc.setNomComp(nomComp);
               l.add(fc);
           }

           String lugar = (String)request.getParameter("lugar");
           if (!lugar.equals("")){
               DataFiltroWS fl = new DataFiltroWS();
               fl.setTipoFiltro(3);
               fl.setLugarEnc(lugar);
               l.add(fl);
           }

           String div_iL =(String)request.getParameter("div_ini_Local");
           String div_fL = (String)request.getParameter("div_fin_Local");
           if(!div_iL.equals("")&& !div_fL.equals("")){
                  float div_ini = Float.valueOf(div_iL);
                  float div_fin = Float.valueOf(div_fL);
                  DataFiltroWS fd = new DataFiltroWS(); //FiltroRangoDividendosLocal(div_ini,div_fin);
                  fd.setTipoFiltro(4);
                  fd.setTipoDividendo(TipoDividendo.LOCAL);
                  fd.setRangoInicial(div_ini);
                  fd.setRangoFinal(div_fin);
                  l.add(fd);
            }

           String div_iE =(String)request.getParameter("div_ini_Empate");
           String div_fE = (String)request.getParameter("div_fin_Empate");
           if(!div_iE.equals("")&& !div_fE.equals("")){
                  float div_ini = Float.valueOf(div_iE);
                  float div_fin = Float.valueOf(div_fE);
                  DataFiltroWS fd = new DataFiltroWS(); //FiltroRangoDividendosLocal(div_ini,div_fin);
                  fd.setTipoFiltro(4);
                  fd.setTipoDividendo(TipoDividendo.EMPATE);
                  fd.setRangoInicial(div_ini);
                  fd.setRangoFinal(div_fin);
                  l.add(fd);
            }

           String div_iV =(String)request.getParameter("div_ini_Visitante");
           String div_fV = (String)request.getParameter("div_fin_Visitante");
           if(!div_iV.equals("")&& !div_fV.equals("")){
                  float div_ini = Float.valueOf(div_iV);
                  float div_fin = Float.valueOf(div_fV);
                  DataFiltroWS fd = new DataFiltroWS(); //FiltroRangoDividendosLocal(div_ini,div_fin);
                  fd.setTipoFiltro(4);
                  fd.setTipoDividendo(TipoDividendo.VISITANTE);
                  fd.setRangoInicial(div_ini);
                  fd.setRangoFinal(div_fin);
                  l.add(fd);
            }

           String anio_i = request.getParameter("comboAnio_ini");
           String mes_i =  request.getParameter("comboMes_ini");
           String dia_i =  request.getParameter("comboDia_ini");

           String anio_f = request.getParameter("comboAnio_fin");
           String mes_f = request.getParameter("comboMes_fin");
           String dia_f = request.getParameter("comboDia_fin");
           if ((!anio_i.equals("Año")) && (!mes_i.equals("Mes")) && (!dia_i.equals("Dia")) && (!anio_f.equals("Año")) && (!mes_f.equals("Mes")) && (!dia_f.equals("Dia"))){
           
                int anio_ini = Integer.valueOf(anio_i);
                int mes_ini = Integer.valueOf(mes_i);
                int dia_ini = Integer.valueOf(dia_i);

                int anio_fin = Integer.valueOf(anio_f);
                int mes_fin = Integer.valueOf(mes_f);
                int dia_fin = Integer.valueOf(dia_f);

                DataFechaHora ini = new DataFechaHora(); //DataFechaHora(dia_ini,mes_ini,anio_ini,0,0);
                ini.setAnio(anio_ini);
                ini.setMes(mes_ini);
                ini.setDia(dia_ini);
                ini.setHora(0);
                ini.setMinuto(0);
                ini.setSegundos(0);

                DataFechaHora fin = new DataFechaHora();//DataFechaHora(dia_fin,mes_fin,anio_fin,0, 0);
                fin.setAnio(anio_fin);
                fin.setMes(mes_fin);
                fin.setDia(dia_fin);
                fin.setHora(23);
                fin.setMinuto(59);
                fin.setSegundos(59);


                DataFiltroWS ff= new DataFiltroWS(); //FiltroRangoFecha(ini,fin);
                ff.setTipoFiltro(5);
                ff.setFechaIni(ini);
                ff.setFechaFin(fin);
                l.add(ff);
           }

           String estado = (String)request.getParameter("estado");
           if (estado!=null) {
               DataFiltroWS fe= new DataFiltroWS(); //FiltroEstadoPartido("pendiente");
               fe.setTipoFiltro(2);
               if(estado.equals("pend"))
                    fe.setEstado("pendiente");
                else
                    fe.setEstado("finalizado");
                  
                l.add(fe);

           }
      
            ListBeanFiltro lb = new ListBeanFiltro();
            
            for (DataFiltroWS data : l){
                lb.getList().add(data);
            }


            ListBeanDataPartidoComp listb =  icw.listarPartidos(request.getSession().getId(),lb);
            request.setAttribute("listaPartidos", listb.getList());
            request.getRequestDispatcher("/WEB-INF/home/filtroPartido.jsp").forward(request,response);
        }
    else
         request.getRequestDispatcher("/WEB-INF/home/filtroPartido.jsp").forward(request,response);



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
