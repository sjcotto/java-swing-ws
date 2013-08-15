/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ParticiparEnPenca.java
 *
 * Created on Nov 3, 2011, 3:42:56 PM
 */

package dispositivomovil.Pencas;

import WebServices.DispMovilWS.DataFechaHora;
import WebServices.DispMovilWS.DataIdNombre;
import WebServices.DispMovilWS.DataInfoPartido;
import WebServices.DispMovilWS.DataInfoPenca;
import WebServices.DispMovilWS.DataInfoUsuarioPenca;
import WebServices.DispMovilWS.DataMensajePenca;
import WebServices.DispMovilWS.DispMovilWS;
import WebServices.DispMovilWS.DispMovilWSService;
import dispositivomovil.DispositivoMovil;
import dispositivomovil.objetos.Competicion;
import dispositivomovil.objetos.Mensaje;
import dispositivomovil.objetos.Partido;
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
public class ParticiparEnPenca extends javax.swing.JPanel {


    /** Creates new form ParticiparEnPenca */
    public ParticiparEnPenca(JFrame m) {
        initComponents();
        this.setSize(320,420);
        this.jLabelError.setText("");

        this.dispMov = (DispositivoMovil) m;
        String [] s = {"Nombre","Monto"};

        if (((DispositivoMovil)m).online){
            try{
                Properties prop = new Properties();
                FileInputStream fis = new FileInputStream("web_services.xml");
                prop.loadFromXML(fis);
                String dir = prop.getProperty("dir_publicar");

                DispMovilWSService dms = new DispMovilWSService(new java.net.URL("http://"+dir+":8280/dispMovilWS?wsdl"),
                        new javax.xml.namespace.QName("http://WebServices/", "DispMovilWSService"));
                DispMovilWS disp = dms.getDispMovilWSPort();

                List<DataInfoPenca> lista = (List)disp.obtenerInfoPencas(this.dispMov.nickUsuLogueado).getList();

                nueva = new ArrayList<Competicion>();
                for (DataInfoPenca dataIP: lista) {
                    Competicion comp = new Competicion();
                    comp.setNombre(dataIP.getNombre());
                    comp.setId(dataIP.getIdComp());
                    Penca p = new Penca();
                    p.setMonto(dataIP.getMontoMin());
                    p.setPozo(dataIP.getPozo());
                    p.setFinalizada(dataIP.isFinalizada());
                    p.setParticipa(dataIP.isParticipaUsuario());
                    comp.setPenca(p);
                    nueva.add(comp);
                }

                Object[][] tabla = new Object[nueva.size()][2];
                for (int i =0;i< nueva.size();i++) {
                    tabla[i][0]= nueva.get(i).getNombre();
                    tabla[i][1]= nueva.get(i).getPenca().getMonto();
                }

                TableModel dtm = new DefaultTableModel(tabla,s);
                this.tablaPenca.setModel(dtm);

            }
        catch(Exception e){
            this.jLabelError.setText("Error: Se perdio la conexion");
             dispMov.online = false;
             ImageIcon imIcon= new ImageIcon("images/desconectado.png");
             this.dispMov.jLabel1.setIcon(new ImageIcon(imIcon.getImage().getScaledInstance(20,20,20)));
             dispMov.timer.start();


             List comps = this.dispMov.em.createQuery("Select c from Competicion c").getResultList();
            // pencas = this.dispMov.em.createQuery("Select p from Penca p").getResultList();

             nueva = new ArrayList<Competicion>();
             for (int i =0;i< comps.size();i++) {
                    if (((Competicion)(comps.get(i))).getPenca()!=null){
                        nueva.add((Competicion)comps.get(i));
                     }
            }
             Object[][] tabla = new Object[nueva.size()][2];
            for (int i =0;i< nueva.size();i++) {

                tabla[i][0]= nueva.get(i).getNombre();
                tabla[i][1]= nueva.get(i).getPenca().getMonto();
            }
            TableModel dtm = new DefaultTableModel(tabla,s);
            this.tablaPenca.setModel(dtm);
        }
        }else{
            List comps = this.dispMov.em.createQuery("Select c from Competicion c").getResultList();
            nueva = new ArrayList<Competicion>();
             for (int i =0;i< comps.size();i++) {
                    if (((Competicion)(comps.get(i))).getPenca()!=null){
                        nueva.add((Competicion)comps.get(i));
                     }
            }
             Object[][] tabla = new Object[nueva.size()][2];
            for (int i =0;i< nueva.size();i++) {

                tabla[i][0]= nueva.get(i).getNombre();
                tabla[i][1]= nueva.get(i).getPenca().getMonto();
            }
            TableModel dtm = new DefaultTableModel(tabla,s);
            this.tablaPenca.setModel(dtm);
        }

       
        jButton1.setEnabled(false);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
        jButton4.setEnabled(false);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPenca = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabelError = new javax.swing.JLabel();

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(320, 420));
        setVerifyInputWhenFocusTarget(false);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Pencas");

        tablaPenca.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaPenca.setOpaque(false);
        tablaPenca.setShowHorizontalLines(false);
        tablaPenca.setShowVerticalLines(false);
        tablaPenca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPencaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaPenca);

        jButton1.setBackground(java.awt.Color.darkGray);
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Participar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(java.awt.Color.darkGray);
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Apostar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(java.awt.Color.darkGray);
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Ver Tabla");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(java.awt.Color.darkGray);
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Mensajes");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabelError.setForeground(new java.awt.Color(255, 51, 51));
        jLabelError.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jButton4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabelError)))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(109, 109, 109)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGap(54, 54, 54)
                .addComponent(jLabelError)
                .addGap(24, 24, 24))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:


        try{
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream("web_services.xml");
            prop.loadFromXML(fis);
            String dir = prop.getProperty("dir_publicar");
            

            DispMovilWSService dms = new DispMovilWSService(new java.net.URL("http://"+dir+":8280/dispMovilWS?wsdl"),new javax.xml.namespace.QName("http://WebServices/", "DispMovilWSService"));
            DispMovilWS disp = dms.getDispMovilWSPort();

            boolean ok = disp.participarEnPenca(this.dispMov.nickUsuLogueado, idPenca);
            disp.confirmarParticipacion(this.dispMov.nickUsuLogueado, ok);
            
            if (ok){
                Competicion c = ((Competicion)this.dispMov.em.find(Competicion.class, idPenca));
                if (c==null){
                    
                    // Si o si la penca es nueva, viene del servidor y no tengo en la base a la competicion
                    // La participa por las
                    this.dispMov.em.getTransaction().begin();
                    Competicion compNueva = new Competicion();
                    compNueva.setId(idPenca);
                    compNueva.setNombre(this.nombreComp);

                    Penca penca = new Penca();
                    penca.setMensajes(new ArrayList<Mensaje>());
                    penca.setMonto(this.pencaGuardada.getMonto());
                    penca.setParticipa(this.pencaGuardada.isParticipa());
                    penca.setFinalizada(this.pencaGuardada.isFinalizada());
                    penca.setPartidos(new ArrayList<Partido>());
                    penca.setPos(new ArrayList<PosicionPenca>());
                    penca.setPozo(this.pencaGuardada.getPozo());

                    compNueva.setPenca(penca);
                    
                    this.dispMov.em.persist(compNueva);
                    this.dispMov.em.getTransaction().commit();
                }


                ApostarPenca ap = new ApostarPenca(this.dispMov,idPenca);
                this.dispMov.jDesktopPane1.add(ap);
                ap.setVisible(true);
                ap.setSize(320,420);
                this.removeAll();
                this.dispMov.jDesktopPane1.remove(this);
            }
            else{
              this.jLabelError.setText("Saldo Insuficiente");
              this.jLabelError.setVisible(true);
            }


        }
        catch(Exception e){
            this.jLabelError.setText("Error: Se perdio la conexion");
            dispMov.online = false;
            ImageIcon imIcon= new ImageIcon("images/desconectado.png");
            this.dispMov.jLabel1.setIcon(new ImageIcon(imIcon.getImage().getScaledInstance(20,20,20)));
            dispMov.timer.start();

        }
        
   


    }//GEN-LAST:event_jButton1ActionPerformed

    private void tablaPencaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPencaMouseClicked
        // TODO add your handling code here:
         fila = this.tablaPenca.rowAtPoint(evt.getPoint());
        columna = this.tablaPenca.columnAtPoint(evt.getPoint());
       
        if ((fila > -1) && (columna > -1)){
            idPenca = nueva.get(fila).getId();
            this.nombreComp = (String)this.tablaPenca.getValueAt(fila,0);
            pencaGuardada = nueva.get(fila).getPenca();
        }
          
            if (this.dispMov.online){
                try{
                    Properties prop = new Properties();
                    FileInputStream fis = new FileInputStream("web_services.xml");
                    prop.loadFromXML(fis);
                    String dir = prop.getProperty("dir_publicar");

                    DispMovilWSService dms = new DispMovilWSService(new java.net.URL("http://"+dir+":8280/dispMovilWS?wsdl"),new javax.xml.namespace.QName("http://WebServices/", "DispMovilWSService"));
                    DispMovilWS disp = dms.getDispMovilWSPort();

                    DataInfoPenca dip = disp.verTablaPosicionesPenca(this.dispMov.nickUsuLogueado, idPenca);
                    if (dip.isParticipaUsuario()){
                        jButton1.setEnabled(false);
                        jButton2.setEnabled(true);
                        jButton3.setEnabled(true);
                        jButton4.setEnabled(true);


                    }
                 else{
                        jButton1.setEnabled(true);
                        jButton2.setEnabled(false);
                        jButton3.setEnabled(true);
                        jButton4.setEnabled(false);
                    }
                }
            catch(Exception e){

                this.jLabelError.setText("Error: Se perdio la conexion");
                dispMov.online = false;
                ImageIcon imIcon= new ImageIcon("images/desconectado.png");
                this.dispMov.jLabel1.setIcon(new ImageIcon(imIcon.getImage().getScaledInstance(20,20,20)));
                dispMov.timer.start();
                Penca dip= this.dispMov.em.find(Penca.class, ((Competicion)this.dispMov.em.find(Competicion.class, idPenca)).getPenca().getId());
                   if (dip.isParticipa()){
                        jButton1.setEnabled(false);
                        jButton2.setEnabled(true);
                        jButton3.setEnabled(true);
                        jButton4.setEnabled(true);
                   }
                   else{
                        jButton1.setEnabled(false);
                        jButton2.setEnabled(false);
                        jButton3.setEnabled(true);
                        jButton4.setEnabled(false);
                    }

                }
         }else{
             Penca dip= this.dispMov.em.find(Penca.class, ((Competicion)this.dispMov.em.find(Competicion.class, idPenca)).getPenca().getId());
                   if (dip.isParticipa()){
                        jButton1.setEnabled(false);
                        jButton2.setEnabled(true);
                        jButton3.setEnabled(true);
                        jButton4.setEnabled(true);
                   }
                   else{
                        jButton1.setEnabled(false);
                        jButton2.setEnabled(false);
                        jButton3.setEnabled(true);
                        jButton4.setEnabled(false);
                    }
         }
        this.repaint();

    }//GEN-LAST:event_tablaPencaMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        ApostarPenca ap = new ApostarPenca(this.dispMov,idPenca);
        this.dispMov.jDesktopPane1.add(ap);
        ap.setVisible(true);
        ap.setSize(320,420);
        
        //this.dispMov.getLayeredPane().remove(this);
//        this.dispMov.jDesktopPane1.remove(this);
        this.removeAll();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        TablaPenca tp = new TablaPenca(this.dispMov,idPenca);
        this.dispMov.jDesktopPane1.add(tp);
        tp.setVisible(true);
        tp.setSize(320,420);
        this.removeAll();
        this.dispMov.getLayeredPane().remove(this);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        MensajeForo mf = new MensajeForo(this.dispMov,idPenca);
        this.dispMov.jDesktopPane1.add(mf);
        mf.setVisible(true);
        mf.setSize(320,420);
        this.removeAll();
        this.dispMov.getLayeredPane().remove(this);
        
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelError;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaPenca;
    // End of variables declaration//GEN-END:variables

    private DispositivoMovil dispMov;
    private int fila, columna,idPenca;
    private String nombreComp;

    List<Competicion> nueva ;

    private Penca pencaGuardada= null;
}
