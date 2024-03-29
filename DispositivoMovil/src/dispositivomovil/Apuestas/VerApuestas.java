/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VerApuestas.java
 *
 * Created on Nov 9, 2011, 6:21:14 PM
 */

package dispositivomovil.Apuestas;
 
import WebServices.DispMovilWS.DataApuestaWS;
import WebServices.DispMovilWS.DataFechaHora;
import WebServices.DispMovilWS.DispMovilWS;
import WebServices.DispMovilWS.DispMovilWSService;
import dispositivomovil.DispositivoMovil;
import java.awt.Color;
import java.awt.Component;
import java.io.FileInputStream;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.border.LineBorder;


class MiRendererDeLista extends JTextArea implements ListCellRenderer {

    public Component getListCellRendererComponent(
      JList lista,Object valor,int indice,
      boolean seleccionado, boolean conFoco ) {

      setBorder( LineBorder.createBlackLineBorder() );
      // Presenta el text correspondiente al item
      setText( valor.toString() );
      // Pinta en los colores indicados y con la fuente seleccionada...
      if( seleccionado ) {
        // .. en el caso de un item marcado (rojo/blanco)
        setBackground( Color.darkGray );
        setForeground( Color.white );
      }
      else {
        // .. en el caso de un item no marcado ( gris/negro)
        setBackground( Color.white );
        setForeground( Color.DARK_GRAY );
      }
      return( this );
    }
}


public class VerApuestas extends javax.swing.JPanel {

    /** Creates new form VerApuestas */
    public VerApuestas(DispositivoMovil disp) {
        initComponents();
        this.dispMovil = disp;
        jLabel3.setVisible(false);

        ImageIcon ic = new ImageIcon("images/listar.png");
        ImageIcon im = new ImageIcon(ic.getImage().getScaledInstance(15,15,15));
        this.jButton1.setIcon(im);


       
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tcomienzo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfin = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();

        setOpaque(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Seleccione el rango de fechas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setOpaque(false);

        tcomienzo.setFont(new java.awt.Font("Dialog", 2, 12));
        tcomienzo.setText("dd/mm/aaaa");
        tcomienzo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tcomienzoMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Comienzo");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Fin");

        tfin.setFont(new java.awt.Font("Dialog", 2, 12));
        tfin.setText("dd/mm/aaaa");
        tfin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfinMouseClicked(evt);
            }
        });

        jButton1.setBackground(java.awt.Color.darkGray);
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Mostrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfin)
                            .addComponent(tcomienzo, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)))
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tcomienzo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setForeground(new java.awt.Color(153, 0, 0));
        jLabel3.setText("jLabel3");

        jScrollPane1.setOpaque(false);

        jList1.setForeground(java.awt.Color.darkGray);
        jList1.setOpaque(false);
        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
                .addGap(29, 29, 29))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addContainerGap(28, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tcomienzoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tcomienzoMouseClicked
        // TODO add your handling code here:
        tcomienzo.setText("");
        jLabel3.setVisible(false);
    }//GEN-LAST:event_tcomienzoMouseClicked

    private void tfinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfinMouseClicked
        // TODO add your handling code here:
        tfin.setText("");
        
        jLabel3.setVisible(false);
    }//GEN-LAST:event_tfinMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        //creamos las fechas a partir de los tcomienzo y tfin, de haber error lo indicamos

        DataFechaHora fi = new DataFechaHora();
        DataFechaHora ff = new DataFechaHora();

        String a = tcomienzo.getText();
        String b = tfin.getText();

        try{
            String[] aa =  a.split("/");

            int anio = Integer.valueOf(aa[2]);
            int mes = Integer.valueOf(aa[1]);
            int dia = Integer.valueOf(aa[0]);

            fi.setAnio(anio);
            fi.setDia(dia);
            fi.setMes(mes);
            fi.setHora(1);
            fi.setMinuto(1);
            fi.setSegundos(1);

            String[] bb =  b.split("/");//fecha final

            int aniof = Integer.valueOf(bb[2]);
            int mesf = Integer.valueOf(bb[1]);
            int diaf = Integer.valueOf(bb[0]);

            ff.setAnio(aniof);
            ff.setDia(diaf);
            ff.setMes(mesf);
            ff.setHora(1);
            ff.setMinuto(1);
            ff.setSegundos(1);

            if ((fi.getAnio()<=0)||(fi.getDia()> 31) || (fi.getDia()<=0) || (fi.getMes()<=0) || (fi.getMes()>12)||
                 (fi.getHora()< 0) || (fi.getHora()>23) || (fi.getMinuto()<0)
                || (fi.getMinuto()>59) || (fi.getSegundos()<0) || (fi.getSegundos()>59)) {
               throw (new Exception("fuera de rango"));
            }

            if ((ff.getAnio()<=0)||(ff.getDia()> 31) || (ff.getDia()<=0) || (ff.getMes()<=0) || (ff.getMes()>12)||
                 (ff.getHora()< 0) || (ff.getHora()>23) || (ff.getMinuto()<0)
                || (ff.getMinuto()>59) || (ff.getSegundos()<0) || (ff.getSegundos()>59)) {
               throw (new Exception("fuera de rango"));
            }


        }catch(Exception e){
            jLabel3.setText("Fecha Invalida: "+e.getMessage());
            jLabel3.setVisible(true);
            return;
        }

        try{
            String dir = null;
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream("web_services.xml");
            prop.loadFromXML(fis);
            dir = prop.getProperty("dir_publicar");

            DispMovilWSService dms = new DispMovilWSService(new java.net.URL("http://"+dir+":8280/dispMovilWS?wsdl"),
                         new javax.xml.namespace.QName("http://WebServices/", "DispMovilWSService"));

            DispMovilWS disp = dms.getDispMovilWSPort();

            List<DataApuestaWS> list = disp.mostrarHistorialApuestas(this.dispMovil.nickUsuLogueado, fi, ff).getList();

            String[] apus = new String[list.size()];
            String n = System.getProperty("line.separator");

            for (int i = 0;i < list.size();i++){
                DataApuestaWS data = list.get(i);
                String str=null;

                if (data.getTipo() == 0){
                    str = data.getCompeticion().getNombre()+n+
                            "Campeon :"+data.getEquipo().getNombre()+n;
                            
                    
                }
                else if (data.getTipo()==1){

                    str= data.getPartido().getDataInfoPart().getNomComp()+n+
                            "Res Excato "+data.getGolesL()+" a "+data.getGolesV()+n;

                }
                else if (data.getTipo()==2){
                    str=data.getCompeticion().getNombre()+n+
                            "Goleador "+data.getJugador().getNombre()+" a "+data.getGolesV()+n;

                }else if (data.getTipo()==3){
                    
                    str= data.getPartido().getDataInfoPart().getNomComp()+n+
                            data.getPartido().getDataInfoPart().getNomLocal()+" vs "+data.getPartido().getDataInfoPart().getNomVisita()+n+
                            "Apuesta :" + data.getTipoResultado().toString()+n;                           
                
                }
                str = str +
                        "Monto :"+data.getMonto()+n+
                        "Dividendo :"+data.getDividendo()+n+
                        "Beneficio :"+data.getBeneficio()+n+
                        "Paquete :"+data.getPertenecePaquete()+n+
                        "Estado :"+data.getEstadoApuesta().toString();
                apus[i]=str;
            }

            this.jList1.setCellRenderer(new MiRendererDeLista());
            this.jList1.setListData(apus);

        }catch(Exception e){
            if (e.getMessage().contains("WSDL")) {
                jLabel3.setText("Se perdio la conexion");
                this.dispMovil.online = false;
                ImageIcon imIcon= new ImageIcon("images/desconectado.png");
                this.dispMovil.jLabel1.setIcon(new ImageIcon(imIcon.getImage().getScaledInstance(20,20,20)));
                this.dispMovil.timer.start();
            } else {
                jLabel3.setVisible(true);
                jLabel3.setText(e.getMessage());
            }
            
        }

        //si la fecha fue correncamente creada..procedemos..

    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tcomienzo;
    private javax.swing.JTextField tfin;
    // End of variables declaration//GEN-END:variables
    private DispositivoMovil dispMovil;
}
