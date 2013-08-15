/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WebServices;

import Interface.FabricaWeb;
import Interface.IControladorAccesosWeb;
import java.io.FileInputStream;
import java.util.Properties;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;

import javax.xml.ws.Endpoint;
import java.util.logging.Logger;

/**
 *
 * @author gonzalo
 */


@WebService
@SOAPBinding(style=Style.DOCUMENT, parameterStyle=ParameterStyle.WRAPPED)
public class RegistroAccesoWS {

    private Endpoint endpoint = null;

    private Logger log = Logger.getLogger(RegistroAccesoWS.class.getName());

    @WebMethod
    public void registrarAcceso(
            @WebParam (name="browser_SO", partName="browser_SO") String browser_SO,
            @WebParam (name="ip", partName="ip") String ip,
            @WebParam (name="url", partName="url") String url) {

        IControladorAccesosWeb iCA = (new FabricaWeb()).getIControladorAccesosWeb();
        System.out.println("************************* Registro ");
        iCA.registrarAcceso(browser_SO,ip,url);
        System.out.println("************************* Fin Registro ");
    }

    @WebMethod(exclude=true)
    public void publicar() {
        log.info("publicando el endpoint mf");

               String dir = null;

        try {
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream("web_services.xml");
            prop.loadFromXML(fis);
            prop.list(System.out);
            dir = prop.getProperty("dir_publicar");
            fis.close();

        }
        catch (Exception exc){
            System.out.println("Error leyendo archivo properties: "+exc.getMessage());
        }


        endpoint = Endpoint.publish("http://"+dir+":8280/registroAccesoWS", this);
        log.info("endpoint RegistroAccesoWS publicado en http://"+dir+":8280/registroAccesoWS");
    }

    @WebMethod(exclude=true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

}
