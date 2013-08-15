/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Jugador.java
 *
 * Created on 23/08/2011, 10:09:54 PM
 */

package Formularios;

/**
 *
 * @author santiago
 */
import DataTypes.DataJugador;
import Interface.*;
import DataTypes.TipoPosicion;
import DataTypes.DataFechaHora;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import Excepciones.ExNoExisteJugador;
import Excepciones.ExNoExisteJugadorEnMemoria;

public class Jugador extends javax.swing.JInternalFrame {

    /** Creates new form Jugador */
    public Jugador(int id,Tarea1 parent) {
        initComponents();

        this.parent = parent;
        parent.setModoActivado(false);

        java.awt.Component content = parent.getContentPane();
        int x = (content.getWidth() - this.getWidth()) / 2;
        int y = (content.getHeight() - this.getHeight()) / 2;
        this.setLocation(x, y);

        idJ = id;
        //inicializamos las variables
        Integer[] anios = new  Integer[41];
        Integer[] dias = new  Integer[31];
        Double[] altura = new Double[101];
        Double[] peso = new Double[81];
        Integer[] meses = new  Integer[12];
        Integer[] edad = new Integer[40];

        for (int i = 0; i <= 40; i++)
            anios[i] = i+1960;
        for (int i = 0; i < 31; i++)
               dias[i] = i+1;
        for (int i = 0; i <= 100; i++){
            altura[i] = (double)((float)(120 + i))/100.0;
        }
        for (int i = 0; i <=80; i++)
               peso[i] = (double)(float)(i+40);
        for (int i = 0; i <12; i++)
               meses[i] = i+1;
        for (int i = 0; i <30; i++)
               edad[i] = i+12;


        FechaAnio.setModel(new javax.swing.DefaultComboBoxModel(anios));
        FechaMes.setModel(new javax.swing.DefaultComboBoxModel(meses));
        FechaDia.setModel(new javax.swing.DefaultComboBoxModel(dias));
        Altura.setModel(new javax.swing.DefaultComboBoxModel(altura));
        Peso.setModel(new javax.swing.DefaultComboBoxModel(peso));
        

        if (id == -1){
            //estamos en el caso de ingresar un nuevo jugador
            this.setTitle("Nuevo Jugador");
        }else{
            //estamos en el caso de modificar un jugador
            this.setTitle("Modificar Jugador");
            Fabrica f = new Fabrica();
            IControladorJugadores icj = f.getIControladorJugadores();
            icJugadores = icj;/// GONZALO#################################################################################################
            try {
                DataJugador d = icj.seleccionarJugador(id);
                //cargamos los datos a la ventana de jugador.
                TipoPosicion posicion = d.getPosicion();
                String pos;
                if (posicion.equals(posicion.Golero))
                    pos = "Golero";
                if (posicion.equals(posicion.Delantero))
                    pos="Delantero";
                if (posicion.equals(posicion.Defensa))
                    pos="Defensa";
                if (posicion.equals(posicion.Volante));
                    pos="Volante";
                tNom.setText(d.getNombre());
                tLugarNacimiento.setText(d.getLugarNacimiento());
                tNombreCompleto.setText(d.getNombreCompleto());
                
                DataFechaHora data2 = d.getFechaDeNacimiento();
                int dia = data2.getDia();
                int mes = data2.getMes();
                int anio = data2.getAnio();
                FechaAnio.setSelectedItem(anio);
                FechaMes.setSelectedItem(mes);
                FechaDia.setSelectedItem(dia);
                double alt = d.getAltura();
                double pes = d.getPeso();
                Altura.setSelectedItem(alt);
                Peso.setSelectedItem(pes);

                //cargar la imagen a mostrar, en caso que se pueda, y si no se puede mostar en el label que no se pudo..
                pathImage = d.getPathImage();

    if (pathImage.equals("")){
            jLabel2.setText("sin imagen");
    }else{
        ImageIcon image;
        try{

            image = new ImageIcon(pathImage);

            jLabel2.setText("");
            if(image.getIconHeight()>128 || image.getIconWidth()>128){
                int a = image.getIconWidth()-128;
                int b = image.getIconHeight() - 128;
                int razon = a ;
                if (a < b)
                    razon = b;
                ImageIcon imageScalada = new ImageIcon(image.getImage().getScaledInstance(image.getIconWidth()-razon, image.getIconHeight()-razon, 128));
                jLabel2.setIcon(imageScalada);
            }else{
               jLabel2.setIcon(image);
            }
        }catch (Exception e2){
            jLabel2.setText("Error al cargar");
        }
    }

            }catch (ExNoExisteJugador e){
                JOptionPane.showMessageDialog(null, "Error: "+ e.getMessage() , "Error", JOptionPane.ERROR_MESSAGE);
            }
            catch (Exception e){
                //algun mensaje..
                JOptionPane.showMessageDialog(null, "Error: "+ e.getMessage() , "Error", JOptionPane.ERROR_MESSAGE);
            }
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        tNom = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tNombreCompleto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tLugarNacimiento = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        FechaAnio = new javax.swing.JComboBox();
        FechaMes = new javax.swing.JComboBox();
        FechaDia = new javax.swing.JComboBox();
        Golero = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        Volante = new javax.swing.JRadioButton();
        Delantero = new javax.swing.JRadioButton();
        Defensa = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Altura = new javax.swing.JComboBox();
        Peso = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        jLabel2.setText(" ");
        jLabel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jLabel2.setMaximumSize(new java.awt.Dimension(128, 128));
        jLabel2.setMinimumSize(new java.awt.Dimension(128, 128));
        jLabel2.setPreferredSize(new java.awt.Dimension(128, 128));

        jButton3.setText("Cargar foto");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setText("Aceptar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton4.setText("Cancelar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Jugador"));

        jLabel3.setText("Nombre");

        jLabel4.setText("Nombre Completo");

        jLabel5.setText("Lugar de Nacimiento");

        tLugarNacimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tLugarNacimientoActionPerformed(evt);
            }
        });

        jLabel6.setText("Fecha");

        FechaAnio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        FechaAnio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FechaAnioMouseClicked(evt);
            }
        });
        FechaAnio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FechaAnioActionPerformed(evt);
            }
        });

        FechaMes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        FechaMes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FechaMesMouseClicked(evt);
            }
        });

        FechaDia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        FechaDia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FechaDiaMouseClicked(evt);
            }
        });

        buttonGroup1.add(Golero);
        Golero.setText("Golero");

        jLabel8.setText("Posicion:");

        buttonGroup1.add(Volante);
        Volante.setText("Volante");

        buttonGroup1.add(Delantero);
        Delantero.setSelected(true);
        Delantero.setText("Delantero");

        buttonGroup1.add(Defensa);
        Defensa.setText("Defensa");

        jLabel9.setText("Altura:");

        jLabel10.setText("Peso:");

        Altura.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        Peso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Año");

        jLabel7.setText("Mes");

        jLabel11.setText("Dia");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tNom, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(3, 3, 3)
                        .addComponent(tNombreCompleto, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Peso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Altura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(21, 21, 21)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Delantero)
                                    .addComponent(Defensa)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(FechaAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tLugarNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(FechaMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGap(60, 60, 60)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Golero)
                                    .addComponent(Volante)
                                    .addComponent(jLabel11)
                                    .addComponent(FechaDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tNombreCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tLugarNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(FechaAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FechaDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FechaMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(Delantero)
                            .addComponent(Volante))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Defensa)
                            .addComponent(Golero))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(Altura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(Peso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel11))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, 0, 0, Short.MAX_VALUE)
                        .addGap(28, 28, 28))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(550, 550, 550)
                .addComponent(jButton5)
                .addGap(6, 6, 6)
                .addComponent(jButton4))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5)
                    .addComponent(jButton4)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public String abrirImagen(JLabel label){
            JFileChooser chooser = new JFileChooser();
            chooser.setMultiSelectionEnabled(false);
            chooser.setFileFilter(new FiltroImagen());
            chooser.setAcceptAllFileFilterUsed(false);
            int e = chooser.showOpenDialog(null);

            String path = "";
            if (e == JFileChooser.APPROVE_OPTION){
                if (chooser.getSelectedFile() != null) {
                    path = chooser.getSelectedFile().getPath();
                    ImageIcon image = new ImageIcon(path);
                    label.setText("");
                    if(image.getIconHeight()>128 || image.getIconWidth()>128){
                        int a = image.getIconWidth()-128;
                        int b = image.getIconHeight() - 128;
                        int razon = a ;
                        if (a < b)
                            razon = b;
                        ImageIcon imageScalada = new ImageIcon(image.getImage().getScaledInstance(image.getIconWidth()-razon, image.getIconHeight()-razon, 128));

                        label.setIcon(imageScalada);
                    }else{
                       label.setIcon(image);
                    }
                }
            }
            return path;
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        pathImage = abrirImagen(jLabel2);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Fabrica f =  new Fabrica();
        IControladorJugadores iCJ = f.getIControladorJugadores();

        //Controlamos que los datos esten bien ingresados para llamar a la operacion de sistema.
        String MensajeError = "Se han producido los siguiente errores: \n";
        boolean error=false;
        String Nombre = tNom.getText();
        String NombreCompleto = tNombreCompleto.getText();
        String Lugar = tLugarNacimiento.getText();

        TipoPosicion posicion=TipoPosicion.Defensa;
        if (Defensa.isSelected()){
            posicion = posicion.Defensa;
        }
        if (Delantero.isSelected()){
            posicion = posicion.Delantero;
        }
        if (Volante.isSelected()){
            posicion = posicion.Volante;
        }
        if (Golero.isSelected()){
            posicion = posicion.Golero;
        }
        if (Nombre.isEmpty()){
            MensajeError = MensajeError.concat("Nombre invalido\n");
            error=true;

        }
        if (NombreCompleto.isEmpty()){
            MensajeError=MensajeError.concat("Nombre completo vacio\n");
            error=true;
        }

        if (Lugar.isEmpty()){
            MensajeError=MensajeError.concat("Lugar Vacio\n");
            error=true;
        }


        if (error){
            JOptionPane.showMessageDialog( null, MensajeError, "Error: Entrada invalida", JOptionPane.ERROR_MESSAGE);
        }else{

            parent.setModoActivado(true);
            
            Fabrica fab = new Fabrica();
            IControladorJugadores icJ = fab.getIControladorJugadores();

            DataFechaHora df = new DataFechaHora((Integer)FechaDia.getSelectedItem(),(Integer)FechaMes.getSelectedItem(),(Integer)FechaAnio.getSelectedItem(),0,0);
            DataJugador data = new DataJugador(idJ,Nombre,NombreCompleto,posicion,df,-1,Lugar,(Double)Altura.getSelectedItem(),(Double)Peso.getSelectedItem(),pathImage);

            if (idJ == -1){
                try{
                    int i = iCJ.ingresarJugador(data);//operacion del sistema, esta queda encargada de asignarle el id correspondiente
                    
                    String salida= Integer.toString(i);
                    this.dispose();
                    JOptionPane.showMessageDialog(null, "Se ha creado el jugador de id = "+ salida,"Nuevo Jugador", JOptionPane.INFORMATION_MESSAGE );
                }catch (Exception e){
                    //ultima excepcion
                }
            }else{
                try{
                    icJugadores.modificarDatos(data); //GONZALO#########################################################
                    this.dispose();
                    JOptionPane.showMessageDialog(null, "Se ha modificado el jugador","Modificar Jugador", JOptionPane.INFORMATION_MESSAGE );

                }catch(ExNoExisteJugadorEnMemoria e){
                    this.dispose();
                    JOptionPane.showMessageDialog(null, "Error: "+e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE );
                }catch (Exception e){
                    this.dispose();
                    JOptionPane.showMessageDialog(null, "Error: "+e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE );
                }
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        parent.setModoActivado(true);
        this.dispose();
}//GEN-LAST:event_jButton4ActionPerformed

    private void tLugarNacimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tLugarNacimientoActionPerformed
        // Tthis.dispose();ODO add your handling code here:
}//GEN-LAST:event_tLugarNacimientoActionPerformed

    private void FechaAnioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FechaAnioMouseClicked

}//GEN-LAST:event_FechaAnioMouseClicked

    private void FechaAnioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FechaAnioActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_FechaAnioActionPerformed

    private void FechaMesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FechaMesMouseClicked

}//GEN-LAST:event_FechaMesMouseClicked

    private void FechaDiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FechaDiaMouseClicked
        // TODO add your handling code here:
}//GEN-LAST:event_FechaDiaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox Altura;
    private javax.swing.JRadioButton Defensa;
    private javax.swing.JRadioButton Delantero;
    private javax.swing.JComboBox FechaAnio;
    private javax.swing.JComboBox FechaDia;
    private javax.swing.JComboBox FechaMes;
    private javax.swing.JRadioButton Golero;
    private javax.swing.JComboBox Peso;
    private javax.swing.JRadioButton Volante;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField tLugarNacimiento;
    private javax.swing.JTextField tNom;
    private javax.swing.JTextField tNombreCompleto;
    // End of variables declaration//GEN-END:variables
    private String pathImage = "";
    private int idJ;
    private Tarea1 parent;
    private IControladorJugadores icJugadores; /// GONZALO
}
