/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VerForoPenca.java
 *
 * Created on Nov 3, 2011, 4:48:49 PM
 */

package dispositivomovil.Pencas;

import WebServices.DispMovilWS.DataInfoPenca;
import WebServices.DispMovilWS.DataMensajePenca;
import WebServices.DispMovilWS.DispMovilWS;
import WebServices.DispMovilWS.DispMovilWSService;
import dispositivomovil.DispositivoMovil;
import dispositivomovil.objetos.Competicion;
import dispositivomovil.objetos.Mensaje;
import dispositivomovil.objetos.Penca;
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
public class VerForoPenca extends javax.swing.JPanel {

    /** Creates new form VerForoPenca */
    public VerForoPenca(JFrame m,int idPenca) {
        initComponents();
        this.dispMov = (DispositivoMovil) m;
        this.jLabelError.setVisible(false);
         String [] s = {"Emisor","Receptor","Fecha","Estado"};

         if (this.dispMov.online){
            try{
                Properties prop = new Properties();
                FileInputStream fis = new FileInputStream("web_services.xml");
                prop.loadFromXML(fis);
                String dir = prop.getProperty("dir_publicar");
                DispMovilWSService dms = new DispMovilWSService(new java.net.URL("http://"+dir+":8280/dispMovilWS?wsdl"),new javax.xml.namespace.QName("http://WebServices/", "DispMovilWSService"));
                DispMovilWS disp = dms.getDispMovilWSPort();

                DataInfoPenca info= disp.verTablaPosicionesPenca(this.dispMov.nickUsuLogueado,idPenca);

                List<DataMensajePenca> mens = info.getMensajes();

                for (DataMensajePenca mjp : mens){
                    Mensaje me = new Mensaje();
                    me.setEmisor(mjp.getEmisor());
                    me.setFecha(new java.sql.Date(mjp.getFecha().getAnio(),mjp.getFecha().getMes(),mjp.getFecha().getDia()));
                    me.setMessage(mjp.getMensaje());
                    me.setPublico(mjp.isPublico());
                    me.setReceptor(mjp.getReceptor());
                    me.setToSend(false);
                    mensjs.add(me);
                }



            Object[][] tabla = new Object[mensjs.size()][4];
            for (int i =0;i< mensjs.size();i++) {
                tabla[i][0]= mensjs.get(i).getEmisor();
                tabla[i][1] = mensjs.get(i).getReceptor();
                tabla[i][2]= mensjs.get(i).getFecha().getDate()+"/"+mensjs.get(i).getFecha().getMonth()+"/"+mensjs.get(i).getFecha().getYear();
                if (mensjs.get(i).getPublico())
                    tabla[i][3] = "Publico";
                 else
                     tabla[i][3] = "Privado";

             }
            TableModel dtm = new DefaultTableModel(tabla,s);
            this.tablaMens.setModel(dtm);

            }
         catch (Exception e){

             this.jLabelError.setText("Error: Se perdio la conexion");
             dispMov.online = false;
             ImageIcon imIcon= new ImageIcon("images/desconectado.png");
             this.dispMov.jLabel1.setIcon(new ImageIcon(imIcon.getImage().getScaledInstance(20,20,20)));
             dispMov.timer.start();

              Penca p= this.dispMov.em.find(Penca.class, ((Competicion)this.dispMov.em.find(Competicion.class, idPenca)).getPenca().getId());
             List<Mensaje> mensajs= p.getMensajes();
             this.mensjs=mensajs;
              Object[][] tabla = new Object[mensjs.size()][4];
                for (int i =0;i< mensjs.size();i++) {
                    tabla[i][0]= mensjs.get(i).getEmisor();
                    tabla[i][1] = mensjs.get(i).getReceptor();
                    tabla[i][2]= mensjs.get(i).getFecha().getDate()+"/"+mensjs.get(i).getFecha().getMonth()+"/"+mensjs.get(i).getFecha().getYear();
                if (mensjs.get(i).getPublico())
                  tabla[i][3] = "Publico";
                else
                  tabla[i][3] = "Privado";

                }
                TableModel dtm = new DefaultTableModel(tabla,s);
                this.tablaMens.setModel(dtm);


             }

        }else{
             Penca p= this.dispMov.em.find(Penca.class, ((Competicion)this.dispMov.em.find(Competicion.class, idPenca)).getPenca().getId());
            List<Mensaje> mensajs= p.getMensajes();
            this.mensjs=mensajs;
            Object[][] tabla = new Object[mensjs.size()][4];
            for (int i =0;i< mensjs.size();i++) {
                tabla[i][0]= mensjs.get(i).getEmisor();
                tabla[i][1] = mensjs.get(i).getReceptor();
             tabla[i][2]= mensjs.get(i).getFecha().getDate()+"/"+mensjs.get(i).getFecha().getMonth()+"/"+mensjs.get(i).getFecha().getYear();
            if (mensjs.get(i).getPublico())
                tabla[i][3] = "Publico";
            else
                tabla[i][3] = "Privado";

            }
            TableModel dtm = new DefaultTableModel(tabla,s);
            this.tablaMens.setModel(dtm);
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaMens = new javax.swing.JTable();
        jLabelError = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(320, 420));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Foro");

        tablaMens.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaMens.setOpaque(false);
        tablaMens.setShowHorizontalLines(false);
        tablaMens.setShowVerticalLines(false);
        tablaMens.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMensMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaMens);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(jLabel1)
                .addContainerGap(150, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(191, 191, 191)
                .addComponent(jLabelError)
                .addContainerGap(129, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addComponent(jLabelError)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tablaMensMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMensMouseClicked
        // TODO add your handling code here:

          fila = this.tablaMens.rowAtPoint(evt.getPoint());
         columna = this.tablaMens.columnAtPoint(evt.getPoint());

        if  ((fila > -1) && (columna > -1))
            this.jTextArea1.setText(mensjs.get(fila).getMessage());
         
    }//GEN-LAST:event_tablaMensMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelError;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTable tablaMens;
    // End of variables declaration//GEN-END:variables

    private DispositivoMovil dispMov;
    private int fila,columna;
    private List<Mensaje> mensjs = new ArrayList<Mensaje>();
}
