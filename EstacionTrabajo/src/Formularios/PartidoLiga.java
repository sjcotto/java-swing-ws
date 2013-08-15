

package Formularios;

import Interface.*;
import DataTypes.DataIdNombre;
import DataTypes.DataFechaHora;
import DataTypes.DataEquipo;
import DataTypes.DataResultadoAltaPartido;
import java.util.*;
import javax.swing.BoxLayout;
import java.awt.GridBagConstraints;
import javax.swing.JSeparator;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.ListSelectionModel;
import DataTypes.DataPartidoNoDefinidoLigaInterfaz;
import Excepciones.ExNoExisteCompeticion;
import Excepciones.ExNoExisteLiga;
import Excepciones.ExLigaYaDefinida;
import Excepciones.ExCompetenciaNoAsignada;
import Excepciones.ExFechaInvalida;
import Excepciones.ExEquipoInvalido;
import Excepciones.ExPartidoYaDefinido;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import javax.swing.BorderFactory;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridBagLayout;

 
public class PartidoLiga extends javax.swing.JInternalFrame {

    /** Creates new form PartidoLiga */
    public PartidoLiga(Tarea1 parent) {
        initComponents();

        this.parent = parent;

        java.awt.Component content = parent.getContentPane();
        int x = (content.getWidth() - this.getWidth()) / 2;
        int y = (content.getHeight() - this.getHeight()) / 2;
        this.setLocation(x, y);

        this.icCompeticiones = (new Fabrica()).getIControladorCompeticiones();


        List<DataIdNombre> pd = icCompeticiones.listarLigasNoDefinidas();
        
        //this.setVisible(true);
        
        if (!pd.isEmpty()){
            this.parent.setModoActivado(false);

            jPanel4.setLayout(new BoxLayout(jPanel4,BoxLayout.Y_AXIS));
            jScrollPane5.setVisible(false);
            jLabel24.setVisible(false);
            jPanel5.setVisible(false);
            int i;


            String[] anios = new  String[21];
            String[] dias = new String[32];
            String[] horas = new  String[25];
            String[] minutos = new  String[61];
            String[] meses = new String[13];

            anios[0] = "Año"; horas[0] = "Hora"; minutos[0] = "Minuto";
            meses[0] = "Mes";

            for (i = 1; i <= 20; i++)
                anios[i] = ((Integer)(i+2011-1)).toString();
            for (i = 1; i <= 12; i++)
                   meses[i] = ((Integer)(i)).toString();
            for (i = 1; i <= 24; i++)
                horas[i] = ((Integer)(i-1)).toString();
            for (i = 1; i <= 60; i++)
                minutos[i] = ((Integer)(i-1)).toString();
            for (i = 1; i <= 31; i++)
                dias[i] = ((Integer)(i)).toString();

            comboAnio.setModel(new javax.swing.DefaultComboBoxModel(anios));
            comboMes.setModel(new javax.swing.DefaultComboBoxModel(meses));
            comboDia.setModel(new javax.swing.DefaultComboBoxModel(dias));
            comboMin.setModel(new javax.swing.DefaultComboBoxModel(minutos));
            comboHora.setModel(new javax.swing.DefaultComboBoxModel(horas));


            int size = pd.size();
            Object [][] obj = new Object[size][2];
            String[] s = {"Identificador","Nombre"};
            idCs = new Integer[size];

            for (i = 0; i < size; i++){
                obj[i][0] = ((DataIdNombre)pd.get(i)).getId();
                idCs[i] = (Integer)obj[i][0];
                obj[i][1] = ((DataIdNombre)pd.get(i)).getNombre();
            }


            comboDia.setEnabled(false);

            comboAnio.setSelectedIndex(0);
            comboMes.setSelectedIndex(0);
            comboMin.setSelectedIndex(0);
            comboHora.setSelectedIndex(0);
            comboDia.setSelectedIndex(0);


            textoLugar.setText("");

            jTable2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            TableModel model = new DefaultTableModel(obj,s);
            jTable2.setModel(model);
            jButton16.setVisible(true);

            setSize(590, 370);
            this.setVisible(true);
        }
        else{
            String str = "No existen en el sistema ligas a definir.";
            JOptionPane.showMessageDialog(null, str, "Atencion",JOptionPane.ERROR_MESSAGE);
            this.setVisible(false);
            this.dispose();

        }
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable()
        {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; // Disallow the editing of any cell
            }
        };
        jButton16 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
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
        jLabel1 = new javax.swing.JLabel();

        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jScrollPane4.setMaximumSize(new java.awt.Dimension(600, 700));
        jScrollPane4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane4MouseClicked(evt);
            }
        });
        jScrollPane4.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jScrollPane4ComponentShown(evt);
            }
        });

        jTable2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 51), 2, true));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable2);

        jButton16.setText("Cerrar");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel24.setText("Seleccione un encuentro");

        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane5.setViewportBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane5.setMaximumSize(new java.awt.Dimension(600, 700));

        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setMaximumSize(new java.awt.Dimension(800, 800));
        jPanel4.setOpaque(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 593, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 379, Short.MAX_VALUE)
        );

        jScrollPane5.setViewportView(jPanel4);

        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 13));
        jLabel25.setText("Lugar");

        textoLugar.setText("jTextField1");

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

        botonAceptar.setText("Aceptar");
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
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(comboAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(comboMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(botonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonCancelar2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(comboDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(comboHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(comboMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(135, Short.MAX_VALUE))
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
                    .addComponent(botonCancelar2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonAceptar))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabel1.setText("Seleccione una liga");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(492, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel24))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE))
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel24)
                    .addComponent(jButton16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:

        fila = jTable2.rowAtPoint(evt.getPoint());
        columna = jTable2.columnAtPoint(evt.getPoint());
        if ((fila > -1) && (columna > -1)){
            //IControladorCompeticiones icc = (new Fabrica()).getIControladorCompeticiones();


            try {
                icCompeticiones.ingresarIdLiga(idCs[fila]);
            }
            catch (ExNoExisteCompeticion exc) {
                JOptionPane.showMessageDialog(null,exc.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
            }
            catch (ExNoExisteLiga exc) {
                JOptionPane.showMessageDialog(null,exc.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
            }
            catch (ExLigaYaDefinida exc) {
                JOptionPane.showMessageDialog(null,exc.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
            }
            catch (Exception exc) {
            }
                 

            List<DataPartidoNoDefinidoLigaInterfaz> ps_nodef = icCompeticiones.getEncuentrosNoFormadosDeLiga();


            GridBagConstraints g = new GridBagConstraints();
            jTable2.setEnabled(false);
            boolean first = true;
            bg = new ButtonGroup();
            jPanel4.removeAll();

            int i = 0;
            Integer k = 0;
            buttonCorrespLocal = new Hashtable();
            buttonCorrespVisit = new Hashtable();

            for (DataPartidoNoDefinidoLigaInterfaz d : ps_nodef){

                JPanel boton = new JPanel();
                boton.setLayout(new GridBagLayout());
                g.gridheight = g.gridwidth = 1;
                g.fill = GridBagConstraints.HORIZONTAL;

                for (int j = 0; j < d.getEncuentrosVisit().size(); j++){
                    if (j == 0)
                        g.insets = new Insets(10,0,0,0);
                    else if (j == 1)
                        g.insets = new Insets(0,0,0,0);

                    g.gridx = 1;
                    g.gridy = j;

                    String st = ((DataEquipo)(d.getEncuentrosVisit().get(j))).getNombre();


                    JRadioButton jb = new JRadioButton(st);
                    jb.setActionCommand(k.toString());
                    buttonCorrespLocal.put(k, d.getEquipoLocal().getId());
                    buttonCorrespVisit.put(k,((DataEquipo)(d.getEncuentrosVisit().get(j))).getId());

                    if (first && j == 0){
                        jb.setSelected(true);
                        first = false;
                    }
                    bg.add(jb);
                    boton.add(jb,g);
                    k++;
                }


                g.fill = GridBagConstraints.HORIZONTAL;
                g.gridx = 0;
                g.gridheight = 5;
                g.gridy = 0;
                g.insets = new Insets(2,0,0,35);
                JLabel cr = new JLabel(d.getEquipoLocal().getNombre());
                cr.setFont(new Font("Arial",Font.ITALIC+Font.BOLD,16));
                boton.add(cr,g);

                g.fill = GridBagConstraints.VERTICAL;
                g.gridx = 0;
                g.gridheight = 4;
                g.gridy = 1;
                g.insets = new Insets(0,0,0,35);
                JLabel cr1 = new JLabel("(Local)");
                cr1.setFont(new Font("Arial",Font.ITALIC,13));
                boton.add(cr1,g);


                g.fill = GridBagConstraints.VERTICAL;
                g.gridx = 0;
                g.gridy = GridBagConstraints.RELATIVE;
                g.gridwidth = 2;
                g.insets = new Insets(0,0,10,0);
                JSeparator st = new JSeparator();
                boton.add(st,g);

                boton.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.black));
                jPanel4.add(boton);
                jPanel4.updateUI();
            }

            jScrollPane5.setVisible(true);
            jLabel24.setVisible(true);
            textoLugar.setText("");
            jPanel5.setVisible(true);
            jTable2.setEnabled(true);
            jButton16.setVisible(false);
            comboAnio.setSelectedIndex(0);
            comboMes.setSelectedIndex(0);
            comboDia.setSelectedIndex(0);
            comboHora.setSelectedIndex(0);
            comboMin.setSelectedIndex(0);

            setSize(590, 710);
        }
}//GEN-LAST:event_jTable2MouseClicked

    private void jScrollPane4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane4MouseClicked
        if (jTable2.getY() <= evt.getY()){
            jTable2.clearSelection();
            jScrollPane5.setVisible(false);
            jPanel5.setVisible(false);
            jLabel24.setVisible(false);
            jButton16.setVisible(true);
            jScrollPane4.setVisible(true);
            setSize(590, 370);
        }
}//GEN-LAST:event_jScrollPane4MouseClicked

    private void jScrollPane4ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jScrollPane4ComponentShown
        // TODO add your handling code here:
}//GEN-LAST:event_jScrollPane4ComponentShown

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        JOptionPane.showMessageDialog(null,"No se creo el Partido de Liga", "Atencion",JOptionPane.WARNING_MESSAGE);
        parent.setModoActivado(true);
        this.dispose();
}//GEN-LAST:event_jButton16ActionPerformed

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
                String mens_error="";
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
                    Integer k = Integer.parseInt((String)(bg.getSelection()).getActionCommand());
                    
                    DataResultadoAltaPartido r = null;
                    try {
                        r = icCompeticiones.seleccionarEncuentroADefinir((Integer)buttonCorrespLocal.get(k),
                                                             (Integer)buttonCorrespVisit.get(k),
                                                              f, textoLugar.getText());
                        String rs = "Encuentro asignado con éxito a la liga \""+(String)(jTable2.getValueAt(jTable2.getSelectedRow(),1))+"\".\n";
                        if (r.getCompeticionQuedoDefinida())
                            rs += "La liga quedo definida.";
                         this.dispose();
                        JOptionPane.showMessageDialog(this, rs, "Exito de alta partido",JOptionPane.INFORMATION_MESSAGE);
                        

                    }
                    catch (ExCompetenciaNoAsignada exc) {
                        JOptionPane.showMessageDialog(null,exc.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                    }
                    catch (ExFechaInvalida exc) {
                        JOptionPane.showMessageDialog(null,exc.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                    }
                    catch (ExEquipoInvalido exc) {
                        JOptionPane.showMessageDialog(null,exc.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                    }
                    catch (ExPartidoYaDefinido exc) {
                        JOptionPane.showMessageDialog(null,exc.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                    }
                    catch (Exception exc) {
                        JOptionPane.showMessageDialog(null,"Exception"+exc.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                    }

                    this.dispose();   //si sale por excepcion
                 }

                 else{
                        mens_error = "Faltan ingresar datos:\n"+mens_error;
                        JOptionPane.showMessageDialog(this, mens_error, "Atencion",JOptionPane.ERROR_MESSAGE);
                 }



    }//GEN-LAST:event_botonAceptarActionPerformed

    private void botonCancelar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelar2ActionPerformed

        this.parent.setModoActivado(true);
        this.dispose();
}//GEN-LAST:event_botonCancelar2ActionPerformed

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked

}//GEN-LAST:event_jPanel5MouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
            jTable2.clearSelection();
            jScrollPane5.setVisible(false);
            jPanel5.setVisible(false);
            jLabel24.setVisible(false);
            jButton16.setVisible(true);
            jScrollPane4.setVisible(true);
            setSize(590, 370);
    }//GEN-LAST:event_formMouseClicked

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:

    }//GEN-LAST:event_formInternalFrameActivated

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened

    }//GEN-LAST:event_formInternalFrameOpened


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAceptar;
    private javax.swing.JButton botonCancelar2;
    private javax.swing.JComboBox comboAnio;
    private javax.swing.JComboBox comboDia;
    private javax.swing.JComboBox comboHora;
    private javax.swing.JComboBox comboMes;
    private javax.swing.JComboBox comboMin;
    private javax.swing.JButton jButton16;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField textoLugar;
    // End of variables declaration//GEN-END:variables
    private Integer[] idCs;
    int fila,columna;
    private ButtonGroup bg;
    private Map<Integer,Integer> buttonCorrespLocal;
    private Map<Integer,Integer> buttonCorrespVisit;
    private Tarea1 parent;
    private boolean flagBorrar = false;

    IControladorCompeticiones icCompeticiones;
}
 // dede