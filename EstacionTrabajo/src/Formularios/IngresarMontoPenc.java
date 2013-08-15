package Formularios;
import Interface.IControladorCompeticiones;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import DataTypes.TipoCompeticion;
import Excepciones.ExDatosNoAsignados;
import Excepciones.ExDividendosInvalidos;
import Excepciones.ExEquipoInvalido;
import Excepciones.ExDividendosYaAsignados;
 
public class IngresarMontoPenc extends javax.swing.JInternalFrame {

    /** Creates new form IngresarDividendos */
    public IngresarMontoPenc( Tarea1 parent,TipoCompeticion tC, IControladorCompeticiones icc) { // GONZALO
        initComponents();

        this.icCompeticiones = icc; // GONZALO

        this.parent = parent;
        parent.setModoActivado(false);

        java.awt.Component content = parent.getContentPane();
        int x = (content.getWidth() - this.getWidth()) / 2;
        int y = (content.getHeight() - this.getHeight()) / 2;
        this.setLocation(x, y);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel23 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton18 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();

        setTitle("Ingrese monto de penca");
        setToolTipText("'Aceptar' para ingresar el proximo dividendo");

        jLabel23.setText("Monto penca: ");

        jTextField2.setColumns(4);

        jButton18.setText("Aceptar");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton17.setText("Cancelar");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton18)
                    .addComponent(jLabel23))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton17))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton18)
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
               
        boolean error = false;
        try{
            float div = Float.valueOf(jTextField2.getText());
            try{
                icCompeticiones.setMontoPenca(div); // GONZALO
                JOptionPane.showMessageDialog(null, "Se ha ingresado correctamente el monto de la penca", "Ingreso monto penca", JOptionPane.INFORMATION_MESSAGE);
                JInternalFrame j = new confirmarCompeticion(parent,icCompeticiones); // GONZALO
                parent.getLayeredPane().add(j);
                j.setVisible(true);
                this.dispose();
            }catch (ExEquipoInvalido e1){
                JOptionPane.showMessageDialog(null, "Error: "+ e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                error = true;
            }catch (ExDividendosInvalidos e1){
                JOptionPane.showMessageDialog(null, "Error: "+ e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                error = true;
            }catch (ExDividendosYaAsignados e1){
                JOptionPane.showMessageDialog(null, "Error: "+ e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                error = true;
            }catch (ExDatosNoAsignados e1){
                JOptionPane.showMessageDialog(null, "Error: "+ e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                error = true;
            }           
        }catch (Exception r){
            JOptionPane.showMessageDialog(null, "monto invalido, ingrese un numero valido", "Error",JOptionPane.ERROR_MESSAGE);
            r.printStackTrace();
        }
        jTextField2.setText("");

    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
//        Fabrica f = new Fabrica();
//        Interface.IControladorCompeticiones icc = f.getIControladorCompeticiones();
        icCompeticiones.finalizar();
        parent.setModoActivado(true);
        this.dispose();
    }//GEN-LAST:event_jButton17ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
    
    private Tarea1 parent;
    private IControladorCompeticiones icCompeticiones; /// GONZALO
}
