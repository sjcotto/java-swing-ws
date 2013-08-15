
package WebServices.DispMovilWS;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6
 * Generated source version: 2.1
 * 
 */
@WebFault(name = "ExDivsNoAsignados", targetNamespace = "http://WebServices/")
public class ExDivsNoAsignados_Exception
    extends java.lang.Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private ExDivsNoAsignados faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public ExDivsNoAsignados_Exception(String message, ExDivsNoAsignados faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public ExDivsNoAsignados_Exception(String message, ExDivsNoAsignados faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: WebServices.DispMovilWS.ExDivsNoAsignados
     */
    public ExDivsNoAsignados getFaultInfo() {
        return faultInfo;
    }

}