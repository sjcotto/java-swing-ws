
package WebServices.CompeticionesWS;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6
 * Generated source version: 2.1
 * 
 */
@WebFault(name = "ExNoExisteCompeticion", targetNamespace = "http://WebServices/")
public class ExNoExisteCompeticion_Exception
    extends java.lang.Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private ExNoExisteCompeticion faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public ExNoExisteCompeticion_Exception(String message, ExNoExisteCompeticion faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public ExNoExisteCompeticion_Exception(String message, ExNoExisteCompeticion faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: WebServices.CompeticionesWS.ExNoExisteCompeticion
     */
    public ExNoExisteCompeticion getFaultInfo() {
        return faultInfo;
    }

}