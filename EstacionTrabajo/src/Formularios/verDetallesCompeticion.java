/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * verDetallesCompeticion.java
 *
 * Created on 24/08/2011, 12:33:55 AM
 */

package Formularios;

import DataTypes.DataCompeticion;
import DataTypes.TipoCompeticion;
import DataTypes.DataDivEquipo;
import DataTypes.DataPartido;
import Interface.Fabrica;
import Interface.IControladorCompeticiones;
import Excepciones.ExDatosNoAsignados;
import java.util.Collections;
import java.util.List;
import javax.swing.table.TableModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
/**
 *
 * @author santiago
 */
public class verDetallesCompeticion extends javax.swing.JInternalFrame {

    /** Creates new form verDetallesCompeticion */
    public verDetallesCompeticion(DataCompeticion dataComp, Tarea1 parent, IControladorCompeticiones icc) {
        initComponents();

        this.icCompeticiones = icc;

        this.parent = parent;
        parent.setModoActivado(false);

        java.awt.Component content = parent.getContentPane();
        int x = (content.getWidth() - this.getWidth()) / 2;
        int y = (content.getHeight() - this.getHeight()) / 2;
        this.setLocation(x, y);

       


        jLabel37.setText(dataComp.getNombre());
        jLabel43.setText(Integer.toString(dataComp.getId()));
        jLabel45.setText(dataComp.getTipo().toString());
        if (dataComp.getEstaDefinida())
           jLabel47.setText("Si");
        else
           jLabel47.setText("No");

        String path = dataComp.getPathImage();

        if (path.equals("")){
            jLabel1.setText("No tiene imagen");
        }else{
            try{
                ImageIcon image = new ImageIcon(path);
                jLabel1.setText("");
                if(image.getIconHeight()>128 || image.getIconWidth()>128){
                    int a = image.getIconWidth()-128;
                    int b = image.getIconHeight() - 128;
                    int razon = a ;
                    if (a < b)
                        razon = b;
                    ImageIcon imageScalada = new ImageIcon(image.getImage().getScaledInstance(image.getIconWidth()-razon, image.getIconHeight()-razon, 128));
                    jLabel1.setIcon(imageScalada);
                }else{
                   jLabel1.setIcon(image);
                }
            }catch (Exception e2){
                jLabel1.setText("Error al cargar la imagen");
            }
        }

        
        if (!(dataComp.getTipo()==dataComp.getTipo().PartidoIndividual)) {
            List<DataDivEquipo> dividendos = dataComp.getDividendos();
            int largoDividendos = dividendos.size();

            String[] esq = {"Id", "Nombre", "Dividendo"};

            Object[][] mat = new Object[largoDividendos][3];
            for (int i=0; i<largoDividendos; i++) {
                DataDivEquipo dataDE = dividendos.get(i);
                mat[i][0] = dataDE.getId();
                mat[i][1] = dataDE.getNombre();
                mat[i][2] = dataDE.getDividendo();
            }

            TableModel dtm = new DefaultTableModel(mat,esq);
            tablaEquipos.setModel(dtm);
            try{
                
                if(dataComp.getMontoPenca()>0){
                    this.MontoPenca.setText(Float.toString(dataComp.getMontoPenca()));
                }
                else{
                    this.MontoPenca.setText("Indefinido");
                }
                List<DataTypes.DataGoleador> goleadores = icCompeticiones.getJugadoresCampeonato(dataComp.getId());
                int largoGoleadores = goleadores.size();
                System.out.println(largoGoleadores+" largo golear" );
                if(largoGoleadores>0){
                    this.DivGoleador.setText("Goleadores");
                }
                else{
                    this.DivGoleador.setText("Indefinido");
                }
                String[] esq2 = {"Id", "Nombre", "Goles","Dividendo"};
                //Collections.sort(goleadores);
                
                Object[][] mat2 = new Object[largoGoleadores][4];
                for (int i=0; i<largoGoleadores; i++) {
                    DataTypes.DataGoleador dataDG = goleadores.get(i);
                    mat2[i][0] = dataDG.getId();
                    mat2[i][1] = dataDG.getNombre();
                    mat2[i][2] = dataDG.getGoles();
                    mat2[i][3]= dataDG.getDividendo();
                }

                TableModel dtm2 = new DefaultTableModel(mat2,esq2);
                this.tablaGoleadores.setModel(dtm2);
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
            }
            
        } else{
            this.jScrollPane7.setVisible(false);
            this.jLabel48.setVisible(false);
            this.jPanel1.setVisible(false);
        }

        //Fabrica f = new Fabrica();
        //IControladorCompeticiones icc = f.getIControladorCompeticiones();
        listDataPart = null;

        

        try {
            listDataPart = icCompeticiones.infoPartidosCompeticion();
            

        }
        catch (ExDatosNoAsignados exc) {
            JOptionPane.showMessageDialog(null,exc.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
            icCompeticiones.finalizarVerDetallesComp();
            this.dispose();
        }
        catch (Exception exc) {
            icCompeticiones.finalizarVerDetallesComp();
            this.dispose();
        }
        int largoPart = listDataPart.size();

        String [] esq2 = {"Indice","Id","Fecha","Local",
                          "Visitante","Goles Loc",
                          "Gol Vis", "Div local","Div visita",
                          "Div empate","Resultado"};

        Object[][] mat2 = new Object[largoPart][11];
        for (int i=0; i<largoPart; i++) {
            DataPartido dataP = (DataPartido) listDataPart.get(i);
            mat2[i][0] = i;
            mat2[i][1] = dataP.getDataInfoPart().getIdPar();
            mat2[i][2] = dataP.getDataInfoPart().getFechaHora().toString();
            mat2[i][3] = dataP.getDataInfoPart().getNomLocal();
            mat2[i][4] = dataP.getDataInfoPart().getNomVisita();
            int golL = dataP.getGolesL();
            int golV = dataP.getGolesV();

            //NUEVOOOOOOOOOOOO
            if (golL==-1)
                mat2[i][5] = "No finalizado";
            else{
                if ((dataP.getDataInfoPart().getTipoC()==TipoCompeticion.Copa) && (golL==golV))
                    mat2[i][5] = Integer.toString(golL)+" ("+Integer.toString(dataP.getPenalesL()) +")";
                else
                    mat2[i][5] = Integer.toString(golL);
            }
            if (golL==-1)
                mat2[i][6] = "No finalizado";
            else{
                if ((dataP.getDataInfoPart().getTipoC()==TipoCompeticion.Copa) && (golL==golV))
                    mat2[i][6] = Integer.toString(golV)+" ("+Integer.toString(dataP.getPenalesV()) +")";
                else
                    mat2[i][6] = Integer.toString(golV);
            }
            if (dataP.getDividendos().getEstanAsignados()) {
                 mat2[i][7] = dataP.getDividendos().getDividendoLocal();
                 mat2[i][8] = dataP.getDividendos().getDividendoVisita();
                 mat2[i][9] = dataP.getDividendos().getDividendoEmpate();
            }
            else{
               mat2[i][7]= "No Asignado";
               mat2[i][8]= "No Asignado";
               mat2[i][9]= "No Asignado";
            }


            //**///

            if (dataP.getEstaFinalizado()) {
                golL=golL+dataP.getPenalesL();
                golV=golV+dataP.getPenalesV();
                if (golL>golV){
                      mat2[i][10]="Gano Local";
                }
                else{
                    if (golL<golV){
                       mat2[i][10]="Gano Visita";
                    }
                    else{
                         mat2[i][10] ="Empate";
                    }
                }

            }
            else{
               mat2[i][10]= "No finalizado";
            }
        }

        TableModel dtm2 = new DefaultTableModel(mat2,esq2);
        tablaPartidos.setModel(dtm2);
        jButton21.setEnabled(false);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel49 = new javax.swing.JLabel();
        jButton21 = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tablaEquipos = new javax.swing.JTable()
        {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        jLabel45 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tablaPartidos = new javax.swing.JTable()
        {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        jButton22 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        mo = new javax.swing.JLabel();
        MontoPenca = new javax.swing.JLabel();
        DivGoleador = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tablaGoleadores = new javax.swing.JTable()
        {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        setTitle("Detalles de Competicion");

        jLabel49.setText("Partidos");

        jButton21.setText("Aceptar");
        jButton21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton21MouseClicked(evt);
            }
        });
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jLabel42.setText("Id: ");

        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel43.setText("jLabel43");

        jLabel44.setText("Tipo: ");

        jLabel47.setText("jLabel47");

        tablaEquipos.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaEquipos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane7.setViewportView(tablaEquipos);

        jLabel45.setText("jLabel45");

        jLabel33.setText("Nombre: ");

        jLabel46.setText("Definida?: ");

        jLabel37.setText("jLabel37");

        jLabel48.setText("Equipos");

        tablaPartidos.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaPartidos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaPartidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPartidosMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tablaPartidos);

        jButton22.setText("Cancelar");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jLabel1.setText("Imgen");
        jLabel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel1.setPreferredSize(new java.awt.Dimension(128, 128));

        mo.setText("Monto penca:");

        MontoPenca.setText("jLabel3");

        DivGoleador.setText("jLabel5");

        tablaGoleadores.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaGoleadores.setEnabled(false);
        jScrollPane9.setViewportView(tablaGoleadores);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(mo)
                        .addGap(18, 18, 18)
                        .addComponent(MontoPenca))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(DivGoleador)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mo)
                    .addComponent(MontoPenca))
                .addGap(18, 18, 18)
                .addComponent(DivGoleador, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(420, 420, 420)
                        .addComponent(jLabel48))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel33)
                                    .addComponent(jLabel42)
                                    .addComponent(jLabel44))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel37)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel43)
                                        .addComponent(jLabel45))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel46)
                                .addGap(25, 25, 25)
                                .addComponent(jLabel47))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(jLabel49)))
                        .addGap(130, 130, 130)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton22)))))
                .addGap(29, 29, 29))
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 917, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(jLabel37))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(jLabel43))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel44)
                            .addComponent(jLabel45))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel46)
                            .addComponent(jLabel47))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel49))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton22)
                                    .addComponent(jButton21)))
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton21MouseClicked
        // TODO add your handling code here:
}//GEN-LAST:event_jButton21MouseClicked

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
        if (!this.finPartido) {
            JOptionPane.showInternalMessageDialog(this,"Fin ver Detalles Competicion","Detalles De Competicion", JOptionPane.INFORMATION_MESSAGE);
            parent.setModoActivado(true);
            this.dispose();
        } else {
            List<DataTypes.DataEvento> le=null;
            DataPartido p = this.listDataPart.get(indice);

            le= this.listDataPart.get(indice).getEventos();
            javax.swing.JInternalFrame jif = new JugadoresEquiposPartido(this.parent,this.idSeleccionadoTabla,nomLocal,nomVisita,this.icCompeticiones,le);
            parent.getLayeredPane().add(jif);
            jif.setVisible(true);
            this.dispose();
        }
}//GEN-LAST:event_jButton21ActionPerformed

    private void tablaPartidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPartidosMouseClicked
        fila = this.tablaPartidos.rowAtPoint(evt.getPoint());

        if (fila > -1){
            indice=(Integer) this.tablaPartidos.getValueAt(fila,0);
            idSeleccionadoTabla = (Integer) this.tablaPartidos.getValueAt(fila,1);


            String nomGoles = (String) (this.tablaPartidos.getValueAt(fila,5));
            this.finPartido = !(nomGoles.equals("No finalizado"));
            nomLocal =  (String) this.tablaPartidos.getValueAt(fila,3);
            nomVisita = (String) this.tablaPartidos.getValueAt(fila,4);

            jButton21.setEnabled(true);
        }
}//GEN-LAST:event_tablaPartidosMouseClicked

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        //Fabrica f = new Fabrica();
        parent.setModoActivado(true);
        //IControladorCompeticiones icc = f.getIControladorCompeticiones();
        icCompeticiones.finalizarVerDetallesComp();
        this.dispose();
    }//GEN-LAST:event_jButton22ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DivGoleador;
    private javax.swing.JLabel MontoPenca;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel mo;
    private javax.swing.JTable tablaEquipos;
    private javax.swing.JTable tablaGoleadores;
    private javax.swing.JTable tablaPartidos;
    // End of variables declaration//GEN-END:variables
    private int fila;
    private Tarea1 parent;
    private int idSeleccionadoTabla;
    private boolean finPartido;
    private String nomLocal;
    private String nomVisita;

    private IControladorCompeticiones icCompeticiones;

    private List<DataPartido> listDataPart;
    private int indice;

}
