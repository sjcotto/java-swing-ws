package Formularios;


import Interface.*;
import DataTypes.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import Excepciones.*;
import javax.swing.JOptionPane;

/**
 *
 * @author tprog083
 */
public class SeleccionarEquiposNoAsignadosCopa extends javax.swing.JInternalFrame {

    /** Creates new form SeleccionarEquiposNoAsignadosCopa */
    public SeleccionarEquiposNoAsignadosCopa(List<DataLlave> listaLlaves, Tarea1 parent, IControladorCompeticiones icc) {
        initComponents();

        this.icCompeticiones = icc;

        this.parent = parent;
        parent.setModoActivado(false);

        java.awt.Component content = parent.getContentPane();
        int x = (content.getWidth() - this.getWidth()) / 2;
        int y = (content.getHeight() - this.getHeight()) / 2;
        this.setLocation(x, y);

        jLabel2.setText("");
        jLabel4.setText("");
        jPanel1.setVisible(false);       

        cargarLlaves(listaLlaves);        
        if (listaLlaves.size()<2){
            this.bMantener.setEnabled(false);
            PuedeIngresarPred=false;
        }
        else{
              this.bMantener.setEnabled(true);
             PuedeIngresarPred=true;
        }
        jTable1.setEnabled(false);
    }

    private boolean cargarEquiposLibres(){

            //Fabrica f = new Fabrica();
            //IControladorCompeticiones icc = f.getIControladorCompeticiones();
            try{
                List<DataIdNombre> listDataIdNom = icCompeticiones.listarEquiposNoAsignadosCopa();

                if (listDataIdNom.size() < 2){
                    JOptionPane.showMessageDialog(null, "No existen equipos", "Error", JOptionPane.ERROR_MESSAGE);
                    bElegir.setEnabled(false);
                    return false;
                }
                int nroDatasIdNom = listDataIdNom.size();
                Object [][] mat = new Object[nroDatasIdNom][2];
                for (int i=0; i<nroDatasIdNom; i++) {
                    DataIdNombre dataIN = (DataIdNombre) listDataIdNom.get(i);
                    mat[i][0] = dataIN.getId();
                    mat[i][1] = dataIN.getNombre();
                }

                String[] arr= {"Id","Nombre"};
                TableModel modelo = new DefaultTableModel(mat,arr);
                jTable1.setModel(modelo);
                
                this.setTitle("Lista de equipos no asignados");
            }
            catch(Exception e){

            }
            return true;

    }

    private void cargarLlaves(List<DataLlave> listaLlaves){

                int nroDatasIdNom = listaLlaves.size();
                Object [][] mat = new Object[nroDatasIdNom][2];
                for (int i=0; i<nroDatasIdNom; i++) {
                    DataLlave dataIN = (DataLlave) listaLlaves.get(i);
                    mat[i][0] = dataIN.getNombre();
                    mat[i][1] = dataIN.getFase();
                }
                

                String[] arr= {"Nombre","Nº fase"};
                TableModel modelo = new DefaultTableModel(mat,arr);
                jTable1.setModel(modelo);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        bCancelar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        bSiguienta = new javax.swing.JButton();
        lIntruccion = new javax.swing.JLabel();
        bMantener = new javax.swing.JButton();
        bElegir = new javax.swing.JButton();

        setTitle("Alta Llave");

        jScrollPane1.setEnabled(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.setEnabled(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        bCancelar.setText("Cancelar");
        bCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelarActionPerformed(evt);
            }
        });

        jPanel1.setEnabled(false);

        jLabel1.setText("Local :");

        jLabel2.setText("jLabel2");

        jLabel3.setText("Visitante: ");

        jLabel4.setText("jLabel4");

        bSiguienta.setText("Siguiente");
        bSiguienta.setEnabled(false);
        bSiguienta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bSiguientaMouseClicked(evt);
            }
        });
        bSiguienta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSiguientaActionPerformed(evt);
            }
        });

        lIntruccion.setText("'Seleccione el equipo local de la tabla'");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lIntruccion, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(bSiguienta)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lIntruccion))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(bSiguienta)
                .addContainerGap())
        );

        bMantener.setText("Mantener llaves");
        bMantener.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bMantenerMouseClicked(evt);
            }
        });
        bMantener.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bMantenerActionPerformed(evt);
            }
        });

        bElegir.setText("Elegir equipos");
        bElegir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bElegirMouseClicked(evt);
            }
        });
        bElegir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bElegirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bMantener)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bElegir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bMantener)
                    .addComponent(bElegir)
                    .addComponent(bCancelar))
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int fila = jTable1.rowAtPoint(evt.getPoint());
        int columna = jTable1.columnAtPoint(evt.getPoint());

        if ((fila > -1) && (columna > -1)&&jTable1.isEnabled()){
            if (faseInicial){
                if(seleccionDelLocal){
                    seleccionDelLocal=false;
                    idLocal = (Integer) jTable1.getValueAt(fila,0);
                    jLabel2.setText((String)jTable1.getValueAt(fila, 1));
                    lIntruccion.setText("'Selecciones el equipo visitante en la tabla'");
                    bSiguienta.setEnabled(false);
                }
                else{
                    idVisita = (Integer) jTable1.getValueAt(fila,0);
                    jLabel4.setText((String)jTable1.getValueAt(fila, 1));
                    jTable1.setEnabled(false);                    
                    bSiguienta.setEnabled(true);
                    lIntruccion.setText("'Siguiente' para continuar con el alta de llave");
                }
            }
            else{
                if(seleccionDelLocal){
                    seleccionDelLocal=false;
                    nomLlaveLocal = (String) jTable1.getValueAt(fila,0);
                    jLabel2.setText(nomLlaveLocal);
                    lIntruccion.setText("'Seleccione la llave visitante'");
                    bSiguienta.setEnabled(false);
                }
                else{
                    nomLlaveVisita = (String) jTable1.getValueAt(fila,0);
                    jLabel4.setText(nomLlaveVisita);
                    jTable1.setEnabled(false);
                    bSiguienta.setEnabled(true);
                    lIntruccion.setText("'Siguiente' para continuar con el alta de llave");
                }
            }
            DefaultTableModel temp = (DefaultTableModel)jTable1.getModel();
            temp.removeRow(fila);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void bMantenerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bMantenerActionPerformed
    
    }//GEN-LAST:event_bMantenerActionPerformed

    private void bElegirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bElegirActionPerformed
       
    }//GEN-LAST:event_bElegirActionPerformed

    private void bSiguientaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bSiguientaMouseClicked

        //Fabrica f = new Fabrica();
        //IControladorCompeticiones icc = f.getIControladorCompeticiones();

        if (faseInicial){
            try{
                icCompeticiones.ingresarDatosPartida(idLocal, idVisita);
                JInternalFrame frame = new IngresarTipoLlave(parent,icCompeticiones);
                parent.getLayeredPane().add(frame);
                frame.setVisible(true);
                this.dispose();
            }catch(ExDatosNoAsignados e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }catch(ExCopaYaDefinida e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }catch(ExNoExisteEquipo e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            try{
                icCompeticiones.ingresarLlavesPredecesoras(nomLlaveLocal, nomLlaveVisita);
                JInternalFrame frame = new IngresarTipoLlave(parent,icCompeticiones);
                parent.getLayeredPane().add(frame);
                frame.setVisible(true);
                this.dispose();
            }catch(ExLLavesInvalidas e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }catch(ExNoExisteLaLlave e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }catch(ExLlavesDeDistintasFases e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }catch(ExLlavesDifierenEnSucesoras e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error gdsf g", JOptionPane.ERROR_MESSAGE);
            }
        }
        parent.setModoActivado(true);//si salta excepcion
        this.dispose();
    }//GEN-LAST:event_bSiguientaMouseClicked

    private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed
            //Fabrica f =  new Fabrica();
            parent.setModoActivado(true);
            //IControladorCompeticiones iCC= f.getIControladorCompeticiones();
            icCompeticiones.limpiarMemoriaAuxiliar();
            this.dispose();
    }//GEN-LAST:event_bCancelarActionPerformed

    private void bSiguientaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSiguientaActionPerformed

    }//GEN-LAST:event_bSiguientaActionPerformed

    private void bElegirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bElegirMouseClicked
        boolean sePuede = true;

        if (!cargarEquiposLibres())
            return;

        faseInicial = true;
        bMantener.setVisible(false);
        bElegir.setVisible(false);
        jPanel1.setVisible(true);
        jTable1.setEnabled(true);
        lIntruccion.setText("'Seleccione el equipo local de la tabla'");
        jTable1.setEnabled(true);
    }//GEN-LAST:event_bElegirMouseClicked

    private void bMantenerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bMantenerMouseClicked
        if (PuedeIngresarPred){
            bMantener.setVisible(false);
            bElegir.setVisible(false);
            jPanel1.setVisible(true);
            jTable1.setEnabled(true);
            faseInicial = false;
            lIntruccion.setText("Seleccione la Llave local de la tabla");
            jLabel2.setText("");
         }else{
            JOptionPane.showMessageDialog(null, "Debe haber 2 o mas llaves", "Error", JOptionPane.ERROR_MESSAGE);
         }
    }//GEN-LAST:event_bMantenerMouseClicked

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancelar;
    private javax.swing.JButton bElegir;
    private javax.swing.JButton bMantener;
    private javax.swing.JButton bSiguienta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lIntruccion;
    // End of variables declaration//GEN-END:variables
    private int idLocal,idVisita;
    private boolean faseInicial=false;
    private String nomLlaveLocal;
    private String nomLlaveVisita;
    private Tarea1 parent;
    private boolean PuedeIngresarPred;
    private boolean PuedeIngresarEqui;
    private boolean seleccionDelLocal=true;

    private IControladorCompeticiones icCompeticiones;
}
