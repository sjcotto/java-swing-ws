/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * IngresarDividendos.java
 *
 * Created on 24/08/2011, 12:33:14 AM
 */

package Formularios;

/**
 *
 * @author santiago
 */

import DataTypes.*; //casi todos
import Interface.Fabrica;
import Interface.IControladorCompeticiones;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import DataTypes.TipoCompeticion;
import DataTypes.DataIdNombre;
import java.util.List;
import Excepciones.ExDatosNoAsignados;
import Excepciones.ExDividendosInvalidos;
import Excepciones.ExEquipoInvalido;
import Excepciones.ExDividendosYaAsignados;

public class IngresarDividendos extends javax.swing.JInternalFrame {

    /** Creates new form IngresarDividendos */
    public IngresarDividendos(List<DataIdNombre> l, Tarea1 parent,TipoCompeticion tC, IControladorCompeticiones icc) { // GONZALO
        initComponents();

        this.icCompeticiones = icc; // GONZALO

        this.parent = parent;
        parent.setModoActivado(false);

        java.awt.Component content = parent.getContentPane();
        int x = (content.getWidth() - this.getWidth()) / 2;
        int y = (content.getHeight() - this.getHeight()) / 2;
        this.setLocation(x, y);

        tipoC = tC;
        lista = l;
        DataIdNombre equipo = lista.remove(0);
        jLabel27.setText(Integer.toString(equipo.getId()));
        jLabel22.setText(equipo.getNombre());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton18 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();

        setToolTipText("'Aceptar' para ingresar el proximo dividendo");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ingresar dividendos"));

        jLabel21.setText("Equipo: ");

        jLabel40.setText("id: ");

        jLabel27.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(23, 37, 121));
        jLabel27.setText("jLabel27");

        jLabel41.setText("Nombre: ");

        jLabel22.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(23, 37, 121));
        jLabel22.setText("nombre del cuadrooooooooooooooooo");

        jLabel23.setText("Dividendo: ");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel41)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel40)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(227, Short.MAX_VALUE)
                .addComponent(jButton18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton17)
                .addGap(12, 12, 12))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel40)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton18)
                    .addComponent(jButton17)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:

        
//        Fabrica f = new Fabrica();
//        IControladorCompeticiones icc = f.getIControladorCompeticiones();

        boolean error = false;
        try{
            float div = Float.valueOf(jTextField2.getText());
            int id = Integer.parseInt(jLabel27.getText());
            try{
                icCompeticiones.ingresarDividendoEquipo(id, div); // GONZALO
                JOptionPane.showMessageDialog(null, "Se ha ingresado correctamente el dividendo", "Ingreso Dividendos", JOptionPane.INFORMATION_MESSAGE);
                error = false;
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
            //ahora cargamos el proximo si es que existe
            if (!error){
                if (lista.size() > 0){
                    DataIdNombre equipo = lista.remove(0);
                    jLabel27.setText(Integer.toString(equipo.getId()));
                    jLabel22.setText(equipo.getNombre());
                }else{
                   //estamos en el caso de que se termina se ingresar dividendos y no hubo errores
                        JInternalFrame j = new SeleccionJugadoresApuesta(parent,tipoC,icCompeticiones); // GONZALO
                        /*
                        DataCompeticion dataC = icCompeticiones.mostrarInfoCompetencia(); // GONZALO                        
                        JInternalFrame j = new confirmarCompeticion(dataC,parent,icCompeticiones); // GONZALO*/
                        parent.getLayeredPane().add(j);
                        j.setVisible(true);
                        this.dispose();
                }
            }
        }catch (Exception r){
            JOptionPane.showMessageDialog(null, "Divisor invalido, ingrese un numero valido", "Error",JOptionPane.ERROR_MESSAGE);
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
    private List<DataIdNombre> lista;
    private Tarea1 parent;
    private TipoCompeticion tipoC = null;

    private IControladorCompeticiones icCompeticiones; /// GONZALO
}
