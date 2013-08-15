package Formularios;
import Interface.*;
import DataTypes.*;

import javax.swing.JOptionPane;
public class IngresoResultado extends javax.swing.JInternalFrame {

    /** Creates new form IngresoResultado */
    public IngresoResultado(DataPartido dataP,Tarea1 parent, IControladorCompeticiones icc){
        initComponents();
        

        this.icCompeticiones = icc;

        idL=dataP.getDataInfoPart().getIdLocal();
        idV=dataP.getDataInfoPart().getIdVisita();

        this.parent = parent;
        parent.setModoActivado(false);//no hace falta

        java.awt.Component content = parent.getContentPane();
        int x = (content.getWidth() - this.getWidth()) / 2;
        int y = (content.getHeight() - this.getHeight()) / 2;
        this.setLocation(x, y);

        this.tipO=dataP.getDataInfoPart().getTipoC();
        
        this.idPartido.setText(dataP.getDataInfoPart().getIdPar()+"");
        this.fecha.setText(dataP.getDataInfoPart().getFechaHora().toString());
        this.lugar.setText(dataP.getDataInfoPart().getLugar());
        this.idComp.setText(dataP.getDataInfoPart().getIdComp()+"");
        this.nomCom.setText(dataP.getDataInfoPart().getNomComp());
        this.tipoComp.setText(dataP.getDataInfoPart().getTipoC().toString());
        this.idLocal.setText(dataP.getDataInfoPart().getIdLocal()+"");
        this.nomLocal.setText(dataP.getDataInfoPart().getNomLocal());
        this.idVisita.setText(dataP.getDataInfoPart().getIdVisita()+"");
        this.nomVisita.setText(dataP.getDataInfoPart().getNomVisita());
        this.divLocal.setText(dataP.getDividendos().getDividendoLocal()+"");
        this.divEmpate.setText(dataP.getDividendos().getDividendoEmpate()+"");
        this.divVisita.setText(dataP.getDividendos().getDividendoVisita()+"");
        this.LocalL.setText(dataP.getDataInfoPart().getNomLocal());
        this.VisitanteL.setText(dataP.getDataInfoPart().getNomVisita());
        this.PenalPanel.setVisible(false);
        

           
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox();
        idPartido = new javax.swing.JLabel();
        fecha = new javax.swing.JLabel();
        lugar = new javax.swing.JLabel();
        idComp = new javax.swing.JLabel();
        nomCom = new javax.swing.JLabel();
        tipoComp = new javax.swing.JLabel();
        idLocal = new javax.swing.JLabel();
        nomLocal = new javax.swing.JLabel();
        idVisita = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        nomVisita = new javax.swing.JLabel();
        divLocal = new javax.swing.JLabel();
        divEmpate = new javax.swing.JLabel();
        divVisita = new javax.swing.JLabel();
        aceptar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        LocalL = new javax.swing.JLabel();
        VisitanteL = new javax.swing.JLabel();
        golVisita = new javax.swing.JTextField();
        golLocal = new javax.swing.JTextField();
        PenalPanel = new javax.swing.JPanel();
        penalLocal = new javax.swing.JTextField();
        penalVisita = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setTitle("Ingreso de Resultado");

        idPartido.setText("jLabel1");

        fecha.setText("jLabel2");

        lugar.setText("jLabel3");

        idComp.setText("jLabel4");

        nomCom.setText("jLabel1");

        tipoComp.setText("jLabel2");

        idLocal.setText("jLabel3");

        nomLocal.setText("jLabel4");

        idVisita.setText("jLabel5");

        jLabel6.setText("Id Partido");

        jLabel7.setText("Fecha");

        jLabel8.setText("Lugar");

        jLabel9.setText("Id Competencia");

        jLabel10.setText("Nombre Competicion");

        jLabel11.setText("Tipo Competicion");

        jLabel12.setText("Id Local");

        jLabel13.setText("Nombre local");

        jLabel14.setText("Id Visitante");

        jLabel15.setText("Nombre visitante");

        jLabel16.setText("Dividendo local");

        jLabel17.setText("Dividendo empate");

        jLabel18.setText("Dividendo visitante");

        nomVisita.setText("jLabel19");

        divLocal.setText("jLabel20");

        divEmpate.setText("jLabel21");

        divVisita.setText("jLabel22");

        aceptar.setText("Aceptar");
        aceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aceptarMouseClicked(evt);
            }
        });
        aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        LocalL.setText("Locatario");

        VisitanteL.setText("goles visita");

        golVisita.setText("0");

        golLocal.setText("0");
        golLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                golLocalActionPerformed(evt);
            }
        });

        penalLocal.setText("0");

        penalVisita.setText("0");

        jLabel3.setText("Penales");

        javax.swing.GroupLayout PenalPanelLayout = new javax.swing.GroupLayout(PenalPanel);
        PenalPanel.setLayout(PenalPanelLayout);
        PenalPanelLayout.setHorizontalGroup(
            PenalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PenalPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PenalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(PenalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(penalLocal, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(penalVisita, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        PenalPanelLayout.setVerticalGroup(
            PenalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PenalPanelLayout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(17, 17, 17)
                .addComponent(penalLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(penalVisita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jLabel4.setText("Goles");

        jLabel1.setText("Detalles del partido");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(aceptar)
                            .addGap(61, 61, 61)
                            .addComponent(jButton2))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(35, 35, 35)
                                    .addComponent(jLabel1))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(67, 67, 67)
                                    .addComponent(idPartido))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(85, 85, 85)
                                    .addComponent(fecha))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addGap(87, 87, 87)
                                    .addComponent(lugar))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addGap(39, 39, 39)
                                    .addComponent(idComp))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addGap(16, 16, 16)
                                    .addComponent(nomCom))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addGap(33, 33, 33)
                                    .addComponent(tipoComp))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel12)
                                    .addGap(77, 77, 77)
                                    .addComponent(idLocal))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addGap(53, 53, 53)
                                    .addComponent(nomLocal))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel14)
                                    .addGap(60, 60, 60)
                                    .addComponent(idVisita))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel15)
                                    .addGap(33, 33, 33)
                                    .addComponent(nomVisita))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel16)
                                    .addGap(43, 43, 43)
                                    .addComponent(divLocal))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel17)
                                    .addGap(28, 28, 28)
                                    .addComponent(divEmpate))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel18)
                                    .addGap(23, 23, 23)
                                    .addComponent(divVisita)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LocalL)
                            .addComponent(VisitanteL))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(golLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(golVisita, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(PenalPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(LocalL)
                        .addGap(24, 24, 24)
                        .addComponent(VisitanteL))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(golLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(golVisita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(PenalPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(aceptar)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel6))
                    .addComponent(idPartido))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel7))
                    .addComponent(fecha))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel8))
                    .addComponent(lugar))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel9))
                    .addComponent(idComp))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel10))
                    .addComponent(nomCom))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel11))
                    .addComponent(tipoComp))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel12))
                    .addComponent(idLocal))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel13))
                    .addComponent(nomLocal))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel14))
                    .addComponent(idVisita))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel15))
                    .addComponent(nomVisita))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel16))
                    .addComponent(divLocal))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel17))
                    .addComponent(divEmpate))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel18))
                    .addComponent(divVisita))
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarActionPerformed
        // TODO add your handling code here:
        try{
            gL= Integer.valueOf(golLocal.getText());
            gV= Integer.valueOf(golVisita.getText());
            if( tipO==TipoCompeticion.Copa){
                if(gV==gL){
                    this.PenalPanel.setVisible(true);
                     pL= Integer.valueOf(penalLocal.getText());
                     pV= Integer.valueOf(penalVisita.getText());
                     if(pL==pV){
                        JOptionPane.showMessageDialog(null, "Ingrese penales distintos", "Debe haber ganador",JOptionPane.WARNING_MESSAGE);
                        golLocal.setEnabled(false);
                        golVisita.setEnabled(false);
                     }
                     else{
                        //Fabrica f =  new Fabrica();
                        //IControladorCompeticiones iCC= f.getIControladorCompeticiones();
                        icCompeticiones.ingresarResultado(gL, pL, gV, pV);
                        javax.swing.JInternalFrame i = new  SeleccionJugadoresFinalizar(parent, idL,idV, icCompeticiones);
                        parent.getLayeredPane().add(i);
                        i.setVisible(true);
                        this.dispose();
                    }
                }
                 else{
                        //Fabrica f =  new Fabrica();
                        //IControladorCompeticiones iCC= f.getIControladorCompeticiones();
                        icCompeticiones.ingresarResultado(gL, pL, gV, pV);
                        javax.swing.JInternalFrame i = new  SeleccionJugadoresFinalizar(parent, idL,idV,icCompeticiones);
                        parent.getLayeredPane().add(i);
                        i.setVisible(true);
                        this.dispose();
                    }
            }
            else{
                //Fabrica f =  new Fabrica();
                //IControladorCompeticiones iCC= f.getIControladorCompeticiones();
                icCompeticiones.ingresarResultado(gL, pL, gV, pV);
                javax.swing.JInternalFrame i = new  SeleccionJugadoresFinalizar(parent, idL,idV,icCompeticiones);
                parent.getLayeredPane().add(i);
                i.setVisible(true);
                this.dispose();
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Marcador invalido, ingrese un nimero valido", "Error",JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_aceptarActionPerformed

    private void aceptarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aceptarMouseClicked
        // TODO add your handling code here:
       

    }//GEN-LAST:event_aceptarMouseClicked

    private void golLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_golLocalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_golLocalActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        //Fabrica f =  new Fabrica();
        parent.setModoActivado(true);
        //IControladorCompeticiones iCC= f.getIControladorCompeticiones();
        icCompeticiones.limpiarMemoriaAuxiliar();
        this.dispose();
    }//GEN-LAST:event_jButton2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LocalL;
    private javax.swing.JPanel PenalPanel;
    private javax.swing.JLabel VisitanteL;
    private javax.swing.JButton aceptar;
    private javax.swing.JLabel divEmpate;
    private javax.swing.JLabel divLocal;
    private javax.swing.JLabel divVisita;
    private javax.swing.JLabel fecha;
    private javax.swing.JTextField golLocal;
    private javax.swing.JTextField golVisita;
    private javax.swing.JLabel idComp;
    private javax.swing.JLabel idLocal;
    private javax.swing.JLabel idPartido;
    private javax.swing.JLabel idVisita;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lugar;
    private javax.swing.JLabel nomCom;
    private javax.swing.JLabel nomLocal;
    private javax.swing.JLabel nomVisita;
    private javax.swing.JTextField penalLocal;
    private javax.swing.JTextField penalVisita;
    private javax.swing.JLabel tipoComp;
    // End of variables declaration//GEN-END:variables
    private Tarea1 parent;
    private int idL,idV;
    private int gL=0,gV=0,pL=0,pV=0;
    private TipoCompeticion tipO;

    private IControladorCompeticiones icCompeticiones;
}
