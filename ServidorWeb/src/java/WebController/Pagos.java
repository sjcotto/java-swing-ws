package WebController;


import java.math.BigDecimal;
import java.util.Properties;
import adaptivepayments.AdaptivePayments;
import com.paypal.svcs.types.ap.PayRequest;
import com.paypal.svcs.types.ap.PayResponse;
import com.paypal.svcs.types.ap.ReceiverList;
import com.paypal.svcs.types.ap.Receiver;
import com.paypal.svcs.types.common.DetailLevelCode.*;
import com.paypal.svcs.types.common.ClientDetailsType;
import com.paypal.svcs.types.common.RequestEnvelope;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import WebServices.UsuarioWS.DataUsuario;
import WebServices.UsuarioWS.UsuarioWS;





public class Pagos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try{

            String inMonto = request.getParameter("Saldo");
            String email= request.getParameter("email");
            if(null==email){
                email="";
            }
            System.out.println(email+" email");

          
            //propiedades del sdk, con lo que no hace falta el archivo anexo de configuracion
            Properties propiedades = new Properties();

//            FileInputStream fis = new FileInputStream("web_services.xml");
//            propiedades.loadFromXML(fis);
//            String dir = propiedades.getProperty("dir_publicar");
//            propiedades.clear();

            propiedades.put("X-PAYPAL-SECURITY-USERID", "Ibet_1320853851_biz_api1.gmail.com");
            propiedades.put("X-PAYPAL-SECURITY-PASSWORD", "1320853876");
            propiedades.put("X-PAYPAL-SECURITY-SIGNATURE", "APxIjl.M7vrghWfW0QexDeqArTJUAE6zXx.7iKXf0hKL5lNKgW-R-BBN");
            propiedades.put("X-PAYPAL-APPLICATION-ID", "APP-80W284485P519543T");
            propiedades.put("X-PAYPAL-SANDBOX-EMAIL-ADDRESS","talleribet@gmail.com");


            propiedades.put("X-PAYPAL-DEVICE-IPADDRESS","127.0.0.1");

            propiedades.put("X-PAYPAL-REQUEST-DATA-FORMAT", "SOAP11");
            propiedades.put("X-PAYPAL-RESPONSE-DATA-FORMAT", "SOAP11");
            propiedades.put("API_BASE_ENDPOINT","https://svcs.sandbox.paypal.com");
            propiedades.put("CLIENT_AUTH_SSL","false");
            propiedades.put("JSSE_PROVIDER","SunJSSE");
            propiedades.put("TRUST_ALL_CONNECTION","true");
            propiedades.put("LOGFILENAME","paypal_sdk.log");
            propiedades.put("LOGENABLED","true");
        
            
//            propiedades.put("USE_PROXY","false");
//            propiedades.put("PROXY_HOST","127.0.0.1");
//            propiedades.put("PROXY_PORT","8080");

              //setear proxy
            System.setProperty("http.proxyHost", "httpproxy.fing.edu.uy");
            System.setProperty("http.proxyPort", "3128");
            System.setProperty("https.proxyHost", "httpproxy.fing.edu.uy");
            System.setProperty("https.proxyPort", "3128");

            System.setProperty("http.nonProxyHosts","localhost");
           // System.setProperty("http.nonProxyPort", "8080");
            
            //System.setProperty("http.nonProxyHosts","localhost/ultimosPart");
            
            


            propiedades.put("USE_PROXY","true");
            propiedades.put("PROXY_HOST","httpproxy.fing.edu.uy");
            propiedades.put("PROXY_PORT","3128");

            AdaptivePayments  aP=new AdaptivePayments(propiedades);
            PayRequest req=new PayRequest();

            if(!email.equals("")){
                req.setActionType("PAY");//se ha de pagar a un jugador implicitamente
            }
            else{
                req.setActionType("CREATE");
            }

            RequestEnvelope re=new RequestEnvelope();
            re.setErrorLanguage("en_US");
            re.setDetailLevel(com.paypal.svcs.types.common.DetailLevelCode.RETURN_ALL);

            req.setRequestEnvelope(re );
            req.setCurrencyCode("USD");            
            StringBuffer url = request.getRequestURL();

           System.out.print(url);
           
           String protocol="http://";
           if (url.charAt(4)=='s'){
                protocol = "https://";
             }

           String retorno = protocol +request.getServerName()+":"+request.getServerPort()+"/IngresoMonedero";
           req.setReturnUrl(retorno+"?Saldo="+inMonto);
           req.setCancelUrl(retorno);



            System.out.println("retorno: "+retorno);


            ClientDetailsType cliD=new ClientDetailsType() ;
            cliD.setApplicationId("APP-1JE429016473214C");//***
            cliD.setCustomerId("usuario");
            cliD.setIpAddress("127.0.0.1");
            req.setClientDetails(cliD);

            Receiver r=new Receiver();
            if(!email.equals("")){
                r.setEmail(email); //se gira automaticamente al usuario
            }
            else{
                r.setEmail("Ibet_1320853851_biz@gmail.com");//se gira a Ibet previa aprobacion
            }

            double operar=0.96;//se calcula un monto mayor para pagar el costo de paypal

            if(email.equals("")){
                operar=1.00;
            }
            double mon=Double.parseDouble(inMonto)/operar;

            mon= Math.rint(mon*100)/100; //redondeo

            String strMonto=Double.toString(mon);
//            System.out.println(strMonto+" monto");
//            int indice=strMonto.indexOf(".");
//            if(indice==-1){
//                strMonto+=".00";
//            }
//            else{
//                if(strMonto.length()-indice>3){
//                    strMonto=strMonto.substring(0,indice+3);
//                }
//            }

            System.out.println(strMonto+" monto past");
            mon=Double.parseDouble(strMonto);
            BigDecimal  monto=BigDecimal.valueOf(mon);
            r.setAmount(monto);
            ReceiverList l=new ReceiverList() ;
            l.getReceiver().add(r);
            req.setReceiverList(l);

            req.setFeesPayer("EACHRECEIVER");
            if(!email.equals("")){  
                req.setSenderEmail("Ibet_1320853851_biz@gmail.com");//Ibet envia el monto
            }

           

            
            //System.setProperty("https.nonProxyHosts","localhost");
            //System.setProperty("http.nonProxyHosts","http://localhost:8080");

            
         System.clearProperty("http.proxyHost");
         System.clearProperty("http.proxyPort");
         System.clearProperty("https.proxyHost");
         System.clearProperty("https.proxyPort");
           

PayResponse respuesta= aP.pay(req);

            String clave=respuesta.getPayKey();
            System.out.println("clave: "+clave);
            System.out.println("estatus: "+respuesta.getPaymentExecStatus());
            System.out.println("string respuesta: "+respuesta.toString());
            System.out.println("envelope: "+respuesta.getResponseEnvelope().toString());

            if(!email.equals("")){
                HttpSession s = request.getSession();
                UsuarioWS usuProxy = (UsuarioWS)s.getAttribute("ControladorUsuario");
                usuProxy.retirarSaldoUsuario(s.getId(),Integer.parseInt( inMonto));
                DataUsuario dataU = usuProxy.obtenerUsuarioLogueado(s.getId());
                s.setAttribute("usuario",dataU);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/home/VerInfoUsuario.jsp");
                dispatcher.forward(request, response);


            }
            else{
                //vamos a mandar el send redirect

              //response.sendRedirect("/ultimosPart");

              request.setAttribute("clave",clave);
              request.getRequestDispatcher("/infoUsuario").forward(request,response);   
            }
        }
        catch(Exception e){
            request.setAttribute("mensaje", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/home/error.jsp").forward(request,response);
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
