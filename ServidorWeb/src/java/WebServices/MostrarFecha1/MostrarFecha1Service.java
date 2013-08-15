
package WebServices.MostrarFecha1;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "MostrarFecha1Service", targetNamespace = "http://WebServices/", wsdlLocation = "http://pcunix124:8280/mostrarFecha1?wsdl")
public class MostrarFecha1Service
    extends Service
{

    private final static URL MOSTRARFECHA1SERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(WebServices.MostrarFecha1.MostrarFecha1Service.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = WebServices.MostrarFecha1.MostrarFecha1Service.class.getResource(".");
            url = new URL(baseUrl, "http://pcunix124:8280/mostrarFecha1?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://pcunix124:8280/mostrarFecha1?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        MOSTRARFECHA1SERVICE_WSDL_LOCATION = url;
    }

    public MostrarFecha1Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MostrarFecha1Service() {
        super(MOSTRARFECHA1SERVICE_WSDL_LOCATION, new QName("http://WebServices/", "MostrarFecha1Service"));
    }

    /**
     * 
     * @return
     *     returns MostrarFecha1
     */
    @WebEndpoint(name = "MostrarFecha1Port")
    public MostrarFecha1 getMostrarFecha1Port() {
        return super.getPort(new QName("http://WebServices/", "MostrarFecha1Port"), MostrarFecha1.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns MostrarFecha1
     */
    @WebEndpoint(name = "MostrarFecha1Port")
    public MostrarFecha1 getMostrarFecha1Port(WebServiceFeature... features) {
        return super.getPort(new QName("http://WebServices/", "MostrarFecha1Port"), MostrarFecha1.class, features);
    }

}
