/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DetallesEquipos.java
 *
 * Created on 23/08/2011, 10:15:19 PM
 */

package Formularios;

/**
 *
 * @author santiago
 */
import DataTypes.DataEquipo;
import javax.swing.ImageIcon;
import Interface.Fabrica;
import Interface.IControladorEquipos;
import DataTypes.DataIdNombre;
import java.util.List;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

public class DetallesEquipos extends javax.swing.JInternalFrame {

    /** Creates new form DetallesEquipos */
    public DetallesEquipos(DataEquipo data,Tarea1 parent) {
        initComponents();

        this.parent = parent;
        parent.setModoActivado(false);

        java.awt.Component content = parent.getContentPane();
        int x = (content.getWidth() - this.getWidth()) / 2;
        int y = (content.getHeight() - this.getHeight()) / 2;
        this.setLocation(x, y);

        String pathImage = data.getImagePath();
        int idEquipo = data.getId();
        String nombre = data.getNombre();

        //cargamos la imagen en el label de Detalles
        if (pathImage.equals("")){
            jLabel16.setText("No tiene imagen");
        }else{
            try{
                ImageIcon image = new ImageIcon(pathImage);
                jLabel16.setText("");
                if(image.getIconHeight()>128 || image.getIconWidth()>128){
                    int a = image.getIconWidth()-128;
                    int b = image.getIconHeight() - 128;
                    int razon = a ;
                    if (a < b)
                        razon = b;
                    ImageIcon imageScalada = new ImageIcon(image.getImage().getScaledInstance(image.getIconWidth()-razon, image.getIconHeight()-razon, 128));
                    jLabel16.setIcon(imageScalada);
                }else{
                   jLabel16.setIcon(image);
                }
            }catch (Exception e2){
                jLabel16.setText("Error al cargar");
            }
        }

        jLabel14.setText(nombre);//seteamos el nombre del equipo

        //cargamos la lista de jugadores que disputaron algun partido.
        Fabrica f = new Fabrica();
        IControladorEquipos e = f.getIControladorEquipos();
        Object[][] dataTabla =null;
        String [] columnas = {"Id","Nombre"};
        
        try{
            List<DataIdNombre> lista =e.seleccionarEquipo(idEquipo);
            //ponemos esa lista en la tabla del internalframe Detallesequipos
            dataTabla = new Object[lista.size()][2];
            for (int i = 0;i<lista.size();i++){
                DataIdNombre e3 = (DataIdNombre) lista.get(i);
                dataTabla[i][0] = e3.getId();
                dataTabla[i][1] = e3.getNombre();
            }
            TableModel model = new DefaultTableModel(dataTabla,columnas);
            TablaJugadoresEquipo.setModel(model);
        }catch (Excepciones.ExNoExisteEquipo e3){
            JOptionPane.showMessageDialog(null, "Error: "+e3.getMessage(), "Error", idEquipo);
        }
        catch (Exception e3){
            JOptionPane.showMessageDialog(null, "Error: "+e3.getMessage(), "Error", idEquipo);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaJugadoresEquipo = new javax.swing.JTable()
        {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        jButton9 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setTitle("Detalles de Equipos");

        jLabel15.setText("Jugadores que disputaron algun partido:");

        jLabel16.setText("jLabel16");
        jLabel16.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        TablaJugadoresEquipo.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(TablaJugadoresEquipo);

        jButton9.setText("Aceptar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel14.setText("jLabel14");

        jLabel13.setText("Nombre: ");

        jLabel1.setText("Imagen: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel14))))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                                    .addComponent(jButton9)
                                    .addGap(11, 11, 11)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)))))
                .addGap(38, 38, 38))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                        .addComponent(jButton9)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
       parent.setModoActivado(true);
       this.dispose();
}//GEN-LAST:event_jButton9ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaJugadoresEquipo;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
    private Tarea1 parent;
}
