/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DivsResultadoExacto.java
 *
 * Created on 5/10/2011, 06:33:02 PM
 */
package Formularios;

import Interface.*;
import javax.swing.JOptionPane;
/**
 *
 * @author miguel
 */
public class DivsResultadoExacto extends javax.swing.JInternalFrame {

    /** Creates new form DivsResultadoExacto */
    public DivsResultadoExacto(Tarea1 parent, int golesLocal, int golesVisita, 
                                int idPart, int idComp,boolean s,IControladorCompeticiones icc_i) {
        initComponents();
        java.awt.Component content = parent.getContentPane();
        int x = (content.getWidth() - this.getWidth()) / 2;
        int y = (content.getHeight() - this.getHeight()) / 2;
        this.setLocation(x, y);
        this.idPart = idPart; this.idComp = idComp;
        gL = golesLocal; gV = golesVisita;
        title1Label.setText("Dividendo a resultado. ");
        this.sum = s;
        
        title2Label.setText("Goles del equipo local        : "+gL);
        title3Label.setText("Goles del equipo visitante : "+gV);
        this.parent = parent;
        
        this.icc = icc_i;
 
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        title1Label = new javax.swing.JLabel();
        aceptarButton = new javax.swing.JButton();
        cancelarButton = new javax.swing.JButton();
        title2Label = new javax.swing.JLabel();
        title3Label = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        saldoField = new javax.swing.JTextField();

        title1Label.setText("d");

        aceptarButton.setText("Aceptar");
        aceptarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarButtonActionPerformed(evt);
            }
        });

        cancelarButton.setText("Cancelar");
        cancelarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarButtonActionPerformed(evt);
            }
        });

        title2Label.setText("d");

        title3Label.setText("jLabel1");

        jLabel1.setText("Dividendo : ");

        saldoField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saldoFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(aceptarButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cancelarButton))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(saldoField, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(48, 48, 48))
                    .addComponent(title3Label, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                    .addComponent(title2Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(title1Label, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                .addGap(73, 73, 73))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(title1Label)
                .addGap(18, 18, 18)
                .addComponent(title2Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(title3Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(saldoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelarButton)
                    .addComponent(aceptarButton))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void aceptarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarButtonActionPerformed
    javax.swing.JInternalFrame it=null;
    
    

    String s = saldoField.getText();
    if (s.isEmpty()){
        JOptionPane.showMessageDialog(null,"No ha asignado un dividendo","ERROR", JOptionPane.ERROR_MESSAGE);
    }
    else{
        boolean er = false;
        try {
           float divL = Float.valueOf(s);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingreso Invalido: Ingrese Dividendo Correctamente", "ERROR",JOptionPane.ERROR_MESSAGE);
            saldoField.setText("");


        }
        
         Float div = Float.parseFloat(s);
            if (div.floatValue() > 1){
               icc.almacenarAsigDividendoRes(gL, gV, div.floatValue());
               if (!(gL==3 && gV==3)){
                   
                     if (gV < 3)
                         it = new DivsResultadoExacto(this.parent,gL,gV+1,this.idPart,
                                                       this.idComp,!sum,this.icc);
                     else
                         it = new DivsResultadoExacto(this.parent,gL+1,0,this.idPart,
                                                      this.idComp,!sum,this.icc);
                    
                    this.parent.getLayeredPane().add(it);
                    it.setVisible(true);
                    this.dispose();
                }else{
                    JOptionPane.showMessageDialog(null,"Los dividendos se han asignado correctamente","Exito", JOptionPane.INFORMATION_MESSAGE);
                    icc.finalizarAsigDivsResultados();
                    this.dispose();
                }
            }
            else{
                //icc.almacenarAsigDividendoRes(gL, gV, div.floatValue());
                JOptionPane.showMessageDialog(null,"El dividendo a asignar debe ser mayor a 1","ERROR", JOptionPane.ERROR_MESSAGE);
                saldoField.setText("");
            }
        }
    
    
    
}//GEN-LAST:event_aceptarButtonActionPerformed

private void saldoFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saldoFieldActionPerformed
    
}//GEN-LAST:event_saldoFieldActionPerformed

private void cancelarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarButtonActionPerformed

    JOptionPane.showMessageDialog(null,"No se han asignados dividendos a los resultados.","Atencion", JOptionPane.WARNING_MESSAGE);
  
    icc.limpiarMemoriaDivs();
    this.dispose();
}//GEN-LAST:event_cancelarButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarButton;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField saldoField;
    private javax.swing.JLabel title1Label;
    private javax.swing.JLabel title2Label;
    private javax.swing.JLabel title3Label;
    // End of variables declaration//GEN-END:variables
    private Tarea1 parent;
    private int idPart;
    private int idComp;
    private boolean sum;
    private int gL;
    private int gV;
    private IControladorCompeticiones icc;
}

