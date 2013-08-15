/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * IngresarTipoCriterioLiga.java
 *
 * Created on 25/08/2011, 12:38:08 PM
 */

package Formularios;

/**
 *
 * @author santiago
 */
import Interface.Fabrica;
import Interface.IControladorCompeticiones;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import DataTypes.TipoCompeticion;
import DataTypes.DataIdNombre;
import java.util.List;
import DataTypes.TipoCriterio;
import Excepciones.ExCriteriosInvalidos;

public class IngresarTipoCriterioLiga extends javax.swing.JInternalFrame {

    /** Creates new form IngresarTipoCriterioLiga */
    public IngresarTipoCriterioLiga(List<DataIdNombre> l, Tarea1 parent,TipoCompeticion tC, IControladorCompeticiones icc) {
        initComponents();

        this.icCompeticiones = icc; // GONZALO

        this.parent = parent;
        parent.setModoActivado(false);//no hace falta

        java.awt.Component content = parent.getContentPane();
        int x = (content.getWidth() - this.getWidth()) / 2;
        int y = (content.getHeight() - this.getHeight()) / 2;
        this.setLocation(x, y);

        tipoC = tC;
        lista = l;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelLiga = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        c1 = new javax.swing.JComboBox();
        c2 = new javax.swing.JComboBox();
        c3 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setTitle("Ingrese los criterios de la Liga");

        panelLiga.setBorder(javax.swing.BorderFactory.createTitledBorder("Criterios Liga"));

        jLabel28.setText("Criterio 1:");

        jLabel29.setText("Criterio 2:");

        jLabel30.setText("Criterio 3:");

        c1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Diferencia Goles", "Resultado", "Goles a favor" }));

        c2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Diferencia Goles", "Resultado", "Goles a favor" }));
        c2.setSelectedIndex(1);

        c3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Diferencia Goles", "Resultado", "Goles a favor" }));
        c3.setSelectedIndex(2);

        javax.swing.GroupLayout panelLigaLayout = new javax.swing.GroupLayout(panelLiga);
        panelLiga.setLayout(panelLigaLayout);
        panelLigaLayout.setHorizontalGroup(
            panelLigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLigaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelLigaLayout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(c1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLigaLayout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(c2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLigaLayout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(c3, 0, 0, Short.MAX_VALUE)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        panelLigaLayout.setVerticalGroup(
            panelLigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLigaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(c1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(c2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLigaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(c3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Aceptar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelLiga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(246, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelLiga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        TipoCriterio [] d = new TipoCriterio[3];
        if (c1.getSelectedIndex() == 0){
            d[0] = d[0].DiferenciaGoles;
        }
        if (c1.getSelectedIndex() == 1){
            d[0] = d[0].Resultado;
        }
        if (c1.getSelectedIndex() == 2){
            d[0] = d[0].GolesFavor;
        }
        if (c2.getSelectedIndex() == 0){
            d[1] = d[1].DiferenciaGoles;
        }
        if (c2.getSelectedIndex() == 1){
            d[1] = d[1].Resultado;
        }
        if (c2.getSelectedIndex() == 2){
            d[1] = d[1].GolesFavor;
        }
        if (c3.getSelectedIndex() == 0){
            d[2] = d[2].DiferenciaGoles;
        }
        if (c3.getSelectedIndex() == 1){
            d[2] = d[2].Resultado;
        }
        if (c3.getSelectedIndex() == 2){
            d[2] = d[2].GolesFavor;
        }
//        Fabrica f = new Fabrica();
//        IControladorCompeticiones icc = f.getIControladorCompeticiones();

        try{
            icCompeticiones.ingresarOrdenLiga(d); // GONZALO
            //vamos a la pantalla de asignar dividendos ahora
            JInternalFrame j2 = new IngresarDividendos(lista,parent,tipoC,icCompeticiones); // GONZALO
            parent.getLayeredPane().add(j2);
            j2.setVisible(true);
            this.dispose();
        }catch (ExCriteriosInvalidos e1){
            JOptionPane.showMessageDialog(null, "Error: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }catch (Exception e2){
            JOptionPane.showMessageDialog(null, "Error: " +e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

        private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            // TODO add your handling code here:
            parent.setModoActivado(true);
            this.dispose();
        }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox c1;
    private javax.swing.JComboBox c2;
    private javax.swing.JComboBox c3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JPanel panelLiga;
    // End of variables declaration//GEN-END:variables
    private List<DataIdNombre> lista;
    private TipoCompeticion tipoC;
    private Tarea1 parent;

    IControladorCompeticiones icCompeticiones;
}
