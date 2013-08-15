/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ListaFiltro.java
 *
 * Created on 23/08/2011, 09:13:47 PM
 */
package Formularios;

/**
 *
 * @author santiago
 */
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.RowFilter;
import java.util.List;
import DataTypes.*; 
import Interface.Fabrica;
import Interface.IControladorEquipos;
import Interface.IControladorCompeticiones;
import Interface.IControladorJugadores;
import Excepciones.ExNoExisteCompeticion;
import Excepciones.ExNoExisteJugador;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import Excepciones.ExNoExisteJugadorEnMemoria;
import javax.swing.ListSelectionModel;

public class ListaFiltro extends javax.swing.JInternalFrame {

    

    /** Creates new form ListaFiltro */
    public ListaFiltro(String op,Tarea1 parent) {
        initComponents();

        this.parent = parent;
        parent.setModoActivado(false);

        java.awt.Component content = parent.getContentPane();
        int x = (content.getWidth() - this.getWidth()) / 2;
        int y = (content.getHeight() - this.getHeight()) / 2;
        this.setLocation(x, y);


        //hay que asignar cierto contenido a la tabla...dependiendo de la operacion y del contenido
        //que se le pasa al constructor.
        //modelo indica el modelo que hay q setearle a la tabla
        //segun la operacion seteamos los valores

        TableModel modelo = null; //lo armamos segun la operacion
        if (op.equals("Detalles Equipo")){
            Fabrica f =  new Fabrica();
            String[] columnas = {"Id", "Nombre"};
            Object[][] data=null;

            IControladorEquipos iCE = f.getIControladorEquipos();
            List<DataEquipo> lista = iCE.listarEquipos();
            listaEquipos = lista;
            data= new Object[lista.size()][2];
            for (int i = 0;i<lista.size();i++){
                DataEquipo da = (DataEquipo) lista.get(i);
                data[i][0] = da.getId();
                data[i][1] = da.getNombre();
            }
            modelo = new DefaultTableModel(data,columnas);
            jTable1.setModel(modelo);
            this.setTitle("Lista de Equipos registrados en el Sistema");

        }else if ((op.equals("Detalles Jugador")) || ((op.equals("Modificar Jugador"))) || op.equals("Eliminar Jugador")){
            Fabrica f =  new Fabrica();
            String[] columnas = {"Id", "Nombre"};
            Object[][] data=null;

            IControladorJugadores iCJ = f.getIControladorJugadores();
            List<DataIdNombre> lista = iCJ.listaJugadores();

            data= new Object[lista.size()][2];

            for (int i = 0;i<lista.size();i++){
                DataIdNombre dataIN = (DataIdNombre) lista.get(i);
                data[i][0] = dataIN.getId();
                data[i][1] = dataIN.getNombre();
            }
            modelo = new DefaultTableModel(data,columnas);

            jTable1.setModel(modelo);
            this.setTitle("Lista de Jugadores registrados en el Sistema");

        }else if (op.equals("Detalles Competicion")){
            Fabrica f = new Fabrica();
            IControladorCompeticiones icc = f.getIControladorCompeticiones();
            List<DataIdNombre> listDataIdNom = icc.listarCompetencias();

            int nroDatasIdNom = listDataIdNom.size();
            Object [][] mat = new Object[nroDatasIdNom][2];
            for (int i=0; i<nroDatasIdNom; i++) {
                DataIdNombre dataIN = (DataIdNombre) listDataIdNom.get(i);
                mat[i][0] = dataIN.getId();
                mat[i][1] = dataIN.getNombre();
            }

            String[] arr= {"Id","Nombre"};
            modelo = new DefaultTableModel(mat,arr);
            jTable1.setModel(modelo);

            this.setTitle("Lista de Competiciones registradas en el Sistema");
        }else if (op.equals("Finalizar Partido")){
            Fabrica f = new Fabrica();
            IControladorCompeticiones icc = f.getIControladorCompeticiones();
            List<DataIdNombre> listDataIdNom = icc.listarCompeticionesNoFinalizadas();

            int nroDatasIdNom = listDataIdNom.size();
            
            Object [][] mat = new Object[nroDatasIdNom][2];
            for (int i=0; i<nroDatasIdNom; i++) {
                DataIdNombre dataIN = (DataIdNombre) listDataIdNom.get(i);
                mat[i][0] = dataIN.getId();
                mat[i][1] = dataIN.getNombre();
            }

            String[] arr= {"Id","Nombre"};
            modelo = new DefaultTableModel(mat,arr);
            jTable1.setModel(modelo);

            this.setTitle("Lista de Competiciones con partidos definidos y no finalizados");
        }else if (op.equals("Partidos Finalizables")){
            Fabrica f = new Fabrica();
            IControladorCompeticiones icc = f.getIControladorCompeticiones();
            List<DataIdNombre> listDataIdNom = icc.listarCompeticionesNoFinalizadas();

            int nroDatasIdNom = listDataIdNom.size();
            Object [][] mat = new Object[nroDatasIdNom][2];
            for (int i=0; i<nroDatasIdNom; i++) {
                DataIdNombre dataIN = (DataIdNombre) listDataIdNom.get(i);
                mat[i][0] = dataIN.getId();
                mat[i][1] = dataIN.getNombre();
            }

            String[] arr= {"Id","Nombre"};
            modelo = new DefaultTableModel(mat,arr);
            jTable1.setModel(modelo);

            this.setTitle("Lista de partidos definidos y no finalizados");
        }


        operacion = op;

        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelo);
        jTable1.setRowSorter(sorter);
        filtro.addKeyListener(new KeyListener() {

        @Override
        public void keyTyped(KeyEvent arg0) {
            String i = "(?i)";
            String texto= i.concat(filtro.getText());

            if (texto.length() == 4) {
                sorter.setRowFilter(null);
            } else {
                sorter.setRowFilter(RowFilter.regexFilter(texto));
            }
        }
        @Override
        public void keyReleased(KeyEvent arg0) {
            String i = "(?i)";
            String texto= i.concat(filtro.getText());
             if (texto.length() == 4) {
                sorter.setRowFilter(null);
            } else {
                sorter.setRowFilter(RowFilter.regexFilter(texto,1));
            }
        }
        @Override
        public void keyPressed(KeyEvent arg0) {
            String i = "(?i)";
            String texto= i.concat(filtro.getText());

            if (texto.length() == 4) {
                sorter.setRowFilter(null);
            } else {
                sorter.setRowFilter(RowFilter.regexFilter(texto,1));
            }

                }
                });

                //inicializamos las variables globales
                jButton1.setEnabled(false);
                idSeleccionadoTabla = -1;
                nombreSeleccionTabla = "";
                fila = -1;
                columna = -1;
    }

    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable()
        {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        filtro = new javax.swing.JTextField();

        setTitle("Seleccione un elemento de la tabla");

        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

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
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        filtro.setColumns(4);
        filtro.setFont(new java.awt.Font("DejaVu Sans", 2, 12)); // NOI18N
        filtro.setToolTipText("filtrar el contenido de la tabla");
        filtro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(255, 255, 255)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(367, Short.MAX_VALUE)
                        .addComponent(filtro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(filtro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        parent.setModoActivado(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (operacion.equals("Detalles Jugador")){
           this.setTitle("Cargando datos...");
           Fabrica f = new Fabrica();
           IControladorJugadores icj = f.getIControladorJugadores();

            try {
                DataJugador a=icj.seleccionarJugador(idSeleccionadoTabla);
                DetallesJugador j = new DetallesJugador(a,parent);
                parent.getLayeredPane().add(j);
                j.setVisible(true);
            }catch (ExNoExisteJugador e1){
                JOptionPane.showMessageDialog(null, "Error: "+e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                parent.setModoActivado(true);
            }
            catch (Exception e1){
                JOptionPane.showMessageDialog(null, "Error: "+e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                parent.setModoActivado(true);
            }
            this.dispose();

        }else if (operacion.equals("Modificar Jugador")) {
            Jugador j = new Jugador(idSeleccionadoTabla,parent);
            j.setVisible(true);
            parent.getLayeredPane().add(j);
            this.dispose();

        }else if (operacion.equals("Eliminar Jugador")){
            int idJugador = idSeleccionadoTabla;
            //
            Fabrica f = new Fabrica();
            IControladorJugadores icj = f.getIControladorJugadores();

            parent.setModoActivado(true);
            boolean ok = false;
            try{
                ok = icj.eliminarJugador(idJugador);
            }catch (ExNoExisteJugador e1){
                JOptionPane.showMessageDialog(null, "Error: "+e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            catch (Exception e1){
                   JOptionPane.showMessageDialog(null, "Error: "+e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

            if (!ok){
                    JOptionPane.showMessageDialog( null, "No se puede eliminar al jugar, esta asosiado a algun equipo", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{
                //preguntamos si quiere confirmar y despues
                try{
                    int a = JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar el jugador", "Confirmar eliminacion", JOptionPane.YES_NO_OPTION);

                    if (a == JOptionPane.YES_OPTION){
                        icj.confirmarEliminacion();
                        JOptionPane.showMessageDialog( null, "Se ha eliminado el jugador", "Jugador Eliminado", JOptionPane.INFORMATION_MESSAGE);
                    }
                 }catch (ExNoExisteJugadorEnMemoria e1){
                       JOptionPane.showMessageDialog(null, "Error: "+e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }catch (Exception e1){
                       JOptionPane.showMessageDialog(null, "Error: "+e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            this.dispose();

        }else if (operacion.equals("Detalles Equipo")){
            int idEquipo = idSeleccionadoTabla;
            DataEquipo dataE = null;
            for (DataEquipo d : listaEquipos){
                //buscamos el dataEquipo que tenga el mismo id que idEquipo y le obtenemos el path
                if (d.getId()==idEquipo){
                    dataE = d;
                }
            }
            JInternalFrame j = new DetallesEquipos(dataE,this.parent);
            this.parent.getLayeredPane().add(j);
            j.setVisible(true);
            this.dispose();
        } else if (this.operacion.equals("Detalles Competicion")){
            int idComp = this.idSeleccionadoTabla;
            Fabrica f = new Fabrica();
            IControladorCompeticiones icc = f.getIControladorCompeticiones();
            DataCompeticion dataComp;
            try {
                dataComp = icc.verInfoCompeticion(idComp);
                JInternalFrame j = new verDetallesCompeticion(dataComp, this.parent, icc);
                this.parent.getLayeredPane().add(j);
                j.setVisible(true);
            }
            catch  (ExNoExisteCompeticion exc){
                JOptionPane.showMessageDialog(null,exc.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
                parent.setModoActivado(true);
            }
            catch (Exception exc)  {
                icc.finalizarVerDetallesComp();
                parent.setModoActivado(true);
            }
            this.dispose();

        }else if (this.operacion.equals("Finalizar Partido")){
            JInternalFrame j = new PartidosFinalizables(idSeleccionadoTabla,this.parent);
            this.parent.getLayeredPane().add(j);
            j.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        fila = jTable1.rowAtPoint(evt.getPoint());
        columna = jTable1.columnAtPoint(evt.getPoint());

        if ((fila > -1) && (columna > -1)){
            idSeleccionadoTabla = (Integer) jTable1.getValueAt(fila,0);
            nombreSeleccionTabla = (String) jTable1.getValueAt(fila,1);
            jButton1.setEnabled(true);
        }

    }//GEN-LAST:event_jTable1MouseClicked

    private void filtroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filtroActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField filtro;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
    private String operacion;
    private int fila,columna,idSeleccionadoTabla;
    private String nombreSeleccionTabla;
    private List<DataEquipo> listaEquipos;
    private Tarea1 parent;

}
