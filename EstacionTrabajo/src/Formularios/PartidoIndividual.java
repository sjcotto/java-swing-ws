

package Formularios;

import DataTypes.*;
import Interface.*;
import Excepciones.ExNoExisteCompeticion;
import Excepciones.ExNoExistePartidoIndividual;
import Excepciones.ExPartidoYaDefinido;
import Excepciones.ExFechaInvalida;
import Excepciones.ExEquipoInvalido;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.table.DefaultTableModel;
import java.util.List;


public class PartidoIndividual extends javax.swing.JInternalFrame {

    /** Creates new form PartidoIndividual */
    public PartidoIndividual(Tarea1 parent) {
        initComponents();

        this.icCompeticiones = (new Fabrica()).getIControladorCompeticiones(); // GONZALO
        
        this.parent = parent;       

        java.awt.Component content = parent.getContentPane();
        int x = (content.getWidth() - this.getWidth()) / 2;
        int y = (content.getHeight() - this.getHeight()) / 2;
        this.setLocation(x, y);
        
        List<DataPartidoIndividualNoDefinido> pd = icCompeticiones.listarPartidosIndividualesNoDefinidos();

        if (!pd.isEmpty()){
            parent.setModoActivado(false);
            button1Form.setSelected(true);
            botonAceptar1.setEnabled(false);
            int i;

            jb = new ButtonGroup();
            jb.add(button1Form); jb.add(button2Form);

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


            comboDia.setEnabled(false);

            comboAnio.setSelectedIndex(0);
            comboMes.setSelectedIndex(0);
            comboMin.setSelectedIndex(0);
            comboHora.setSelectedIndex(0);
            comboDia.setSelectedIndex(0);

            comboDia.setEnabled(false);
            botonAceptar1.setEnabled(false);
            textoLugar1.setText("");


            String[] cols = {"Id Competencia","Competencia","Equipo 1","Equipo 2"};
            Object[][] objs = new Object[pd.size()][4];
            idsEquiposPartIndiv = new Integer[pd.size()][2];

            int id1, id2;

            for (i = 0; i < pd.size(); i++){

                objs[i][0] = ((DataPartidoIndividualNoDefinido)pd.get(i)).getIdCompeticion();
                objs[i][1] = ((DataPartidoIndividualNoDefinido)pd.get(i)).getNombreCompeticion();
                objs[i][2] = ((DataPartidoIndividualNoDefinido)pd.get(i)).getDataEquipo1().getNombre();
                objs[i][3] = ((DataPartidoIndividualNoDefinido)pd.get(i)).getDataEquipo2().getNombre();
                id1 = ((DataPartidoIndividualNoDefinido)pd.get(i)).getDataEquipo1().getId();
                id2 = ((DataPartidoIndividualNoDefinido)pd.get(i)).getDataEquipo2().getId();

                if (i == 0){
                    button1Form.setText((String)objs[i][2]);
                    button2Form.setText((String)objs[i][3]);
                    idCompPartIndiv = (Integer)objs[i][0];
                    button1Form.setActionCommand((new Integer(id1)).toString());
                    button2Form.setActionCommand((new Integer(id2)).toString());
                }

                idsEquiposPartIndiv[i][0] = id1;
                idsEquiposPartIndiv[i][1] = id2;


            }

            TablaPartIndiv.setModel(new DefaultTableModel(objs,cols));
            TablaPartIndiv.setCellSelectionEnabled(false);
            TablaPartIndiv.setRowSelectionAllowed(true);
            TablaPartIndiv.setColumnSelectionAllowed(false);

            TablaPartIndiv.getSelectionModel().setSelectionInterval(0,0);
            setVisible(true);

        }
        else{
             String str = "No existen en el sistema partidos individuales a definir.";
             JOptionPane.showMessageDialog(null, str, "Atencion",JOptionPane.ERROR_MESSAGE);
             this.dispose();
        }
    }

    private void chequearTodosValidos(){
        boolean b = true;
        b = b && comboMes.getSelectedIndex() > 0;
        b = b && comboAnio.getSelectedIndex() > 0;
        b = b && comboDia.getSelectedIndex() > 0;
        b = b && comboDia.isEnabled();
        b = b && comboHora.getSelectedIndex() > 0;
        b = b && comboMin.getSelectedIndex() > 0;
        b = b && !textoLugar1.getText().isEmpty();
        if (b)
            botonAceptar1.setEnabled(true);
        else
            botonAceptar1.setEnabled(false);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPaneTablaPartIndiv = new javax.swing.JScrollPane();
        TablaPartIndiv = new javax.swing.JTable()
        {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; // Disallow the editing of any cell
            }
        }
        ;
        panel2Form = new javax.swing.JPanel();
        panelForm = new javax.swing.JPanel();
        label1PartIndiv = new javax.swing.JLabel();
        textoLugar1 = new javax.swing.JTextField();
        label2PartIndiv = new javax.swing.JLabel();
        comboAnio = new javax.swing.JComboBox();
        comboMes = new javax.swing.JComboBox();
        comboDia = new javax.swing.JComboBox();
        botonAceptar1 = new javax.swing.JButton();
        botonCancelar3 = new javax.swing.JButton();
        comboHora = new javax.swing.JComboBox();
        comboMin = new javax.swing.JComboBox();
        tituloForm = new javax.swing.JLabel();
        label1Form = new javax.swing.JLabel();
        button2Form = new javax.swing.JRadioButton();
        button1Form = new javax.swing.JRadioButton();
        tituloTablaPartIndiv = new javax.swing.JLabel();

        TablaPartIndiv.setModel(new javax.swing.table.DefaultTableModel(
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
        TablaPartIndiv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaPartIndivMouseClicked(evt);
            }
        });
        scrollPaneTablaPartIndiv.setViewportView(TablaPartIndiv);

        panel2Form.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        label1PartIndiv.setFont(new java.awt.Font("Tahoma", 0, 13));
        label1PartIndiv.setText("Lugar");

        textoLugar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoLugar1ActionPerformed(evt);
            }
        });
        textoLugar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textoLugar1KeyReleased(evt);
            }
        });

        label2PartIndiv.setFont(new java.awt.Font("Tahoma", 0, 12));
        label2PartIndiv.setText("Fecha");

        comboAnio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboAnio.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                comboAnioPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        comboMes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboMes.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                comboMesPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        comboDia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboDia.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                comboDiaPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        botonAceptar1.setText("Aceptar");
        botonAceptar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptar1ActionPerformed(evt);
            }
        });

        botonCancelar3.setText("Cancelar");
        botonCancelar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelar3ActionPerformed(evt);
            }
        });

        comboHora.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboHora.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                comboHoraPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        comboMin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboMin.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                comboMinPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        javax.swing.GroupLayout panelFormLayout = new javax.swing.GroupLayout(panelForm);
        panelForm.setLayout(panelFormLayout);
        panelFormLayout.setHorizontalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label1PartIndiv, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label2PartIndiv))
                .addGap(18, 18, 18)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelFormLayout.createSequentialGroup()
                        .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelFormLayout.createSequentialGroup()
                                .addComponent(comboAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(comboMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelFormLayout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(botonAceptar1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelFormLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(comboDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(comboHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(comboMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelFormLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(botonCancelar3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(textoLugar1))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        panelFormLayout.setVerticalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label1PartIndiv)
                    .addComponent(textoLugar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label2PartIndiv)
                    .addComponent(comboAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAceptar1)
                    .addComponent(botonCancelar3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tituloForm.setFont(new java.awt.Font("Tahoma", 1, 16));
        tituloForm.setText("Ingresar Datos del Partido");

        label1Form.setFont(new java.awt.Font("Tahoma", 0, 13));
        label1Form.setText("Equipo local:");

        button2Form.setText("jRadioButton2");
        button2Form.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2FormActionPerformed(evt);
            }
        });

        button1Form.setText("jRadioButton1");

        javax.swing.GroupLayout panel2FormLayout = new javax.swing.GroupLayout(panel2Form);
        panel2Form.setLayout(panel2FormLayout);
        panel2FormLayout.setHorizontalGroup(
            panel2FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2FormLayout.createSequentialGroup()
                .addGroup(panel2FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel2FormLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(panel2FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel2FormLayout.createSequentialGroup()
                                .addGap(110, 110, 110)
                                .addComponent(tituloForm))
                            .addComponent(label1Form)
                            .addGroup(panel2FormLayout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addGroup(panel2FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(button1Form, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(button2Form, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))))
                        .addGap(88, 88, 88))
                    .addGroup(panel2FormLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panel2FormLayout.setVerticalGroup(
            panel2FormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel2FormLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tituloForm)
                .addGap(37, 37, 37)
                .addComponent(label1Form)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button1Form)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button2Form)
                .addGap(7, 7, 7)
                .addComponent(panelForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tituloTablaPartIndiv.setFont(new java.awt.Font("Tahoma", 1, 14));
        tituloTablaPartIndiv.setText("Seleccione el partido individual a definir");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel2Form, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tituloTablaPartIndiv)
                    .addComponent(scrollPaneTablaPartIndiv, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tituloTablaPartIndiv)
                .addGap(10, 10, 10)
                .addComponent(scrollPaneTablaPartIndiv, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel2Form, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TablaPartIndivMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaPartIndivMouseClicked
        if (evt.getClickCount() == 2){
            Integer fila = TablaPartIndiv.getSelectedRow();
            Integer col = TablaPartIndiv.getSelectedColumn();

            if (fila >= 0 && col >= 0){

                button1Form.setText((String)TablaPartIndiv.getValueAt(fila, 2));
                button2Form.setText((String)TablaPartIndiv.getValueAt(fila, 3));

                idCompPartIndiv = (Integer)TablaPartIndiv.getValueAt(fila, 0);
                button1Form.setActionCommand((idsEquiposPartIndiv[fila][0]).toString());
                button2Form.setActionCommand((idsEquiposPartIndiv[fila][1]).toString());
            }

        }
    }//GEN-LAST:event_TablaPartIndivMouseClicked

    private void textoLugar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoLugar1ActionPerformed

}//GEN-LAST:event_textoLugar1ActionPerformed

    private void textoLugar1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoLugar1KeyReleased
        chequearTodosValidos();
}//GEN-LAST:event_textoLugar1KeyReleased

    private void comboAnioPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_comboAnioPopupMenuWillBecomeInvisible
        chequearTodosValidos();
    }//GEN-LAST:event_comboAnioPopupMenuWillBecomeInvisible

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
        chequearTodosValidos();
    }//GEN-LAST:event_comboMesPopupMenuWillBecomeInvisible



    private void comboDiaPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_comboDiaPopupMenuWillBecomeInvisible
        chequearTodosValidos();
}//GEN-LAST:event_comboDiaPopupMenuWillBecomeInvisible

    private void botonAceptar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptar1ActionPerformed


        DataFechaHora f;
//        IControladorCompeticiones icc = (new Fabrica()).getIControladorCompeticiones();

        Integer idLocal = Integer.parseInt(jb.getSelection().getActionCommand());
        Integer idVisit;

        if (button1Form.isSelected())
            idVisit = Integer.parseInt(button2Form.getActionCommand());
        else
            idVisit = Integer.parseInt(button1Form.getActionCommand());

        
        Integer anio = Integer.parseInt((String)comboAnio.getSelectedItem());
        Integer mes = Integer.parseInt((String)comboMes.getSelectedItem());
        Integer dia = Integer.parseInt((String)comboDia.getSelectedItem());
        Integer hora = Integer.parseInt((String)comboHora.getSelectedItem());
        Integer min = Integer.parseInt((String)comboMin.getSelectedItem());


        f = new DataFechaHora(dia,mes,anio,hora,min);
        boolean res=false;
        parent.setModoActivado(true);

        try {
            res = icCompeticiones.ingresarDatosPartidoIndividual(idCompPartIndiv.intValue(),idLocal.intValue(),idVisit.intValue(), f, textoLugar1.getText()); // GONZALO
        }
        catch (ExNoExisteCompeticion exc) {
            this.dispose();
            JOptionPane.showMessageDialog(null,exc.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
            
        }
        catch (ExNoExistePartidoIndividual exc) {
            this.dispose();
            JOptionPane.showMessageDialog(null,exc.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
            
        }
        catch (ExPartidoYaDefinido exc) {
            this.dispose();
            JOptionPane.showMessageDialog(null,exc.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
            
        }
        catch (ExFechaInvalida exc) {
            this.dispose();
            JOptionPane.showMessageDialog(null,exc.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
            
        }
        catch (ExEquipoInvalido exc) {
            this.dispose();
            JOptionPane.showMessageDialog(null,exc.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
            
        }
        catch (Exception exc) {
            this.dispose();
            JOptionPane.showMessageDialog(null,"Error!!"+exc.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
        }

        if (res){
            JOptionPane.showMessageDialog(null, "El partido ha sido creado con éxito.", "Resultado", JOptionPane.INFORMATION_MESSAGE);
        }
        this.dispose();
    }//GEN-LAST:event_botonAceptar1ActionPerformed

    private void botonCancelar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelar3ActionPerformed
        JOptionPane.showMessageDialog(null,"No se creo el Partido Individual","Resultado", JOptionPane.WARNING_MESSAGE);
        parent.setModoActivado(true);
        this.dispose();
}//GEN-LAST:event_botonCancelar3ActionPerformed

    private void comboHoraPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_comboHoraPopupMenuWillBecomeInvisible
        chequearTodosValidos();
}//GEN-LAST:event_comboHoraPopupMenuWillBecomeInvisible

    private void comboMinPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_comboMinPopupMenuWillBecomeInvisible
        chequearTodosValidos();
}//GEN-LAST:event_comboMinPopupMenuWillBecomeInvisible

    private void button2FormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2FormActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_button2FormActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaPartIndiv;
    private javax.swing.JButton botonAceptar1;
    private javax.swing.JButton botonCancelar3;
    private javax.swing.JRadioButton button1Form;
    private javax.swing.JRadioButton button2Form;
    private javax.swing.JComboBox comboAnio;
    private javax.swing.JComboBox comboDia;
    private javax.swing.JComboBox comboHora;
    private javax.swing.JComboBox comboMes;
    private javax.swing.JComboBox comboMin;
    private javax.swing.JLabel label1Form;
    private javax.swing.JLabel label1PartIndiv;
    private javax.swing.JLabel label2PartIndiv;
    private javax.swing.JPanel panel2Form;
    private javax.swing.JPanel panelForm;
    private javax.swing.JScrollPane scrollPaneTablaPartIndiv;
    private javax.swing.JTextField textoLugar1;
    private javax.swing.JLabel tituloForm;
    private javax.swing.JLabel tituloTablaPartIndiv;
    // End of variables declaration//GEN-END:variables
    private Integer idCompPartIndiv;
    private Integer[][] idsEquiposPartIndiv;
    private ButtonGroup jb;
    private Tarea1 parent;

    private IControladorCompeticiones icCompeticiones;
}
