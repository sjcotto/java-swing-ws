
package WebServices.CompeticionesWS;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6
 * Generated source version: 2.1
 * 
 */
@WebFault(name = "ExDatosNoAsignados", targetNamespace = "http://WebServices/")
public class ExDatosNoAsignados_Exception
    extends java.lang.Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private ExDatosNoAsignados faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public ExDatosNoAsignados_Exception(String message, ExDatosNoAsignados faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public ExDatosNoAsignados_Exception(String message, ExDatosNoAsignados faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: WebServices.CompeticionesWS.ExDatosNoAsignados
     */
    public ExDatosNoAsignados getFaultInfo() {
        return faultInfo;
    }

}