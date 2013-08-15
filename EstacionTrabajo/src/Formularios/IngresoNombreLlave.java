

package Formularios;
import Interface.*;
import DataTypes.*;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import Excepciones.ExNoExisteCompeticion;
import Excepciones.ExCopaYaDefinida;
import Excepciones.ExYaExisteLLave;
import javax.swing.JOptionPane;

/**
 *
 * @author tprog083
 */
public class IngresoNombreLlave extends javax.swing.JInternalFrame {

    public IngresoNombreLlave(int idCopa, Tarea1 parent, IControladorCompeticiones icc) {
        initComponents();

        this.icCompeticiones = icc;

        this.parent = parent;
        parent.setModoActivado(false);//no hace falta

        java.awt.Component content = parent.getContentPane();
        int x = (content.getWidth() - this.getWidth()) / 2;
        int y = (content.getHeight() - this.getHeight()) / 2;
        this.setLocation(x, y);

        this.idCopa=idCopa;

        this.aceptar1.setEnabled(false);

   

    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cancelar1 = new javax.swing.JButton();
        aceptar1 = new javax.swing.JButton();
        tnombreLlave = new javax.swing.JTextField();

        setTitle("Ingrese el Nombre de la Llave");

        jLabel1.setText("Nombre Llave");

        cancelar1.setText("Cancelar");
        cancelar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelar1MouseClicked(evt);
            }
        });
        cancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelar1ActionPerformed(evt);
            }
        });

        aceptar1.setText("Siguiente");
        aceptar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aceptar1MouseClicked(evt);
            }
        });
        aceptar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptar1ActionPerformed(evt);
            }
        });

        tnombreLlave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tnombreLlaveMouseClicked(evt);
            }
        });
        tnombreLlave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tnombreLlaveKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tnombreLlaveKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tnombreLlaveKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tnombreLlave, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(aceptar1)
                        .addGap(37, 37, 37)
                        .addComponent(cancelar1)))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tnombreLlave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelar1)
                    .addComponent(aceptar1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelar1MouseClicked
        //Fabrica f =  new Fabrica();
        parent.setModoActivado(true);
        //iCC= f.getIControladorCompeticiones();
        icCompeticiones.limpiarMemoriaAuxiliar();
        this.dispose();
}//GEN-LAST:event_cancelar1MouseClicked

    private void aceptar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aceptar1MouseClicked
        try{
            //Fabrica f =  new Fabrica();
            //iCC= f.getIControladorCompeticiones();
            List<DataLlave> list = icCompeticiones.ingresarDatosLlaveDeCopa(idCopa, tnombreLlave.getText());
            JInternalFrame frame = new SeleccionarEquiposNoAsignadosCopa(list,this.parent,icCompeticiones);
            frame.setVisible(true);
            parent.getLayeredPane().add(frame);
            this.dispose();

        }catch(ExNoExisteCompeticion e){
            JOptionPane.showMessageDialog(null,e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            parent.setModoActivado(true);
            this.dispose();
        }catch(ExCopaYaDefinida e){
            JOptionPane.showMessageDialog(null,e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            parent.setModoActivado(true);
            this.dispose();
        }catch(ExYaExisteLLave e){
            JOptionPane.showMessageDialog(null,e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            parent.setModoActivado(true);
            this.dispose();
        }
        
    }//GEN-LAST:event_aceptar1MouseClicked

    private void aceptar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptar1ActionPerformed
        // TODO add your handling code here:
       
}//GEN-LAST:event_aceptar1ActionPerformed

    private void tnombreLlaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tnombreLlaveMouseClicked
        // TODO add your handling code here:
        tnombreLlave.setText("");
}//GEN-LAST:event_tnombreLlaveMouseClicked

    private void tnombreLlaveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tnombreLlaveKeyPressed
        aceptar1.setEnabled(!(this.tnombreLlave.getText().equals("")));
}//GEN-LAST:event_tnombreLlaveKeyPressed

    private void cancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelar1ActionPerformed
           
    }//GEN-LAST:event_cancelar1ActionPerformed

    private void tnombreLlaveKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tnombreLlaveKeyReleased
        aceptar1.setEnabled(!(this.tnombreLlave.getText().equals("")));
    }//GEN-LAST:event_tnombreLlaveKeyReleased

    private void tnombreLlaveKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tnombreLlaveKeyTyped
        aceptar1.setEnabled(!(this.tnombreLlave.getText().equals("")));
    }//GEN-LAST:event_tnombreLlaveKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptar1;
    private javax.swing.JButton cancelar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField tnombreLlave;
    // End of variables declaration//GEN-END:variables
    private int idCopa;
    private Tarea1 parent;

    private IControladorCompeticiones icCompeticiones;
}
