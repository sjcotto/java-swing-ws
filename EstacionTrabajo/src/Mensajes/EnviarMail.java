package Mensajes;


import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EnviarMail{
    
    private Properties props;
    private String host;
    private String protocolo;
    private int puerto;
    private String remitente;
    private String password;
    
    public EnviarMail(String host,String protocolo,int puerto,String remitente,String password){
        props = new Properties();
        this.host=host;
        this.protocolo=protocolo;
        this.puerto=puerto;
        this.remitente=remitente;
        this.password=password;
    }
    
   
     public EnviarMail(){

            props = new Properties();
            host = "smtp.fing.edu.uy";
            protocolo = "smtp";
            this.remitente="tprog081@fing.edu.uy";
            this.password="3C)60^!X";
    }
	
	
	
	
    public void mail(String receptor, String asunto,String mensage){
        
       
        
        try{
            props.setProperty("mail.smtp.host", host);
            props.setProperty("mail.smtp.user",remitente);
            props.setProperty("mail.smtp.auth", "true");
            props.setProperty("mail.smtp.password",password);
          

            Session session = Session.getDefaultInstance(props);
            

            
            session.setDebug(true);//borrar
            MimeMessage msg = new MimeMessage (session);
            msg.setFrom(new InternetAddress(remitente));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(receptor));
            msg.setSubject(asunto);
            msg.setText(mensage);

           

            Transport transporte = session.getTransport(protocolo);
            transporte.connect(remitente,password);
            transporte.sendMessage(msg,msg.getAllRecipients());
            transporte.close();  
            
        }
        catch(MessagingException e){
            System.out.println("envio fallido");
            e.printStackTrace();
        }catch(Exception ee){
            System.out.print(ee.getMessage());
        }
    }
    public String getEmail(String mailto){
            return mailto.substring(0,mailto.indexOf("?"));
    }
}
