

package Formularios;
import DataTypes.*;
import Interface.*;
import javax.swing.JOptionPane;


public class ConfirmacionAltaLlave extends javax.swing.JInternalFrame {

    /** Creates new form ConfirmacionAltaLlave */
    public ConfirmacionAltaLlave(Tarea1 parent, IControladorCompeticiones icc) {
        initComponents();

        this.icCompeticiones = icc;

        this.parent = parent;
        parent.setModoActivado(false);//no hace falta

        java.awt.Component content = parent.getContentPane();
        int x = (content.getWidth() - this.getWidth()) / 2;
        int y = (content.getHeight() - this.getHeight()) / 2;
        this.setLocation(x, y);


        String[] anios = new  String[21];
        String[] dias = new String[32];
        String[] horas = new  String[25];
        String[] minutos = new  String[61];
        String[] meses = new String[13];

        anios[0] = "Año"; horas[0] = "Hora"; minutos[0] = "Minuto";
        meses[0] = "Mes";


        for (int i = 1; i <= 20; i++)
            anios[i] = ((Integer)(i+2011-1)).toString();
        for (int i = 1; i <= 12; i++)
               meses[i] = ((Integer)(i)).toString();
        for (int i = 1; i <= 24; i++)
            horas[i] = ((Integer)(i-1)).toString();
        for (int i = 1; i <= 60; i++)
            minutos[i] = ((Integer)(i-1)).toString();
        for (int i = 1; i <= 31; i++)
            dias[i] = ((Integer)(i)).toString();

        comboAnio.setModel(new javax.swing.DefaultComboBoxModel(anios));
        comboMes.setModel(new javax.swing.DefaultComboBoxModel(meses));
        comboDia.setModel(new javax.swing.DefaultComboBoxModel(dias));
        comboMin.setModel(new javax.swing.DefaultComboBoxModel(minutos));
        comboHora.setModel(new javax.swing.DefaultComboBoxModel(horas));


        comboDia.setEnabled(false);

        comboAnio.setSelectedIndex(0);
        comboMes.setSelectedIndex(0);
        comboMin.setSelectedIndex(0);
        comboHora.setSelectedIndex(0);
        comboDia.setSelectedIndex(0);

        comboDia.setEnabled(false);
  

    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        textoLugar = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        comboAnio = new javax.swing.JComboBox();
        comboMes = new javax.swing.JComboBox();
        comboDia = new javax.swing.JComboBox();
        botonAceptar = new javax.swing.JButton();
        botonCancelar2 = new javax.swing.JButton();
        comboHora = new javax.swing.JComboBox();
        comboMin = new javax.swing.JComboBox();

        setTitle("Confirmar Alta Llave");

        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel25.setText("Lugar");

        textoLugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoLugarActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel26.setText("Fecha");

        comboAnio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboMes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboMes.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                comboMesPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        comboDia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        botonAceptar.setText("Confirmar");
        botonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarActionPerformed(evt);
            }
        });

        botonCancelar2.setText("Cancelar");
        botonCancelar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelar2ActionPerformed(evt);
            }
        });

        comboHora.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboMin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textoLugar, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(comboAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(comboMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(botonAceptar))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(botonCancelar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboDia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(comboHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(comboMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(textoLugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(comboAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonCancelar2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 570, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 192, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(28, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboMesPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_comboMesPopupMenuWillBecomeInvisible
        if (comboMes.getSelectedIndex() > 0){
            Integer mes = Integer.parseInt((String)comboMes.getSelectedItem());
            int cantDias=0;
            switch(mes){
                case 1:case 3:case 5:case 7: case 8:case 10:case 12:
                    cantDias = 31;
                    break;
                case 4:case 6:case 9:case 11:
                    cantDias = 30;
                    break;
                default: cantDias= 28;
                break;
            }

            String[] dias = new String[cantDias+1];
            dias[0] = "Dia";
            for (int i = 1; i <= cantDias; i++)
                dias[i] = (new Integer(i)).toString();
            comboDia.setModel(new javax.swing.DefaultComboBoxModel(dias));
            comboDia.setEnabled(true);
        } else
            comboDia.setEnabled(false);
}//GEN-LAST:event_comboMesPopupMenuWillBecomeInvisible



    
    private void botonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarActionPerformed
        String mens_error = "";
        if (textoLugar.getText().isEmpty())
            mens_error += "\t* Nombre de lugar vacio\n";

        if (comboAnio.getSelectedIndex() == 0 || comboMes.getSelectedIndex() == 0
            || comboDia.getSelectedIndex() == 0 || comboHora.getSelectedIndex() == 0
            || comboMin.getSelectedIndex() == 0)
            mens_error += "\t* Fecha no definida";

        if (mens_error.isEmpty()){

            parent.setModoActivado(true);
            
            //IControladorCompeticiones icc = (new Fabrica()).getIControladorCompeticiones();

            Integer anio = Integer.parseInt((String)comboAnio.getSelectedItem());
            Integer mes = Integer.parseInt((String)comboMes.getSelectedItem());
            Integer dia = Integer.parseInt((String)comboDia.getSelectedItem());
            Integer hora = Integer.parseInt((String)comboHora.getSelectedItem());
            Integer min = Integer.parseInt((String)comboMin.getSelectedItem());

            DataFechaHora f = new DataFechaHora(dia, mes, anio, hora, min);


            icCompeticiones.confirmarAltaLlaveCopa(f, textoLugar.getText());

            String res = "Llave creada con exito";
            JOptionPane.showMessageDialog(this,res,"Resultado",JOptionPane.INFORMATION_MESSAGE);

            this.dispose();
        }
        else{
            mens_error = "Faltan definir datos:\n"+mens_error;
            JOptionPane.showMessageDialog(this,mens_error,"Resultado",JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_botonAceptarActionPerformed

    private void botonCancelar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelar2ActionPerformed
        //Fabrica f =  new Fabrica();
        parent.setModoActivado(true);
        //IControladorCompeticiones iCC= f.getIControladorCompeticiones();
        icCompeticiones.limpiarMemoriaAuxiliar();

        this.dispose();
}//GEN-LAST:event_botonCancelar2ActionPerformed

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked

}//GEN-LAST:event_jPanel5MouseClicked

    private void textoLugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoLugarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoLugarActionPerformed

    private void comboAnioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboAnioMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_comboAnioMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAceptar;
    private javax.swing.JButton botonCancelar2;
    private javax.swing.JComboBox comboAnio;
    private javax.swing.JComboBox comboDia;
    private javax.swing.JComboBox comboHora;
    private javax.swing.JComboBox comboMes;
    private javax.swing.JComboBox comboMin;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField textoLugar;
    // End of variables declaration//GEN-END:variables
    Tarea1 parent;

    IControladorCompeticiones icCompeticiones;
}
