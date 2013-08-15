
package WebServices.RegistroAccesoWS;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the WebServices.RegistroAccesoWS package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RegistrarAccesoResponse_QNAME = new QName("http://WebServices/", "registrarAccesoResponse");
    private final static QName _RegistrarAcceso_QNAME = new QName("http://WebServices/", "registrarAcceso");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: WebServices.RegistroAccesoWS
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RegistrarAccesoResponse }
     * 
     */
    public RegistrarAccesoResponse createRegistrarAccesoResponse() {
        return new RegistrarAccesoResponse();
    }

    /**
     * Create an instance of {@link RegistrarAcceso }
     * 
     */
    public RegistrarAcceso createRegistrarAcceso() {
        return new RegistrarAcceso();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarAccesoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebServices/", name = "registrarAccesoResponse")
    public JAXBElement<RegistrarAccesoResponse> createRegistrarAccesoResponse(RegistrarAccesoResponse value) {
        return new JAXBElement<RegistrarAccesoResponse>(_RegistrarAccesoResponse_QNAME, RegistrarAccesoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarAcceso }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebServices/", name = "registrarAcceso")
    public JAXBElement<RegistrarAcceso> createRegistrarAcceso(RegistrarAcceso value) {
        return new JAXBElement<RegistrarAcceso>(_RegistrarAcceso_QNAME, RegistrarAcceso.class, null, value);
    }

}
