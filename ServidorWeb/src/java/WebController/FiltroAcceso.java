/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WebController;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import WebServices.RegistroAccesoWS.*;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;
import javax.xml.namespace.QName;

/**
 *
 * @author gonzalo
 */
public class FiltroAcceso implements Filter{

    private FilterConfig filterConf = null;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConf = filterConfig;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (request instanceof HttpServletRequest) {
            HttpServletRequest req = (HttpServletRequest) request;

            System.out.println("**********************************");
            String browser_SO = req.getHeader("user-agent");
            String ip = request.getRemoteAddr();
            StringBuffer url = req.getRequestURL();

            String dir = null;
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream("web_services.xml");
            prop.loadFromXML(fis);
            dir = prop.getProperty("dir_publicar");

            RegistroAccesoWSService ser = new RegistroAccesoWSService(new URL("http://"+dir+":8280/registroAccesoWS?wsdl"),
                    new QName("http://WebServices/", "RegistroAccesoWSService"));
            RegistroAccesoWS regAccWS = ser.getRegistroAccesoWSPort();

            regAccWS.registrarAcceso(browser_SO,ip,url.toString());
        }
        chain.doFilter(request, response);

    }

    public void destroy() {

    }


}
