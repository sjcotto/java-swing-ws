/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MensajeForo.java
 *
 * Created on Nov 3, 2011, 4:52:27 PM
 */

package dispositivomovil.Pencas;


import WebServices.DispMovilWS.DataInfoPenca;
import WebServices.DispMovilWS.DataInfoUsuarioPenca;
import WebServices.DispMovilWS.DataMensajePenca;
import WebServices.DispMovilWS.DispMovilWS;
import WebServices.DispMovilWS.DispMovilWSService;
import dispositivomovil.DispositivoMovil;
import dispositivomovil.objetos.Competicion;
import dispositivomovil.objetos.Mensaje;
import dispositivomovil.objetos.Penca;
import dispositivomovil.objetos.PosicionPenca;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author tprog084
 */
public class MensajeForo extends javax.swing.JPanel {

    /** Creates new form MensajeForo */
    public MensajeForo(JFrame m,int idPenca) {
        initComponents();

        this.setSize(320,420);


        this.dispMov = (DispositivoMovil) m;
         this.idPenca=idPenca;
         this.jLabelError.setVisible(false);
         nick="";
          String [] s = {"Nick"};
        List<DataInfoUsuarioPenca> iup=null;
         if (dispMov.online){
                try{
                    Properties prop = new Properties();
                    FileInputStream fis = new FileInputStream("web_services.xml");
                    prop.loadFromXML(fis);
                    String dir = prop.getProperty("dir_publicar");
                    DispMovilWSService dms = new DispMovilWSService(new java.net.URL("http://"+dir+":8280/dispMovilWS?wsdl"),new javax.xml.namespace.QName("http://WebServices/", "DispMovilWSService"));
                    DispMovilWS disp = dms.getDispMovilWSPort();
               
                  DataInfoPenca info= disp.verTablaPosicionesPenca(this.dispMov.nickUsuLogueado,idPenca);
                  iup=info.getTablaPosiciones();
                

                   List<String> nicks = new ArrayList<String>();
                   
                   for (int i =0;i< iup.size();i++) {
                        if(!this.dispMov.nickUsuLogueado.equals(iup.get(i).getNick()))
                            nicks.add(iup.get(i).getNick());
                   }
                   Object[][] tabla = new Object[nicks.size()][1];
                    for (int i =0;i< nicks.size();i++) {
                        tabla[i][0]= (String)nicks.get(i);
                    }
                    TableModel dtm = new DefaultTableModel(tabla,s);
                    this.tablaUss.setModel(dtm);
               }
               catch(Exception e){
                    this.jLabelError.setText("Error: Se perdio la conexion");
                    dispMov.online = false;
                    ImageIcon imIcon= new ImageIcon("images/desconectado.png");
                    this.dispMov.jLabel1.setIcon(new ImageIcon(imIcon.getImage().getScaledInstance(20,20,20)));
                    dispMov.timer.start();

                    Penca p= this.dispMov.em.find(Penca.class, ((Competicion)this.dispMov.em.find(Competicion.class, idPenca)).getPenca().getId());
                    List<PosicionPenca> diup=p.getPos();
                    List<String> nicks = new ArrayList<String>();
                     
                     for (int i =0;i< diup.size();i++) {
                        if(!this.dispMov.nickUsuLogueado.equals(diup.get(i).getNick()))
                            nicks.add(diup.get(i).getNick());
                     }
                    Object[][] tabla = new Object[nicks.size()][1];
                      for (int i =0;i< nicks.size();i++) {
                            tabla[i][0]= (String)nicks.get(i);
                   }
                     TableModel dtm = new DefaultTableModel(tabla,s);
                    this.tablaUss.setModel(dtm);
           }

        }else{
             Penca p= this.dispMov.em.find(Penca.class, ((Competicion)this.dispMov.em.find(Competicion.class, idPenca)).getPenca().getId());
             List<PosicionPenca> diup=p.getPos();
           
             List<String> nicks = new ArrayList<String>();
            for (int i =0;i< diup.size();i++) {
                        if(!this.dispMov.nickUsuLogueado.equals(diup.get(i).getNick()))
                            nicks.add(diup.get(i).getNick());
                     }
              Object[][] tabla = new Object[nicks.size()][1];
             for (int i =0;i< nicks.size();i++) {
                  tabla[i][0]= (String)nicks.get(i);
              }
            TableModel dtm = new DefaultTableModel(tabla,s);
            this.tablaUss.setModel(dtm);
        }

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUss = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jLabelError = new javax.swing.JLabel();

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(320, 420));

        tablaUss.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaUss.setShowHorizontalLines(false);
        tablaUss.setShowVerticalLines(false);
        tablaUss.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaUssMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaUss);

        jButton1.setBackground(java.awt.Color.darkGray);
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Ver Foro");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Mensaje");

        jButton2.setBackground(java.awt.Color.darkGray);
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Enviar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton1.setText("Privado");
        jRadioButton1.setOpaque(false);

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton2.setSelected(true);
        jRadioButton2.setText("Publico");
        jRadioButton2.setOpaque(false);

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Mensajes");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(52, 52, 52))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelError, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addComponent(jButton1)
                .addGap(29, 29, 29)
                .addComponent(jLabelError, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tablaUssMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUssMouseClicked
        // TODO add your handling code here:

          fila = this.tablaUss.rowAtPoint(evt.getPoint());
        columna = this.tablaUss.columnAtPoint(evt.getPoint());

        if ((fila > -1) && (columna > -1)){
            nick =(String) this.tablaUss.getValueAt(fila,0);

        }
    }//GEN-LAST:event_tablaUssMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        String mensaje = this.jTextArea1.getText();
         boolean estado;
        if (this.jRadioButton1.isSelected())
                    estado=false;
                else
                    estado=true;

        
        if (this.dispMov.online){
            try{
                Properties prop = new Properties();
                FileInputStream fis = new FileInputStream("web_services.xml");
                prop.loadFromXML(fis);
                String dir = prop.getProperty("dir_publicar");
                
                DispMovilWSService dms = new DispMovilWSService(new java.net.URL("http://"+dir+":8280/dispMovilWS?wsdl"),new javax.xml.namespace.QName("http://WebServices/", "DispMovilWSService"));
                DispMovilWS disp = dms.getDispMovilWSPort();
                if (estado) nick="";
                disp.enviarMensaje(idPenca,this.dispMov.nickUsuLogueado ,nick,estado,mensaje);

//                // Lo persisto por las dudas que pierda la conexion al ver el foro
//                this.dispMov.em.getTransaction().begin();
//                Penca p= this.dispMov.em.find(Penca.class, ((Competicion)this.dispMov.em.find(Competicion.class, idPenca)).getPenca().getId());
//                Mensaje m = new Mensaje();
//                m.setEmisor(this.dispMov.nickUsuLogueado);
//                m.setMessage(mensaje);
//                m.setPublico(estado);
//                if (estado) nick="";
//                m.setReceptor(nick);
//                m.setToSend(true);
//
//                m.setFecha(new java.sql.Date(this.dispMov.fechaHora.getAnio(),this.dispMov.fechaHora.getMes(),this.dispMov.fechaHora.getDia()));
//
//                 List<Mensaje> mens = p.getMensajes();
//                 mens.add(m);
//                 p.setMensajes(mens);
//                 this.dispMov.em.persist(m);
//                 this.dispMov.em.getTransaction().commit();

                this.jLabelError.setText("Mensaje Enviado con Exito");
                this.jLabelError.setVisible(true);
                this.jTextArea1.setText("");
            }
            catch(Exception e){
                this.jLabelError.setText("Error: Se perdio la conexion");
                dispMov.online = false;
                ImageIcon imIcon= new ImageIcon("images/desconectado.png");
                this.dispMov.jLabel1.setIcon(new ImageIcon(imIcon.getImage().getScaledInstance(20,20,20)));
                dispMov.timer.start();

                 this.dispMov.em.getTransaction().begin();
                 Penca p= this.dispMov.em.find(Penca.class, ((Competicion)this.dispMov.em.find(Competicion.class, idPenca)).getPenca().getId());
                 Mensaje m = new Mensaje();
                 m.setEmisor(this.dispMov.nickUsuLogueado);
                 m.setMessage(mensaje);
                 m.setPublico(estado);
                 if (estado) nick="";
                 m.setReceptor(nick);
                 m.setToSend(true);
                 m.setFecha(new java.sql.Date(this.dispMov.fechaHora.getAnio(),this.dispMov.fechaHora.getMes(),this.dispMov.fechaHora.getDia()));

                 List<Mensaje> mens = p.getMensajes();
                 mens.add(m);
                 p.setMensajes(mens);
                 this.dispMov.em.persist(m);
                 this.jLabelError.setText("El Mensaje se enviara al retornar conexion");
                 this.jLabelError.setVisible(true);
                 this.dispMov.em.getTransaction().commit();

            }
        }else{
             this.dispMov.em.getTransaction().begin();
             Penca p= this.dispMov.em.find(Penca.class, ((Competicion)this.dispMov.em.find(Competicion.class, idPenca)).getPenca().getId());
             Mensaje m = new Mensaje();
             m.setEmisor(this.dispMov.nickUsuLogueado);
             m.setMessage(mensaje);
             m.setPublico(estado);
             if (estado) nick="";
             m.setReceptor(nick);
             m.setToSend(true);
             m.setFecha(new java.sql.Date(this.dispMov.fechaHora.getAnio(),this.dispMov.fechaHora.getMes(),this.dispMov.fechaHora.getDia()));

             List<Mensaje> mens = p.getMensajes();
             mens.add(m);
             p.setMensajes(mens);
             this.dispMov.em.persist(m);
            this.jLabelError.setText("El Mensaje se enviara al retornar conexion");
            this.jLabelError.setVisible(true);
            this.dispMov.em.getTransaction().commit();
        }

        

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        VerForoPenca ver = new VerForoPenca(this.dispMov,idPenca);
       this.dispMov.jDesktopPane1.add(ver);
        ver.setVisible(true);
        ver.setSize(320,420);
         this.removeAll();
        this.dispMov.jDesktopPane1.remove(this);

    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelError;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTable tablaUss;
    // End of variables declaration//GEN-END:variables

    private DispositivoMovil dispMov;
    private String nick;
    private int fila,columna,idPenca;
}
