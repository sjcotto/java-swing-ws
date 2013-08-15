/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WebServices;


import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.xml.ws.Endpoint;
import java.util.logging.Logger;

import Interface.Fabrica;
import Interface.IControladorFecha;
import java.io.FileInputStream;
import java.util.Properties;


@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class MostrarFecha1 {

    private Endpoint endpoint = null;
 
    private Logger log = Logger.getLogger(MostrarFecha1.class.getName());

    @WebMethod
    public DataTypes.DataFechaHora getFecha() {
        Fabrica f = new Fabrica();
        IControladorFecha icf = f.getIControladorFecha();

        return icf.getFecha();
    }

    @WebMethod(exclude = true)
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


            endpoint = Endpoint.publish("http://"+dir+":8280/mostrarFecha1", this);
            log.info("endpoint MostrarFecha publicado en http://"+dir+":8280/mostrarFecha1");
	}

	@WebMethod(exclude = true)
	public Endpoint getEndpoint() {
            return endpoint;
	}
}
