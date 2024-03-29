
package WebServices.CompeticionesWS;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6
 * Generated source version: 2.1
 * 
 */
@WebFault(name = "ExNoExistePartidoEnCompeticion", targetNamespace = "http://WebServices/")
public class ExNoExistePartidoEnCompeticion_Exception
    extends java.lang.Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private ExNoExistePartidoEnCompeticion faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public ExNoExistePartidoEnCompeticion_Exception(String message, ExNoExistePartidoEnCompeticion faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public ExNoExistePartidoEnCompeticion_Exception(String message, ExNoExistePartidoEnCompeticion faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: WebServices.CompeticionesWS.ExNoExistePartidoEnCompeticion
     */
    public ExNoExistePartidoEnCompeticion getFaultInfo() {
        return faultInfo;
    }

}
