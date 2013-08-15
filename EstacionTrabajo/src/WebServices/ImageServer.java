/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WebServices;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

/**
 * 
 * @author e408610
 */
@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class ImageServer {
	private Endpoint endpoint = null;
	private Logger log = Logger.getLogger(ImageServer.class.getName());

	@WebMethod
	public byte[] getImage(@WebParam(name = "fileName") String name)
			throws Exception {
		byte[] byteArray = null;
		try {
			log.info("Leyendo el archivo.");
			File f = new File(name);
			FileInputStream streamer = new FileInputStream(f);
			byteArray = new byte[streamer.available()];
			streamer.read(byteArray);
		} catch (IOException iOException) {
			log.log(Level.SEVERE, "Error al intentar leer el archivo.",
					iOException);
			throw iOException;
		}
		return byteArray;
	}

	@WebMethod(exclude = true)
	public void publicar() {
		log.info("publicando el endpoint calculator");

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


		endpoint = Endpoint.publish("http://"+dir+":8280/imageServer", this);
		log
				.info("endpoint calculator publicado en http://"+dir+":8280/imageServer");
	}

	@WebMethod(exclude = true)
	public Endpoint getEndpoint() {
		return endpoint;
	}

}