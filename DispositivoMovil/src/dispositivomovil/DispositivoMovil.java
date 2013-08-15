/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DispositivoMovil.java
 *
 * Created on 02/11/2011, 10:41:26 PM
 */

package dispositivomovil;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.Timer;

import WebServices.DispMovilWS.DataIdNombre;
import WebServices.DispMovilWS.DataInfoPartido;
import WebServices.DispMovilWS.DataInfoPenca;
import WebServices.DispMovilWS.DataInfoUsuarioPenca;
import WebServices.DispMovilWS.DataMensajePenca;
import WebServices.DispMovilWS.DispMovilWS;
import WebServices.DispMovilWS.DispMovilWSService;
import WebServices.MostrarFecha1.MostrarFecha1;
import WebServices.MostrarFecha1.MostrarFecha1Service;
import dispositivomovil.Apuestas.*;
import dispositivomovil.Pencas.*;
import dispositivomovil.objetos.*;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Santiago
 */
public class DispositivoMovil extends javax.swing.JFrame {

    public void obtenerInformacionServidor() {
        //obtenemos la informacion de las pencas y las seteamos a la bd
        try {
            System.out.println("TIMER INFORMACION");
            String dir = null;
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream("web_services.xml");
            prop.loadFromXML(fis);
            dir = prop.getProperty("dir_publicar");

            DispMovilWSService dms = new DispMovilWSService(new java.net.URL("http://"+dir+":8280/dispMovilWS?wsdl"),
                         new javax.xml.namespace.QName("http://WebServices/", "DispMovilWSService"));

            DispMovilWS disp = dms.getDispMovilWSPort();

            List<DataIdNombre> list = disp.listarCompetencias().getList();

            if (!this.emf.isOpen()) {
                this.emf = Persistence.createEntityManagerFactory("DispositivoMovil1PU");
                this.em = emf.createEntityManager();
            }

            MostrarFecha1Service mfs = new MostrarFecha1Service(new java.net.URL("http://"+dir+":8280/mostrarFecha1?wsdl"),
                    new javax.xml.namespace.QName("http://WebServices/", "MostrarFecha1Service"));
            MostrarFecha1 wsFecha = mfs.getMostrarFecha1Port();

            this.fechaHora = wsFecha.getFecha();
            this.jLabelFechaHora.setText(this.fechaHora.getStr());

            this.em.getTransaction().begin();

            for (DataIdNombre a : list){
                Competicion tt = this.em.find(Competicion.class, a.getId());
                if (tt==null){
                    Competicion nuevo = new Competicion();
                    nuevo.setId(a.getId());
                    nuevo.setNombre(a.getNombre());
                    //nuevo.setPenca(null);
                    this.em.persist(nuevo);
                    //this.em.flush();
                }
            }
            this.em.getTransaction().commit();

            if (!this.nickUsuLogueado.equals("")) {
                List<DataInfoPenca> l = disp.obtenerInfoPencas(this.nickUsuLogueado).getList();

                for(DataInfoPenca data : l){
                    int idComp = data.getIdComp();

                    Competicion comp = this.em.find(Competicion.class, idComp);
                    boolean actualiza = false;
                    if (comp.getPenca()!=null)
                        actualiza=true;

                    Penca p = new Penca();

                    //creamos la instancia de Penca y la asociamos a comp
                    p.setFinalizada(data.isFinalizada());
                    p.setMonto(data.getMontoMin());
                    p.setPozo(data.getPozo());
                    p.setParticipa(data.isParticipaUsuario());

                    // Vemos la tabla de posiciones de la penca
                    List<DataInfoUsuarioPenca> poss = data.getTablaPosiciones();
                    List<PosicionPenca> listaPaPenca = new ArrayList<PosicionPenca>();

                    for (DataInfoUsuarioPenca data2 : poss){
                        PosicionPenca posPenc = new PosicionPenca();
                        posPenc.setNick(data2.getNick());
                        posPenc.setPuntos(data2.getPuntos());

                        listaPaPenca.add(posPenc);
                    }
                    p.setPos(listaPaPenca);

                    // Vemos los mensajes de la penca
                    List<DataMensajePenca> listaM = data.getMensajes();
                    List<Mensaje> listaPa = new ArrayList<Mensaje>();

                    // Para que no se repitan los mensjaes
                    p.getMensajes().clear();

                    for(DataMensajePenca data3 : listaM){
                        Mensaje m1 = new Mensaje();
                        m1.setEmisor(data3.getEmisor());
                        m1.setMessage(data3.getMensaje());
                        m1.setPublico(data3.isPublico());
                        m1.setReceptor(data3.getReceptor());
                        m1.setToSend(false);
                        WebServices.DispMovilWS.DataFechaHora fecha = data3.getFecha();

                        java.sql.Date f = new java.sql.Date(fecha.getAnio(),fecha.getMes(),fecha.getDia());
                        m1.setFecha(f);

                        listaPa.add(m1);//
                    }
                    p.setMensajes(listaPa);

                    // Vemos los partidos que el usuario puede apostar
                    List<DataInfoPartido> l3 = new ArrayList<DataInfoPartido>();

                    p.getPartidos().clear();

                    if (data.isParticipaUsuario()) {
                        if (!data.isFinalizada()) {
                            l3 = disp.obtenerPartidosNoFinalizadosPenca(this.nickUsuLogueado, idComp).getList();

                            for (DataInfoPartido data4 : l3){
                                Partido pp = new Partido();
                                pp.setFechaHora(data4.getFechaHora().getStr());
                                pp.setId(data4.getIdPar());
                                pp.setLugar(data4.getLugar());

                                pp.setIdEqL(data4.getIdLocal());
                                pp.setIdEqV(data4.getIdVisita());
                                pp.setNombreEqL(data4.getNomLocal());
                                pp.setNombreEqV(data4.getNomVisita());

                                p.getPartidos().add(pp);

                            }
                        }
                    }

                    Penca borrame;

                    if (actualiza){
                        this.em.getTransaction().begin();
                        borrame  = this.em.find(Penca.class,comp.getPenca().getId());
                        comp.setPenca(null);
                        this.em.remove(borrame);
                        //this.em.flush();//mm
                        this.em.getTransaction().commit();
                    }

                    comp.setPenca(p);

                    this.em.getTransaction().begin();
                    this.em.persist(p);
                    //this.em.flush();//mm
                    this.em.getTransaction().commit();
                    
                }
            }
            
            this.timerInfoServ.setRepeats(true);
            this.timerInfoServ.start();
        }
        catch(Exception exc){
            if (exc.getMessage().contains("WSDL")) {
                this.timer.start();
                this.timerInfoServ.stop();
                online = false;
                ImageIcon imIcon= new ImageIcon("images/desconectado.png");
                jLabel1.setIcon(new ImageIcon(imIcon.getImage().getScaledInstance(20,20,20)));
            }else{
                javax.swing.JOptionPane.showMessageDialog(this, "Error inesperado   "+exc.getCause().getMessage(), "BORRAR", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void conectarWS(){
        try{
            System.out.println("TIMER");
            // Pedimos la informacion al ServidorCentral
            String dir = null;
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream("web_services.xml");
            prop.loadFromXML(fis);
            dir = prop.getProperty("dir_publicar");

            DispMovilWSService dms = new DispMovilWSService(new java.net.URL("http://"+dir+":8280/dispMovilWS?wsdl"),
                    new javax.xml.namespace.QName("http://WebServices/", "DispMovilWSService"));
            DispMovilWS disp = dms.getDispMovilWSPort();

            MostrarFecha1Service mfs = new MostrarFecha1Service(new java.net.URL("http://"+dir+":8280/mostrarFecha1?wsdl"),
                    new javax.xml.namespace.QName("http://WebServices/", "MostrarFecha1Service"));
            MostrarFecha1 wsFecha = mfs.getMostrarFecha1Port();

            this.fechaHora = wsFecha.getFecha();
            this.jLabelFechaHora.setText(this.fechaHora.getStr());

            // Tenemos que enviar la informacion nueva al sistema
            this.actualizarDatos();

            online = true;
            ImageIcon imIcon= new ImageIcon("images/conectado.png");
            this.jLabel1.setIcon(new ImageIcon(imIcon.getImage().getScaledInstance(20,20,20)));

            if (this.timerInfoServ!=null)
                timerInfoServ.stop();

            //el tiempo ahora esta en 5 segundo, lo toma en milisegundos
            //leemos del archivo de configuracion el tiempo
            int tiempoInf;
            try {
                tiempoInf = Integer.valueOf(prop.getProperty("tiempoInformacion"));
            }
            catch(Exception exc){
                tiempoInf = 30;
            }

            this.timerInfoServ = new Timer (tiempoInf*1000, new ActionListener () {
                public void actionPerformed(ActionEvent e) {
                    obtenerInformacionServidor();
                 }
            });

            timer.stop();
            this.timerInfoServ.start();
        }catch(Exception e){
            // Si se perdio la conexion
            online = false;
            ImageIcon imIcon= new ImageIcon("images/desconectado.png");
            jLabel1.setIcon(new ImageIcon(imIcon.getImage().getScaledInstance(20,20,20)));
            timer.setRepeats(true);//para que se repita
            timer.start();//si en otro momento se conecta por otra cosa, hay q
        }
    }

    public void actualizarDatos(){

        try{
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream("web_services.xml");
            prop.loadFromXML(fis);
            String dir = prop.getProperty("dir_publicar");
            DispMovilWSService dms = new DispMovilWSService(new java.net.URL("http://"+dir+":8280/dispMovilWS?wsdl"),
            new javax.xml.namespace.QName("http://WebServices/", "DispMovilWSService"));

            DispMovilWS disp = dms.getDispMovilWSPort();

            List<Competicion> comps = new ArrayList<Competicion>();
            comps = (List<Competicion>)em.createQuery("Select c from Competicion c").getResultList();
            
            for(Competicion c : comps){
                Penca penca = c.getPenca();
                if (penca!=null){
                    List<Partido> parts = c.getPenca().getPartidos();
                    List<Mensaje> mens = c.getPenca().getMensajes();
                    for(Partido p: parts){
                        if (p.isAposto()){
                            try{
                                disp.obtenerPartidosNoFinalizadosPenca(this.nickUsuLogueado, c.getId());
                                disp.apostarPartidoPenca(this.nickUsuLogueado,p.getId(), p.getGolesLoc(), p.getGolesVis());
                                p.setAposto(false);
                                em.getTransaction().begin();
                                em.merge(p);
                                em.getTransaction().commit();
                            }
                            catch(Exception e){
                                System.out.println(e.getMessage());
                            }

                        }
                    }
                    for(Mensaje m: mens){
                        if (m.isToSend()){
                            try{

                                disp.enviarMensaje(c.getId(),m.getEmisor(),m.getReceptor() ,m.getPublico(),m.getMessage());
                                m.setToSend(false);
                                em.getTransaction().begin();
                                em.merge(m);
                                em.getTransaction().commit();
                            }
                            catch(Exception e){
                                System.out.println(e.getMessage());
                            }
                        }
                    }
                }

            }

             

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    /** Creates new form DispositivoMovil */
    public DispositivoMovil() {
        initComponents();

        this.emf = Persistence.createEntityManagerFactory("DispositivoMovil1PU");
        this.em = emf.createEntityManager();

        jLabel1.setText("");
        this.botonAtras.setVisible(false);
        this.nickUsuLogueado = "";
        this.jLabelFechaHora.setText("");

        ImageIcon ic = new ImageIcon("images/home.png");
        ImageIcon im = new ImageIcon(ic.getImage().getScaledInstance(25,25,25));
        this.botonAtras.setIcon(im);
        this.botonAtras.setText("");

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method ispablito puto
     * always regenerated by the Form Editor. 
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane2 = new Fondo("images/fondoDisp.jpg");
        jDesktopPane1 = new Fondo("images/fondo.jpg");
        botonApagar = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        botonAtras = new javax.swing.JButton();
        jLabelFechaHora = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Laboratorio 4 - Grupo 17");
        setBackground(new java.awt.Color(0, 0, 0));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jDesktopPane1.setSize(320, 480);
        jDesktopPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jDesktopPane1.setBounds(27, 133, 325, 460);
        jDesktopPane2.add(jDesktopPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonApagar.setOpaque(false);
        botonApagar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonApagarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout botonApagarLayout = new javax.swing.GroupLayout(botonApagar);
        botonApagar.setLayout(botonApagarLayout);
        botonApagarLayout.setHorizontalGroup(
            botonApagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        botonApagarLayout.setVerticalGroup(
            botonApagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        botonApagar.setBounds(130, 600, 110, 80);
        jDesktopPane2.add(botonApagar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel1.setText("jLabel1");

        botonAtras.setBackground(new java.awt.Color(0, 0, 0));
        botonAtras.setForeground(new java.awt.Color(255, 255, 255));
        botonAtras.setText("Menu");
        botonAtras.setFocusable(false);
        botonAtras.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonAtras.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAtrasActionPerformed(evt);
            }
        });

        jLabelFechaHora.setForeground(new java.awt.Color(255, 255, 255));
        jLabelFechaHora.setText("jLabel2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                .addComponent(jLabelFechaHora)
                .addGap(49, 49, 49)
                .addComponent(botonAtras)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabelFechaHora))
                    .addComponent(botonAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBounds(27, 109, 325, 25);
        jDesktopPane2.add(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAtrasActionPerformed

        // Borro todo jpanel que no sirve
        Component[] componentes = this.jDesktopPane1.getComponents();
        for (int i=0; i<componentes.length; i++) {
            if (componentes[i] instanceof InicioSesion ||
                componentes[i] instanceof MenuPrincipal ||
                componentes[i] instanceof Perfil ||
                componentes[i] instanceof ApostarCampeon ||
                componentes[i] instanceof ApostarGoleadores ||
                componentes[i] instanceof Competiciones ||
                componentes[i] instanceof ConfirmarApuesta ||
                componentes[i] instanceof Partidos ||
                componentes[i] instanceof VerApuestas ||
                componentes[i] instanceof VerPaquete ||
                componentes[i] instanceof ApostarPenca ||
                componentes[i] instanceof MensajeForo ||
                componentes[i] instanceof ParticiparEnPenca ||
                componentes[i] instanceof TablaPenca ||
                componentes[i] instanceof VerForoPenca ) {

                this.jDesktopPane1.remove(componentes[i]);
            }
        }

        MenuPrincipal mp = new MenuPrincipal(this);
        this.jDesktopPane1.add(mp);
        mp.setVisible(true);
        mp.setSize(320, 420);

    }//GEN-LAST:event_botonAtrasActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        //conectarWS();
        try{
            if (this.emf.isOpen()) {
                this.em.close();
                this.emf.close();
            }

        //persistimos, recorremos comp
        }catch(Exception e ){
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_formWindowClosing

    private void botonApagarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonApagarMouseClicked
        // TODO add your handling code here:

        if (!prendido){
            if (!this.emf.isOpen()) {
                this.emf = Persistence.createEntityManagerFactory("DispositivoMovil1PU");
                this.em = emf.createEntityManager();
            }

            //el tiempo ahora esta en 5 segundo, lo toma en milisegundos
            //leemos del archivo de configuracion el tiempo
            int t;
            try{
                Properties propiedad = new Properties();
                FileInputStream fis = new FileInputStream("web_services.xml");
                propiedad.loadFromXML(fis);
                t = Integer.valueOf(propiedad.getProperty("tiempo"));
                //JOptionPane.showMessageDialog(rootPane, fis, nickUsuLogueado, WIDTH);
            }catch(Exception e){
                t = 5;//valor por defecto
            }

            timer = new Timer (t*1000, new ActionListener ()
            {
                public void actionPerformed(ActionEvent e)
                {

                    conectarWS();
                 }
            });


            conectarWS();
            this.prendido = true;
            this.botonAtras.setVisible(true);

            MenuPrincipal menu = new MenuPrincipal(this);
            this.jDesktopPane1.add(menu);

            menu.setVisible(true);
            menu.setSize(320, 480);

        }else{
            this.prendido = false;
            this.jLabel1.setIcon(null);
            this.jLabelFechaHora.setText("");

            this.timer.stop();
            this.timerInfoServ.stop();

            this.jLabel1.setText("");
            this.botonAtras.setVisible(false);
            // Borro todo jpanel que no sirve
            Component[] componentes = this.jDesktopPane1.getComponents();
            for (int i=0; i<componentes.length; i++) {
                if (componentes[i] instanceof InicioSesion ||
                    componentes[i] instanceof MenuPrincipal ||
                    componentes[i] instanceof Perfil ||
                    componentes[i] instanceof ApostarCampeon ||
                    componentes[i] instanceof ApostarGoleadores ||
                    componentes[i] instanceof Competiciones ||
                    componentes[i] instanceof ConfirmarApuesta ||
                    componentes[i] instanceof Partidos ||
                    componentes[i] instanceof VerApuestas ||
                    componentes[i] instanceof VerPaquete ||
                    componentes[i] instanceof ApostarPenca ||
                    componentes[i] instanceof MensajeForo ||
                    componentes[i] instanceof ParticiparEnPenca ||
                    componentes[i] instanceof TablaPenca ||
                    componentes[i] instanceof VerForoPenca ) {
                    
                    this.jDesktopPane1.remove(componentes[i]);
                }
            }

            this.jDesktopPane1.repaint();
            try{
                this.em.close();
                this.emf.close();
            }catch(Exception e ){
                System.out.println(e.getMessage());
            }


        }
    }//GEN-LAST:event_botonApagarMouseClicked


    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DispositivoMovil d = new DispositivoMovil();
                d.setVisible(true);
                //d.setSize(365, 720);
                d.setResizable(false);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel botonApagar;
    public javax.swing.JButton botonAtras;
    public javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JDesktopPane jDesktopPane2;
    public javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelFechaHora;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    public boolean online = false;

    public Timer timer;
    public Timer timerInfoServ;

    public boolean prendido = false;
    public String nickUsuLogueado;
    public WebServices.MostrarFecha1.DataFechaHora fechaHora;
    public EntityManagerFactory emf = null;
    public EntityManager em = null;
}
